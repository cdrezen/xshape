package xshape.Save;

import java.awt.Dimension;
import java.awt.Point;
import xshape.Model.Polygon;
import xshape.Model.Shape;

public class ShapeSave{
    public Point position;
    public Dimension size;
    public int nbSides, sideLength;
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