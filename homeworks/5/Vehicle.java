
/* CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
   Homework Assignment 5 
   Skeleton for Problems 4-9
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Cameron Stone
   Student UIN: 832007843
   Acknowledgements: N/a
*/

import java.util.*; // for Collections.sort() and ArrayList

class Vehicle {

  // problem 4 Exercise 2.1: Write a simple Vehicle class that has fields for (at least) current speed, current direction in
  // degrees, and owner name.
  // private variables for each object of the vehicle class
  private int currentSpeed;
  private int currentDirection;
  private String ownerName;
  private int id;


  // problem 4Exercise 2.3: Add a static field to your Vehicle class to hold the next vehicle identification number, and a
  // non-static field to the Vehicle class to hold each car's ID number.
  // static variables for the vehicle class. these are "global" variables across
  // any instance of the vehicle class. 

  private static int nextId = 1;
  public static final int TURN_LEFT = -90;
  public static final int TURN_RIGHT = 90;
  
  // no args constructor. set currentspeed, currentdirection and ownername to default value,
  // and use the static nextId variable to generate a new id.
  
  // problem 5 Exercise 2.7: Add two constructors to Vehicle: a no-arg constructor and one that takes an initial owner's
  // name. Modify the main program so that it generates the same output it did before
  
  public Vehicle(){
    this.currentSpeed = 0;
    this.currentDirection = 0;
    this.ownerName = "";
    this.id = nextId++;
  }

  // constructor with 1 argument: the ownerName string. same as the no args contructor except
  // assign the ownerName to the passed in string.
  public Vehicle(String ownerName){
    this.currentSpeed = 0;
    this.currentDirection = 0;
    this.ownerName = ownerName;
    this.id = nextId++;

  }

  // all argument constructor. assign currentspeed, currentdirection, ownername to the arguments
  // passed in, generate id with the static nextId variable.
  public Vehicle(int currentSpeed, int currentDirection, String ownerName) {
    this.currentSpeed = currentSpeed;
    this.currentDirection = currentDirection;
    this.ownerName = ownerName;
    this.id = nextId++;
  }

  // getters are setters. Here, we are defining getters and setters for most of the 
  // variables (clarification on why i didn't create setters for certain attributes
  // can be found in exercise 2.13)

  // problem 4 Exercise 2.13: Make the fields in your Vehicle class private, and add accessor methods for the fields.
  // Which fields should have methods to change them, and which should not?

  public int getCurrentSpeed() {
      return currentSpeed;
  }
  public void setCurrentSpeed(int currentSpeed) {
      this.currentSpeed = currentSpeed;
  }
  public int getCurrentDirection() {
      return currentDirection;
  }
  public void setCurrentDirection(int currentDirection) {
      this.currentDirection = currentDirection;
  }
  public String getOwnerName() {
      return ownerName;
  }
  public void setOwnerName(String ownerName) {
      this.ownerName = ownerName;
  }
  public int getId() {
    return id;
  }
  public static int getNextId() {
    return nextId;
  }

  //problem 5,  Exercise 2.9: Add a static method to Vehicle that returns the highest identification number used thus far.
  public static int getHighestIdUsed() {
    return nextId - 1;
  }

  // problem 7, Exercise 2.15: Add a changeSpeed method that changes the current speed of the vehicle to a passed-in
  // value and add a stop method that sets the speed to zero

  // changeSpeed method. this method returns nothing and takes in a new speed.
  // i added a check to see if the speed is greater than 0 ebcause it doesn't make sense to input a negative speed.
  // if this condition is passed the current speed is updated, if it's not, we print to the user that a positive speed
  // value needs to be passed in for the function to work.
  public void changeSpeed(int newSpeed) {
    if(newSpeed >= 0){
        this.currentSpeed = newSpeed;
    } 
    else{
        System.out.println("Error: Enter a positive speed value.");
    }
  }

  // stop method. this method returns nothing and takes no parameters. it simply
  // stops the car by setting currentSpeed to 0.
  public void stop(){
    this.currentSpeed = 0;
  }

  // problem 8, Exercise 2.17: Add two turn methods to Vehicle: one that takes a number of degrees to turn and one that
  //takes either of the constants Vehicle.TURN_LEFT or Vehicle.TURN_RIGHT.

