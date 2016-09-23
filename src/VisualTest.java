import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFileChooser;

public class VisualTest{
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
//        if (args.length < 2){
//            System.out.format("Usage: -Datastructure Datasize [hashtablesize] \n-H: Hash Table -T: Balanced Search Tree\nExample:java test -T 10000\nExample: java Test -H 10000 20000\n");
//            return;
//        }
    	
    	
//    	JFileChooser jfc = new JFileChooser();
//    	jfc.setDialogTitle("Choose a sample input txt!");
//    	int x = jfc.showOpenDialog(null);
//    	if( x == JFileChooser.APPROVE_OPTION ) {
//    		File file = jfc.getSelectedFile();
//    		try {
//				System.setIn(new FileInputStream(file));
//			} catch (FileNotFoundException e) {
//				e.printStackTrace();
//			}
//    	}
		try {
			System.setIn(new FileInputStream(new File("Sample.txt")));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    	args = new String[] {"-T", "32"};
        String[] a = StdIn.readAllStrings();
        if (args[0].equals("-H")) {
            if (args.length < 2) {
                System.out.format("You have to specify the hashtable size as well\n");
                return;
            }
            LinearProbingHashST<Integer, Integer> lphs = new LinearProbingHashST<Integer, Integer>(Integer.parseInt(args[1]));
            VisualHTFrame frame = new VisualHTFrame(lphs);
            
            List<VisualHTAction> actions = new ArrayList<VisualHTAction>();

            for (int i = 0; i < a.length; i++){
                try {
                    if (a[i].equals("Insert")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i]), Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Search")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Delete")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Rank")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("GetValByRank")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("rangeCount")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i]),Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("kSmallest")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("kLargest")){
                    	actions.add(new VisualHTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                } catch (NoSuchMethodException exc) {
                	exc.printStackTrace();
                }
            }
            VisualHTModel state = frame.getModel();
            for( VisualHTAction action: actions ) {
            	state.stepForward(action,true);
//				try {
//					Thread.sleep(20);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
            }
            frame.setVisible(true);
            return;
            
        }
        else if (args[0].equals("-T")) {
            RedBlackBST<Integer, Integer> rbt = new RedBlackBST<Integer, Integer>();
            VisualBSTFrame frame = new VisualBSTFrame(rbt);
            
            List<VisualBSTAction> actions = new ArrayList<VisualBSTAction>();

            for (int i = 0; i < a.length; i++){
                try {
                    if (a[i].equals("Insert")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i]), Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Search")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Delete")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("Rank")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("GetValByRank")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("rangeCount")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i]),Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("kSmallest")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                    else if (a[i].equals("kLargest")){
                    	actions.add(new VisualBSTAction( a[i], Arrays.asList(new Object[]{Integer.parseInt(a[++i])})));
                    }
                } catch (NoSuchMethodException exc) {
                	exc.printStackTrace();
                }
            }
            VisualBSTModel state = frame.getModel();
            for( VisualBSTAction action: actions ) {
            	state.stepForward(action,true);
//				try {
//					Thread.sleep(20);
//				} catch (InterruptedException e) {
//					e.printStackTrace();
//				}
            }
            frame.setVisible(true);
            return;
        }
        else {
            if (args.length < 2) System.out.format("Usage: -Datastructure Datasize [hashtablesize] \n-H: Hash Table -T: Balanced Search Tree\nExample:java test -T 10000\nExample: java Test -H 10000 20000\n");
        }
    }
}
