CREATE DATABASE person;

CREATE TABLE p.person
(
    first_name VARCHAR(15) NOT NULL,
    last_name VARCHAR(20) NOT NULL,
    login VARCHAR(30) NOT NULL,
    phone_number VARCHAR(20) NOT NULL,
	email VARCHAR(30) NOT NULL,
    id_person INT PRIMARY KEY NOT NULL AUTO_INCREMENT
);
ALTER TABLE p.person ADD CONSTRAINT unique_login UNIQUE (login);
