import java.awt.Component;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

public class VisualHTAction {
	List<Object> arguments = new ArrayList<Object>();
	Method method;
	String text = "";
	public List<Object> getArguments() {
		return arguments;
	}
	public void setArguments(List<Object> arguments) {
		this.arguments = arguments;
	}
	public Method getType() {
		return method;
	}
	public void setType(Method type) {
		this.method = type;
	}
	public VisualHTAction(Method type, List<Object> arguments) {
		super();
		this.arguments = arguments;
		this.method = type;
	}
	public VisualHTAction(String type, List<Object> arguments) throws NoSuchMethodException {
		super();
		this.arguments = arguments;

		for( Method m: LinearProbingHashST.class.getDeclaredMethods() ) {
			if( m.getName().equalsIgnoreCase(type) && Modifier.isPublic(m.getModifiers()) && m.getParameterTypes().length == arguments.size() ) {
				method = m;
				break;
			}
		}
		if( method == null )
			throw new NoSuchMethodException(type);
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(method.getName());
		for( int i = 0; i < arguments.size(); i++ ) {
			builder.append(' ');
			builder.append(arguments.get(i).toString());
		}
		return builder.toString();
	}
	
	public String getResult() {
		return text;
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
	public String act(LinearProbingHashST tree) {
		return act(tree, false, null);
	}
	public String act(LinearProbingHashST tree, boolean silent, Component parent) {
		Class<?>[] params = method.getParameterTypes();
		if( params.length > 0 ) {
			Integer[] vals = new Integer[arguments.size()];
			for( int i = 0; i < arguments.size(); i++ ) {
				vals[i] = ((Number)arguments.get(i)).intValue();
			}
			try {
				Object o = method.invoke(tree, vals);
				text = (o == null ? "null" : o.toString());
				if( method.getReturnType() != void.class && (o == null || o.getClass() != Void.class) && !silent )
					JOptionPane.showMessageDialog(parent, text, methodToString(method, vals), JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
				text = "An exception was thrown of type: " +e1.getClass() + ": " + e1.getMessage();
				if( !silent )
					JOptionPane.showMessageDialog(parent, text, methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
			}
		} else {
			try {
				Object o = method.invoke(tree, (Object[])null);
				text = (o == null ? "null" : o.toString());
				if( method.getReturnType() != void.class && (o == null || o.getClass() != Void.class) && !silent )
					JOptionPane.showMessageDialog(parent, text, methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
			} catch (Exception e1) {
				e1.printStackTrace();
				text = "An exception was thrown of type: " +e1.getClass() + ": " + e1.getMessage();
				if( !silent )
					JOptionPane.showMessageDialog(parent, text, methodToString(method, (Object[])null), JOptionPane.PLAIN_MESSAGE);
			}
		}
		return text;
	}
}
