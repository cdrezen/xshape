package xshape;

public class ShapeFactory 
{
    public ShapeFactory() {
    }
    public Rectangle createRectangle(double posX, double posY, 
    double height, double width) {
        return new RectangleAwt(posX, posY, height, width);
    }
}
