import structure5.*;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;

public class LexiconTrie implements Lexicon {

    public LexiconNode root;

    public void LexiconTrie(){} 

    public int wordCount = 0;
    
    public boolean addWord(String word) {
	if (containsWord(word)){
	    //System.out.println("contains word");
	    return false;
	} else if (word.isEmpty()){
	    //System.out.println("is empty");
	    return false;
	}
	wordCount++;
	return addWordHelper(word, root);
    }

    public boolean addWordHelper(String word, LexiconNode current){
	if (word.isEmpty()){
	    current.isWord();
	    return true;
	} else if (current.getChild(word.charAt(0)) == null){
	    current.addChild(new LexiconNode(word.charAt(0), false));
	    
	}
	if (word.length() == 1){
	    Character tempChar = word.charAt(0);
	    word = "";
	    return addWordHelper(word, current.getChild(tempChar));

	} else{
	    return addWordHelper(word.substring(1), current.getChild(word.charAt(0)));
	}    
    }
	
    public int addWordsFromFile(String filename) {
	int addCount = 0;
	Scanner scan = new Scanner(System.in);
	while (scan.hasNext()){
	    addWord(scan.next());
	}
	return addCount;
    }
    
    
    public boolean removeWord(String word) {
	if (!(containsWord(word))){
	    return false;
	} else{
	    wordCount--;
	    return removeWordHelper(word, root.getChild(word.charAt(0)));
	}
    }
    
    public boolean removeWordHelper(String word, LexiconNode current){
	LexiconNode child = current.getChild(word.charAt(1));
	if(child.isWord && word.length() == 2){
	    child.isWord = false;
	    if (child.numChild() == 0) {
		current.removeChild(word.charAt(1));
	    }
	    return true;
	}else if (removeWordHelper(word.substring(1), child) && !(child.isWord) && (child.numChild() == 0)){
	    current.removeChild(child.letter());
	    return true;
	}
	return false;
    }
	
    public int numWords() { return wordCount; }
    
    public boolean containsWord(String word){
	return containsWordHelper(word, root);
    }

    public boolean containsWordHelper(String word, LexiconNode current){
	if (word.isEmpty()){
	    return current.isWord; // If at end of word, returns true to contain iff the current node is in fact the end of a word. 
	} else if(current.getChild(word.charAt(0)) == null){
	    return false;
	}
	if (word.length() == 1){
	    //System.out.println("got to word length is 1");
	    Character tempChar = word.charAt(0);
	    word = "";
	    return containsWordHelper(word, current.getChild(tempChar));

	} else{
	    return containsWordHelper(word.substring(1), current.getChild(word.charAt(0)));
	}    

    }
    
    public boolean containsPrefix(String prefix){
	return containsPrefixHelper(prefix, root);
    }

    public boolean containsPrefixHelper(String prefix, LexiconNode current){
	if (prefix.isEmpty()) {
	    return current.hasChild();
	} else if(current.getChild(prefix.charAt(0)) == null) {
	    return false;
	}
	if (prefix.length() == 1){
	    Character tempChar = prefix.charAt(0);
	    prefix = "";
	    return containsPrefixHelper(prefix, current.getChild(tempChar));
	    
	} else{
	    return containsPrefixHelper(prefix.substring(1), current.getChild(prefix.charAt(0)));
	}
    }      
    
    public Iterator<String> iterator() {
	Vector<String> vect = new Vector<String>();
	iteratorHelper(vect, root, "");
	return vect.iterator();
    }

    public void iteratorHelper(Vector<String> vect, LexiconNode current, String s){
	//s = s + current.letter();
	if (current.numChild() == 0){
	    s = s + current.letter();
	    vect.add(s);
	    return;
	} else if (current.isWord){
	    s = s + current.letter();
	    vect.add(s);	    
	} else if (current.compareTo(root) != 0){
		s = s + current.letter();
	}
	for (int i = 0; i < 26; i++){
	    if(current.childVect.get(i) != null){
		iteratorHelper(vect, (current.childVect.get(i)), s);
	    }
	}
    }
	
    
    public Set<String> suggestCorrections(String target, int maxDistance) { return null; }
    
    public Set<String> matchRegex(String pattern){ return null; }
    
    
    
    public static void main(String args[]){
	LexiconTrie test = new LexiconTrie();
	test.root = new LexiconNode('0', false);
	test.addWord("are");
	test.addWord("a");
	test.addWord("as");
	test.addWord("ask");
	System.out.println(test.containsWord("are"));
	System.out.println(test.containsWord("aztec"));
	(test.root).print();

	//test.addWordsFromFile("/Network/Servers/fuji.cs.williams.edu/Volumes/Users2/20nnn1/cs/cs136/nnn1-js16-lab8/smallIn.txt");

	System.out.println(test.wordCount);
	//test.removeWord("are");
	//test.removeWord("as");
	System.out.println("\n" + test.containsWord("are"));
	System.out.println(test.containsWord("as"));
	System.out.println(test.containsWord("ask"));
	System.out.println(test.wordCount);

	//Iterator<String> iterTest = new Iterator<String>();
	Iterator<String> iterTest = test.iterator();
	while (iterTest.hasNext()){
	    System.out.println(iterTest.next());
	}
    }
}
