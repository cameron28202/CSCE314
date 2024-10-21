
/* CSCE 314 [Sections 598, 599] Programming Languages, Fall 2024
   Homework Assignment 5 
   Skeleton for Problems 2 & 3
   Written by Hyunyoung Lee for CSCE 314 Students

   Student Name: Cameron Stone 
   Student UIN: 832007843
   Acknowledgements: N/a
*/

class FibonacciNumber {
    private int value;
    private boolean isEven;

    public FibonacciNumber(int value) {
        this.value = value;
        this.isEven = (value % 2 == 0);
    }
    

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

    @Override
    public String toString() {
        return value + (isEven ? " *" : "");
    }

}

class SubsetOutputFib {
    public static void main(String[] args) {
         if(args.length != 2){
             System.out.println("Please enter 2 integers.");
             return;
         }
 
         int be = Integer.parseInt(args[0]);
         int en = Integer.parseInt(args[1]);
 
         if(be < 0 || en < 0){
             // ensure input is positive
             be = Math.abs(be);
             en = Math.abs(en);
             System.out.println("You entered a negative integer. Input has been corrected by taking the absolute value of input.");
         }
 
         if(be > en){
             // swap numbers
             int temp = be;
             be = en;
             en = temp;
             System.out.println("Your value for the first integer is greater than the second integer. Input has been corrected by swapping the inputs.");
         }
 
         int lo = 1;
         int hi = 1;
         String mark;
 
         if(be == 1){
             System.out.println("1: " + lo);
         }
         for(int i = 2; i <= en; i++){
             if(hi % 2 == 0){
                 mark = " *";
             }
             else{
                 mark = "";
             }
             if(i >= be){
                 System.out.println(i + ": " + hi + mark);
             }
             hi = lo + hi;
             lo = hi - lo;
         }
     }
 }

 class ImprovedFibonacci { // Problem 3
    static final int MAX_INDEX = 9;

    public static void main(String[] args) {
        FibonacciNumber[] numbers = new FibonacciNumber[MAX_INDEX];

        numbers[0] = new FibonacciNumber(1);
        numbers[1] = new FibonacciNumber(1);

        for (int i = 2; i < MAX_INDEX; i++) {
            int fibValue = numbers[i - 1].getValue() + numbers[i - 2].getValue();
            numbers[i] = new FibonacciNumber(fibValue);
            System.out.println((i + 1) + ": " + numbers[i]);
        }
    }
}