
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2

   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: N/a
*/

import java.util.Objects;

public final class Point {

  // represents a two-dimensional coordinate. we need variables x and y.

  public double x;
  public double y;

  // constructor that sets the values of x and y
  public Point(double x, double y){
    this.x = x;
    this.y = y;
  }


  /*
    Equals function takes an object s, checks if the object is a Point, if so then it casts the object to
    a point, checks if the x and y value are the same,and returns true if so.
    This function will return false if the object is not an instance of Point or if the x or y value are different.

    This follows an invariant pattern, only allowing an object of the same class
  */

  @Override
  public boolean equals(Object s){
    if(s instanceof Point){
      Point p = (Point) s;
      if(p.x == x && p.y == y){
        return true;
      }
    }
    return false;
  }

  /*
    hashCode function uses the Objects.hash() function to generate a hash for multiple
    values. This includes both fields x and y. This function can be used for something like
    a hashmap to determine what bucket to put each point in, and make lookup much faster.
  */

  @Override
  public int hashCode(){
    return Objects.hash(x, y);  
  }

  /*
    toString function simply displays the x and y value
    nicely formatted. returns a string.
  */
  @Override
  public String toString(){
    return "Point x value: " + x + "\n" + "Point y value: " + y;
  }
}

