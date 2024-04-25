package xshape.Model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public abstract class ShapeAbstact implements Shape
{
    public Point position;
    public Dimension size;

    public ShapeAbstact(Point position, Dimension size)
    {
        this.position = position;
        this.size = size;
    }

    public ShapeAbstact(int x, int y, int width, int height)
    {
        this(new Point(x, y), new Dimension(width, height));
    }

    @Override
    public Point position() {
        return this.position;
    }

    @Override
    public Dimension size() {
        return this.size;
    }

    @Override
    public void setPos(int posX, int posY) {
        this.position.x = posX;
        this.position.y = posY;
    }

    @Override
    public void setCenterToPos(int posX, int posY)
    {
        this.position.x = posX - (this.size.width / 2);
        this.position.y = posY - (this.size.height / 2);
    }

    @Override
    public void setSize(int width, int height) {
        this.size.width = width;
        this.size.height = height;
    }

    @Override
    public Shape translate(Point vec) {
        setPos(this.position.x + vec.x, this.position.y + vec.y);
        return this;
    }

    @Override
    public void draw(Graphics g) {
        drawAt(g, this.position.x, this.position.y);
    }

    @Override
    public Shape clone() {
        throw new UnsupportedOperationException();
    }
}
