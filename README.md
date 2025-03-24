The code when runned creates a gui which is integrated with mysql databae connection

also create a database on mysql using the code

CREATE DATABASE railway;

USE railway;

CREATE TABLE booking (
    Name VARCHAR(100) NOT NULL,
    Date DATE NOT NULL,
    Number_of_passengers INT NOT NULL,
    Destination VARCHAR(100) NOT NULL,
    PRIMARY KEY (Name)
);
