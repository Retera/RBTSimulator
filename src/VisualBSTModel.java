import java.awt.Rectangle;
import java.util.ArrayDeque;

import javax.swing.JOptionPane;


public class VisualBSTModel {
	VisualBSTFrame parent;
	ArrayDeque<VisualBSTState> history = new ArrayDeque<VisualBSTState>();
	ArrayDeque<VisualBSTState> future = new ArrayDeque<VisualBSTState>();
	public VisualBSTModel(VisualBSTFrame parent, RedBlackBST<?,?> tree) {
		VisualBSTState treeState = new VisualBSTState(tree);
		history.addLast(treeState);
		this.parent = parent;
	}
	
	public void stepForward(VisualBSTAction action) {
		stepForward(action,false);
	}
	
	public void stepForward(VisualBSTAction action, boolean silent) {
		boolean good = true;
		if( future.size() > 0 ) {
			int x = JOptionPane.showConfirmDialog(parent, "This action will remove all actions after this point from the memory.\nOK to continue?", "Confirmation", JOptionPane.YES_NO_OPTION);
			if( x != JOptionPane.YES_OPTION ) {
				good = false;
			} else {
				future.clear();
			}
		}
		if( good ) {
			VisualBSTState currentState = history.peekLast();
			if( currentState.getActions().size() > 0 ) {
				currentState = currentState.copy();
				history.addLast(currentState);
			}
			action.act(currentState.getTree(),silent,parent);
			currentState.getActions().add(action);
			refresh();
		}
	}
	
	public void stepForward() {
		if( future.isEmpty() ) {
			JOptionPane.showMessageDialog(parent, "No forward steps available!");
		} else {
			history.addLast(future.removeLast());
			refresh();
		}
	}
	
	public void stepBackward() {
		if( history.isEmpty() || history.size() < 2 ) {
			JOptionPane.showMessageDialog(parent, "No backward steps available!");
		} else {
			future.addLast(history.removeLast());
			refresh();
		}
	}
	
	public void seek(int index) {
		System.out.println(getState().getActions().size() + "," + history.size() + " futureSize = " + future.size());
		while( history.size() > index + 1 ) {
			future.addLast(history.removeLast());
		}
		while( history.size() < index + 1 ) {
			history.addLast(future.removeLast());
		}
		System.out.println(getState().getActions().size() + "," + history.size() + " futureSize = " + future.size());
		refresh();
	}
	
	public void refresh() {
		parent.getVbp().repaint();
		parent.getControls().refresh();
		parent.getTimeTrack().refresh();
		
		parent.setIconImage(getState().getImage(new Rectangle(0,0,64,64)));
	}
	
	public RedBlackBST<?,?> getTree() {
		return history.peekLast().getTree();
	}
	
	public VisualBSTState getState() {
		return history.peekLast();
	}

	public ArrayDeque<VisualBSTState> getHistory() {
		return history;
	}

	public void setHistory(ArrayDeque<VisualBSTState> history) {
		this.history = history;
	}

	public ArrayDeque<VisualBSTState> getFuture() {
		return future;
	}

	public void setFuture(ArrayDeque<VisualBSTState> future) {
		this.future = future;
	}

	public VisualBSTFrame getParent() {
		return parent;
	}

	public void setParent(VisualBSTFrame parent) {
		this.parent = parent;
	}
}
