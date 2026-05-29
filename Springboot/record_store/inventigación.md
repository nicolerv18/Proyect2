# Guía Completa: DTO, Mappers, Validaciones, JSON View y Controladores Abstractos

> Referencia técnica para arquitecturas REST con Spring Boot / Java

---

## Tabla de Contenidos

1. [DTO (Data Transfer Object)](#1-dto-data-transfer-object)
2. [Mappers](#2-mappers)
3. [Validaciones](#3-validaciones)
4. [JSON View](#4-json-view)
5. [Controladores Abstractos](#5-controladores-abstractos)
6. [Integración de todos los conceptos](#6-integración-de-todos-los-conceptos)

---

## 1. DTO (Data Transfer Object)

### ¿Qué es un DTO?

Un **DTO (Data Transfer Object)** es un objeto simple cuya única responsabilidad es **transportar datos entre capas** de la aplicación (por ejemplo, entre el controlador y el servicio, o entre el backend y el cliente HTTP). No contiene lógica de negocio.

### ¿Por qué usar DTOs?

| Sin DTO | Con DTO |
|---|---|
| Se expone directamente la entidad JPA | Se expone solo lo que el cliente necesita |
| Riesgo de exponer datos sensibles (passwords, etc.) | Control total sobre los campos expuestos |
| El cliente depende de la estructura interna de la BD | El contrato de la API es independiente del modelo |
| Cambios en la entidad rompen la API | Cambios internos no afectan el contrato externo |

### Ejemplo básico

**Entidad JPA (no se expone directamente):**
```java
@Entity
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String email;
    private String password;       // ¡No debe salir al cliente!
    private LocalDateTime creadoEn;
}
```

**DTO de respuesta (lo que el cliente recibe):**
```java
public class UsuarioResponseDTO {
    private Long id;
    private String nombre;
    private String email;

    // Constructors, getters, setters
}
```

**DTO de solicitud (lo que el cliente envía):**
```java
public class UsuarioCreateDTO {
    private String nombre;
    private String email;
    private String password;
}
```

### Tipos comunes de DTOs

```
UsuarioCreateDTO     → Para crear un recurso (POST)
UsuarioUpdateDTO     → Para actualizar un recurso (PUT/PATCH)
UsuarioResponseDTO   → Para enviar datos al cliente (GET)
UsuarioPatchDTO      → Actualización parcial (campos opcionales)
UsuarioListItemDTO   → Vista resumida para listados
```

### DTOs con Records de Java 16+

```java
// Más conciso y limpio con Java Records
public record UsuarioResponseDTO(Long id, String nombre, String email) {}

public record UsuarioCreateDTO(String nombre, String email, String password) {}
```

### DTOs anidados

```java
public class PedidoResponseDTO {
    private Long id;
    private UsuarioResponseDTO usuario;    // DTO anidado
    private List<ProductoItemDTO> items;
    private BigDecimal total;
}
```

---

## 2. Mappers

### ¿Qué es un Mapper?

Un **Mapper** es el componente responsable de **convertir** objetos de un tipo a otro: de entidad a DTO y viceversa. Centraliza esa lógica para evitar duplicación.

### Tipos de Mappers

#### A) Mapper Manual (sin librería)

```java
@Component
public class UsuarioMapper {

    // Entidad → DTO de respuesta
    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNombre(usuario.getNombre());
        dto.setEmail(usuario.getEmail());
        return dto;
    }

    // DTO de creación → Entidad
    public Usuario toEntity(UsuarioCreateDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setPassword(dto.getPassword()); // se encripta en el servicio
        return usuario;
    }

    // Lista de entidades → Lista de DTOs
    public List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios) {
        return usuarios.stream()
                       .map(this::toResponseDTO)
                       .collect(Collectors.toList());
    }
}
```

#### B) Mapper con ModelMapper (librería)

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.modelmapper</groupId>
    <artifactId>modelmapper</artifactId>
    <version>3.1.1</version>
</dependency>
```

```java
@Configuration
public class ModelMapperConfig {
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        // Configuración estricta para evitar mapeos accidentales
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper;
    }
}

@Service
public class UsuarioService {

    @Autowired
    private ModelMapper modelMapper;

    public UsuarioResponseDTO convertToDTO(Usuario usuario) {
        return modelMapper.map(usuario, UsuarioResponseDTO.class);
    }

    public Usuario convertToEntity(UsuarioCreateDTO dto) {
        return modelMapper.map(dto, Usuario.class);
    }
}
```

#### C) Mapper con MapStruct (recomendado para proyectos grandes)

```xml
<!-- pom.xml -->
<dependency>
    <groupId>org.mapstruct</groupId>
    <artifactId>mapstruct</artifactId>
    <version>1.5.5.Final</version>
</dependency>
```

```java
@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    // Mapeo automático por nombre de campo
    UsuarioResponseDTO toResponseDTO(Usuario usuario);

    // Ignorar campo o renombrar
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "nombre", target = "nombreCompleto")
    Usuario toEntity(UsuarioCreateDTO dto);

    // Lista automática
    List<UsuarioResponseDTO> toResponseDTOList(List<Usuario> usuarios);
}
```

MapStruct **genera el código en tiempo de compilación**, lo que lo hace muy eficiente.

### ¿Dónde ubicar los Mappers?

```
src/
└── main/java/com/app/
    ├── controller/
    ├── service/
    ├── repository/
    ├── entity/
    ├── dto/
    │   ├── request/
    │   │   ├── UsuarioCreateDTO.java
    │   │   └── UsuarioUpdateDTO.java
    │   └── response/
    │       └── UsuarioResponseDTO.java
    └── mapper/
        └── UsuarioMapper.java      ← aquí van los mappers
```

---

## 3. Validaciones

### Validación con Bean Validation (JSR-380)

Spring Boot incluye `spring-boot-starter-validation` que implementa **Jakarta Bean Validation**. Las anotaciones se colocan en los DTOs (no en las entidades).

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-validation</artifactId>
</dependency>
```

### Anotaciones de validación más comunes

```java
public class UsuarioCreateDTO {

    @NotNull(message = "El nombre es obligatorio")
    @Size(min = 2, max = 100, message = "El nombre debe tener entre 2 y 100 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "El formato del email no es válido")
    private String email;

    @NotNull
    @Size(min = 8, message = "La contraseña debe tener al menos 8 caracteres")
    @Pattern(regexp = "^(?=.*[A-Z])(?=.*\\d).+$",
             message = "La contraseña debe tener al menos una mayúscula y un número")
    private String password;

    @Min(value = 0, message = "La edad no puede ser negativa")
    @Max(value = 120, message = "La edad no puede ser mayor a 120")
    private Integer edad;

    @NotEmpty(message = "Los roles no pueden estar vacíos")
    private List<String> roles;

    @Past(message = "La fecha de nacimiento debe estar en el pasado")
    private LocalDate fechaNacimiento;

    @Future(message = "La fecha de vencimiento debe ser futura")
    private LocalDate fechaVencimiento;

    @DecimalMin(value = "0.0", inclusive = false)
    @Digits(integer = 6, fraction = 2)
    private BigDecimal salario;
}
```

### Activar validación en el controlador

```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(
            @Valid @RequestBody UsuarioCreateDTO dto) {
        // Si la validación falla, Spring lanza MethodArgumentNotValidException
        // automáticamente antes de entrar aquí
        return ResponseEntity.ok(service.crear(dto));
    }
}
```

> `@Valid` → activa las validaciones del DTO  
> `@Validated` → versión de Spring, permite grupos de validación

### Manejo global de errores de validación

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationErrors(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errores.put(error.getField(), error.getDefaultMessage());
        });

        Map<String, Object> respuesta = new HashMap<>();
        respuesta.put("status", HttpStatus.BAD_REQUEST.value());
        respuesta.put("errores", errores);
        respuesta.put("timestamp", LocalDateTime.now());

        return ResponseEntity.badRequest().body(respuesta);
    }
}
```

**Respuesta JSON resultante:**
```json
{
  "status": 400,
  "timestamp": "2024-01-15T10:30:00",
  "errores": {
    "email": "El formato del email no es válido",
    "password": "La contraseña debe tener al menos 8 caracteres"
  }
}
```

### Validaciones personalizadas (Custom Constraints)

```java
// 1. Crear la anotación
@Documented
@Constraint(validatedBy = EmailUnicoValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailUnico {
    String message() default "El email ya está registrado";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

// 2. Implementar el validador
@Component
public class EmailUnicoValidator implements ConstraintValidator<EmailUnico, String> {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        if (email == null) return true;
        return !usuarioRepository.existsByEmail(email);
    }
}

// 3. Usar en el DTO
public class UsuarioCreateDTO {
    @EmailUnico
    @Email
    private String email;
}
```

### Grupos de validación

```java
// Definir grupos
public interface OnCreate {}
public interface OnUpdate {}

// Aplicar según el grupo
public class UsuarioDTO {
    @Null(groups = OnCreate.class)        // null al crear
    @NotNull(groups = OnUpdate.class)     // obligatorio al actualizar
    private Long id;

    @NotBlank(groups = {OnCreate.class, OnUpdate.class})
    private String nombre;
}

// Activar grupo específico en el controlador
@PostMapping
public ResponseEntity<?> crear(@Validated(OnCreate.class) @RequestBody UsuarioDTO dto) { ... }

@PutMapping("/{id}")
public ResponseEntity<?> actualizar(@Validated(OnUpdate.class) @RequestBody UsuarioDTO dto) { ... }
```

---

## 4. JSON View

### ¿Qué es `@JsonView`?

`@JsonView` es una anotación de **Jackson** que permite definir **vistas** sobre un mismo objeto, controlando qué campos se serializan/deserializan según el contexto. Útil cuando un mismo DTO se usa en múltiples endpoints con diferente granularidad.

### Definir las vistas

```java
// Interfaces que definen las vistas (se pueden anidar)
public class Vistas {
    public interface Publica {}                         // Vista básica/pública
    public interface Interna extends Publica {}         // Extiende la pública
    public interface Admin extends Interna {}           // Extiende la interna
}
```

### Aplicar las vistas a los campos

```java
public class UsuarioDTO {

    @JsonView(Vistas.Publica.class)
    private Long id;

    @JsonView(Vistas.Publica.class)
    private String nombre;

    @JsonView(Vistas.Interna.class)   // solo para uso interno
    private String email;

    @JsonView(Vistas.Admin.class)     // solo para admins
    private LocalDateTime creadoEn;

    // NO tiene @JsonView → NUNCA se serializa con vistas activas
    private String password;
}
```

### Usar las vistas en el controlador

```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    // Endpoint público: solo id y nombre
    @GetMapping("/publico/{id}")
    @JsonView(Vistas.Publica.class)
    public UsuarioDTO getPublico(@PathVariable Long id) {
        return service.findById(id);
    }

    // Endpoint interno: id, nombre, email
    @GetMapping("/interno/{id}")
    @JsonView(Vistas.Interna.class)
    public UsuarioDTO getInterno(@PathVariable Long id) {
        return service.findById(id);
    }

    // Endpoint admin: todos los campos anotados con vistas
    @GetMapping("/admin/{id}")
    @JsonView(Vistas.Admin.class)
    public UsuarioDTO getAdmin(@PathVariable Long id) {
        return service.findById(id);
    }
}
```

**Respuestas según la vista:**

Vista `Publica`:
```json
{ "id": 1, "nombre": "Carlos" }
```

Vista `Interna`:
```json
{ "id": 1, "nombre": "Carlos", "email": "carlos@mail.com" }
```

Vista `Admin`:
```json
{ "id": 1, "nombre": "Carlos", "email": "carlos@mail.com", "creadoEn": "2024-01-01T00:00:00" }
```

### Configuración global en Spring Boot

```java
@Configuration
public class JacksonConfig {
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
        return builder -> builder
            .defaultViewInclusion(false); // Solo serializa campos con @JsonView activo
    }
}
```

### @JsonView en la deserialización (request body)

```java
@PostMapping
public ResponseEntity<?> crear(
        @JsonView(Vistas.Interna.class)
        @RequestBody UsuarioDTO dto) {
    // Solo los campos con @JsonView(Interna) serán leídos del JSON
    return ResponseEntity.ok(service.crear(dto));
}
```

---

## 5. Controladores Abstractos

### ¿Qué es un controlador abstracto?

Un **controlador abstracto** (o controlador genérico) encapsula las operaciones CRUD comunes en una clase base reutilizable. Los controladores concretos solo deben implementar lo específico de cada recurso.

### Problema que resuelve

Sin controlador abstracto, cada controlador repite el mismo patrón:

```java
// UsuarioController - código repetido
@GetMapping("/{id}") public ResponseEntity<UsuarioDTO> findById(...) { ... }
@GetMapping         public ResponseEntity<List<UsuarioDTO>> findAll()  { ... }
@PostMapping        public ResponseEntity<UsuarioDTO> create(...)       { ... }
@PutMapping("/{id}") public ResponseEntity<UsuarioDTO> update(...)     { ... }
@DeleteMapping("/{id}") public ResponseEntity<Void> delete(...)        { ... }

