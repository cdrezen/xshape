package xshape.Save;

import java.awt.Point;

import xshape.Model.Dimension;
import xshape.Model.Polygon;
import xshape.Model.Shape;

public class ShapeSave{
    public Point position;
    public Dimension size;
    public int nbSides;
    public double sideLength;
    public String type;
    public ShapeSave(Shape s){
        this.position = s.position();
        this.size = s.size();
        this.type = s.name();
        if (s instanceof Polygon){
            nbSides = ((Polygon) s).nbSides();
            sideLength = ((Polygon) s).sideLength();
        }
    }
}