import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.List;


public class VisualHTState {
	LinearProbingHashST<?,?> tree;
    List<VisualHTAction> actions;
	public VisualHTState(LinearProbingHashST<?, ?> tree, List<VisualHTAction> actions) {
		super();
		this.tree = tree;
		this.actions = new ArrayList<VisualHTAction>(actions);
	}
	public VisualHTState(LinearProbingHashST<?, ?> tree) {
		super();
		this.tree = tree;
		this.actions = new ArrayList<VisualHTAction>();
	}
	public VisualHTState copy() {
		LinearProbingHashST<?,?> copyTree = new LinearProbingHashST<Integer,Integer>(getM());
		for(VisualHTAction action: actions) {
			action.act(copyTree,true,null);
		}
		return new VisualHTState(copyTree,actions);
	}
	public LinearProbingHashST<?, ?> getTree() {
		return tree;
	}
	public void setTree(LinearProbingHashST<?, ?> tree) {
		this.tree = tree;
	}
	public List<VisualHTAction> getActions() {
		return actions;
	}
	public VisualHTAction getLatestAction() {
		return actions.get(actions.size() - 1);
	}
	public void setActions(List<VisualHTAction> actions) {
		this.actions = actions;
	}

	public void draw(Graphics g, Rectangle bounds) {
		
		((Graphics2D)g).setStroke(new BasicStroke(3));
//		
		int middle = bounds.width / 2;
		
//		
//		Object root = getRoot();
//
//		int height = tree.height();
//		int numLeaves = (1 << height); // number of leaves
//		//int maxSpacing = (numLeaves) * CIRCLE_SPACING;
//		int circleSize = bounds.width / (numLeaves + 1);
//		int circleSpacing = ((bounds.height) / (height+2));
		Comparable[] keys = getKeys();
		Object[] values = getValues();
//
		Font f;
		g.setFont(f = new Font("Arial",Font.BOLD,bounds.width / (keys.length)));
		int spacingMax = 0;

		for( int i = 0; i < keys.length; i++ ) {
			String text = keys[i] == null ? " " : keys[i] + "";
			int tempSpacing = (int)f.getStringBounds(text, ((Graphics2D)g).getFontRenderContext()).getWidth();
			if( tempSpacing > spacingMax ) {
				spacingMax = tempSpacing;
			}
		}
		int spacing = (int)(spacingMax * 1.25f);//(int)(f.getSize() * 2.25f);
		if( spacing < 1 )
			spacing = 1;
		int units = bounds.width / spacing;
		if( units < 1 )
			units = 1;
		for( int i = 0; i < keys.length; i++ ) {
			g.setColor(keys[i] == null ? Color.black : Color.white);
			g.fillRect(spacing * ((i) % (units)), f.getSize() * 3 * ((int)(i / (units)))+f.getSize()*2, spacing, (int)(f.getSize()*1.25f));
			g.setColor(Color.blue);
			g.drawRect(spacing * ((i) % (units)), f.getSize() * 3 * ((int)(i / (units)))+f.getSize()*2, spacing, (int)(f.getSize()*1.25f));
			g.setColor(Color.black);
			String text = keys[i] == null ? " " : keys[i] + "";
			g.drawString(text,  + spacing * ((i) % (units)) + (int)((spacing - f.getStringBounds(text, ((Graphics2D)g).getFontRenderContext()).getWidth())/2), f.getSize() * 3 * ((int)(i / (units))+1));
		}
//		for( int i = 0; i < values.length; i++ ) {
//			g.drawString(values[i] + "", i * (f.getSize()*5), f.getSize() * 3);
//		}
//		
//		Point rootStart = new Point(middle, circleSpacing);
//		drawTree(g,rootStart,root, 0, bounds.width, circleSize, circleSpacing);
	}
	
	// hacks to get at the implementation

	public Comparable<?>[] getKeys() {
		try {
			Field rootField = tree.getClass().getDeclaredField("keys");
			rootField.setAccessible(true);
			return (Comparable[])rootField.get(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object[] getValues() {
		try {
			Field rootField = tree.getClass().getDeclaredField("vals");
			rootField.setAccessible(true);
			return (Object[])rootField.get(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer getM() {
		try {
			Field rootField = tree.getClass().getDeclaredField("M");
			rootField.setAccessible(true);
			return (Integer)rootField.get(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Image getImage(Rectangle imgSize) {
		BufferedImage img = new BufferedImage(imgSize.height, imgSize.width, BufferedImage.TYPE_4BYTE_ABGR);
		Graphics g = img.getGraphics();
		g.setColor(Color.black);
		g.drawRect(0, 0, imgSize.width, imgSize.height);
		draw(g, imgSize);
		
		g.dispose();
		return img;
	}
}
