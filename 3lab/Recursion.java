/*
 * Recursion.java
 *
 * [Insert Your Name, etc. Here]
 *
 * Starter code for the recursion lab.
 *
 */

public class Recursion {


    /*****  1  ***************************************************/

    /*
     * Return number of cannoballs in pyramid with the given height.
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
     */
    public static boolean isPalindrome(String str) {
	if (str.length() == 1){
	    return true;
	}
	else{
	    if (str.charAt(0) == str.charAt((str.length()-1))){
		str = str.substring(1, (str.length()-1));
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
     * brackets, and braces.
     */
    public static boolean isBalanced(String str) {
	if (str.length() == 0){
	    return true;
	}
	//else if (isPalindrome(str)){
	//    return true;
	//}
	else if (((str.length())%2) != 0){
	    return false;
	}
	else{
	    if (str.contains("()")){
		int bunchIndex1 = str.indexOf("()");
		str = (str.substring(0, bunchIndex1) + str.substring((bunchIndex1 + 2), (str.length())));
		//System.out.println(str+"1");
		return isBalanced(str);
	    } else if (str.contains("[]")){
		int bunchIndex2 = str.indexOf("[]");
		str = (str.substring(0, bunchIndex2) + str.substring((bunchIndex2 + 2), (str.length())));
		//System.out.println(str+"2");
		return isBalanced(str);
	    } else if (str.contains("{}")){
		int bunchIndex3 = str.indexOf("{}");
		str = (str.substring(0, bunchIndex3) + str.substring((bunchIndex3 + 2), (str.length())));
		//System.out.println(str+"3");
		return isBalanced(str);
	    } else{
		//System.out.print("got here");
		return false;
	    }
	}
    }


    /*****  4  ***************************************************/

    /*
     * Print all substrings of str.  (Order does not matter.)
     */
    public static void printSubstringsHelper(String str, String soFar) {
	if ( !str.isEmpty()){ 
	    printSubstringsHelper(str.substring(1), "");
	    soFar = soFar + str.charAt(0);
	    System.out.println(soFar); 
	    printSubstringsHelper(str.substring(1), soFar);
	}
	
	
    }
    
    public static void printSubstrings(String str){
	if (str == ""){
	    System.out.print("");
	} else{
	    printSubstringsHelper(str, "");
	}
    }
    
    /*****  5  ***************************************************/

    /*
     * Print number in binary
     */
    public static void printInBinary(int number) {
	if (number == 1){
	    System.out.print(1);
	} else if (number == 0){
	    System.out.println(0);
	}
	else{
	    int leastsig = number % 2;
	    number = number / 2;
	    printInBinary(number);
	    System.out.print(leastsig);
	}
    }
    
    
    /*****  6  ***************************************************/

    /*
     * Return whether a subset of the numbers in nums add up to sum,
     * and print them out.
     */
    public static boolean printSubSetSum(int nums[], 
					 int targetSum) {
	return printSubSetSumHelper(nums, targetSum, 0);
    }

    public static boolean printSubSetSumHelper(int nums[], int targetSum, int index){
	if (nums.length == index){
	    System.out.print(nums[index]);
	    return (targetSum == 0);
	} else{
	    return printSubSetSumHelper(nums, targetSum - nums[index], index + 1) ||
		printSubSetSumHelper(nums, targetSum, index + 1);
	}
    }


    /*
     * Return the number of different ways elements in nums can be
     * added together to equal sum.
     */
    public static int countSubSetSumSolutions(int nums[], 
					      int targetSum) {
	return 0;
    }



    /*****  7  ***************************************************/
    
    public static void listCompletions(String digitSequence, 
				       Lexicon lex) {
    
    }

    /**************************************************************/
    
    /*
     * Add testing code to main to demonstrate that each of your 
     * recursive methods works properly.
     */
    public static void main(String args[]) {

	// test code for problem 1
	System.out.println(countCannonballs(3));
	System.out.println(countCannonballs(10));

	// test code for problem 2
	System.out.println(isPalindrome("mom"));
	System.out.println("!!" + isPalindrome("[(())]"));

	// test code for problem 3
	System.out.println(isBalanced("[{()}]"));
	System.out.println(isBalanced("[()]()]"));
	System.out.println(isBalanced("[((]"));

	// test code for problem 4
	printSubstrings("bcd");
	// test code for problem 5
	printInBinary(0);

	// test code for problem 6
    }
}