// ProductoController - MISMO código repetido
@GetMapping("/{id}") public ResponseEntity<ProductoDTO> findById(...) { ... }
// ... etc
```

### Implementación del controlador abstracto genérico

#### Interfaz base del servicio

```java
public interface CrudService<T, ID, CreateDTO, UpdateDTO> {
    T findById(ID id);
    List<T> findAll();
    T create(CreateDTO dto);
    T update(ID id, UpdateDTO dto);
    void delete(ID id);
}
```

#### Controlador abstracto base

```java
@Validated
public abstract class AbstractCrudController<
        ResponseDTO,
        CreateDTO,
        UpdateDTO,
        ID> {

    // Las subclases proveen el servicio específico
    protected abstract CrudService<ResponseDTO, ID, CreateDTO, UpdateDTO> getService();

    @GetMapping("/{id}")
    public ResponseEntity<ResponseDTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @GetMapping
    public ResponseEntity<List<ResponseDTO>> findAll() {
        return ResponseEntity.ok(getService().findAll());
    }

    @PostMapping
    public ResponseEntity<ResponseDTO> create(
            @Valid @RequestBody CreateDTO dto) {
        ResponseDTO created = getService().create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResponseDTO> update(
            @PathVariable ID id,
            @Valid @RequestBody UpdateDTO dto) {
        return ResponseEntity.ok(getService().update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable ID id) {
        getService().delete(id);
        return ResponseEntity.noContent().build();
    }
}
```

#### Controlador concreto (hereda todo lo anterior)

```java
@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController extends AbstractCrudController<
        UsuarioResponseDTO,
        UsuarioCreateDTO,
        UsuarioUpdateDTO,
        Long> {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    protected CrudService<UsuarioResponseDTO, Long, UsuarioCreateDTO, UsuarioUpdateDTO> getService() {
        return usuarioService;
    }

    // Solo sobreescribe lo que es específico de usuarios
    @GetMapping("/buscar")
    public ResponseEntity<List<UsuarioResponseDTO>> buscarPorNombre(@RequestParam String nombre) {
        return ResponseEntity.ok(usuarioService.buscarPorNombre(nombre));
    }
}
```

### Controlador abstracto con paginación

```java
public abstract class AbstractPageableController<ResponseDTO, CreateDTO, UpdateDTO, ID>
        extends AbstractCrudController<ResponseDTO, CreateDTO, UpdateDTO, ID> {

    protected abstract PageableService<ResponseDTO, ID, CreateDTO, UpdateDTO> getPageableService();

    @Override
    protected CrudService<ResponseDTO, ID, CreateDTO, UpdateDTO> getService() {
        return getPageableService();
    }

    @GetMapping("/paginated")
    public ResponseEntity<Page<ResponseDTO>> findAllPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return ResponseEntity.ok(getPageableService().findAllPaginated(pageable));
    }
}
```

### Controlador abstracto con @JsonView integrado

```java
public abstract class AbstractCrudController<ResponseDTO, CreateDTO, UpdateDTO, ID> {

    protected abstract CrudService<ResponseDTO, ID, CreateDTO, UpdateDTO> getService();

    @GetMapping("/{id}")
    @JsonView(Vistas.Publica.class)
    public ResponseEntity<ResponseDTO> findById(@PathVariable ID id) {
        return ResponseEntity.ok(getService().findById(id));
    }

    @PostMapping
    @JsonView(Vistas.Interna.class)
    public ResponseEntity<ResponseDTO> create(@Valid @RequestBody CreateDTO dto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(getService().create(dto));
    }
}
```

### Sobrescribir un método del controlador abstracto

```java
@RestController
@RequestMapping("/api/productos")
public class ProductoController extends AbstractCrudController<
        ProductoResponseDTO, ProductoCreateDTO, ProductoUpdateDTO, Long> {

    @Autowired
    private ProductoService productoService;

    @Override
    protected CrudService<...> getService() { return productoService; }

    // Sobreescribir el delete con lógica de negocio extra
    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        productoService.archivarEnLugarDeEliminar(id); // lógica especial
        return ResponseEntity.noContent().build();
    }
}
```

---

## 6. Integración de todos los conceptos

### Flujo completo de una petición REST

```
Cliente HTTP
    │
    ▼
