DROP TABLE IF EXISTS users;

CREATE TABLE users(
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS cinemas;

CREATE TABLE cinemas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    location VARCHAR(255) NOT NULL,
    img VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS movies;

CREATE TABLE movies(
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    genre VARCHAR(255) NOT NULL,
    duration INT NOT NULL,
    synopsis TEXT NOT NULL,
    img VARCHAR(255) NOT NULL
);

DROP TABLE IF EXISTS screenings;

CREATE TABLE screenings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    cinema_id INT NOT NULL,
    movie_id INT NOT NULL,
    start_time DATETIME NOT NULL,
    screening_type_id INT NOT NULL,
    FOREIGN KEY (movie_id) REFERENCES movies(id) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (cinema_id) REFERENCES cinemas(id) ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS seats;

CREATE TABLE seats (
    id INT AUTO_INCREMENT PRIMARY KEY,
    screening_id INT NOT NULL,
    number CHAR(5) NOT NULL,
    status VARCHAR(255) NOT NULL,
    row_number INT NOT NULL,
    col_number INT NOT NULL,
    FOREIGN KEY (screening_id) REFERENCES screenings(id)
);

DROP TABLE IF EXISTS bookings;

CREATE TABLE bookings(
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    screening_id INT NOT NULL,
    booking_time DATETIME NOT NULL,
    total_price DOUBLE NOT NULL,
    payment_method VARCHAR(255) NOT NULL,
    qr VARCHAR(255) NOT NULL,
    FOREIGN KEY (screening_id) REFERENCES screenings(id),
    FOREIGN KEY (user_id) REFERENCES users(id)
);

DROP TABLE IF EXISTS tickets;

CREATE TABLE tickets(
    id INT AUTO_INCREMENT PRIMARY KEY,
    booking_id INT NOT NULL,
    seat_id INT NOT NULL,
    FOREIGN KEY (seat_id) REFERENCES seats(id),
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);