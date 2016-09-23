import java.util.*;

public class TestHashing{
    /*
     Some Prime Numbers
     23209
     220373
     2475989
     22222223 <-This is number is so interesting to be prime. :-)
     244823041
     2111011129 
    */

    private static int TESTSIZE = 0;
    private int N;           // number of key-value pairs in the symbol table
    private int M;           // size of linear probing table
    private static int[][] countDate;
    private static int[] dataset;
    private static int[] k;
    private static int CAPACITY = 0;
    private static int hashSize = 0;
    private static double[] PERCENTAGE = new double[6];
    static double startTest, endTest;

    public static String testInsertion(LinearProbingHashST<Integer, Integer> lphs){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.insert(dataset[i], dataset[i]);

    	}

    	endTest =System.nanoTime();


    	return String.format(" %.2f  ", (endTest-startTest)/2000.0);
    }

    public static String testDeletion(LinearProbingHashST<Integer, Integer> lphs){


    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.delete(dataset[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testSearch(LinearProbingHashST<Integer, Integer> lphs){


    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.search(dataset[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testRank(LinearProbingHashST<Integer, Integer> lphs){

    	startTest = System.nanoTime();
    	for (int i = 0; i < 2000; i ++){

    		lphs.rank(dataset[i]);

    	}

    	endTest =System.nanoTime();


    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testKSmallest(LinearProbingHashST<Integer, Integer> lphs){

    	
    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.kSmallest(k[i]);

    	}

    	endTest =System.nanoTime();
    	return String.format(" %.2f  ", (endTest-startTest)/2000.0);
    }

    public static String testKLargest(LinearProbingHashST<Integer, Integer> lphs){


    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.kLargest(k[i]);

    	}

    	endTest =System.nanoTime();
    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testgetValByRank(LinearProbingHashST<Integer, Integer> lphs){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i++){

    		int value = lphs.getValByRank(k[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }



    public static String testCount(LinearProbingHashST<Integer, Integer> lphs){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		lphs.rangeCount(countDate[i][0],countDate[i][1]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static void testall(){

    	LinearProbingHashST<Integer, Integer> lphs = new LinearProbingHashST<Integer, Integer>(CAPACITY);

    	dataset = new int[2000];

    	countDate = new int[2000][2];

        int TLOG = (int)(Math.log(TESTSIZE));
        
    	Random rand = new Random(System.currentTimeMillis());

    	String rowOne = "| Unit Operation\t|" + " Insertion\t" + "|" + "Search\t\t" + "|" + "Rank\t\t" + "|" + "Deletion\t" + "|";

    	String rowTwo = "|   Time(ms)\t\t|";

    	String rowThree = "| Range Operation\t|" + " KSamllest\t" + "|" + " KLargest\t" + "|" + "RangeCount\t" + "|"+"GetVByRank\t" + "|";

    	String rowFour = "|   Time(ms)\t\t|";

    	for (int i = 0; i < TESTSIZE; i ++){

    		int newElement = rand.nextInt();

    		lphs.insert(newElement, newElement);

    	}

    	for (int i = 0; i < 2000; i ++){

    		dataset[i] = rand.nextInt();

    		int l = rand.nextInt();

    		int h = rand.nextInt();

    		if (h < l){
    			int temp = h;

    			h = l;

    			l = temp;
    		}

    		countDate[i][0] = l;

    		countDate[i][1] = h;
    	}


    	System.out.println("----------------------- R E P O R T ------------------------");
    	System.out.println("-----------------------  Hash Table ------------------------");

    	/* Test Insertion */

    	rowTwo = rowTwo + testInsertion(lphs) + "\t|";



    	k = new int[2000];

    	/* Test Search */

    	rowTwo = rowTwo + testSearch(lphs) + "\t|";

    	/* Test Rank */

    	rowTwo = rowTwo + testRank(lphs) + "\t|";

    	lphs.size();

    	/* Test cases */



    	for (int i = 0; i < 2000; i ++){

    		k[i] = rand.nextInt(TLOG) + TLOG;
    	}
    	//System.out.println(k[i]);


    	/* KSmallest */

    	rowFour = rowFour + testKSmallest(lphs) + "\t|";

    	/* KLargest */

    	rowFour = rowFour + testKLargest(lphs) + "\t|";


    	/* Range Count*/

    	rowFour = rowFour + testCount(lphs) + "\t|";

    	/* Test getValByRank */

    	rowFour = rowFour +  testgetValByRank(lphs) + "\t|";

    	/* Test Deletion */

    	rowTwo = rowTwo + testDeletion(lphs) + "\t|";




    	System.out.println(rowOne + "\n" + rowTwo + "\n" + rowThree + "\n" + rowFour + "\n");

    	System.out.format("TESTSIZE is %d \n", TESTSIZE);

    	System.out.println("----------------------- E    N    D ------------------------");



    }
public static void testcombination(){
        
		LinearProbingHashST<Integer, Integer> lphs = new LinearProbingHashST<Integer, Integer>(CAPACITY);
        
        dataset = new int[TESTSIZE];
        
        int[] rank = new int[TESTSIZE];
        
        int[] test = new int[10000];
        
        int[] opeartion = new int[TESTSIZE];
        
        countDate = new int[10000][2];

        Random rand = new Random(System.currentTimeMillis());
    
    
        /* Construct a data structure that has n elements in it */
        for (int i = 0; i < TESTSIZE; i ++){
            
            dataset[i] = rand.nextInt();
            
            lphs.insert(dataset[i],dataset[i]);

            rank[i] = rand.nextInt(TESTSIZE) ;
            
        }
        
        int TLOG = (int)(Math.log(TESTSIZE));

        for (int i = 0; i < 10000; i ++){
            
            int l = rand.nextInt(TLOG) + TLOG;
            
            int h = rand.nextInt(TLOG) + TLOG;
            
            if (h < l){
                int temp = h;
                
                h = l;
                
                l = temp;
            }
            
            countDate[i][0] = l;
            
            countDate[i][1] = h;
        }
        
        
        int t = 0;
        for (int i = 0; i < 6; i++){
        	while(PERCENTAGE[i] > 0){
        		opeartion[t++] = i;
        		PERCENTAGE[i]--;
        	}
        }
        
        Collections.shuffle(Arrays.asList(opeartion));
        

        
        for (int j = 0; j < 10000; j++) test[j] = rand.nextInt();
        
        startTest = System.nanoTime();
        for (int j = 0; j < 10000; j++){
            if (opeartion[j] == 0) lphs.insert(test[j],test[j]); 
            else if (opeartion[j] == 1) lphs.delete(test[j - j/2]);
            else if (opeartion[j] == 2) lphs.search(test[j - j/2]);
            else if (opeartion[j] == 3) lphs.rank(test[j]);
            else if (opeartion[j] == 4) lphs.getValByRank(rank[j]);
            else if (opeartion[j] == 5) lphs.rangeCount(countDate[j][0],countDate[j][1]);
        }
        endTest = System.nanoTime();
    	

        System.out.format("Time: %.2f(ms) \n", (endTest-startTest)/1000000);
        System.out.format("Testsize is %d\n", TESTSIZE);
        
    }
    
    
    
    /***********************************************************************
     *  Unit test client.
     ***********************************************************************/
    public TestHashing(int size, int capacity) {
        TESTSIZE = size;
        CAPACITY = capacity;
        testall();
    }
    
    public TestHashing(int size, int capacity,double percentage[]) {
        TESTSIZE = size;
        CAPACITY = capacity;
        System.out.format("Insert\t\tDelete\t\tSearch\t\n");
        for (int i = 0; i < 3; i ++){
        	System.out.format("%.2f%%\t\t",percentage[i]);
        }
        System.out.println();
        System.out.format("Rank\t\tGetVByRank\tCount\n");
        for (int i = 3; i < 6; i ++){
        	System.out.format("%.2f%%\t\t",percentage[i]);
        }
        System.out.println();
        for (int i = 0; i < 6;i ++){
        	PERCENTAGE[i] = percentage[i]*10000/100;
        }
        TESTSIZE = size;
        testcombination();
    }
}
