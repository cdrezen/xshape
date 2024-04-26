package xshape.Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
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

    private Point relativePos(Shape shape)
    {
        return new Point(Math.abs(this.position.x - shape.position().x), 
                        Math.abs(this.position.y - shape.position().y));
    }

    private Dimension relativeSize(Shape shape)
    {
        return new Dimension(Math.abs(this.size.width - shape.size().width), 
                        Math.abs(this.size.height - shape.size().height));
    }


    @Override
    public void setPos(int posX, int posY) {
        // TODO Auto-generated method stub
        for (Shape shape : components) 
        {
            Point rpos = relativePos(shape);
            shape.setPos(rpos.x + posX,
                         rpos.y + posY);
        }
        super.setPos(posX, posY);
    }

    @Override
    public void setCenterToPos(int posX, int posY) {
        // TODO Auto-generated method stub
        for (Shape shape : components) {
            Point rpos = relativePos(shape);
            Dimension rsize = relativeSize(shape);
            // shape.setCenterToPos((int)(rpos.x - ((double)rsize.width / 2) + posX),
            //                     (int)(rpos.y - ((double)rsize.height / 2) + posY));
            double x = (rpos.x - (rsize.width / 2.0) + posX) - (shape.size().width / 2.0);
            double y = (rpos.y - (rsize.height / 2.0) + posY) - (shape.size().height / 2.0);
            shape.setPos((int)x, (int)y);
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
    public void resetCenter() {
        // TODO Auto-generated method stub
        super.resetCenter();
        if(components == null) return;
        for (Shape shape : components) {
            shape.setCenter(center);
        }
    }

    @Override
    public void setDegrees(int degrees) {
        for (Shape shape : components) {
            shape.setDegrees(degrees);
        }
        super.setDegrees(degrees);
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
        int minX, minY, maxX, maxY;
        minX = minY = Integer.MAX_VALUE;
        maxX = maxY = 0;

        for (Shape shape : components) {

            Point pos = shape.position();
            
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);

            Dimension sz = shape.size();
            maxX = Math.max(maxX, pos.x + sz.width); 
            maxY = Math.max(maxY, pos.y + sz.height);
        }

        this.position = new Point(minX, minY);
        this.size = new Dimension(maxX - minX, maxY - minY);
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
    public void drawSelection(Graphics g)
    {
        drawSelection(g, true, Color.GREEN, DEFAULT_MARGIN);
    }

    @Override
    public void drawSelection(Graphics g, int margin) 
    {
        for (Shape shape : components) {
            shape.drawSelection(g);
        }

        Graphics2D g2d = (Graphics2D)g;

        Color oldColor = g2d.getColor();
        Stroke oldStroke = g2d.getStroke();

        g2d.setStroke(DASHED);
        g2d.setColor(Color.green);

        g2d.drawRect(position.x, position.y, size.width, size.height);

        g2d.setColor(oldColor);
        g2d.setStroke(oldStroke);
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

        component.setCenter(center);

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

        component.resetCenter();
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
