package xshape;

import java.awt.Dimension;
import java.awt.Graphics;


public interface ShapeOperation {
	void setPos(int posX, int posY);
	Dimension getSize();
	boolean isIn(int x, int y);
	void draw(Graphics g);
	void drawAt(Graphics g, int x, int y);
	void drawSelectionRect(Graphics g);
}
