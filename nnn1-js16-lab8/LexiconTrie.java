import structure5.*;
import java.util.Iterator;

public class LexiconTrie implements Lexicon {

    public LexiconNode root;

    public void LexiconTrie(){} 
    
    public boolean addWord(String word) {
	if (containsWord(word)){
	    return false;
	} else if (word.isEmpty()){
	    return false;
	}
    }

    public boolean addWordHelper(String word, LexiconNode current){
	if (word.isEmpty()){
	    current.isWord();
	    return true;
	} else if (current.getChild(word.charAt(0)) == null){
	    current.addChild(new LexiconNode(word.charAt(0), false));
	}
	addWordHelper(word.substring(1), current.getChild(word.charAt(0)));
    }
	
    public int addWordsFromFile(String filename) {
    }

    public boolean removeWord(String word) { return true; }

    public int numWords() { return 0; }
    
    public boolean containsWord(String word){
	return containsWordHelper(word, root);
    }

    public boolean containsWordHelper(String word, LexiconNode current){
	if (word.isEmpty()){
	    return true;
	} else if(current.getChild(word.charAt(0)) == null){
	    return false;
	}
	containsWordHelper(word.substring(1),current.getChild(word.charAt(0)));

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
	containsPrefixHelper(prefix.substring(1), current.getChild(prefix.charAt(0)));
    }      
    
    public Iterator<String> iterator() { return null; }
    
    public Set<String> suggestCorrections(String target, int maxDistance) { return null; }
    
    public Set<String> matchRegex(String pattern){ return null; }
}
