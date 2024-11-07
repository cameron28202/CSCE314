
/* CellTest.java skeleton written by Hyunyoung Lee 
   CSCE 314 [Sections 598, 599] Fall 2024  
   Assignment 6 Problem 3 Test class 
   class CellTest

   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: N/a 
*/


public class CellTest {

  /*
    the int_sum function takes in a Cell<Integer>. because the cell class implements iterable,
    we have access to an iterator. we can either explicitly store this iterator in a variable with 
    Iterator<Integer> iterator = current.iterator() and use a while loop, but a for each loop is
    easier.
  */
  public static int int_sum(Cell<Integer> current){
    if (current == null) return 0;

    int sum = 0;
    for(Integer num : current){
      sum += num;
    }
    return sum;
  }

  /*
    num_sum function takes a linked list full of values that have a superclass
    of Number. we add each number's doubleValue to the sum, and return it
  */
  public static double num_sum(Cell<? extends Number> current){
    if (current == null) return 0.0;

    double sum = 0.0;
    for(Number num : current){
      sum += num.doubleValue();
    }
    return sum;
  }

  /*
    print function takes a linkedlist of any type (use generic type parameter <T>),
    loops through and prints each item.
  */
  // implement print
  public static <T> void print(Cell<T> current){
    if (current == null) return;

    for(T item : current){
      System.out.println(item);
    }
  }

	
  public static void main (String args[]) {
    Cell<Integer> intlist = 
        new Cell<Integer>(1, 
          new Cell<Integer>(22, 
            new Cell<Integer>(21, 
              new Cell<Integer>(12, 
                new Cell<Integer>(24, 
                  new Cell<Integer>(17, null))))));
        
    Cell<Integer> nullintlist = null;

    System.out.println("===");
    print(intlist);
    System.out.println("sum of intlist is " + int_sum(intlist));
    System.out.println("sum of null list is " + int_sum(nullintlist));
    System.out.println("===");

    Cell<Double> doublelist = 
        new Cell<Double>(1., 
          new Cell<Double>(16., 
            new Cell<Double>(13.72, 
              new Cell<Double>(5., 
                new Cell<Double>(22., 
                  new Cell<Double>(7.1, null))))));

    System.out.println("===");
    print(doublelist);
    System.out.println("sum ints = " + num_sum(intlist));
    System.out.println("sum doubles = " + num_sum(doublelist));
    System.out.println("===");
  }
}

