package xshape.Model;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.text.Position;

public class ShapeGroup extends ShapeAbstact
{
    ArrayList<Shape> components;

    public ShapeGroup(Point position, Dimension size) {
        super(position, size);
        this.components = new ArrayList<Shape>();
    }

    public ShapeGroup(int x, int y, int width, int height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    public ShapeGroup()
    {
        this(0, 0, 0 ,0);
    }


    @Override
    public void setPos(int posX, int posY) {
        // TODO Auto-generated method stub
        for (Shape shape : components) {
            shape.setPos(Math.abs(this.position.x - shape.position().x) + posX,
                         Math.abs(this.position.y - shape.position().y) + posY);
        }
        super.setPos(posX, posY);
    }

    @Override
    public void setCenterToPos(int posX, int posY) {
        // TODO Auto-generated method stub
        for (Shape shape : components) {
            //shape.translate(new Point(Math.abs(this.position.x - posX), Math.abs(this.position.y - posY)));
            shape.setCenterToPos(Math.abs(this.position.x - shape.position().x) / 2 + posX,
                                Math.abs(this.position.y - shape.position().y) / 2 + posY);
        }
        super.setCenterToPos(posX, posY);
    }

    @Override
    public void setSize(int width, int height) {
        
        int diffW = size.width - width;
        int diffH = size.height - height;
        for (Shape shape : components) {
            Dimension sz = shape.size();
            shape.setSize(Math.abs(sz.width + diffW), Math.abs(height + diffH));
        }
        super.setSize(width, height);
    }

    @Override
    public Shape translate(Point vec) {
        // TODO Auto-generated method stub
        return super.translate(vec);
    }

    @Override
    public boolean contains(int x, int y) 
    {
        for (Shape shape : components) {
            if(shape.contains(x, y)) return true;
        }

        return false;
    }

    public boolean isEmpty() { return components.isEmpty(); }

    private void onPositionUpdate(Point position)
    {
        this.position.x = Math.min(this.position.x, position.x);
        this.position.y = Math.min(this.position.y, position.y);
    }

    private void recalculateBounds()
    {
        int minX, minY, maxW, maxH;
        minX = minY = Integer.MAX_VALUE;
        maxW = maxH = 0;

        for (Shape shape : components) {
            Point pos = shape.position();
            Dimension sz = shape.size();
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
            maxW = Math.max(maxW, Math.abs(pos.x - this.position.x) + sz.width); 
            maxH = Math.max(maxH, Math.abs(pos.y - this.position.y) + sz.height);
        }

        this.position = new Point(minX, minY);
        this.size = new Dimension(maxW, maxH);
    }

    public ShapeGroup getShapesAt(int x, int y)
    {
        ShapeGroup group = new ShapeGroup(new Point(position), new Dimension(size));

        for (Shape shape : components) {
            if(shape.contains(x, y)) group.add(shape);
            //else System.out.println(x + " " + y + " not in " + shape.position() + " " + shape.size());
        }

        return group.isEmpty() ? null : group;
    }

    public ShapeGroup getShapesIn(Rectangle rect)
    {
        ShapeGroup group = new ShapeGroup(new Point(position), new Dimension(size));

        for (Shape shape : components) {
            Point pos = shape.position();
            if(rect.contains(pos.x, pos.y)) group.add(shape);
        }

        return group.isEmpty() ? null : group;
    }

    public ShapeGroup getShapesAt(Point position) { return  getShapesAt(position.x, position.y); }

    // public ShapeGroup getShapesIn(Point position, Dimension size)
    // {
    //     ShapeGroup group = new ShapeGroup(new Point(position), new Dimension(size));
        
    //     for (Shape s : components) {
    //         if(s.contains(shape)) group.add(s);
    //     }

    //     return group;
    // }

    @Override
    public void draw(Graphics g) {
        for (Shape shape : components) {
            shape.draw(g);
        }
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        for (Shape shape : components) {
            shape.drawAt(g, x, y);
        }
    }

    @Override
    public void drawSelection(Graphics g) {
        for (Shape shape : components) {
            shape.drawSelection(g);
        }
        g.drawRect(position.x, position.y, size.width, size.height);
    }

    @Override
    public void add(Shape component) 
    {
        if(isEmpty())
        {
            this.position = component.position();
            this.size = component.size();
            this.components.add(component);
        }
        else
        {
            this.components.add(component);
            recalculateBounds();
        }

        //onPositionUpdate(component.position());
    }

    @Override
    public void remove(Shape component) {
        this.components.remove(component);
        if(components.isEmpty())
        {
            this.position = new Point(0, 0);
            this.size = new Dimension(0,0);
        }
        else
        {
            recalculateBounds();
        }
    }

    @Override
    public Shape clone()
    {
        System.err.println("group clone");

        ShapeGroup group = new ShapeGroup(new Point(position), new Dimension(size));
        for (Shape shape : components) {
            Shape newShape = shape.clone();
            group.add(newShape);
        }
        return group;
    }
}
