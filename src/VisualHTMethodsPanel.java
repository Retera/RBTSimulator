import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;


public class VisualHTMethodsPanel extends JPanel {
	
	VisualHTModel bstModel;
	
	public VisualHTMethodsPanel(VisualHTModel bstModel) {
		setPreferredSize(new Dimension(300,400));
		this.bstModel = bstModel;
		buildButtonGUI();
	}
	
	public void buildButtonGUI() {
		Method[] methods = LinearProbingHashST.class.getDeclaredMethods();
		List<MethodButton> buttons = new ArrayList<MethodButton>();
		for( Method m: methods ) {
			if( Modifier.isPublic(m.getModifiers()) )
				buttons.add(new MethodButton(m));
		}
		
		setLayout(new GridLayout(buttons.size(),1));
		for(MethodButton mb: buttons)
			add(mb);
	}
	public static String methodToString(Method m) {
		StringBuilder builder = new StringBuilder();
		builder.append(m.getName());
		builder.append('(');
		Class<?>[] parameters = m.getParameterTypes();
		boolean first = true;
		for(Class<?> klass: parameters) {
			if( !first )
				builder.append(',');
			else
				first = false;
			builder.append(klass.getSimpleName());
		}
		builder.append(')');
		return builder.toString();
	}
	
	public static String methodToString(Method m, Object[] vals) {
		StringBuilder builder = new StringBuilder();
		builder.append(m.getName());
		builder.append('(');
		boolean first = true;
		if( vals != null )
			for(Object o: vals) {
				if( !first )
					builder.append(',');
				else
					first = false;
				builder.append(o.toString());
			}
		builder.append(')');
		return builder.toString();
	}
	
	class MethodButton extends JButton implements ActionListener {
		Method method;
		
		public MethodButton(Method method) {
			super(methodToString(method));
			addActionListener(this);
			this.method = method;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			Class<?>[] params = method.getParameterTypes();
			if( params.length > 0 ) {
				
				JPanel dataEntry = new JPanel();
//				List<JComboBox> boxes = new ArrayList<JComboBox>();
				List<JSpinner> spinners = new ArrayList<JSpinner>();
				JLabel label = new JLabel("Enter the parameters for " + method.getName() + " below:");
				for( Class<?> klass: params ) {
//					if( klass ==  ) {
//						
//					}
					spinners.add(new JSpinner(new SpinnerNumberModel(1,-100000000,100000000,1)));
				}
				
				GroupLayout layout = new GroupLayout(dataEntry);

				GroupLayout.Group horizGroup;
				GroupLayout.Group vertGroup;
				layout.setHorizontalGroup(layout.createSequentialGroup()
						.addGap(8)
						.addGroup(layout.createParallelGroup()
								.addComponent(label)
								.addGroup(horizGroup = layout.createSequentialGroup())
								)
						.addGap(8)
						);
				layout.setVerticalGroup(layout.createSequentialGroup()
						.addGap(8)
						.addGroup(layout.createSequentialGroup()
								.addComponent(label)
								.addGroup(vertGroup = layout.createParallelGroup())
								)
						.addGap(8)
						);
				
				for( JSpinner spinner: spinners ) {
					horizGroup.addComponent(spinner);
					vertGroup.addComponent(spinner);
				}
				
				dataEntry.setLayout(layout);
				
				int x = JOptionPane.showConfirmDialog(this, dataEntry, "Method Parameters", JOptionPane.OK_CANCEL_OPTION);
				if( x == JOptionPane.OK_OPTION ) {
					List<Object> vals = new ArrayList<Object>();
//					Integer[] vals = new Integer[spinners.size()];
					for( int i = 0; i < spinners.size(); i++ ) {
						vals.add(((Number)spinners.get(i).getValue()).intValue());
					}
//					try {
//						Object o = method.invoke(bstModel.getTree(), vals);
//						System.out.println(method.getReturnType());
//						if( method.getReturnType() != void.class && (o == null || o.getClass() != Void.class) )
//							JOptionPane.showMessageDialog(this, (o == null ? "null" : o), methodToString(method, vals), JOptionPane.PLAIN_MESSAGE);
//					} catch (Exception e1) {
//						e1.printStackTrace();
//						JOptionPane.showMessageDialog(this, "An exception was thrown of type: " +e1.getClass() + ": " + e1.getMessage(), methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
//					}
					VisualHTAction action = new VisualHTAction(method, vals);
					bstModel.stepForward(action);
				}
			} else {
				List<Object> vals = new ArrayList<Object>();
				VisualHTAction action = new VisualHTAction(method, vals);
				bstModel.stepForward(action);
//				try {
//					Object o = method.invoke(bstModel.getTree(), (Object[])null);
//					System.out.println(method.getReturnType());
//					if( method.getReturnType() != void.class && (o == null || o.getClass() != Void.class) )
//						JOptionPane.showMessageDialog(this, (o == null ? "null" : o), methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
//				} catch (Exception e1) {
//					e1.printStackTrace();
//					JOptionPane.showMessageDialog(this, "An exception was thrown of type: " +e1.getClass() + ": " + e1.getMessage(), methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
//				}
			}
			bstModel.refresh();
		}
	}
}
