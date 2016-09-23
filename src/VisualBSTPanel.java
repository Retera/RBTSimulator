import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.geom.Rectangle2D;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import javax.swing.JPanel;


public class VisualBSTPanel extends JPanel {
	public static final int CIRCLE_SIZE = 24;
	public static final int CIRCLE_SPACING = (CIRCLE_SIZE * 3) / 2;
	
	VisualBSTModel tree;
	
	public VisualBSTPanel(VisualBSTModel tree) {
		setPreferredSize(new Dimension(800,400));
		this.tree = tree;
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		tree.getState().draw(g, getBounds());
	}
}