  // turn method returns nothing and takes in an integer degrees.
  // this version of the method that takes in an integer turns the car 
  // by the number of degrees, making sure not to go over 360.
  public void turn(int degrees){
    // make sure degrees never go over 360
    this.currentDirection = (this.currentDirection + degrees + 360) % 360;
  }

  // this turn method also returns nothing and takes in a string direction.
  public void turn(String direction){
    // if "LEFT" is passed in as the direction, we use the static final int TURN_LEFT previously
    // defined in the class, and call our previously defined turn method passing in this TURN_LEFT variable.
    if(direction.equals("LEFT")){
      turn(TURN_LEFT);
    }

    //same thing if "RIGHT" is passed in...
    else if(direction.equals("RIGHT")){
      turn(TURN_RIGHT);
    }

    // if LEFT or RIGHT is not passed in, the method was used incorrectly. do nothing and tell the user of the mistake.
    else{
      System.out.println("Incorrect usage. Enter either LEFT or RIGHT.");
    }
  }

  // problem 5, Exercise 2.10: Add a toString method to Vehicle.
  // toString method for a vehicle in case we ever want to print it. this will display its attributes.
  @Override
  public String toString() {
      return "Vehicle " + id + " attributes: currentSpeed = " + currentSpeed + 
              ", currentDirection = " + currentDirection + 
              ", ownerName = " + ownerName;
  }
  
}

/*

  problem 4 Exercise 2.13:

  - currentSpeed should have a getter and a setter because the speed of the
    vehicle could change.
  - currentDirection should have a getter and setter for the same reason
  - ownerName should have a getter or setter because the vehicle could be sold
    and the ownerName could update
  - id should only have an accessor. We are updating the id based on the static
    variable nextId, it should never be manually set
  - nextId should only have an accessor. We are updating the nextId every time 
    an object of the Vehicle class is made, it should increase by one every time and 
    never be manually set

*/



/*

  Problem 9, Exercise 3.1: Starting with the Vehicle class from the exercises in Chapter 2, create an extended class
  called PassengerVehicle to add a capability for counting the number of seats available in the car and the
  number currently occupied. Provide a new main method in PassengerVehicle to create a few of these
  objects and print them out.

 */
class VehicleTest {
  public static void main(String[] args) {

    // here we define 10 instances of the vehicle class. 5 with the no args constructor, and 5
    // with the single ownerName argument, as instructed in the instructions.
    Vehicle vehicle1 = new Vehicle();
    Vehicle vehicle2 = new Vehicle();
    Vehicle vehicle3 = new Vehicle();
    Vehicle vehicle4 = new Vehicle();
    Vehicle vehicle5 = new Vehicle();

    Vehicle vehicle6 = new Vehicle("Cam");
    Vehicle vehicle7 = new Vehicle("John");
    Vehicle vehicle8 = new Vehicle("Jack");
    Vehicle vehicle9 = new Vehicle("William");
    Vehicle vehicle10 = new Vehicle("Mauricio");


    //here is some testing i did to ensure that each method i created works.
    vehicle6.setCurrentSpeed(1000);
    vehicle6.setCurrentDirection(45);
    System.out.println(vehicle6.toString());
    vehicle6.changeSpeed(-1);
    vehicle6.changeSpeed(100);
    System.out.println(vehicle6.toString());
    vehicle6.stop();
    System.out.println(vehicle6.toString());


    vehicle6.turn(90);
    System.out.println(vehicle6.toString());
    vehicle6.turn("LEFT");
    System.out.println(vehicle6.toString());
    vehicle6.turn("backwards");
  }
}


