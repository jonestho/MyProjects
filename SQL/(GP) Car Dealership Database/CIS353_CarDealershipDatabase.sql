-- CIS 353 - Car Dealership Database Design Project
-- Donovan Heynen
-- Lucas Devried
-- Raphael Bahlbi
-- Thomas Jones
--
-- FILE: CIS353_CarDealershipDatabase.sql
-- ASSIGMENT: Car Dealership Database Implementation
-- DESCRIPTION: This command file creates and populates the Car Dealership 
-- 		Database for our project.
-- ----------------------------------------------------------------------------------------
-- 				Drop the tables
-- ----------------------------------------------------------------------------------------
DROP TABLE Customer CASCADE CONSTRAINTS;
DROP TABLE Salesman CASCADE CONSTRAINTS;
DROP TABLE Specialty CASCADE CONSTRAINTS;
DROP TABLE Car CASCADE CONSTRAINTS;
DROP TABLE Sale CASCADE CONSTRAINTS;
DROP TABLE Appointment CASCADE CONSTRAINTS;
DROP TABLE Purchased CASCADE CONSTRAINTS;
-- ----------------------------------------------------------------------------------------
-- 				Create the tables
-- ----------------------------------------------------------------------------------------
CREATE TABLE Customer (
  customerID	char(9) PRIMARY KEY,
  lastName	varchar2(15),
  firstName	varchar2(15),
  zip		char(5)
);
--
CREATE TABLE Salesman (
  employeeID	char(9) PRIMARY KEY,
  lastName	varchar2(15),
  firstName	varchar2(15),		
  trainerID	char(9)
);
--
CREATE TABLE Specialty (
  specialty	varchar2(15),
  employeeID	char(9)
);
-- NOTE: miles added
CREATE TABLE Car (
  VIN		char(12) PRIMARY KEY,
  year		char(4),
  make		varchar2(15),
  model		varchar2(15),
  color		varchar2(15),
  miles		number,
  price		number
);
-- 
CREATE TABLE Sale (
  saleID	char(9) PRIMARY KEY,
  saleDate	date,
  employeeID	char(9),
  customerID	char(9)
);
-- NOTE: date must be formatted as 'YYYY-MM-DD' & removed description attribute
CREATE TABLE Appointment (
  appointmentNum	number,
  appointmentDate	date,
  employeeID		char(9),
  customerID		char(9),
  -- description		varchar2(25),
  primary key (appointmentNum, appointmentDate)
);
--
CREATE TABLE Purchased (
  saleID	char(9),
  VIN		char(12),
  primary key (saleID, VIN)
);
-- ----------------------------------------------------------------------------------------
-- 				Add the foreign keys
-- ----------------------------------------------------------------------------------------
-- EmployeeID in appointment table should reference EmployeeID in salesman
ALTER TABLE Appointment
ADD FOREIGN KEY (employeeID) REFERENCES Salesman(employeeID)
Deferrable initially deferred;
--
-- CustomerID in appointment table should reference CustomerID in Customer table
ALTER TABLE Appointment
ADD FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
Deferrable initially deferred;
--
-- TrainerID in Salesman should reference EmployeeID that is also in salesman
ALTER TABLE Salesman
ADD FOREIGN KEY (trainerID) REFERENCES Salesman(EmployeeID)
Deferrable initially deferred;
--
-- CustomerID in Sale should reference CustomerID in Customer Table
ALTER TABLE Sale
ADD FOREIGN KEY (CustomerID) REFERENCES Customer(CustomerID)
Deferrable initially deferred;
--
-- EmployeID in sale should reference EmployeeId in Salesman table
ALTER TABLE Sale
ADD FOREIGN KEY (EmployeeID) REFERENCES Salesman(EmployeeID)
Deferrable initially deferred;
--
-- VIN in purchased should reference VIN in Car table
ALTER TABLE Purchased
ADD FOREIGN KEY (VIN) REFERENCES Car(VIN)
Deferrable initially deferred;
-- ----------------------------------------------------------------------------------------
-- 				Populate the database
-- ----------------------------------------------------------------------------------------
--  [ Customer ] - (customerID, lastName, firstName, zip) 
--
insert into Customer values (983287992, 'Lara', 'Connor', 48799);
insert into Customer values (533974642, 'Hendrix', 'Jessica', 41580);
insert into Customer values (316498657, 'Welch', 'Ralph', 39633);
insert into Customer values (900705227, 'Cook', 'Sarah', 39633);
insert into Customer values (776429308, 'Mills', 'Scott', 48799);
insert into Customer values (566441367, 'Frederick', 'David', 39571);
insert into Customer values (165709436, 'Ortiz', 'Robert', 41580);
insert into Customer values (871785399, 'Watkins', 'Derek', 39633);
insert into Customer values (146401439, 'Higgins', 'Mary', 48799);
insert into Customer values (569816946, 'Jones', 'Marvin', 39571);
insert into Customer values (375495242, 'Nunez', 'Kimberly', 39633);
insert into Customer values (544741229, 'Sherman', 'Thomas', 48799);
--
--  [ Salesman ] - (employeeID, lastName, firstName, trainerID) 
--
insert into Salesman values (462596748, 'Sanchez', 'Valerie', null);
insert into Salesman values (964306992, 'Davis', 'Dan', 410298745);
insert into Salesman values (363164384, 'Martinez', 'Nicholas', 981099517);
insert into Salesman values (147457471, 'Johnson', 'Joshua', 363164384);
insert into Salesman values (981099517, 'Wagner', 'Claudia', 363164384);
insert into Salesman values (872130317, 'Spencer', 'Jerome', 462596748);
insert into Salesman values (488456747, 'Young', 'Colleen', 462596748);
insert into Salesman values (410298745, 'Cline', 'Darrell', 872130317);
--
--  [ Specialty ] - (specialty, employeeID)  
--
insert into Specialty values ('Jeep', 462596748);
insert into Specialty values ('Ram', 964306992);
insert into Specialty values ('Chrystler', 363164384);
insert into Specialty values ('Chargers', 872130317);
insert into Specialty values ('Jeep', 872130317);
insert into Specialty values ('Ram', 410298745);
--
--  [ Car ] - (VIN, year, make, model, color, miles, price)
--	
--	NEW:
insert into Car values (855394743553, 2021, 'Jeep', 'Wrangler', 'Black', 0, 55000);
insert into Car values (855394743554, 2021, 'Jeep', 'Wrangler', 'Green', 0, 55000);
insert into Car values (855394743555, 2021, 'Jeep', 'Wrangler', 'Red', 0, 55000);
insert into Car values (855394743556, 2021, 'Jeep', 'Wrangler', 'White', 0, 55000);
insert into Car values (855394743557, 2021, 'Jeep', 'Wrangler', 'Gray', 0, 70000);
insert into Car values (124052189931, 2021, 'Jeep', 'Grand Cherokee', 'White', 0, 44000);
insert into Car values (124052189932, 2021, 'Jeep', 'Grand Cherokee', 'Gray', 0, 44000);
insert into Car values (124052189933, 2021, 'Jeep', 'Grand Cherokee', 'Blue', 0, 44000);
insert into Car values (425168327033, 2021, 'Dodge', 'Challenger', 'Green', 0, 35000);
insert into Car values (425168327034, 2021, 'Dodge', 'Challenger', 'White', 0, 35000);
insert into Car values (425168327035, 2021, 'Dodge', 'Challenger', 'Gray', 0, 52000);
insert into Car values (620894483129, 2021, 'Dodge', 'Charger', 'Blue', 0, 38000);
insert into Car values (620894483130, 2021, 'Dodge', 'Charger', 'Black', 0, 46000);
insert into Car values (620894483131, 2021, 'Dodge', 'Charger', 'Red', 0, 52000);
insert into Car values (620894483132, 2021, 'Dodge', 'Charger', 'Yellow', 0, 72000);
--	USED:
insert into Car values (433293279281, 2015, 'Jeep', 'Wrangler', 'Black', 65321, 30000);
insert into Car values (471398876619, 2012, 'Dodge', 'Avenger', 'White', 74396, 9500);
insert into Car values (506004714133, 2016, 'Chrystler', '200', 'Blue', 48690, 12000);
insert into Car values (779521271173, 2018, 'Jeep', 'Cherokee', 'Green', 52089, 18000);
insert into Car values (285388816260, 2020, 'Jeep', 'Grand Cherokee', 'Black', 14823, 26000);
insert into Car values (935864176801, 2015, 'Ram', '1500', 'White', 62480, 12000);
insert into Car values (112034599584, 2018, 'Ram', '3500', 'Red', 38921, 19000);
--
--  [ Sale ] - (saleID, saleDate, employeeID, customerID)
--
alter session set NLS_DATE_FORMAT = 'YYYY-MM-DD';
--
insert into Sale values (924510356, '2021-10-09', 872130317, 983287992);
insert into Sale values (318691347, '2021-10-12', 462596748, 533974642);
--
--  [ Appointment ] - (appointmentNum, appointmentDate, employeeID, customerID)
--
insert into Appointment values (1, '2021-10-09', 872130317, 983287992);
insert into Appointment values (2, '2021-10-09', 462596748, 533974642);
insert into Appointment values (3, '2021-10-09', 462596748, 316498657);
insert into Appointment values (4, '2021-10-09', 872130317, 900705227);
insert into Appointment values (5, '2021-10-09', 147457471, 776429308);
insert into Appointment values (1, '2021-10-11', 410298745, 566441367);
insert into Appointment values (2, '2021-10-11', 981099517, 165709436);
insert into Appointment values (3, '2021-10-11', 981099517, 871785399);
insert into Appointment values (4, '2021-10-11', 410298745, 146401439);
insert into Appointment values (1, '2021-10-12', 462596748, 533974642);
insert into Appointment values (2, '2021-10-12', 363164384, 569816946);
insert into Appointment values (3, '2021-10-12', 964306992, 375495242);
insert into Appointment values (4, '2021-10-12', 363164384, 544741229);
--
--  [ Purchased ] - (saleID, VIN)
--
insert into Purchased values (924510356, 433293279281);
insert into Purchased values (318691347, 855394743556);
--
COMMIT;