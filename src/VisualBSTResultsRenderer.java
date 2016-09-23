import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;


public class VisualBSTResultsRenderer extends DefaultListCellRenderer {

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> jList,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		VisualBSTAction action = (VisualBSTAction)value;
		super.getListCellRendererComponent(jList, action.getResult(), index, isSelected, cellHasFocus);
//		if( index > jList.getSelectedIndex() )
//			setBackground(Color.blue);
		return this;
	}

}
