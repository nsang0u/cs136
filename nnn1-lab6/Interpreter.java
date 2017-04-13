import structure5.*;
/* Noah Nsangou

10.3: Create 2 additional stacks, one being an intermediate. pop items from original stack to intermediate, and popback. 

10.4: 1 additional stack, pop onto it. 

10.5: New queue, no intermediate, simply enque off of original que. 

 */

public class Interpreter{
    StackVector<Token> valueStack;
    SymbolTable symTable;
    public Interpreter(){
	valueStack = new StackVector<Token>();
	symTable = new SymbolTable();
    }
    
    // Pre: reader read is a valid reader instance
    public void interpret(Reader read){
 
	Token t;
	while (read.hasNext()){
	    t = (Token)read.next();
	    
	    if (t.isNumber()){
		valueStack.push(t);
	    } else if (t.isSymbol()){
		if (t.getSymbol() == "quit"){ return ; }
		switch (t.getSymbol()) {
		case "add":
		    add();
		    break;
		case "pstack":
		    pstack();
		    break;
		case "mul":
		    mul();
		    break;
		case "sub":
		    sub();
		    break;
		case "div":
		    div();
		    break;
		case "dup":
		    dup();
		    break;
		case "exch":
		    exch();
		    break;
		case "eq":
		    eq();
		    break;
		case "ne":
		    ne();
		    break;
		case "ptable":
		    ptable();
		    break;
		case "pop":
		    pop();
		    break;
		    
		}
		
		if (symTable.contains(t.getSymbol())){
		    replaceSym(t);
		   
		} else if ((t.getSymbol()).startsWith("/")){
		    if(!(symTable.contains((t.getSymbol()).substring(1)))){
			def(t.getSymbol(), (Token)read.next());
			read.next(); //skipping "def" token
		    } else {System.out.println("Symbol already defined");}
		}	
	    }
	}
    }
  
    public void pstack(){
	// Post: prints contents of stack in order	
	// Not working: System.out.println(valueStack.toString());
	StackVector<Token> tempStack = new StackVector<Token>(); 
	int size = valueStack.size();
	for (int i = 0; i < size; i++){
	    tempStack.push(valueStack.pop());
	}
	while (!(tempStack.empty())){
	    System.out.print(tempStack.peek() + " ");
	    valueStack.push(tempStack.pop());
	}
    }

    // Pre: valueStack has size 2; both tokens are numbers
    // Post: valueStack has size 1; present token is a number
    public void add(){
	Assert.condition((valueStack.size() == 2), "Valuestack size is not 2.");
	Token addendTok1 = valueStack.pop();
	Token addendTok2 = valueStack.pop();
	double addendDub1 = addendTok1.getNumber();
	double addendDub2 = addendTok2.getNumber();
	double sumTemp = addendDub1 + addendDub2;
	Token sumTempTok = new Token(sumTemp); 
	valueStack.push(sumTempTok); //pushing tokenized sum
	Assert.condition((valueStack.peek()).isNumber(), "Output is not a number");
    }

    public void mul(){
	// Pre: valueStack has size 2; both tokens are numbers
	// Post: valueStack has size 1; pushed token is a number
	Assert.condition((valueStack.size() == 2), "Valuestack size is not 2.");
	Token numTok1 = valueStack.pop();
	Token numTok2 = valueStack.pop();
	double dub1 = numTok1.getNumber();
	double dub2 = numTok2.getNumber();
	double productTemp = dub1 * dub2;
	Token productTempTok = new Token(productTemp);
	valueStack.push(productTempTok);
	Assert.condition((valueStack.peek()).isNumber(), "Output is not a number");
    }
    
    public void sub(){
	// Pre: valueStack has size 2; both tokens are numbers
	// Post: valueStack has size 1; pushed token is a number
	Assert.condition((valueStack.size() == 2), "Valuestack size is not 2.");
	Token subTok = valueStack.pop(); // sub for subtrahend (to be subtracted)
	Token minTok = valueStack.pop(); // min for minuend (to be subtracted from)
	double subDub = subTok.getNumber();
	double minDub = minTok.getNumber();
	double diffTemp = minDub - subDub;
	Token diffTempTok = new Token(diffTemp);
	valueStack.push(diffTempTok);
    }

    public void div(){
	// Pre: valueStack has size 2; both tokens are numbers
	// Post: valueStack has size 1; pushed token is a number
	Assert.condition((valueStack.size() == 2), "Valuestack size is not 2.");
	Token denomTok = valueStack.pop();
	Token numerTok = valueStack.pop();
	double denomDub = denomTok.getNumber();
	double numerDub = numerTok.getNumber();
	double quotTemp = numerDub / denomDub;
	Token quotTempTok = new Token(quotTemp);
	valueStack.push(quotTempTok);
    }

    public void dup(){
	Assert.condition((valueStack.size() == 1), "Valuestack size is " + valueStack.size());
	Token curTok = valueStack.peek();
	Token dupTok = new Token(curTok.getNumber());
	valueStack.push(dupTok);
	//peek and pop
    }

    public void exch(){
	//pop 1, pop 2, push 1, push 2
	Assert.condition((valueStack.size() == 2), "Valuestack size is " + valueStack.size());
	Token putFirst = valueStack.pop();
	Token putSecond = valueStack.pop();
	valueStack.push(putFirst);
	valueStack.push(putSecond);
	Assert.condition((valueStack.size() == 2), "Valuestack size is " + valueStack.size());
    }


    public void eq(){
	Assert.condition((valueStack.size() == 2), "Valuestack size is " + valueStack.size());
	Token tok1 = valueStack.pop();
	Token tok2 = valueStack.pop();
	if (tok1.equals(tok2)){
	    valueStack.push(new Token(true));
	} else{
	    valueStack.push(new Token(false));
	}
    }

    public void ne(){
	Assert.condition((valueStack.size() == 2), "Valuestack size is " + valueStack.size());
	Token tok1 = valueStack.pop();
	Token tok2 = valueStack.pop();
	if (!(tok1.equals(tok2))){
	    valueStack.push(new Token(true));
	} else{
	    valueStack.push(new Token(false));
	}
    }

    public void def(String name, Token value){
	//adds name/value to symbol table
	symTable.add(name.substring(1), value);
	
    }

    public void ptable(){
	System.out.println(symTable.toString());
    }

    public void replaceSym(Token sym){
	valueStack.push(symTable.get((sym.getSymbol())));
    }

    public void pop(){
	valueStack.pop();
    }
    
    public static void main(String[] args){
	Reader read = new Reader();
	Interpreter i = new Interpreter();
	i.interpret(read);
    }
}
