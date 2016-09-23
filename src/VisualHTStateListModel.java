import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

public class VisualHTStateListModel extends DefaultListModel<VisualHTState> {

	VisualHTModel model;

	public VisualHTStateListModel(VisualHTModel model) {
		this.model = model;
		copyFrom();
	}

	public void copyFrom() {
		clear();
		List<VisualHTState> states = new ArrayList<VisualHTState>();
		for( Iterator<VisualHTState> it = model.getHistory().iterator(); it.hasNext();  ) {
			states.add(it.next());
		}
		for( Iterator<VisualHTState> it = model.getFuture().descendingIterator(); it.hasNext();  ) {
			states.add(it.next());
		}
		for( VisualHTState action: states ) {
			if( !contains(action) )
				addElement(action);
		}
		for( int i = size()-1; i >= 0; i-- ) {
			if( !states.contains(getElementAt(i)) ) {
				remove(i);
			}
		}
	}

};