// Hints on the PassengerVehicle class for Problem 9 of Homework 4
class PassengerVehicle extends Vehicle 
                       implements Comparable<PassengerVehicle> 
{

  // private fields of the passenger vehicle class. we have totalSeats
  // and occupied seats.
  private int totalSeats;
  private int occupiedSeats;


  // no args constructor for passengervehicle. here we assign totalseats and occupied seats to 0. 
  // java will also inheritely call super() which will execute the Vehicle no args constructor. 
  // this is because we extend the vehicle class.
  public PassengerVehicle(){
    this.totalSeats = 0;
    this.occupiedSeats = 0;
  }

  // ownerName argument constructor for passengervehicle. here we first call the single string argument
  // constructor passing in the ownerName, which will set all the other private vehicle attributes to 0 and
  // assign the ownername. then we assign the totalseats and occupied seats to 0.
  public PassengerVehicle(String ownerName){
    super(ownerName);
    this.totalSeats = 0;
    this.occupiedSeats = 0;
  }

  // string and itneger constructor. here we still call the superclass string argument constructor. we then
  // assign totalseats to the integer passed into this constructor.
   public PassengerVehicle(String ownerName, int totalSeats){
    super(ownerName);
    this.totalSeats = totalSeats;
    this.occupiedSeats = 0;
  }

  // getters for totalSeats and occupiedSeats. no setters because these values will be calculated like a real vehicle.
  public int getTotalSeats() {
    return totalSeats;
  }
  public int getOccupiedSeats() {
    return occupiedSeats;
  }

  // addPassengers function returns nothing and takes in an integer count. here we are, if occupiedSeats + count is less
  // than the total seats in the vehicle, increasing the occupied seats by count.
  // if we dont have enough seats we do nothing and tell the user how many seats we have left.
  public void addPassengers(int count) {
    if(occupiedSeats + count <= totalSeats){
        occupiedSeats += count;
        System.out.println("Occupied seats updated to: " + occupiedSeats);
    }
    else {
        System.out.println("Error: Only " + (totalSeats - occupiedSeats) + " seats available.");
    }
  }

  // removepassenger function returns nothing and takes in an integer count. here, if we have the occupied seats to remove,
  // we are decraesing occupiedseats by count. if we do not, do nothing and tell the user how many occupied seats we actually have.
  public void removePassengers(int count) {
    if (occupiedSeats - count >= 0){
        occupiedSeats -= count;
        System.out.println("Occupied seats updated to: " + occupiedSeats);
    }
    else{
        System.out.println("Error: Only " + occupiedSeats + " passengers in the vehicle.");
    }
  }

  // here we are overriding the compareto function. we pass in another vehicle to compare with,
  // and use the Integer.compare function to compare the total seats of our vehicle with the 
  // total seats of the other vehicle.
  @Override
  public int compareTo(PassengerVehicle other) {
    return Integer.compare(this.totalSeats, other.totalSeats);
  }

  // function to get available seats. this simply returns totalSeats - how many seats we have occupied.
  public int getAvailableSeats() {
    return totalSeats - occupiedSeats;
  }


  // here we are overriding the tostring method for a passenger vehicle. this will call the super (vehicle) toString we previously defined,
  // then it will print out the passengervehicle specific attributes, totalseats and occupied seats.
  @Override
  public String toString() {
    return super.toString() + " PassengerVehicle attributes: totalSeats = " + totalSeats + 
        ", occupiedSeats = " + occupiedSeats;
  }
  
  // main method
  public static void main(String[] args) {

    // define arraylist of passengervehicle objects.
    ArrayList<PassengerVehicle> pVs = new ArrayList<PassengerVehicle>();

    // populate the arraylist. we have 5 objects, as we are told to do so in the instructions.
    pVs.add(new PassengerVehicle("Cam", 1));
    pVs.add(new PassengerVehicle("Sam", 9));
    pVs.add(new PassengerVehicle("Jackson", 3));
    pVs.add(new PassengerVehicle("William", 5));
    pVs.add(new PassengerVehicle("Natalia", 2));

    // here we are calling the collections.sort method on our arraylist. this will use the compareto function
    // we overrid for passengervehicle and sort in ascending order!
    Collections.sort(pVs);

    // Output in descending order by simply going backwards through arraylist pVs.
    System.out.println("Passenger Vehicles in descending order of total seats:");
    for (int i = pVs.size() - 1; i >= 0; i--) {
        System.out.println(pVs.get(i));
    }

    // Occupy some seats and output available seats to see if our implementation works!
    System.out.println("\nAvailable seats after occupying:");
    for(int i = 0; i < pVs.size(); i++) {
      PassengerVehicle pv = pVs.get(i);
      pv.addPassengers(3); // Occupy 2 seats in each vehicle
      System.out.println(pv.getOwnerName() + "'s vehicle: " + pv.getAvailableSeats() + " seats available");
    }
    // this shouldnt work because we have no passengers to remove!
    pVs.get(0).removePassengers(2);

    // this should work!
    pVs.get(4).removePassengers(2);

  } // end of main

} // end of class PassengerVehicle

