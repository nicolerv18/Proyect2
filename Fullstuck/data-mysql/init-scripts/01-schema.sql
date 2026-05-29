CREATE DATABASE record_store;
GO

USE record_store;
GO

CREATE TABLE [users] (
    user_id INT PRIMARY KEY IDENTITY(1,1),
    first_name VARCHAR(20) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    email VARCHAR(30) NOT NULL UNIQUE,
    password VARBINARY(64) NOT NULL,
    phone VARCHAR(20),
    address VARCHAR(35),
    created_at DATETIME DEFAULT GETDATE(),
    status BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE country (
    country_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50) NOT NULL
);
GO

CREATE TABLE artist (
    artist_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(60) NOT NULL,
    country_id INT,
    start_date DATE,
    description VARCHAR(255),
    status VARCHAR(20),

    CONSTRAINT FK_artist_country
    FOREIGN KEY (country_id)
    REFERENCES country(country_id)
);
GO

CREATE TABLE supplier (
    supplier_id INT PRIMARY KEY IDENTITY(1,1),
    company_name VARCHAR(50) NOT NULL,
    phone VARCHAR(20),
    email VARCHAR(30),
    address VARCHAR(35)
);
GO

CREATE TABLE branch (
    branch_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(30) NOT NULL,
    address VARCHAR(35),
    city VARCHAR(30),
    phone VARCHAR(20),
    status BIT NOT NULL DEFAULT 1
);
GO

CREATE TABLE album (
    album_id INT PRIMARY KEY IDENTITY(1,1),
    title VARCHAR(100) NOT NULL,
    release_date DATE,
    price DECIMAL(10,2) NOT NULL,
    stock INT NOT NULL,
    format VARCHAR(20),
    status VARCHAR(20),
    supplier_id INT,

    CONSTRAINT FK_album_supplier
    FOREIGN KEY (supplier_id)
    REFERENCES supplier(supplier_id)
);
GO

CREATE TABLE genre (
    genre_id INT PRIMARY KEY IDENTITY(1,1),
    name VARCHAR(50) NOT NULL,
    description VARCHAR(255)
);
GO

CREATE TABLE artist_album (
    artist_id INT NOT NULL,
    album_id INT NOT NULL,

    PRIMARY KEY (artist_id, album_id),

    CONSTRAINT FK_artist_album_artist
    FOREIGN KEY (artist_id)
    REFERENCES artist(artist_id),

    CONSTRAINT FK_artist_album_album
    FOREIGN KEY (album_id)
    REFERENCES album(album_id)
);
GO

CREATE TABLE album_genre (
    album_id INT NOT NULL,
    genre_id INT NOT NULL,

    PRIMARY KEY (album_id, genre_id),

    CONSTRAINT FK_album_genre_album
    FOREIGN KEY (album_id)
    REFERENCES album(album_id),

    CONSTRAINT FK_album_genre_genre
    FOREIGN KEY (genre_id)
    REFERENCES genre(genre_id)
);
GO

CREATE TABLE sale (
    sale_id INT PRIMARY KEY IDENTITY(1,1),
    sale_date DATETIME NOT NULL,
    total DECIMAL(10,2) NOT NULL,
    user_id INT NOT NULL,
    branch_id INT NOT NULL,
    status VARCHAR(20),

    CONSTRAINT FK_sale_user
    FOREIGN KEY (user_id)
    REFERENCES [users](user_id),

    CONSTRAINT FK_sale_branch
    FOREIGN KEY (branch_id)
    REFERENCES branch(branch_id)
);
GO

CREATE TABLE sale_detail (
    detail_id INT PRIMARY KEY IDENTITY(1,1),
    quantity INT NOT NULL,
    subtotal DECIMAL(10,2) NOT NULL,
    sale_id INT NOT NULL,
    album_id INT NOT NULL,

    CONSTRAINT FK_sale_detail_sale
    FOREIGN KEY (sale_id)
    REFERENCES sale(sale_id),

    CONSTRAINT FK_sale_detail_album
    FOREIGN KEY (album_id)
    REFERENCES album(album_id)
);
GO