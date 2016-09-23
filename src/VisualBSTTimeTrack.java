import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class VisualBSTTimeTrack extends JPanel {

	VisualBSTModel bstModel;
	
	JList inputs;
	
	VisualBSTStateListModel inputsModel;
	
	boolean listening = true;
	
	public VisualBSTTimeTrack(VisualBSTModel bstModel) {
		setPreferredSize(new Dimension(1200,200));
		this.bstModel = bstModel;
		
		inputs = new JList(inputsModel = new VisualBSTStateListModel(bstModel));
		inputs.setLayoutOrientation(JList.HORIZONTAL_WRAP);
		inputs.setCellRenderer(new VisualBSTStateRenderer(bstModel));
		inputs.setVisibleRowCount(1);
		
		inputs.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = inputs.getSelectedIndex();
				if( listening && index >= 0 ) {
					VisualBSTTimeTrack.this.bstModel.seek(index);
				}
			}
		});
		
		setLayout(new GridLayout(1,1));
		add(new JScrollPane(inputs));
	}
	
	public void refresh() {
		listening = false;
		if( inputsModel != null ) {
			inputsModel.copyFrom();
			inputs.setSelectedIndex(bstModel.getHistory().size()-1);
		}
		listening = true;
	}
	
	@Override
	public void repaint() {
		super.repaint();
		refresh();
	}
}
