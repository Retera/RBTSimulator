

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSplitPane;

public class VisualBSTFrame extends JFrame {
	VisualBSTModel model;
	VisualBSTPanel vbp;
	VisualBSTMethodsPanel methods;
	VisualBSTControls controls;
	VisualBSTTimeTrack timeTrack;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem saveTestCase;
	public VisualBSTFrame(RedBlackBST<?,?> tree) {
		super("Visual Red Black BST Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		model = new VisualBSTModel(this, tree);
		vbp = new VisualBSTPanel(model);
		methods = new VisualBSTMethodsPanel(model);
		controls = new VisualBSTControls(model);
		timeTrack = new VisualBSTTimeTrack(model);
		setContentPane(new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,methods,new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,vbp,controls)),timeTrack));
		pack();
		setLocationRelativeTo(null);
		
		setJMenuBar(createMenu());
	}
	public VisualBSTModel getModel() {
		return model;
	}
	public VisualBSTPanel getVbp() {
		return vbp;
	}
	public VisualBSTMethodsPanel getMethods() {
		return methods;
	}
	public VisualBSTControls getControls() {
		return controls;
	}
	public VisualBSTTimeTrack getTimeTrack() {
		return timeTrack;
	}

	JFileChooser jfc = new JFileChooser();
	public JMenuBar createMenu() {
		menuBar = new JMenuBar();
		
		fileMenu = new JMenu("File");
		menuBar.add(fileMenu);
		
		saveTestCase = new JMenuItem("Save Test Case");
		saveTestCase.setMnemonic(KeyEvent.VK_S);
		saveTestCase.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int x = jfc.showSaveDialog(VisualBSTFrame.this);
				if( x == jfc.APPROVE_OPTION ) {
					File file = jfc.getSelectedFile();
					try (PrintWriter writer = new PrintWriter(file) ) {
						VisualBSTListModel list = controls.inputsModel;
						for( int i = 0; i < list.getSize(); i++ ) {
							writer.println(list.getElementAt(i).toString());
						}
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
				}
			}
		});
		fileMenu.add(saveTestCase);
		
		
		return menuBar;
	}
	
	@Override
	public void repaint() {
		model.refresh();
		super.repaint();
	}
}
