import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class VisualHTControls extends JPanel {
	
	VisualHTModel bstModel;
	
	JButton stepForward, stepBackward;
	
	JList inputs, outputs;
	
	VisualHTListModel inputsModel, outputsModel;
	
	boolean listening = true;
	
	public VisualHTControls(VisualHTModel bstModel) {
		setPreferredSize(new Dimension(300,400));
		this.bstModel = bstModel;
		
		stepForward = new JButton("Step Forward");
		stepForward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VisualHTControls.this.bstModel.stepForward();
			}
		});
		stepBackward = new JButton("Step Backward");
		stepBackward.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				VisualHTControls.this.bstModel.stepBackward();
			}
		});

		inputs = new JList(inputsModel = new VisualHTListModel(bstModel));
		outputs = new JList(outputsModel = new VisualHTListModel(bstModel));
		outputs.setCellRenderer(new VisualHTResultsRenderer());
		
		
		inputs.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				int index = inputs.getSelectedIndex();
				if( listening && index >= 0 ) {
					VisualHTControls.this.bstModel.seek(index);
				}
			}
		});
		
		JPanel lists = new JPanel();
		lists.setLayout(new GridLayout(1,2));
		lists.add(inputs);
		lists.add(outputs);
		
		JScrollPane lowerPane = new JScrollPane(lists);
		
		GroupLayout layout = new GroupLayout(this);

		layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addGroup(layout.createSequentialGroup()
						.addGap(16)
						.addComponent(stepBackward)
						.addGap(16)
						.addComponent(stepForward)
						.addGap(16)
						)
				.addComponent(lowerPane)
				);
		
		layout.setVerticalGroup(layout.createSequentialGroup()
				.addGroup(layout.createSequentialGroup()
						.addGap(16)
						.addGroup(layout.createParallelGroup()
							.addComponent(stepBackward)
							.addComponent(stepForward)
							)
						.addGap(16)
						)
				.addComponent(lowerPane)
				);
		
		setLayout(layout);
	}
	
	public void refresh() {
		int index = inputs.getSelectedIndex();
		int index2 = outputs.getSelectedIndex();
		listening = false;
		inputsModel.copyFrom();
		outputsModel.copyFrom();
		inputs.setSelectedIndex(bstModel.getHistory().size()-1);
		outputs.setSelectedIndex(bstModel.getHistory().size()-1);
		listening = true;
	}
}
