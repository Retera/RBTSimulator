import java.util.*;

public class TestBST{
    
	static double startTest, endTest;
	private static int TESTSIZE ;
	private static int[][] countDate;
	private static int[] dataset;
	private static int[] k;
    private static double[] PERCENTAGE = new double[6];

    public static String testInsertion(RedBlackBST<Integer, Integer> rbt){
        
        
        
    	startTest =System.nanoTime();
    
    	for (int i = 0; i < 2000; i ++){
        
        		rbt.insert(dataset[i], dataset[i]);
        
    	}
    
   		endTest =System.nanoTime();
    

    	return String.format(" %.2f  ", (endTest-startTest)/2000.0);
    }

    public static String testDeletion(RedBlackBST<Integer, Integer> rbt){

    	
    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.delete(dataset[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testSearch(RedBlackBST<Integer, Integer> rbt){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.search(dataset[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testRank(RedBlackBST<Integer, Integer> rbt){


    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.rank(dataset[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testKSmallest(RedBlackBST<Integer, Integer> rbt){
    	

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.kSmallest(k[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f  ", (endTest-startTest)/2000.0);
    }

    public static String testKLargest(RedBlackBST<Integer, Integer> rbt){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.kLargest(k[i]);

    	}
    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static String testgetValByRank(RedBlackBST<Integer, Integer> rbt){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i++){

    		int value = rbt.getValByRank(k[i]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }



    public static String testCount(RedBlackBST<Integer, Integer> rbt){

    	startTest =System.nanoTime();

    	for (int i = 0; i < 2000; i ++){

    		rbt.rangeCount(countDate[i][0],countDate[i][1]);

    	}

    	endTest =System.nanoTime();

    	return String.format(" %.2f ", (endTest-startTest)/2000.0);
    }

    public static void testall(){

    	RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();

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
            
            rbt.insert(newElement, newElement);
            
        }
        
    	rbt.size();

    	for (int i = 0; i < 2000; i ++){

    		dataset[i] = rand.nextInt();

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


    	System.out.println("----------------------- R E P O R T ------------------------");
    	System.out.println("---------------------- RED BLACK TEEE ----------------------");
    	
    	k = new int[2000];
    	
    	for (int i = 0; i < 2000; i ++){

    		k[i] = rand.nextInt(TLOG) ;
    	}
    	//System.out.println(k[i]);
    	
    	/* Test Insertion */

    	rowTwo = rowTwo + testInsertion(rbt) + "\t|";


    	/* Test Search */

    	rowTwo = rowTwo + testSearch(rbt) + "\t|";

    	/* Test Rank */

    	rowTwo = rowTwo + testRank(rbt) + "\t|";

    	/* Test cases */

    	/* KLargest */
    	rowFour = rowFour + testKLargest(rbt) + "\t|";
    	
    	/* KSmallest */
        
    	rowFour = rowFour + testKSmallest(rbt) + "\t|";


    	/* Range Count*/

    	rowFour = rowFour + testCount(rbt) + "\t|";

    	/* Test getValByRank */

    	rowFour = rowFour +  testgetValByRank(rbt) + "\t|";

    	/* Test Deletion */
        rowTwo = rowTwo + testDeletion(rbt) + "\t|";




    	System.out.println(rowOne + "\n" + rowTwo + "\n" + rowThree + "\n" + rowFour + "\n");

    	System.out.format("TESTSIZE is %d \n", TESTSIZE);

    	System.out.println("----------------------- E    N    D ------------------------");



    }

    public static void testcombination(){
        
        RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
        
        dataset = new int[TESTSIZE];

        
        int[] rank = new int[TESTSIZE];
        
        int[] test = new int[10000];
        
        int[] opeartion = new int[TESTSIZE];
        
        countDate = new int[10000][2];
        
        
        Random rand = new Random(System.currentTimeMillis());
        
        /* Construct a data structure that has n elements in it */
        for (int i = 0; i < TESTSIZE; i ++){
            
            dataset[i] = rand.nextInt();
            
            rbt.insert(dataset[i],dataset[i]);

            rank[i] = rand.nextInt(TESTSIZE);
            
        }
        
        int TLOG = (int)(Math.log(TESTSIZE));

        for (int i = 0; i < 10000; i ++){
            
            int l = rand.nextInt(TLOG) + TLOG ;
            
            int h = rand.nextInt(TLOG) + TLOG ;
            
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
            if (opeartion[j] == 0) rbt.insert(test[j],test[j]); 
            else if (opeartion[j] == 1) rbt.delete(test[j - j/2]);
            else if (opeartion[j] == 2) rbt.search(test[j - j/2]);
            else if (opeartion[j] == 3) rbt.rank(test[j]);
            else if (opeartion[j] == 4) rbt.getValByRank(rank[j]);
            else if (opeartion[j] == 5) rbt.rangeCount(countDate[j][0],countDate[j][1]);
        }
        endTest = System.nanoTime();
        
        
        System.out.format("Time: %.2f(ms) \n", (endTest-startTest)/1000000);
        System.out.format("Testsize is %d\n", TESTSIZE);
        
    }
    
    public TestBST(int size){
        TESTSIZE = size;
        testall();
    }

	/*****************************************************************************
	 *  Test client
	 *****************************************************************************/
    
     public TestBST(int size, double[] percentage){
         System.out.format("Insert\t\tDelete\t\tSearch\t\n");
         for (int i = 0; i < 3; i ++){
         	System.out.format("%.2f%%\t\t",percentage[i]);
         }
         System.out.println();
         System.out.format("Rank\t\tGetVByRank\trangeCount\n");
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