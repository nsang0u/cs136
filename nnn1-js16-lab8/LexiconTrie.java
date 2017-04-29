/*
  Noah Nsangou & Jae Mahn Surh
  Thursday Afternoon
  
  A LexiconTrie class that allows the user to add words to the trie and is able to do a list of operations: removing words from the trie, returning whether the trie contains a certain word or prefix, suggest word corrections, and is able to match regular expressions.
  
  In our implementation of the LexiconNode, we constructed each node with a vector of 26 empty spots. Because each node has 26 spots, there is not much of a difference between our implementation and an OrderVector, as both have great amount of empty spots. However, the difference lies in the fact that we could change the nodes to start with an empty node and continually get bigger with every addition. We decided against it, for searching for a child would have gotten much longer than the constant time that we have. Because our trie is very similar to an OrderedVector, there is not much of a difference. Both our trie and an OrderedVector are able to find their children in constant time.


 */


import structure5.*;
import java.util.Iterator;
import java.util.Scanner;
import java.io.File;
import java.util.HashSet;

public class LexiconTrie implements Lexicon {

    public LexiconNode root;

    public void LexiconTrie(){} 

    public int wordCount = 0;
    
    public boolean addWord(String word) {
	if (containsWord(word)){
	    return false;
	} else if (word.isEmpty()){
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
	
    
    public Vector<String> suggestCorrections(String target, int maxDistance) {
	Vector<String> correctVect = new Vector<String>();
	String buildString = "";
	for (int i = 0; i < 26; i++){
	    if (root.childVect.get(i) != null){
		System.out.println(maxDistance);
		suggestCorrectionsHelper(target, maxDistance, buildString, root.childVect.get(i), correctVect);
	    }
	}
	System.out.println(correctVect);
	return correctVect;
    }
    
    public void suggestCorrectionsHelper(String target, int maxDistance, String buildString, LexiconNode current, Vector<String> buildVect){
	if (target.length() == 1) {
	    if ((maxDistance > 0) && (current.isWord)){
		buildString = buildString + current.letter();
		buildVect.add(buildString);
	    }
	    return;
	} else if ((maxDistance < 0) && (target.length() != 0)){
	    return;
	} else if (current.letter() == target.charAt(0)){
	    buildString = buildString + current.letter();
	} else if (current.letter() != target.charAt(0)){
	    maxDistance--;
	    buildString = buildString + current.letter();
	}
	for (int i = 0; i < 26; i++){
	    if (current.childVect.get(i) != null){
		suggestCorrectionsHelper(target.substring(1), maxDistance, buildString, current.childVect.get(i), buildVect);
		
	    }
	}
    }
    
       
    public HashSet<String> matchRegex(String pattern){
	String buildString = "";
	HashSet<String> buildSet = new HashSet<String>(); 
	matchRegexHelper(pattern, buildString, root, buildSet);
	return buildSet;
    }

    public void matchRegexHelper(String pattern, String buildString, LexiconNode current, HashSet<String> buildSet){
	if (pattern.length() == 0){
	    if (current.isWord){
		buildSet.add(buildString);
	    }
	} else if (pattern.charAt(0) == '*'){
	    for( int i = 0; i < 26; i++){
		if (current.childVect.get(i) != null){
		    if (current.childVect.get(i).numChild() == 0) {
			buildString = stringHolder + current.childVect.get(i).letter();
			buildSet.add(buildString);
		    }else{
			buildString = stringHolder + current.childVect.get(i).letter();
			matchRegexHelper(pattern, buildString, current.childVect.get(i), buildSet);
		    }
		}
	    } 
	    matchRegexHelper(pattern.substring(1), buildString, current, buildSet);
	} else if (pattern.charAt(0) == '?'){
	    for (int i = 0; i < 26; i++){
		if (current.childVect.get(i) != null) {
		    matchRegexHelper(pattern.substring(1), buildString + current.childVect.get(i).letter(), current.childVect.get(i), buildSet);
		}
	    }
	    matchRegexHelper(pattern.substring(1), buildString, current, buildSet);
	}
	else {
	    if (current.getChild(pattern.charAt(0)) != null){ //case for if regex char == current char
		buildString = buildString + pattern.charAt(0);
		if (pattern.length() == 1){
		    matchRegexHelper("", buildString, current.getChild(pattern.charAt(0)), buildSet);
		} else {
		    matchRegexHelper(pattern.substring(1), buildString, current.getChild(pattern.charAt(0)), buildSet);
		}
	    }
	}
    }
}
