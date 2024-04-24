package xshape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface Shape // extends ShapeOperation
{
	Shape translate(Point vec);
	Point getPos();
	void setPos(int posX, int posY);
	Dimension getSize();
	void setSize(int width, int height);
	boolean isIn(int x, int y);
	void draw(Graphics g);
	void drawAt(Graphics g, int x, int y);
	void drawSelectionRect(Graphics g);
	Shape clone();
	// void add(Shape s)
	// PrototypeShape translateP(Point vec);
}
