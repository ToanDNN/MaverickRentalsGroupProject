DROP DATABASE IF EXISTS bikesDB;

CREATE DATABASE bikesDB;

USE bikesDB;

CREATE TABLE Station (
                         stationId INT NOT NULL PRIMARY KEY,
                         stationName VARCHAR(255) NOT NULL,
                         stationCapacity INT NOT NULL,
                         stationAvailableBikes INT DEFAULT 0
);

CREATE TABLE BikeType (
                          bikeTypeId INT NOT NULL PRIMARY KEY,
                          typeName VARCHAR(255) NOT NULL,
                          bikePrice DECIMAL(10, 2) NOT NULL
);

CREATE TABLE Bike (
                      bikeId INT NOT NULL PRIMARY KEY,
                      bikeTypeId INT NOT NULL,
                      available BOOLEAN NOT NULL DEFAULT true,
                      stationId INT,
                      FOREIGN KEY (bikeTypeId) REFERENCES BikeType(bikeTypeId),
                      FOREIGN KEY (stationId) REFERENCES Station(stationId)
);

CREATE TABLE Reservation (
                             reservationId INT NOT NULL PRIMARY KEY auto_increment,
                             startTime DATETIME NOT NULL,
                             endTime DATETIME DEFAULT NULL,
                             startStationId INT NOT NULL,
                             endStationId INT DEFAULT NULL,
                             price DECIMAL(10, 2) NOT NULL,
                             customerName VARCHAR(255) NOT NULL,
                             bikeId INT NOT NULL,
                             FOREIGN KEY (startStationId) REFERENCES Station(stationId),
                             FOREIGN KEY (endStationId) REFERENCES Station(stationId),
                             FOREIGN KEY (bikeId) REFERENCES Bike(bikeId)
);


-- Stations
INSERT INTO Station (stationId, stationName, stationCapacity, stationAvailableBikes)
VALUES
    (1, 'Central Station', 10, 4),
    (2, 'Downtown Station', 8, 3),
    (3, 'Uptown Station', 12, 6),
    (4, 'Market Station', 5, 2);

-- Bike Types
INSERT INTO BikeType (bikeTypeId, typeName, bikePrice)
VALUES
    (1, 'Standard', 5.00),
    (2, 'Electric', 10.00),
    (3, 'Cargo', 8.00);

-- Bikes
INSERT INTO Bike (bikeId, bikeTypeId, available, stationId)
VALUES
    (1, 1, true, 1),
    (2, 1, true, 1),
    (3, 2, true, 1),
    (4, 3, true, 2),
    (5, 1, true, 2),
    (6, 2, true, 3),
    (7, 1, true, 3),
    (8, 3, true, 4),
    (9, 2, true, 4),
    (10, 1, true, 4);

-- Reservations
INSERT INTO Reservation (startTime, startStationId, price, customerName, bikeId)
VALUES
    ('2023-04-01 08:00:00', 1, 15.00, 'John Smith', 1),
    ('2023-04-01 09:00:00', 2, 20.00, 'Jane Doe', 4),
    ('2023-04-02 14:30:00', 3, 12.00, 'Alice Johnson', 6),
    ('2023-04-02 16:00:00', 4, 10.00, 'Bob Brown', 8);

