USE record_store
GO

INSERT INTO country (name)
VALUES
('USA'),
('United Kingdom'),
('Colombia'),
('Korea')
GO

INSERT INTO [users] (
    first_name,
    last_name,
    email,
    password,
    phone,
    address,
    created_at,
    status
)
VALUES
(
    'Nicole',
    'Ramirez',
    'nicole@gmail.com',
    HASHBYTES('SHA2_256', '123456'),
    '3001234567',
    'Calle 10',
    GETDATE(),
    1
),
(
    'Sharik',
    'Rojas',
    'sharik@gmail.com',
    HASHBYTES('SHA2_256', 'abcdef'),
    '3019876543',
    'Carrera 15',
    GETDATE(),
    1
),
(
    'Justin',
    'Bahamon',
    'justin@gmail.com',
    HASHBYTES('SHA2_256', 'fedcba'),
    '3028765432',
    'Calle 20',
    GETDATE(),
    1
)
GO

INSERT INTO supplier (
    company_name,
    phone,
    email,
    address
)
VALUES
(
    'Universal Music',
    '1111111',
    'contact@universal.com',
    'New York'
),
(
    'Sony Music',
    '2222222',
    'info@sony.com',
    'Los Angeles'
)
GO

INSERT INTO branch (
    name,
    address,
    city,
    phone,
    status
)
VALUES
(
    'Sucursal Centro',
    'Calle 1',
    'Neiva',
    '5551111',
    1
),
(
    'Sucursal Norte',
    'Carrera 20',
    'Bogota',
    '5552222',
    1
)
GO

INSERT INTO artist (
    name,
    country_id,
    start_date,
    description,
    status
)
VALUES
(
    'Taylor Swift',
    1,
    '2007-01-01',
    'Pop Artist and the music industries biggest star',
    'ACTIVE'
),
(
    'Blackpink',
    4,
    '2016-01-01',
    'K-pop girl group formed by YG Entertainment',
    'ACTIVE'
),
(
    'Bts',
    4,
    '2013-01-01',
    'K-pop boy group formed by Big Hit Entertainment',
    'ACTIVE'
)
GO

INSERT INTO genre (
    name,
    description
)
VALUES
('Rock','Rock music genre'),
('Pop','Popular music genre'),
('K-pop','Korean pop music genre')
GO

INSERT INTO album (
    title,
    release_date,
    price,
    stock,
    format,
    status,
    supplier_id
)
VALUES
(
    'Born Pink',
    '2022-08-12',
    120000,
    10,
    'CD',
    'AVAILABLE',
    1
),
(
    'The life of a showgirl',
    '2025-11-13',
    90000,
    15,
    'VINYL',
    'AVAILABLE',
    2
),
(
    'Arirang',
    '2020-02-21',
    150000,
    20,
    'CD',
    'AVAILABLE',
    1
)
GO

INSERT INTO artist_album (
    artist_id,
    album_id
)
VALUES
(1,2),
(2,1),
(3,3)
GO

INSERT INTO album_genre (
    album_id,
    genre_id
)
VALUES
(1,3),
(2,2),
(3,3)
GO

INSERT INTO sale (
    sale_date,
    total,
    user_id,
    branch_id,
    status
)
VALUES
(
    GETDATE(),
    120000,
    1,
    1,
    'PAID'
)
GO

INSERT INTO sale_detail (
    quantity,
    subtotal,
    sale_id,
    album_id
)
VALUES
(
    1,
    120000,
    1,
    1
)
GO