[Controlador]
    │  Recibe CreateDTO con @Valid
    │  Usa @JsonView para la respuesta
    │
    ▼
[Validaciones]
    │  Bean Validation verifica el DTO
    │  GlobalExceptionHandler captura errores
    │
    ▼
[Mapper]  ← DTO → Entidad
    │
    ▼
[Servicio / Repositorio]
    │
    ▼
[Mapper]  ← Entidad → ResponseDTO
    │
    ▼
[Controlador]
    │  Retorna ResponseDTO serializado con @JsonView
    ▼
Cliente HTTP (JSON)
```

### Ejemplo completo integrado

```java
// === DTO ===
public class ProductoCreateDTO {
    @NotBlank
    @Size(max = 200)
    private String nombre;

    @NotNull
    @DecimalMin("0.01")
    private BigDecimal precio;

    @JsonView(Vistas.Admin.class)
    @NotNull
    private Long proveedorId;
}

// === MAPPER ===
@Mapper(componentModel = "spring")
public interface ProductoMapper {
    @Mapping(source = "proveedorId", target = "proveedor.id")
    Producto toEntity(ProductoCreateDTO dto);

    @Mapping(source = "proveedor.nombre", target = "proveedorNombre")
    ProductoResponseDTO toResponseDTO(Producto producto);
}

