
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2
 
   Student Name: Cameron Stone
   UIN:832007843
   Acknowledgements: N/a
*/

abstract class Shape implements Comparable<Shape> {
  public Point position;
  public double area;
    
  // constructor that sets position as the Point passed as an argument
  // signature: Shape (Point)

  public Shape(Point position, double area){
    this.position = position;
    this.area = area;
  }

  /*
    equals function takes another object. We first return true if we are comparing the object
    with itself. We then check if the objects  have the same class type,
    If not, we return false. We then check if o is an instance of a Shape
    to make sure its safe to cast, and return false if it is not. if it is a shape, we 
    return true if their positions are equal and their areas are equal and false otherwise. 
  */
  @Override
  public boolean equals(Object o){
    if(this == o) return true;
    if(getClass() != o.getClass()) return false;

    if(o instanceof Shape){
      Shape other = (Shape) o;
      return position.equals(other.position) && area == other.area;
    }
    return false;
  }

  // abstract area method will be implemented in square and circle classes
  public abstract double area();

  // compareTo method uses the double compare method to compare the
  // shape by area. This will return -1 if a < b, 0 if a == b, and 1 if a > b.
		@Override
  public int compareTo(Shape s){
    return Double.compare(this.area, s.area);
  }
} 




