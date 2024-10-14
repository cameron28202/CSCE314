package Car;
// Written by Hyunyoung Lee for CSCE 314 Students
// This example is for
//  (1) a simple class and subclass relation,
//  (2) upcasting,
//  (3) an effect of invoking subclass constructor,
//  (4) dynamic binding vs. static binding
//  (5) a variable scoping rule -- no block scope,
//  (6) immutable String objects


import static java.lang.System.out;

public class Car {
  protected int protected_var = 5;
  protected Car() { // constructor
    out.println("A car is constructed!"); 
  }
  protected void run() { out.println("A car is running!"); }
}

class MyCar extends Car {
  MyCar() { // constructor; will invoke Car() first
    out.println("Here, my car is constructed!"); 
  }
  @Override
  protected void run() { out.println("That is my car running!"); }
}

class Main {
  public Car buy(Car c) {
    out.println("I'm buying a Car");
    return c;
  }
				
  public Car buy(MyCar mc) {  //method buy is overloaded
    out.println("I'm buying MyCar");
    return mc;
  }
				
  public static void main(String args[]) {
    Car a = new Car();
    out.println();
		
    //MyCar constructor will first execute Car constructor
    Car b = new MyCar(); //upcasting
    out.println();

    //dynamic binding test
    a.run(); //a refers Car object, so Car.run will run
    b.run(); //b refers MyCar object, so MyCar.run will run
    out.println();

    //static binding test
    Main m = new Main();
    m.buy(a);
    m.buy(b);
    out.println();

    //local variable scope test
    int i = 10;
    out.println("i = " + i);
    out.println();

    //trying to redefine i causes compile error
    /*
    for (int i = 0; i < 5; i++) {  
       out.println("for i = " + i);
    }
    out.println("i = " + i);
    out.println();
    */

    //String object test
    String myName = "H";
    out.println("myName.id 1 =" + myName.hashCode());
    out.println("myName_1 is " + myName);
    myName += " Lee"; // myName refers a new String literal "H Lee"
    out.println("myName.id 2 =" + myName.hashCode());
    out.println("myName_2 is " + myName);
    out.println("The 3rd char in myName = " + myName.charAt(2));
  }
}

