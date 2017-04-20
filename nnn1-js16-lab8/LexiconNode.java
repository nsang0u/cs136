import structure5.*;
import java.util.Iterator;

class LexiconNode implements Comparable<LexiconNode> {
    char letter;
    boolean isWord;
   
    /* TODO: a data structure for keeping track of the children of
	   this LexiconNode */
    Vector<LexiconNode> childVect;

    /**
	 * TODO: Constructor
	 */
    LexiconNode(char letter, boolean isWord) {
	childVect = new Vector<LexiconNode>(26); // Initial capacity 0, grows by 26. Allows leaves to take up no space with their childVects as they have no children. 
	this.letter = letter;
	this.isWord = isWord;
    }

    /**
	 * TODO: Compare this LexiconNode to another.
	 * (You should just compare the characters stored at the Lexicon Nodes.)
     */
    public int compareTo(LexiconNode o) {
	return ((Character)letter).compareTo((Character)o.letter());
    } 
    
    /**
	 * TODO: Return letter stored by this LexiconNode 
	 */
    public char letter() {
       return letter;
    }

    /**
	 * TODO: Add LexiconNode child to correct position in child data structure
	 */
    public void addChild(LexiconNode ln) {
	//if (childVect.capacity() == 0){ 
	//    childVect.add(null);
	//}
	childVect.add((Character.getNumericValue(ln.letter()) - 10), ln);
    }
    
    /**
	 * TODO: Get LexiconNode child for 'ch' out of child data structure 
	 */
    public LexiconNode getChild(char ch) {
	
	return childVect.get(Character.getNumericValue(ch) - 10);
    }
    
    /**
	 * Remove LexiconNode child for 'ch' from child data structure
	 */
    public void removeChild(char ch) {
	childVect.remove(Character.getNumericValue(ch) - 10); //10 because -9 in unicode and then -1 for vector index
    }

    /**
	 * TODO: create an Iterator that iterates over children in alphabetical order
	 */
    //public Iterator<LexiconNode> iterator() {
	
	
    //}

    public void print(){
	//System.out.println(childVect);
	//System.out.println(isWord);
	//System.out.println();

	for (int i = 0; i < 26; i++){
	    System.out.print(childVect.get(i) + " ");
	}
    }

    public boolean hasChild(){
	return (!(childVect.isEmpty()));
    }
    
    public void isWord(){
	isWord = true;
    }
    
    public static void main(String[] args){
	LexiconNode test = new LexiconNode('a', false);
	LexiconNode testChild = new LexiconNode('c', true);
	test.addChild(testChild);
	test.print();
    }
	
}
