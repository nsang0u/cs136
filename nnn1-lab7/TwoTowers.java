/* Noah Nsangou */
// This class solves the two towers problem, printing various attributes of the solution.

public class TwoTowers{
    public final int BLOCKNUM;
    public final double targetHeight;
    
    public TwoTowers(int n){
	BLOCKNUM = n;
	targetHeight = targHeight();
    }

    // Post: returns double h representing the target height given number of blocks blocknum.
    public double targHeight(){
	double h = 0;
	for (int i = 1; i <= BLOCKNUM; i++){
	    h = h + blockHeight(i);
	}
	return h;
    }

    public double blockHeight(double areaNum){
	double height = Math.sqrt(areaNum); // IMPORT JAVA MATH
	return height;
    }

    public void solve(){
	
  
	int bestSolution = 0;
	double bestHeight = 0;
	
	for (int i = ((2 << BLOCKNUM) - 1); i >= 0; i--){
	    double heightCount = 0; // tracking height
	    int curSolution = i;
	    int bitCount = 1; // rightmost bit is considered bit 1, corresponding to block 1
	    int mask = i;
	    while (mask != 0){
		int b = mask & 1;
		if (b == 1){
		    heightCount = heightCount + blockHeight(bitCount);	
		}
		mask = mask >> 1; // bitshift M to lineup next bit. 
		bitCount++; // moving bitcount to next bit
	    }
	    if ((heightCount > bestHeight)&&(heightCount < targetHeight)){ 
		bestHeight = heightCount;
		bestSolution = curSolution;	
	    }
	}
	printOnes(bestSolution);
	System.out.println();
	printZeros(bestSolution);
	System.out.println();
	System.out.println("Height of tower 1 = " + bestHeight);
	System.out.println("Height difference = " + towerDifference(bestHeight));
    }

    public int maskIt(int m){
	// helper method to do the mask work in above code
	return 0; // for now
    }
    
    // Pre: h is a double, here h is the height of a tower solution.
    // Post: returns diff, the difference between towers given h.
    public double towerDifference(double h){
	double diff = 2 * (targetHeight - h);
	return diff;
    }

    // Pre: solution is an int.
    // Post: prints 1-numbered bits of solution.
    public void printOnes(int solution){
	int bitCount = 1;
	System.out.println("Blocks in tower 1: ");
	while (solution != 0){
	    int b = solution & 1;
	    solution = solution >> 1;
	    if (b == 1){
		System.out.print(bitCount + " ");
	    }
	    bitCount = bitCount + 1;
	}
    }

    public void printZeros(int solution){
	int bitCount = 1;
	System.out.println("Blocks in tower 2: ");
	while (solution != 0){
	    int b = solution & 1;
	    solution = solution >> 1;
	    if (b == 0){
		System.out.print(bitCount + " ");
	    }
	    bitCount = bitCount + 1;
	}
    }
    
    public static void main(String args[]){
	int BLOCKSNUM = 14;
	TwoTowers prob = new TwoTowers(BLOCKSNUM - 1);
	prob.solve();
    }
    

}

    
