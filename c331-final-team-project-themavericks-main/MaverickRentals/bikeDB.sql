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

LOAD DATA INFILE "Station.csv"
INTO TABLE bikesDB.Station
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA INFILE "BikeType.csv"
INTO TABLE bikesDB.BikeType
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA INFILE "Bike.csv"
INTO TABLE bikesDB.Bike
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;

LOAD DATA INFILE "Reservation.csv"
INTO TABLE bikesDB.Reservation
FIELDS TERMINATED BY ','
ENCLOSED BY '"'
LINES TERMINATED BY '\n'
IGNORE 1 ROWS;


SELECT b.*, bt.typeName, bt.bikePrice
FROM Bike b
         JOIN BikeType bt USING (bikeTypeId)
WHERE b.stationId = 17 AND b.available = true;

SELECT * FROM Bike
                  JOIN BikeType USING (bikeTypeId)
WHERE stationId = 17 AND available = true;


-- UPDATE Bike SET available = false WHERE bikeId IN (4, 5, 6);
UPDATE Bike SET available = false WHERE bikeId = 4;
UPDATE Bike SET available = false WHERE bikeId = 5;
UPDATE Bike SET available = false WHERE bikeId = 6;



SELECT * FROM RESERVATION;

UPDATE Reservation
SET endTime = '2022-03-23 10:00:00', endStationId = 2
WHERE reservationId = 1;


SELECT * FROM RESERVATION;

SELECT * FROM Bike
                  JOIN BikeType USING (bikeTypeId)
WHERE stationId = 17 AND available = true;

SELECT * FROM Bike
                  JOIN BikeType USING (bikeTypeId)
WHERE stationId = 2 AND available = true;

SELECT * FROM Bike;