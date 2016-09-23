

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

public class VisualHTFrame extends JFrame {
	VisualHTModel model;
	VisualHTPanel vbp;
	VisualHTMethodsPanel methods;
	VisualHTControls controls;
	VisualHTTimeTrack timeTrack;
	
	JMenuBar menuBar;
	JMenu fileMenu;
	JMenuItem saveTestCase;
	public VisualHTFrame(LinearProbingHashST<?,?> tree) {
		super("Visual Red Black BST Frame");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		model = new VisualHTModel(this, tree);
		vbp = new VisualHTPanel(model);
		methods = new VisualHTMethodsPanel(model);
		controls = new VisualHTControls(model);
		timeTrack = new VisualHTTimeTrack(model);
		setContentPane(new JSplitPane(JSplitPane.VERTICAL_SPLIT,new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,methods,new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,vbp,controls)),timeTrack));
		pack();
		setLocationRelativeTo(null);
		
		setJMenuBar(createMenu());
	}
	public VisualHTModel getModel() {
		return model;
	}
	public VisualHTPanel getVbp() {
		return vbp;
	}
	public VisualHTMethodsPanel getMethods() {
		return methods;
	}
	public VisualHTControls getControls() {
		return controls;
	}
	public VisualHTTimeTrack getTimeTrack() {
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
				int x = jfc.showSaveDialog(VisualHTFrame.this);
				if( x == jfc.APPROVE_OPTION ) {
					File file = jfc.getSelectedFile();
					try (PrintWriter writer = new PrintWriter(file) ) {
						VisualHTListModel list = controls.inputsModel;
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
		System.out.println("paint");
		super.repaint();
	}
}
