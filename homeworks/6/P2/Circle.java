
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.lang.Math;
import java.util.Objects;

class Circle extends Shape {
  private double radius;

  // constructor that accepts a Point (for position) and a double
  // (for the radius).
  public Circle(Point p0, double r){
    super(p0, Math.PI * r * r);
    this.radius = r;
  }

  // equals function first returns true if we are comparing with ourselves, 
  // returns false is the objects are not the same type,
  // then now that we know the object is a circle, we cast as a circle and return
  // true if the point and radius are equal. false otherwise.
  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(getClass() != o.getClass()) return false;

    Circle other = (Circle) o;
    return position.equals(other.position) && radius == other.radius;
  }


  // uses objects hash method to return a hash value given position and radius
  @Override
  public int hashCode(){
    return Objects.hash(position, radius);
  }

  // calculates area of circle, using Math.PI for pi and the radius of the object 
  @Override
  public double area(){
    return Math.PI * radius * radius;
  }

  // toString method uses the existing  position toString, then displays the radius of the object
  @Override
  public String toString(){
    return "Circle's position: " + position.toString() + ", radius: " + radius;
  }
}

