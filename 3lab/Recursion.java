/*
 * Recursion.java
 *
 * [Noah Nsangou | nnn1]
 *
 * Starter code for the recursion lab. Each problem is solved recursively. 
 *
 */

public class Recursion {


    /*****  1  ***************************************************/

    /*
     * Return number of cannoballs in pyramid with the given height.
     * The runtime of this method is O(n) in all cases.
     * Pre: height is a non-null integer.
     * Post: returns a non-null integer. 
     */
    public static int countCannonballs(int height) {
	if (height == 0){
	    return 0;
	}
	else{
	    return height*height + (countCannonballs(height-1));
	}
    }

    
    /*****  2  ***************************************************/
    
    /*
     * Return true if str is a palindrome.
     * The runtime of this method is O(n^2)
     * Pre: String str is non-null. 
     * Post: returns a boolean value
     */
    public static boolean isPalindrome(String str) {
	if (str.length() == 1){
	    return true;
	}
	else{
	    if (str.charAt(0) == str.charAt((str.length()-1))){ //If first character is the same as last character,
		str = str.substring(1, (str.length()-1)); //Remove both the first and last character from the string.
		return isPalindrome(str);
	    }
	    else{
		return false;
	    }
	}	
    }

    /*****  3  ***************************************************/

    /*
     * Return true if str is a string of matched parens,
     * brackets, and braces. The runtime of this method 
     * is O(n). 
     * Pre: String str is non-null. 
     * Post: Returns a boolean value. 
     */
    public static boolean isBalanced(String str) {
	if (str.length() == 0){ //if string is empty
	    return true;
	}
	else if (((str.length())%2) != 0){ //if the length of the string is odd
	    return false;
	}
	else{
	    //If a pair of consecutive open/close symbols is found in the string, construct a new string excluding those characters.
	    if (str.contains("()")){
		int bunchIndex1 = str.indexOf("()");
		str = (str.substring(0, bunchIndex1) + str.substring((bunchIndex1 + 2), (str.length())));
		return isBalanced(str);
	    } else if (str.contains("[]")){
		int bunchIndex2 = str.indexOf("[]");
		str = (str.substring(0, bunchIndex2) + str.substring((bunchIndex2 + 2), (str.length())));
		return isBalanced(str);
	    } else if (str.contains("{}")){
		int bunchIndex3 = str.indexOf("{}");
		str = (str.substring(0, bunchIndex3) + str.substring((bunchIndex3 + 2), (str.length())));
		return isBalanced(str);
	    } else{
		return false;
	    }
	}
    }


    /*****  4  ***************************************************/

    /*
     * Print all substrings of str.  (Order does not matter.) The
     * runtime of this method is O(n^2).
     * Pre: Strings str and soFar are non-null. 
     * Post: Void return. 
     */
    public static void printSubstringsHelper(String str, String soFar) {
	if ( !str.isEmpty()){ 
	    printSubstringsHelper(str.substring(1), "");
	    soFar = soFar + str.charAt(0);
	    System.out.print(soFar + " ");  //Added space for ease of reading.
	    printSubstringsHelper(str.substring(1), soFar);
	}
    }
    
    public static void printSubstrings(String str){
	if (str == ""){
	    System.out.print("");
	} else{
	    printSubstringsHelper(str, "");
	}
	System.out.println(""); //For ease of reading results. 
    }
    
    /*****  5  ***************************************************/

    /*
     * Print number in binary. The runtime is O(n) in all cases.
     * Pre: Integer 'number' is non-negative. 
     */
    public static void printInBinary(int number) {
	if (number == 1){
	    System.out.print(1);
	} else if (number == 0){
	    System.out.println(0);
	}
	else{
	    int leastsig = number % 2; // mods number by 2 to obtain least significant digit 'leastsig'
	    number = number / 2;
	    printInBinary(number);
	    System.out.print(leastsig);
	}
    }
    
    
    /*****  6  ***************************************************/

    /*
     * Return whether a subset of the numbers in set 'nums' add up to sum 'targetSum',
     * and print them out.
     * The runtime is O(2^n).
     * Pre: Set 'nums' and integer 'targetSum' are both non-null.
     * Post: Returns a boolean value. 
     */
    public static boolean printSubSetSum(int nums[], 
					 int targetSum) {
	return printSubSetSumHelper(nums, targetSum, 0);
    }

    public static boolean printSubSetSumHelper(int nums[], int targetSum, int index){
	//index represents the index of the number that the method is currently examining.
	if (nums.length == index){ //if method has gone through entire set
	    return (targetSum == 0); //return whether or not the target sum was reached (boolean)
	} else{
	    if  (printSubSetSumHelper(nums, targetSum - nums[index], index + 1)){
		System.out.print(nums[index] + " ");
		return true;
	    }
	    return printSubSetSumHelper(nums, targetSum, index + 1);
	}
    }
    
    
    /*
     * Return the number of different ways elements in nums can be
     * added together to equal sum. The runtime is O(2^n). 
     * Pre: Set 'nums' and integer 'targetSum' are both non-null.
     * Post: Returns a non-negative integer. 
     */
    public static int countSubSetSumSolutions(int nums[], 
					      int targetSum) {
	return countSubSetSumSolutionsHelper(nums, targetSum, 0);
    }
    
    public static int countSubSetSumSolutionsHelper(int nums[], int targetSum, int index){
	if (nums.length == index){
	    if (targetSum == 0){
		return 1; //to be added to and subsequently returned as the total number of successful combinations 
	    }
	    return 0;   
	}else{
	    return countSubSetSumSolutionsHelper(nums, targetSum - nums[index], index + 1) + countSubSetSumSolutionsHelper(nums, targetSum, index + 1);
	}
    }

    /**************************************************************/
    
    /*
     * Add testing code to main to demonstrate that each of your 
     * recursive methods works properly.
     */
    public static void main(String args[]) {

	// test code for problem 1
	System.out.println("#1");
	System.out.println(countCannonballs(3));
	System.out.println(countCannonballs(10));

	// test code for problem 2
	System.out.println("#2");
	System.out.println(isPalindrome("mom"));
	System.out.println(isPalindrome("[(())]"));

	// test code for problem 3
	System.out.println("#3");
	System.out.println(isBalanced("[{()}]"));
	System.out.println(isBalanced("[()]()]"));
	System.out.println(isBalanced("[((]"));

	// test code for problem 4
	System.out.println("#4");
	printSubstrings("bcd");
	
	// test code for problem 5
	System.out.println("#5");
	printInBinary(0);

	// test code for problem 6
	System.out.println("#6a");
	int[] set = {3, 4, 9, 6, 1};
	printSubSetSum(set, 7);
	System.out.println("");
	System.out.println("#6b");
	int[] set2 = {3, 4, 9, 6, 1};
	System.out.println(countSubSetSumSolutions(set2, 7));
	System.out.println("end");
    }
}
