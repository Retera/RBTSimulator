import java.awt.Rectangle;
import java.util.ArrayDeque;

import javax.swing.JOptionPane;


public class VisualHTModel {
	VisualHTFrame parent;
	ArrayDeque<VisualHTState> history = new ArrayDeque<VisualHTState>();
	ArrayDeque<VisualHTState> future = new ArrayDeque<VisualHTState>();
	public VisualHTModel(VisualHTFrame parent, LinearProbingHashST<?,?> tree) {
		VisualHTState treeState = new VisualHTState(tree);
		history.addLast(treeState);
		this.parent = parent;
	}
	
	public void stepForward(VisualHTAction action) {
		stepForward(action,false);
	}
	
	public void stepForward(VisualHTAction action, boolean silent) {
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
			VisualHTState currentState = history.peekLast();
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
		//System.out.println(getState().getActions().size() + "," + history.size() + " futureSize = " + future.size());
		while( history.size() > index + 1 ) {
			future.addLast(history.removeLast());
		}
		while( history.size() < index + 1 ) {
			history.addLast(future.removeLast());
		}
		//System.out.println(getState().getActions().size() + "," + history.size() + " futureSize = " + future.size());
		refresh();
	}
	
	public void refresh() {
		parent.getVbp().repaint();
		parent.getControls().refresh();
		parent.getTimeTrack().refresh();
		
		parent.setIconImage(getState().getImage(new Rectangle(0,0,64,64)));
	}
	
	public LinearProbingHashST<?,?> getTree() {
		return history.peekLast().getTree();
	}
	
	public VisualHTState getState() {
		return history.peekLast();
	}

	public ArrayDeque<VisualHTState> getHistory() {
		return history;
	}

	public void setHistory(ArrayDeque<VisualHTState> history) {
		this.history = history;
	}

	public ArrayDeque<VisualHTState> getFuture() {
		return future;
	}

	public void setFuture(ArrayDeque<VisualHTState> future) {
		this.future = future;
	}

	public VisualHTFrame getParent() {
		return parent;
	}

	public void setParent(VisualHTFrame parent) {
		this.parent = parent;
	}
}
