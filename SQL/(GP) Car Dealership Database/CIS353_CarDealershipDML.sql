SPOOL CIS353_CarDealershipDMLOutput.txt
SET ECHO ON
--
-- Query to print the tables

SELECT *
FROM Customer;

SELECT *
FROM Salesman;

SELECT *
FROM Specialty;  

SELECT *
FROM Car; 

SELECT *
FROM Sale;

SELECT *
FROM Appointment;

SELECT *
FROM Purchased;

/* 4-Relation Join:
Find the customer ID who has an appointment with a salesman who specializes in Jeep.
*/

SELECT DISTINCT C.customerID, E.employeeID
FROM Customer C, Salesman E, Appointment A, Specialty S
WHERE E.employeeID = A.employeeID AND
    A.employeeID = S.employeeID AND
    S.specialty = 'Jeep' AND
    A.customerID = C.customerID;

/* Self-Join:
Find a pair of employee IDs where the employee on the left is a trainee of the other employee. Order by trainee ID.
*/

SELECT DISTINCT E1.employeeID, E2.employeeID
FROM Salesman E1, Salesman E2
WHERE E2.employeeID = E1.trainerID
ORDER BY E1.employeeID;

/* Set-Operation (Minus):
Find the VIN, make, model, and year of a car that hasn't been purchased.
*/

SELECT DISTINCT C.vin, C.make, C.model, C.year
FROM Car C
MINUS
SELECT DISTINCT C.vin, C.make, C.model, C.year
FROM Purchased P, Car C
WHERE C.vin = P.vin;

/* Average:
Find the average price of a vehicle made by Dodge.
*/

SELECT AVG(C.price)
FROM Car C
WHERE C.make = 'Dodge';

/* (FIX) Correlated:
Find the year, make, model, and price of the most expensive car per brand. Order by make.
*/

SELECT C.year, C.make, C.model, C.price
FROM Car C
WHERE C.price IN (SELECT MAX(C.price)
		  FROM Car C
		  GROUP BY C.make)
ORDER BY C.make;

/* Non-Correlated:
Find the cID, first and last name of customers who didn't buy a car. Order by ID.
*/

SELECT C.customerID, C.firstName, C.lastName
FROM Customer C
WHERE C.customerID NOT IN (SELECT S.customerID
			   FROM Sale S)
ORDER BY C.customerID;

/* (FIX) Relational Division:
Find the customer ID, first and last name of those who have an appointment with Valerie Sanchez (462596748)
*/

SELECT C.customerID, C.firstName, C.lastname
FROM Customer C
WHERE NOT EXISTS ((SELECT A.appointmentNum
		   FROM Appointment
		   WHERE A.employeeID = 462596748)
		   MINUS
                  (SELECT A.appointmentNum
		   FROM Appointment, Salesman S
		   WHERE A.customerID = C.customerID AND
		   	 A.employeeID = S.employeeID AND
			 S.employeeID = 462596748));

/* Outer Join:
Find the customer ID, first and last name of every customer. Also show the appointments for those who scheduled one.
*/

SELECT C.customerID, C.firstName, C.lastName, A.appointmentDate
FROM Customer C LEFT OUTER JOIN Appointment A ON C.customerID = A.customerID; 

--
--
SET ECHO OFF
SPOOL OFF