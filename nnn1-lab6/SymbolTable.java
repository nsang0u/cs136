// A simple symbol table for a postscript interpreter.
// (c) 2001,1996, 2001 duane a. bailey

import structure5.*;
import java.util.Iterator;
/**
 * A simple table of symbols for a postscript interpreter.
 * This particular implementation is very expensive, and we'll see
 * improved implementations in the latter part of the semester.
 * @author duane a. bailey
 */
public class SymbolTable
{
    /**
     * A list of string-value associations; representation of a
     * "symbol table".
     */
        private List<Association<String,ing, table.
	         */
	    public static void main(String args[])
    {
	SymbolTable table = new SymbolTable();
	// sometime later:
	table.add("pi",new Token(3.141592653));
	// sometime even later:
	if (table.contains("pi"))
	    {
		Token token = table.get("pi");
		System.out.println(token.getNumber());
	    }
    }
}
