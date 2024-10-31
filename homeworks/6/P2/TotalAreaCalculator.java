
/* Skeleton provided by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 2
 
   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: n/a
*/

class TotalAreaCalculator {

  // define a totalArea double to 0
  // calculate method loops through the shapes array, adds the value returned by invoking the
  // shape's area method to totalArea, then return totalArea.
  public static double calculate(Shape[] shapes) {
    double totalArea = 0.0;
    for(Shape shape : shapes){
      totalArea += shape.area();
    }
    return totalArea;
  }
  // static because every instance of the shape class should have the same total area calculation
}

