import java.awt.Component;
import java.awt.Rectangle;

import javax.swing.DefaultListCellRenderer;
import javax.swing.ImageIcon;
import javax.swing.JList;


public class VisualHTStateRenderer extends DefaultListCellRenderer {
	
	VisualHTModel model;
	
	public VisualHTStateRenderer(VisualHTModel model) {
		this.model = model;
	}

	@Override
	public Component getListCellRendererComponent(JList<? extends Object> jList,
			Object value, int index, boolean isSelected, boolean cellHasFocus) {
		super.getListCellRendererComponent(jList, "", index, isSelected, cellHasFocus);
		
		VisualHTState state = (VisualHTState)value;
		int heightVal = model.getParent().getTimeTrack().getHeight();
		Rectangle imgSize = new Rectangle(0,0,heightVal,heightVal);
		if( imgSize.width < getWidth() )
			imgSize.width = getWidth();
		if( imgSize.height < getHeight() )
			imgSize.height = getHeight();
		this.setIcon(new ImageIcon(state.getImage(imgSize)));
		return this;
	}

}
