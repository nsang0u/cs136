import structure5.*;

/* Noah Nsangou */

public class TwoTowers{
    public int blockNum;
    
    public TwoTowers(int n){
	blockNum = n;
	int maskMax = 0b((Math.pow(2,n) - 1));
	double targetHeight = targHeight();
    }
    
    // Returns binary int given decimal number dec. 
    public int toBinary(int dec){
	//
    }
    
    public double targHeight(){
	double h = 0;
	for (int i = 1, i <= blockNum, i++){
	    h = h + blockHeight(h);
	}
	return h;
    }

    public double blockHeight(int areaNum){
	double height = Math.sqrt(areaNum); // IMPORT JAVA MATH
	return height;
    }

    public int towerHeight(int M){
	// given int M, which is a binary representation of one tower, return the tower height of that representation.
	int mask = M;
	int b;
	int blockCount = 1; // rightmost bit can be considered block 1
	int heightCount = 0; // int to track height of this tower subset, starting at 0
	while (mask != 0){
	    b = M & 1;
	    System.out.print("M & 1 yield result: " + b + ". To be tested if this == 1");
	    if (b == 1){
		heightCount = heightCount + blockHeight(blockCount);	
	    }
	    M = M >> 1; // bitshift M to lineup next bit. 
	}
    }

    public void tester(){
	//targetHeight
	// subtracts from maskmax until 0, calling towerHeight each time, keeping track of max towerHeight. 
	
	
    }



}

    
