
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
  private int currentSpeed;
  private int currentDirection;
  private String ownerName;
  private static int nextId = 1;
  private int id;

  public static final int TURN_LEFT = -90;
  public static final int TURN_RIGHT = -90;
  
  public Vehicle(){
    this.currentSpeed = 0;
    this.currentDirection = 0;
    this.ownerName = "";
    this.id = nextId++;
  }

  public Vehicle(String ownerName){
    this.currentSpeed = 0;
    this.currentDirection = 0;
    this.ownerName = ownerName;
    this.id = nextId++;

  }

  public Vehicle(int currentSpeed, int currentDirection, String ownerName) {
    this.currentSpeed = currentSpeed;
    this.currentDirection = currentDirection;
    this.ownerName = ownerName;
    this.id = nextId++;
  }

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
  public static int getHighestIdUsed() {
    return nextId - 1;
  }

  public void changeSpeed(int newSpeed) {
    if(newSpeed >= 0){
        this.currentSpeed = newSpeed;
    } 
    else{
        System.out.println("Error: Enter a positive speed value.");
    }
  }
  public void stop(){
    this.currentSpeed = 0;
  }
  public void turn(int degrees){
    // make sure degrees never go over 360
    this.currentDirection = (this.currentDirection + degrees + 360) % 360;
  }
  public void turn(String direction){
    if(direction == "LEFT"){
      turn(TURN_LEFT);
    }
    else if(direction == "RIGHT"){
      turn(TURN_RIGHT);
    }
    else{
      System.out.println("Incorrect usage. Enter either LEFT or RIGHT.");
    }
  }

  
  @Override
  public String toString() {
      return "Vehicle " + id + " attributes: currentSpeed = " + currentSpeed + 
              ", currentDirection = " + currentDirection + 
              ", ownerName = " + ownerName;
  }
  
}

/*

  Exercise 2.13:

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

class VehicleTest {
  public static void main(String[] args) {

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
  private int totalSeats;
  private int occupiedSeats;
  // private fields specific to PassengerVehicle such as 
  // total # of seats and occupied seats (both can be of type int, 
  // and properly initialized)

  public PassengerVehicle(){
    this.totalSeats = 0;
    this.occupiedSeats = 0;
  }
  public PassengerVehicle(String ownerName){
    super(ownerName);
    this.totalSeats = 0;
    this.occupiedSeats = 0;
  }
   public PassengerVehicle(String ownerName, int totalSeats){
    super(ownerName);
    this.totalSeats = totalSeats;
    this.occupiedSeats = 0;
  }
  /* constructors: Give three constructors, 
     1. one no-arg constructor,
     2. a constructor with one argument: only owner name as an argument,
     3. a constructor with two arguments: owner name and total # of seats

     Probably you already have the first two constructors in the Vehicle
     class, then, invoke the Vehicle class constructor by using `super`
  */

  public int getTotalSeats() {
    return totalSeats;
  }
  public int getOccupiedSeats() {
    return occupiedSeats;
  }

  public void addPassengers(int count) {
    if(occupiedSeats + count <= totalSeats){
        occupiedSeats += count;
    }
    else {
        System.out.println("Error: Only " + (totalSeats - occupiedSeats) + " seats available.");
    }
  }
  public void removePassengers(int count) {
    if (occupiedSeats - count >= 0){
        occupiedSeats -= count;
    }
    else{
        System.out.println("Error: Only " + occupiedSeats + " passengers in the vehicle.");
    }
  }
  @Override
  public int compareTo(PassengerVehicle other) {
    return Integer.compare(this.totalSeats, other.totalSeats);
  }

  public int getAvailableSeats() {
    return totalSeats - occupiedSeats;
  }

  @Override
  public String toString() {
    return super.toString() + " PassengerVehicle attributes: totalSeats = " + totalSeats + 
        ", occupiedSeats = " + occupiedSeats;
  }
  
  // main method
  public static void main(String[] args) {
    ArrayList<PassengerVehicle> pVs = new ArrayList<PassengerVehicle>();

    pVs.add(new PassengerVehicle("Cam", 1));
    pVs.add(new PassengerVehicle("Sam", 9));
    pVs.add(new PassengerVehicle("Jackson", 3));
    pVs.add(new PassengerVehicle("William", 5));
    pVs.add(new PassengerVehicle("Natalia", 2));

    Collections.sort(pVs);

    // Output in descending order
    System.out.println("Passenger Vehicles in descending order of total seats:");
    for (int i = pVs.size() - 1; i >= 0; i--) {
        System.out.println(pVs.get(i));
    }

    // Occupy some seats and output available seats
    System.out.println("\nAvailable seats after occupying:");
    for(int i = 0; i < pVs.size(); i++) {
      PassengerVehicle pv = pVs.get(i);
      pv.addPassengers(3); // Occupy 2 seats in each vehicle
      System.out.println(pv.getOwnerName() + "'s vehicle: " + pv.getAvailableSeats() + " seats available");
    }

  } // end of main

} // end of class PassengerVehicle

