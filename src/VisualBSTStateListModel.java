import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.DefaultListModel;

public class VisualBSTStateListModel extends DefaultListModel<VisualBSTState> {

	VisualBSTModel model;

	public VisualBSTStateListModel(VisualBSTModel model) {
		this.model = model;
		copyFrom();
	}

	public void copyFrom() {
		clear();
		List<VisualBSTState> states = new ArrayList<VisualBSTState>();
		for( Iterator<VisualBSTState> it = model.getHistory().iterator(); it.hasNext();  ) {
			states.add(it.next());
		}
		for( Iterator<VisualBSTState> it = model.getFuture().descendingIterator(); it.hasNext();  ) {
			states.add(it.next());
		}
		for( VisualBSTState action: states ) {
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