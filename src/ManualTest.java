import java.util.*;

public class ManualTest{
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
        if (args.length < 2){
            System.out.format("Usage: -Datastructure Datasize [hashtablesize] \n-H: Hash Table -T: Balanced Search Tree\nExample:java test -T 10000\nExample: java Test -H 10000 20000\n");
            return;
        }
        String[] a = StdIn.readAllStrings();
        if (args[0].equals("-H")) {
            if (args.length < 3) {
                System.out.format("You have to specify the hashtable size as well\n");
                return;
            }
            LinearProbingHashST<Integer, Integer> lphs = new LinearProbingHashST<Integer, Integer>(Integer.parseInt(args[2]));
            for (int i = 0; i < a.length; i++){
                if (a[i].equals("Insert")){
                    lphs.insert( Integer.parseInt(a[++i]), Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Search")){
                    lphs.search(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Delete")){
                    lphs.delete(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Rank")){
                    lphs.rank(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("GetValByRank")){
                    lphs.getValByRank(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("RangeCount")){
                    lphs.rangeCount(Integer.parseInt(a[++i]),Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("kSmallest")){
                    lphs.kSmallest(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("kLargest")){
                    lphs.kLargest(Integer.parseInt(a[++i]));
                }
            }
            return;
            
        }
        else if (args[0].equals("-T")) {
            RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
            
            for (int i = 0; i < a.length; i++){
                if (a[i].equals("Insert")){
                    rbt.insert( Integer.parseInt(a[++i]), Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Search")){
                    rbt.search(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Delete")){
                    rbt.delete(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Rank")){
                    rbt.rank(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("GetValByRank")){
                    rbt.getValByRank(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("Count")){
                    rbt.rangeCount(Integer.parseInt(a[++i]),Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("kSmallest")){
                    rbt.kSmallest(Integer.parseInt(a[++i]));
                }
                else if (a[i].equals("kLargest")){
                    rbt.kLargest(Integer.parseInt(a[++i]));
                }
            }
            return;
        }
        else {
            if (args.length < 2) System.out.format("Usage: -Datastructure Datasize [hashtablesize] \n-H: Hash Table -T: Balanced Search Tree\nExample:java test -T 10000\nExample: java Test -H 10000 20000\n");
        }
    }
}