// === SERVICIO ===
@Service
public class ProductoService implements CrudService<ProductoResponseDTO, Long, ProductoCreateDTO, ProductoUpdateDTO> {

    @Autowired private ProductoRepository repo;
    @Autowired private ProductoMapper mapper;

    @Override
    public ProductoResponseDTO create(ProductoCreateDTO dto) {
        Producto producto = mapper.toEntity(dto);
        Producto guardado = repo.save(producto);
        return mapper.toResponseDTO(guardado);
    }
    // ...
}

// === CONTROLADOR ===
@RestController
@RequestMapping("/api/productos")
public class ProductoController extends AbstractCrudController<
        ProductoResponseDTO, ProductoCreateDTO, ProductoUpdateDTO, Long> {

    @Autowired private ProductoService productoService;

    @Override
    protected CrudService<...> getService() { return productoService; }
}

// === MANEJO DE ERRORES ===
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidation(MethodArgumentNotValidException ex) {
        // ... construir respuesta de error
    }
}
```

---

## Resumen de buenas prácticas

| Concepto | Buena práctica |
|---|---|
| **DTO** | Un DTO por operación (Create, Update, Response). Nunca exponer la entidad directamente |
| **Mapper** | Centralizar en una clase/interfaz. Preferir MapStruct para proyectos grandes |
| **Validaciones** | Validar en el DTO, no en la entidad. Usar `@RestControllerAdvice` para centralizar errores |
| **JSON View** | Usar herencia de vistas (`Interna extends Publica`) para no duplicar anotaciones |
| **Controlador Abstracto** | Definir operaciones CRUD en la clase base. Solo sobreescribir lo específico |

---

*Documento generado como referencia técnica para arquitecturas REST con Spring Boot*