package xshape.Model;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.AffineTransform;
import java.lang.reflect.Array;
import java.awt.Point;
import java.util.ArrayList;

import javax.swing.text.Position;

public class ShapeGroup extends ShapeAbstact
{
    ArrayList<Shape> components;

    public ShapeGroup(Point position, Dimension size) {
        super(position, size);
        this.components = new ArrayList<Shape>();
        this.selectionColor = Color.cyan;
    }

    public ShapeGroup(int x, int y, int width, int height) {
        this(new Point(x, y), new Dimension(width, height));
    }

    public ShapeGroup()
    {
        this(0, 0, 0 ,0);
    }

    public ShapeGroup(Shape ... shapes)
    {
        this();
        for (Shape shape : shapes) {
            this.add(shape);
        }
    }

    public ArrayList<Shape> getShapeGroup(){
        return components;
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

            // shape.setCenterToPos((int)(rpos.x - ((double)rsize.width / 2.0) + posX),
            //                     (int)(rpos.y - ((double)rsize.height / 2.0) + posY));
            double x = (rpos.x - (rsize.width / 2.0) + posX) - (shape.size().width / 2.0);
            double y = (rpos.y - (rsize.height / 2.0) + posY) - (shape.size().height / 2.0);
            shape.setPos((int)x, (int)y);
        }
        super.setCenterToPos(posX, posY);
    }

    @Override
    public void setSize(int width, int height) {
       
        double qW = 0, qH = 0;

        if(this.size.width > 0) qW = (double)width / this.size.width;//+??
        if(this.size.height > 0) qH = (double)height/ this.size.height;

        //int diffW = width - size.width;
        //int diffH = height - size.height;

        for (Shape shape : components) {
            Dimension asize = shape.size();

            double awidth = (double)asize.width * qW;
            double aheight = (double)asize.height * qH;
            shape.setSize((int)awidth, (int)aheight);

            Point apos = shape.position();
            double ax = (double)apos.x * qW;
            double ay = (double)apos.y * qH;
            shape.setPos((int)ax, (int)ay);
        }
        super.setSize(width, height);
        recalculateBounds();
    }

    @Override
    public void scale(double scale) {
        // TODO Auto-generated method stub
        for (Shape shape : components) {
            shape.scale(scale);
        }
        super.scale(scale);
        recalculateBounds();
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
        resetCenter();
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
        drawSelection(g, true, selectionColor, DEFAULT_MARGIN);
    }

    @Override
    public void drawSelection(Graphics g, int margin) 
    {
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

        component.setCenter(this.center);

        //onPositionUpdate(component.position());
    }

    @Override
    public void add(Shape ... shapes) 
    {
        if(isEmpty())
        {
            this.position = shapes[0].position();
            this.size = shapes[0].size();
        }
        
        for (Shape shape : shapes) {
            this.components.add(shape);
            shape.setCenter(this.center);
        }
        
        recalculateBounds();
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
    public void remove(Shape ... shapes) {

        for (Shape shape : shapes) 
        {
            this.components.remove(shape);
            shape.resetCenter();   
        }

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

    @Override
    public Shape[] getChildren() {
        Shape[] arr = new Shape[this.components.size()];
        return this.components.toArray(arr);
    }

    //group ShapeGroup 'group' inside this
    public void group(ShapeGroup group)
    {
        if(this.components.size() == 1) return;
        this.remove(group.getChildren());
        this.add(group);
    }
    
    public void group(Shape ... shapes)
    {
        ShapeGroup group = new ShapeGroup(position, size);

        group.add(shapes);
        this.remove(shapes);
        this.add(group);
    }

    //ungroup ShapeGroup 'group' inside this
    public void ungroup(ShapeGroup group)
    {
        Shape[] children = group.getChildren();

        if(children == null || children.length != 1) return;

        this.add(children[0].getChildren());
        this.remove(children[0]);
    }

    // //group this inside this, need parent()?
    // public ShapeGroup group()
    // {
    //     if(this.components.size() == 1) 
    //     { 
    //         Shape shape = this.components.get(0);
    //         if(shape.getChildren() != null) ((ShapeGroup)shape).group(); 
    //         return this; 
    //     }
    //     Shape group = this.clone();
    //     this.components.clear();
    //     this.add(group);
    //     return this;
    // }

    // public ShapeGroup ungroup()
    // {
    //     ArrayList<Shape> toRemove = new ArrayList<>();
    //     ArrayList<Shape> toAdd = new ArrayList<>();

    //     for (Shape shape : components) {
    //         Shape[] children = shape.getChildren();
    //         if(children == null || children.length != 1) continue;
    //         System.out.println("boop");

    //         toRemove.add(shape);
    //         for (Shape child : children) {
    //             System.out.println("boop1");
    //             toAdd.add(child);
    //         }
    //     }

    //     for (Shape shape : toAdd) {
    //         this.add(shape);
    //     }

    //     for (Shape shape : toRemove) {
    //         this.remove(shape);
    //     }

    //     return this;
    // }
}
