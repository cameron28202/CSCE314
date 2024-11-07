
/*   
   CellList.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 4

   Student Name:
   UIN:
   Acknowledgements:
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

// Total 40 points for the CellList class

public class CellList<E> implements Iterable<E>, Cloneable, Comparable<CellList<E>> {   
  private Cell<E> n;
  private int length;

  @Override
  public Iterator<E> iterator() { return n.iterator(); }

  /*

    here we override the object.clone function. first we create an empty list
    with the no args celllist constructor. we then check to see if the head of the 
    linked list is null, if so we just return an empty list. otherwise, we now
    have to push the items in the list in reverse order. this is because the push
    method works like the cons operator in haskell, by adding each element to the
    front. we have to first create an array of objects and add the items in reverse
    order. we can then use the push function we define later in this class,
    then return the cloned list.

   */
  @Override
  public CellList<E> clone() {
    CellList<E> clonedList = new CellList<>();

    if(this.n == null){
      return clonedList;
    }

    Object[] elements = new Object[this.length];
    int i = this.length - 1;
    
    for (E item : n) {
        elements[i] = item;
        i--;
    }

    for(Object item : elements){
      clonedList.push((E)item);
    }
    return clonedList;
	}


  // this code was given, we are comparing the list based on length
	@Override
  public int compareTo(CellList<E> list) { 
    if (this.length < list.length) return -1;
    if (this.length == list.length) return 0;
    return 1; 
  }

  // Task 2: override equals() (10 points) 
  // function takes another object and returns a boolean
  @Override
  public boolean equals(Object o) {
    // return true if you are comparing with this
    if (this == o) return true;
    
    // return false if they are not both instances of celllist
    if (getClass() != o.getClass()) return false;
    
    //cast o to a cell list (any type)
    CellList<?> other = (CellList<?>) o;
    
    // check lengths using later defined hashcode function
    if (this.hashCode() != other.hashCode()) return false;
    
    // create arrays with appropriate lengths. this will be filled 
    // from the linkedlists.
    Object[] arr1 = new Object[this.length];
    Object[] arr2 = new Object[other.length];
    
    int i = 0;
    for(E item : this) {
      arr1[i] = item;
      i++;
    }
    
    i = 0;
    for(Object item : other) {
      arr2[i] = item;
      i++;
    }
    
    // we created those arrays so we can sort them, and see if they are
    // equal using the Arrays sort and equals function.
    Arrays.sort(arr1);
    Arrays.sort(arr2);
    return Arrays.equals(arr1, arr2);
  }
  
  @Override
  public int hashCode() {
      return length;
  }

  // no-arg constructor - given
  public CellList() { 
    this.n = null;
    this.length = 0; 
  }
    
  // Task 3: one-arg constructor (5 points)
  public CellList(Iterable<E> iterable) {
    // set n to null, like the no args constructor
    this.n = null;

    this.length = 0; // set our length to be 0 initially
    
    if(iterable == null){
      return; // can't do anything with a null list
    }

    //temp arraylist to store the items in iterable
    ArrayList<E> items = new ArrayList<E>();
    for(E item : iterable){
      items.add(item);
    }

    // now add these items (in reverse order, because push adds to the front)
    for(int i = items.size() - 1; i >= 0; i--){
      push(items.get(i));
    }
    //don't nede to update length because the push function does it for us
	}

				
  // Task 4: total 20 points for toString(), push() and pop()
  // 8 points

  //tostring takes no parameters and returns a string
  public String toString() {

    //say empty linked list if the list is empty
    if(n == null){
      return "[(head: )]";
    }

    //print the first value
		String ans = "[(head: " + n.getVal() + ") ->";

    // store the iterator so we can loop through
    Iterator<E> iterator = n.iterator();

    //skip first value, we already printed head
    iterator.next();

    // while the iterator has a next value ...
    while(iterator.hasNext()){

      // (val)
      ans += " (" + iterator.next() + ")";

      // if there is a next, add an arrow. else, add a closing bracket
      if(iterator.hasNext()){
        ans += " ->";
      }
      else{
        ans += "]";
      }
    }

    //return formatted string!
    return ans;
  }

  public void push(E item) {
    //first, store the current head in a variable called next
    Cell<E> next = n;
    // now, create a new cell that will serve as the list's new head.
    // use the two args cell constructor, that takes in an item of type
    // E as ewll as the next cell.
    Cell<E> newHead = new Cell<E>(item, next);
    // set the new head to this new cell we just created
    n = newHead;
    // increment the length
    length++;
	}


  //pop takes no arguments and returns the element removed
  public E pop() {
    // first, we store the vaue of the current head
    E value = n.getVal();
    // we then more the head to the next node
    n = n.getNext();
    //decrement length
    length--; 
    //return the value we just poppped
    return value;
	}

  // given 
  public E peek() { return n.getVal(); }

  // given 
  public int getLength() { return length; }
}

