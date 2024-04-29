package xshape.Save;

import java.awt.Point;
import java.util.ArrayList;

import xshape.Model.Dimension;
import xshape.Model.Polygon;
import xshape.Model.Shape;
import xshape.Model.ShapeGroup;

public class ShapeSave{
    public Point position;
    public Dimension size;
    public int nbSides;
    public double sideLength;
    public String type;
    public ArrayList<ShapeSave> shapes = new ArrayList<>();
    public ShapeSave(Shape s){
        this.position = s.position();
        this.size = s.size();
        this.type = s.name();
        if (s instanceof Polygon){
            nbSides = ((Polygon) s).nbSides();
            sideLength = ((Polygon) s).sideLength();
        }
        if (s instanceof ShapeGroup){
            ArrayList<Shape> shapesLocal = ((ShapeGroup)s).getShapeGroup();
            for(Shape p: shapesLocal){
                shapes.add(new ShapeSave(p));
            }
        }
    }
}