
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name: Cameron Stone 
   UIN: 832007843
   Acknowledgements: N/a
*/

import java.util.Objects;

class Square extends Shape {

  // private variable for side length
  private double side;

  // constructor that accepts a Point (for position) and a double
  // (for the side length).
  public Square(Point p0, double side){
    // call shape parent class constructor which sets the point and the area
    super(p0, side * side);
    // then set this.side equal to side passed in
    this.side = side;
  }


  // equals function first returns true if we are comparing the object to itself (if so return true),
  // then checks if they are not the same class (if so return false)
  // then, now that we know the object is a square ,we cast to square and return true if their positions and sides are equal.
  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(getClass() != o.getClass()) return false;

    Square other = (Square) o;
    return position.equals(other.position) && side == other.side;
  }

  // hashcode function uses Objects hash function, returning hash value given the position and side
  @Override
  public int hashCode(){
    return Objects.hash(position, side);
  }

  // implement the abstract area function, area of a square
  // is simple side * side
  @Override
  public double area(){
    return this.side * this.side; 
  }

  // toString function will call the point class toString for the position
  @Override
  public String toString() {
      return "Square's position: " + position.toString() + ", side: " + side;
  }
}

