import structure5.*;

/* Noah Nsangou */

public class SubsetIterator<E> implements Iterator<E>{
    private Vector<Integer> vect = new Vector<Integer>();
    
    public SubsetIterator(Iterator<E> iter, int M){
	while (iter.hasNext()){

	    bitNum = M & 1;
	    if (bitNum == 1){
		vect.add(iter.get());
		    
	    }
	}
	
    }
    
    public E next(){
	return 
    }
    
    public void remove(){
	
    }

    
