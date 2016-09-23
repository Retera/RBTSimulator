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


public class VisualBSTState {
	RedBlackBST<?,?> tree;
    List<VisualBSTAction> actions;
	public VisualBSTState(RedBlackBST<?, ?> tree, List<VisualBSTAction> actions) {
		super();
		this.tree = tree;
		this.actions = new ArrayList<VisualBSTAction>(actions);
	}
	public VisualBSTState(RedBlackBST<?, ?> tree) {
		super();
		this.tree = tree;
		this.actions = new ArrayList<VisualBSTAction>();
	}
	public VisualBSTState copy() {
		RedBlackBST<?,?> copyTree = new RedBlackBST<Integer,Integer>();
		for(VisualBSTAction action: actions) {
			action.act(copyTree,true,null);
		}
		return new VisualBSTState(copyTree,actions);
	}
	public RedBlackBST<?, ?> getTree() {
		return tree;
	}
	public void setTree(RedBlackBST<?, ?> tree) {
		this.tree = tree;
	}
	public List<VisualBSTAction> getActions() {
		return actions;
	}
	public VisualBSTAction getLatestAction() {
		return actions.get(actions.size() - 1);
	}
	public void setActions(List<VisualBSTAction> actions) {
		this.actions = actions;
	}

	public void draw(Graphics g, Rectangle bounds) {
		
		((Graphics2D)g).setStroke(new BasicStroke(3));
		
		int middle = bounds.width / 2;
		
		Object root = getRoot();

		int height = tree.height();
		int numLeaves = (1 << height); // number of leaves
		//int maxSpacing = (numLeaves) * CIRCLE_SPACING;
		int circleSize = bounds.width / (numLeaves + 1);
		int circleSpacing = ((bounds.height) / (height+2));

		g.setFont(new Font("Arial",Font.BOLD,(circleSize * 2)/3));
		
		Point rootStart = new Point(middle, circleSpacing);
		drawTree(g,rootStart,root, 0, bounds.width, circleSize, circleSpacing);
	}
	
	public void drawTree(Graphics g, Point loc, Object node, int depth, int width, int circleSize, int circleSpacing) {
		if( node != null ) {
//			int size = size(node);
//			int xSpacing = (int)((CIRCLE_SIZE*2) * (int)(Math.log(size)/Math.log(2) -1));
//			if( xSpacing <= 0 ) {
//				xSpacing = (CIRCLE_SIZE)/2;
//				if( size > 1 )
//					xSpacing += CIRCLE_SIZE/4;
//			}
//			xSpacing *= 4;
//			xSpacing /= 3;
			int xSpacing = width / (4 << depth);
			
			Point left = new Point(loc.x - xSpacing, loc.y + circleSpacing);
			Point right = new Point(loc.x + xSpacing, loc.y + circleSpacing);
			g.setColor(Color.gray);
			g.drawLine(loc.x, loc.y, left.x, left.y);
			g.drawLine(loc.x, loc.y, right.x, right.y);

			if( isRed(node) )
				g.setColor(Color.red);
			else
				g.setColor(Color.black);
			g.fillOval(loc.x-circleSize/2, loc.y-circleSize/2, circleSize, circleSize);
			g.setColor(Color.blue);
			g.drawOval(loc.x-circleSize/2, loc.y-circleSize/2, circleSize, circleSize);
			g.setColor(Color.white);
			String toShow = getKey(node).toString();// + ": " + getValue(node).toString();

			g.setFont(new Font("Arial",Font.BOLD,circleSize));
			Rectangle2D bounds = g.getFont().getStringBounds(toShow, ((Graphics2D)g).getFontRenderContext());
			
			while(bounds.getWidth() > circleSize) {
				g.setFont(new Font("Arial",Font.BOLD,(g.getFont().getSize()*2) / 3));
				bounds = g.getFont().getStringBounds(toShow, ((Graphics2D)g).getFontRenderContext());
			}

			g.drawString(toShow, (int)(loc.x - bounds.getWidth()/2), (int)(loc.y + bounds.getHeight()/3));
//			g.setColor(Color.green);
//			toShow = getSize(node).toString();
//			bounds = g.getFont().getStringBounds(toShow, ((Graphics2D)g).getFontRenderContext());
//			g.drawString(toShow, (int)(loc.x - bounds.getWidth()/2), (int)(loc.y - bounds.getHeight()/2));
			drawTree(g,left,getLeft(node), depth+1, width, circleSize, circleSpacing);
			drawTree(g,right,getRight(node), depth+1, width, circleSize, circleSpacing);
		}
	}
	
	
	// hacks to get at the implementation
	
	public Object getRoot() {
		try {
			Field rootField = tree.getClass().getDeclaredField("root");
			rootField.setAccessible(true);
			return rootField.get(tree);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getLeft(Object node) {
		try {
			Field leftField = node.getClass().getDeclaredField("left");
			leftField.setAccessible(true);
			return leftField.get(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getRight(Object node) {
		try {
			Field leftField = node.getClass().getDeclaredField("right");
			leftField.setAccessible(true);
			return leftField.get(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getKey(Object node) {
		try {
			Field leftField = node.getClass().getDeclaredField("key");
			leftField.setAccessible(true);
			return leftField.get(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getSize(Object node) {
		try {
			Field leftField = node.getClass().getDeclaredField("N");
			leftField.setAccessible(true);
			return leftField.get(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Object getValue(Object node) {
		try {
			Field leftField = node.getClass().getDeclaredField("val");
			leftField.setAccessible(true);
			return leftField.get(node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Boolean isRed(Object node) {
		try {
			Method isRedMethod = null;
			Method[] methods = tree.getClass().getDeclaredMethods();
			for( Method method: methods )
				if( method.getName().equals("isRed") ) {
					isRedMethod = method;
					break;
				}
			isRedMethod.setAccessible(true);
			return (Boolean)isRedMethod.invoke(tree, node);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public Integer size(Object node) {
		try {
			Method sizeMethod = null;
			Method[] methods = tree.getClass().getDeclaredMethods();
			for( Method method: methods )
				if( method.getName().equals("size") && method.getParameterTypes().length == 1 ) {
					sizeMethod = method;
					break;
				}
			sizeMethod.setAccessible(true);
			return (Integer)sizeMethod.invoke(tree, node);
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
