import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

public class VisualBSTListModel extends DefaultListModel<VisualBSTAction> {

	VisualBSTModel model;

	public VisualBSTListModel(VisualBSTModel model) {
		this.model = model;
		copyFrom();
	}

	public void copyFrom() {
		clear();
		List<VisualBSTAction> actions = new ArrayList<VisualBSTAction>(model.getState().getActions());
		for( Iterator<VisualBSTState> it = model.getFuture().descendingIterator(); it.hasNext();  ) {
			actions.add(it.next().getLatestAction());
		}
		for( VisualBSTAction action: actions ) {
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