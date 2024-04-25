package xshape.Model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public interface Shape extends Composable, Prototypable
{
	Point position();
	Dimension size();
	void setPos(int posX, int posY);
	void setCenterToPos(int posX, int posY);
	Shape translate(Point vec);
	void setSize(int width, int height);

	boolean contains(int x, int y);

	void draw(Graphics g);
	void drawAt(Graphics g, int x, int y);
	void drawSelection(Graphics g);
}
