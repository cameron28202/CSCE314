
/* Written by Hyunyoung Lee for CSCE 314 Students Homework 7 Problem 2 

   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: N/a
*/

import java.util.*;

public class Market<T> {
  List<T> stock; // stock of the market

  // stock list, we set this list to be a linkedlist of type T in the constructor
  public Market() { stock = new java.util.LinkedList<T>(); }

  // simple sell method. add the item to the linkedlist using the built in function
  void sell(T item) {
    stock.add(item);
  }

  // simple buy method. if we have anything in stock, remove the first item 
  // from the stock to give it to the buyer. return this item

  public T buy() {
    if(stock.isEmpty()){
      return null;
    }
    return stock.remove(0);
  }

  // list sell method. here we take a collection of type ? extends T so we can be flexible on both
  // the type of list and the type of items in the list. e.g. a list of type Fruit can hold Apple because
  // apple extends fruit. loop through this collection and add each item to the stock. Here we can safely
  // assign each item to type T because every type in the collection extends T. 
  void sell(Collection<? extends T> items) {
    for(T item : items){
      stock.add(item);
    }
  }

  // list buy method. here we accept a collection of type ? super T because we need to make sure
  // the list is filled with the superclass of T. for example we don't want their items list to be
  // of type apple because that is too specific and unsafe. our stock could have apples, but also fruits and galas.
  // we simply loop through the list and add n items from our stock to their items list, given we have the inventory.
  void buy(int n, Collection<? super T> items) {
    if(n > stock.size()){
      n = stock.size();
    }
    for(int i = 0; i < n; i++){
      items.add(stock.remove(0));
    }
  }
} // end of class Market


// Study class Main. You don't need to modify class Main
class Main {
  // three static nested classes expressing example subclass hierarchy
  // Gala <: Apple <: Fruit
  static class Fruit { public String toString () { return "Fruit"; } }
  static class Apple extends Fruit {
                       public String toString () { return "Apple"; }
  }
  static class Gala extends Apple {
                       public String toString () { return "Gala"; }
  }

  public static void main(String args[]) {
    Market<Fruit> farmersmarket = new Market<Fruit> ();

    Deque<Fruit> fruits = new ArrayDeque<Fruit>();
    fruits.addFirst(new Gala());
    fruits.addFirst(new Apple());
    //Fruit a = fruits.remove();
    //if (a instanceof Apple) System.out.println("a is Apple");

    Vector<Apple> apples = new Vector<Apple>();
    apples.addElement(new Apple());
    apples.addElement(new Apple());
    apples.addElement(new Gala());

    farmersmarket.sell(fruits);
    farmersmarket.sell(apples);
    farmersmarket.sell(new Fruit());
    farmersmarket.sell(new Gala());

    ArrayList<Fruit> mybasket = new ArrayList<Fruit>();

    farmersmarket.buy(6, mybasket);

    // print out what you bought
    System.out.println("Here's what I bought");
    for (Fruit e : mybasket) System.out.println(e);
    System.out.println("Enjoy!");
  } // end of main
} // end of class Main

