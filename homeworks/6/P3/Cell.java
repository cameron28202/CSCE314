
/* Cell.java skeleton written by Hyunyoung Lee
   For CSCE 314 [Sections 598, 599] Fall 2024, Assignment 6 Problem 3 

   Student Name: Cameron Stone
   UIN: 832007843
   Acknowledgements: N/a
*/

import java.lang.Iterable;
import java.util.Iterator;
import java.util.NoSuchElementException;

// class Cell: 10 points
// give correct class header - given in the problem statement
public final class Cell<E> implements Iterable<E>{ // modify this header
  // private fields
  private E elem; // stores a value of type E
  private Cell<E> next; // link to the next Cell

  // constructor. here we take in an element of type E and the next cell in the list.
  public Cell (E elem, Cell<E> next) {
		this.elem = elem;
    this.next = next;
	} 

  // iterator() returns a CellIterator object for this object. we define the celliterator class as an inner class below.
	@Override
  public CellIterator<E> iterator() {
    return new CellIterator<>(this);
	}

  // getter and setter methods for the private fields
  public E getVal() {
    return elem;
	} 
  public void setVal(E v) {
    this.elem = v;
	} 
  public Cell<E> getNext() {
    return next;
	} 
  public void setNext(Cell<E> node) {
		this.next = node;
  } 

  // CellIterator: 20 points
  // Having CellIterator as an inner class of Cell makes sense because we are defining the cell iterator only for
  // a cell. we will also be able to access cell's private variables!
  // (2 points) correct class header - given in the problem statement
  class CellIterator<E> implements Iterator<E>{
    private Cell<E> p; //cell of generic type E

    // (3 points) constructor
    public CellIterator (Cell<E> n) {
			this.p = n;
		}

    // hasnext function checks if the current node is null. this is done so that we don't miss the 
    // final node. hasnext checks if we are at a vlid node, next returns the current value and moves to the next node,
    // so by the time we check hasnext again, we are already at the next node. this is why we check the current node.
		@Override
    public boolean hasNext() {
      return p != null;
		} 

    // next function will use the previously defined hasNext function to see if the element has a next.
    // if so, we store the value, move the current node to the next node, and return the stored value.
    // if we do not have a next node then we return nosuchelementexception, as an iterator does
		@Override
    public E next() {
			if(hasNext()){
        E value = p.elem;
        p = p.next;
        return value;
      }
      throw new NoSuchElementException();
		}

  } // end of CellIterator
} // end of Cell




