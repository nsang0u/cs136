import structure5.*;
import java.lang.Math.*;
/* Noah Nsangou */

public class SubsetIterator<E> implements Iterator<E>{
    private Vector<Integer> eVect = new Vector<Integer>();
    private int current = 0;
    
    // Pre: int M is
    // M is not just n in binary and it may never be. M is one number along 0*n to 1*n binary numbers.
    
    public SubsetIterator(Iterator<E> iter, int M){
	while (iter.hasNext()){
	    bitNum = M & 1;
	    if (bitNum == 1){
		vect.add(Math.sqrt(iter.get())); //adding sqrt of height to vector eVect
	    }
	    iter.next();
	    M = M >> 1;
	}
    }
    
    // Returns next element and increments the iterator.
    // This refers to SubsetIterator's own vector, which contains
    // -the elements present according to the binary representation.  
    public E next(){
	E temp = vect.get(current);
	current = current + 1;
	return temp;
    }
    
    // Does nothing
    public void remove(){
    }

    
