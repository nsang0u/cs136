import java.util.*j;
import structure5.*;
/* Noah Nsangou */

public class Interpreter{

    public Interpreter(){
	Stack<Token> valueStack = new Stack<Token>();
    }

    
    public void interpret(Reader read){
	//read in tokens from reader:
	//pushing numbers & symbols (like pi etc) to stack
	// carrying out operations


	
	//Reader read = new Reader();
	Token t;
	while (read.hasNext()){
	    t = (Token)read.next();
	}
	popopopooooppooop
	/*switch (t) {
	case 'num':
	    if (t.isNumber()){
		valueStack.add(t);
		
	    }
	}
	*/
    }
    




    public static void main{
	Reader read = new Reader();
	interpret(read);
	
    }

    
}
