import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

public class VisualHTListModel extends DefaultListModel<VisualHTAction> {

	VisualHTModel model;

	public VisualHTListModel(VisualHTModel model) {
		this.model = model;
		copyFrom();
	}

	public void copyFrom() {
		clear();
		List<VisualHTAction> actions = new ArrayList<VisualHTAction>(model.getState().getActions());
		for( Iterator<VisualHTState> it = model.getFuture().descendingIterator(); it.hasNext();  ) {
			actions.add(it.next().getLatestAction());
		}
		for( VisualHTAction action: actions ) {
			if( !contains(action) )
				addElement(action);
		}
		for( int i = size()-1; i >= 0; i-- ) {
			if( !actions.contains(getElementAt(i)) ) {
				remove(i);
			}
		}
	}

};