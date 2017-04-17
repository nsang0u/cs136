import structure5.*;

/* Noah Nsangou */

public class TwoTowers{
    public final int BLOCKNUM;
    public final double targetHeight;

    public TwoTowers(int n){
	BLOCKNUM = n;
	targetHeight = targHeight();
    }
    
    public double targHeight(){
	double h = 0;
	for (int i = 1; i <= BLOCKNUM; i++){
	    h = h + blockHeight(h);
	}
	return h;
    }

    public double blockHeight(double areaNum){
	double height = Math.sqrt(areaNum); // IMPORT JAVA MATH
	return height;
    }

    public void solve(){ //took int n !@#
	// given n, which is the number of blocks,
  
	int bestSolution;
	double bestHeight;
	
	for (int i = Math.pow(2,BLOCKNUM); i >= 0; i--){ // Counting 1111, 1110, 1101 etc...
	    double heightCount = 0; // tracking height
	    int curSolution = i;
	    int bitCount = 1; // rightmost bit is considered bit 1, corresponding to block 1
	    int mask = i;
	    while (mask != 0){ // change to for loop to go n times, so that 0 set is included
		int b = mask & 1; //move declaration to top for prettiness?
		System.out.print("M & 1 yield result: " + b + ". To be tested if this == 1"); // debugging precaution
		if (b == 1){
		    heightCount = heightCount + blockHeight(bitCount);	
		}
		mask = mask >> 1; // bitshift M to lineup next bit. 
		bitCount++; // moving bitcount to next bit
		if ((heightCount > bestHeight)&&(heightCount <= targetHeight)){ 
		    bestHeight = heightCount;
		    bestSolution = curSolution;
		}
	    }
	}
	System.out.println("finished for loop, bestSolution = " + bestSolution + "bestHeight = " + bestHeight);
	printOnes(bestSolution);
	printZeros(bestSolution);
	System.out.println("Height of tower 1 = " + bestHeight);
	System.out.println("Height difference = " + towerDifference(bestHeight));
    }

    public int maskIt(int m){
	// helper method to do the mask work in above code
	return 0; // for now
    }
    
    // Where h is the height of a tower solution.
    public double towerDifference(double h){
	double diff = 2 * (targetHeight - h);
	return diff;
    }
    
    public void printOnes(int solution){
	int bitCount = 1;
	System.out.println("Blocks in tower 1: ");
	while (solution != 0){
	    int b = solution & 1;
	    if (b == 1){
		System.out.print(bitCount + " ");
	    }
	}
    }

    public void printZeros(int solution){
	int bitCount = 1;
	System.out.println("Blocks in tower 2: ");
	while (solution != 0){
	    int b = solution & 1;
	    if (b == 0){
		System.out.print(bitCount + " ");
	    }
	}
    }

    public static void main(String args[]){
	TwoTowers prob = new TwoTowers(4);
	prob.solve();
    }


}

    
