import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.JOptionPane;


public class VisualBSTActionOld {
	static enum Type {Insert, Search, Delete, Rank, GetValByRank, Count, kSmallest, kLargest}
	List<Object> arguments = new ArrayList<Object>();
	Type type;
	public List<Object> getArguments() {
		return arguments;
	}
	public void setArguments(List<Object> arguments) {
		this.arguments = arguments;
	}
	public Type getType() {
		return type;
	}
	public void setType(Type type) {
		this.type = type;
	}
	public VisualBSTActionOld(Type type, List<Object> arguments) {
		super();
		this.arguments = arguments;
		this.type = type;
	}
	public String act(RedBlackBST tree) {
		return act(tree, false);
	}
	public String act(RedBlackBST tree, boolean silent) {
		String text = "";
		switch (type) {
		case Insert:
			tree.insert((Comparable<?>)arguments.get(0), arguments.get(1));
			break;
		case Search:
			Object result = tree.search((Comparable<?>)arguments.get(0));
			text = "Result of searching for " + arguments.get(0).toString() + ": " + result.toString();
			if( !silent )
				JOptionPane.showMessageDialog(null, text);
			break;
		case Delete:
			tree.delete((Comparable<?>)arguments.get(0));
			break;
		case Rank:
			int rank = tree.rank((Comparable<?>)arguments.get(0));
			text = "Rank of " + arguments.get(0).toString() + ": " + rank;
			if( !silent )
				JOptionPane.showMessageDialog(null, text);
			break;
		case GetValByRank:
			Object val = tree.getValByRank((int)arguments.get(0));
			text = "Rank-based search for " + arguments.get(0).toString() + " yielded: " + val;
			if( !silent )
				JOptionPane.showMessageDialog(null, text);
			break;
		case Count:
			int x = tree.rangeCount((Comparable<?>)arguments.get(0), (Comparable<?>)arguments.get(1));
			text = "Range count between " + arguments.get(0).toString() + " and " + arguments.get(1).toString() + " yielded: " + x; 
			if( !silent )
				JOptionPane.showMessageDialog(null, text);
			break;
		case kSmallest:
			Iterable i = tree.kSmallest((int)arguments.get(0));
			StringBuilder resultsBuilder = new StringBuilder();
			for(Object obj: i) {
				resultsBuilder.append(obj.toString());
				resultsBuilder.append(", ");
			}
			text = "The " + arguments.get(0) + " smallest elements were: " + resultsBuilder.toString();
			if( !silent ) {
				JOptionPane.showMessageDialog(null, text);
			}
			break;
		case kLargest:
			Iterable il = tree.kLargest((int)arguments.get(0));
			StringBuilder resultsBuilder2 = new StringBuilder();
			for(Object obj: il) {
				resultsBuilder2.append(obj.toString());
				resultsBuilder2.append(", ");
			}
			text = "The " + arguments.get(0) + " largest elements were: " + resultsBuilder2.toString();
			if( !silent ) {
				JOptionPane.showMessageDialog(null, text);
			}
			break;
		default:
			break;
		}
		return text;
	}
}
