
public class TestEngine{
    /*
     Prime p >= dataset size * 20 can provide good enough performance
     10^3: 23209
     10^4: 220373
     10^5: 2475989
     10^6: 22222223 <-This is number is so interesting to be prime. :-)
     10^7: 244823041
     10^8: 2111011129* */
    
    /***********************************************************************
     *  Unit test client.
     ***********************************************************************/
    public static void main(String[] args) {
        LinearProbingHashST<Integer, Integer> lphs = new LinearProbingHashST<Integer, Integer>();
    	double[] percentage = new double[8];
    	for (int i = 0; i < 8; i++){
    		
    		percentage[i] = 12.5;
    		
    	}
    	double sum = 0;
        if (args.length < 2) {
        	System.out.format("Usage: -Datastructure Datasize [hashtablesize] [-P percentage for each kind of operations]\n"
        			+ "-H: Hash Table\n"
        			+ "-T: Balanced Search Tree\n"
        			+ "-P: [Insertion] [Deletion] [Search] [Rank] [Rangecount] [getValByRank]\n"
        			+ "Example: java test -T 10000\n"
        			+ "Example: java Test -H 10000 20000 -P 10 10 10 20 20 30\n");
        	return;
        }
        if (args[0].equals("-H")) {
            if (args.length < 3) System.out.format("You have to specify the hashtable size as well\n");
            if (args.length == 10 && args[3].equals("-P")){
            	for (int i = 0; i < 6; i++){
            		percentage[i] = Double.parseDouble(args[4+i]);
            	}
            	
            	new TestHashing(Integer.parseInt(args[1]), Integer.parseInt(args[2]), percentage);
            }
            /* Default  */
            else new TestHashing(Integer.parseInt(args[1]),Integer.parseInt(args[2]));
        }
        else if (args[0].equals("-T")) {
            if (args.length == 9 && args[2].equals("-P"))  {

            	for (int i = 0; i < 6; i++){
            		sum = sum + Double.parseDouble(args[3+i]);
            		percentage[i] = Double.parseDouble(args[3+i]);
            	}
            	if (sum != 100){
            		System.out.format("The total percentage should be 100\n");
            		return;
            	}
            	new TestBST(Integer.parseInt(args[1]), percentage);
            }
            else new TestBST(Integer.parseInt(args[1]));
        }
        else {
            /* Default Percentage */
            if (args.length < 2) System.out.format("Usage: -Datastructure Datasize [hashtablesize]\n  -H: Hash Table -T: Balanced Search Tree\nExample:java test -T 10000\nExample: java Test -H 10000 20000\n");
        }
    }
    

}
