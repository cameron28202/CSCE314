
/* CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
   Homework Assignment 5 
   Skeleton for Problems 2 & 3
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Cameron Stone 
   Student UIN: 832007843
   Acknowledgements: N/a
*/

class FibonacciNumber {
   // private variables for FibonacciNumber class
    private int value;
    private boolean isEven;


    // constructor for FibonacciNumber. assign value to value, and 
    // isEven to true if value % 2 == 0 and false otherwise
    public FibonacciNumber(int value) {
        this.value = value;
        this.isEven = (value % 2 == 0);
    }

    

   // accessors and mutators for fibonaccinumber variables
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isEven() {
        return isEven;
    }

    public void setEven(boolean isEven) {
        this.isEven = isEven;
    }

    // toString method to print a fibonacci number if needed
    @Override
    public String toString() {
        return value + (isEven ? " *" : "");
    }

}

class SubsetOutputFib { // problem 2
   // main function for problem 2: subset output fibonacci number implementation
    public static void main(String[] args) {
      // unpack the arguments passed in through the commandline. 
      // if 2 integers are not passed in, tell the user then exit the program.
      if(args.length != 2){
            System.out.println("Please enter 2 integers.");
            return;
      }

      // unpack be and en

      int be = Integer.parseInt(args[0]);
      int en = Integer.parseInt(args[1]);

      // handle the case where the user inputs a negative number
      if(be < 0 || en < 0){
            // ensure input is positive
            be = Math.abs(be);
            en = Math.abs(en);
            // tell user
            System.out.println("You entered a negative integer. Input has been corrected by taking the absolute value of input.");
      }

      // handle th ecase where the user enters an end value that is smaller than the beginning value
      if(be > en){
            // swap numbers
            int temp = be;
            be = en;
            en = temp;
            // tell user
            System.out.println("Your value for the first integer is greater than the second integer. Input has been corrected by swapping the inputs.");
      }

      // initialize variables
      int lo = 1;  //  previous number in the sequence
      int hi = 1;  //  current number in the sequence
      String mark; // Used to mark even numbers with an asterisk

      // if the starting number is 1, print the fibnoacci nubmer for 1 (1)
      if(be == 1){
         System.out.println("1: " + lo); 
      }

      // now generate the fibonacci numbers from 2 to the end number passed in by the user.
      for(int i = 2; i <= en; i++){
         // mark even numbers with *
         if(hi % 2 == 0){
            mark = " *";
         }
         else{
            mark = "";
         }

         // this is where the code differes the most. we still calculate the fibonacci numbers for the 
         // numbers out of the range passed in by the user, but we only print out numbers they want.
         if(i >= be){
            System.out.println(i + ": " + hi + mark);
         }
         
         // find  the next number in the Fibonacci sequence
         hi = lo + hi;  // new 'hi' is the sum of previous two numbers
         lo = hi - lo;  // new 'lo' is the previous 'hi'
      }
   }
 }

 class ImprovedFibonacci { // Problem 3
   // Maximum index for the Fibonacci sequence 
   static final int MAX_INDEX = 9;

   public static void main(String[] args) {
       // array to store FibonacciNumber objects
       FibonacciNumber[] numbers = new FibonacciNumber[MAX_INDEX];

       // Initialize the first two Fibonacci numbers
       numbers[0] = new FibonacciNumber(1);
       numbers[1] = new FibonacciNumber(1);

       // Generate and store the rest of the Fibonacci sequence in the numbers array we created
       for (int i = 2; i < MAX_INDEX; i++) {
           int fibValue = numbers[i - 1].getValue() + numbers[i - 2].getValue();
           
           // Create a new FibonacciNumber object with the calculated value using the constructor
           numbers[i] = new FibonacciNumber(fibValue);
           
           // Print the index and the Fibonacci number
           System.out.println((i + 1) + ": " + numbers[i]);
       }
   }
}