
Title: Homework 5 README

## First, write your name, UIN, and acknowledge any help received 
## in doing this assignment

Student Name: Cameron Stone
UIN: 832007843
Acknowledgements: N/a

I used Java 21

----------
## Problem 2: Subset Output Fibonacci
----------

### Requirements:
- Implement a subset output Fibonacci number generator
- Handle command line arguments for beginning and end numbers
- Include input validation for negative numbers and swapped begin/end values
- Mark even numbers with an asterisk

### Compilation:
javac SubsetOutputFib.java

### Execution:
java SubsetOutputFib <begin_number> <end_number>

### Expected Output:
- If begin_number = 1, prints first Fibonacci number
- Prints Fibonacci numbers from begin_number to end_number
- Even numbers are marked with an asterisk
- error messages for:
  - Incorrect number of arguments
  - Negative numbers (with auto-correction)
  - Swapped begin/end values (with auto-correction)

----------
## Problem 3: Improved Fibonacci
----------

### Requirements:
- Implement an object-oriented Fibonacci sequence generator
- use my fibonaccinumber class to store values and whether its even or odd
- generate the first 9 Fibonacci numbers
- Mark the even numbers with an asterisk

### Compilation:
javac ImprovedFibonacci.java FibonacciNumber.java

### Execution:
java ImprovedFibonacci

### Expected Output:
- Prints first 9 Fibonacci numbers with index
- Even numbers are marked with an asterisk

----------
## Problem 4: Vehicle Class Implementation
----------

### Requirements:
exercise 2.1:
- create a Vehicle class with fields for:
  - Current speed
  - Current direction in degrees
  - Owner name

Exercise 2.3:
- add static field for next vehicle ID
- add a non static field for car's ID number

Exercise 2.13:
- make all fields t private
- Add  accessor methods when appropriate
- make selective mutator methods, if allowed

### Compilation:
javac Vehicle.java

----------
## Problem 5: Vehicle Class Constructors and Methods
----------

### Requirements:
Exercise 2.7:
- add a no-arg constructor
- add a constructor with owner's name parameter

Exercise 2.9:
- add a static method to return highest ID used

exercise 2.10:
- override the toString method

### Expected Output:
- vehicle objects with appropriate ID assignment
- string representation of a vehicle object showing all of the private variables

----------
## Problem 7: Vehicle Speed Control
----------

### Requirements:
exercse 2.15:
- add a changeSpeed method to change the vehicles speed if it makes logical sense
- add a stop method

### Expected Output:
- vehicles speed is changed to the integer passed in, if it makes logical sense
- vehicles speed is set to 0 when stop method is called

----------
## Problem 8: Vehicle Turn Methods
----------

### Requirements:
Exercise 2.17:
- implement the turn method with degrees parameter
- also implement turn method with TURN_LEFT/TURN_RIGHT constants

### Expected Output:
- vehicle speed is updated when integer is passed into turn method. degrees never go over 360.
- Vehicle speed is updated when proper string is passed in. LEFT or RIGHT. this uses the static variables i made.

----------
## Problem 9: PassengerVehicle Implementation
----------

### Requirements:
Exercise 3.1:
- create a PassengerVehicle class extending Vehicle
- add seat counting capability:
  - total # of  seats
  - Occupied seats
- overload compareto function
- Create a list  of PassengerVehicles
- Demonstrate sorting functionality

### Compilation:
javac PassengerVehicle.java Vehicle.java

### Execution:
java PassengerVehicle

### Expected Output:
- Sorted list of passenger vehicles by total seats (descending as instructed)
- Demonstration of seat occupation functionality
- available seats calculation
- Error handling for invalid passenger operations