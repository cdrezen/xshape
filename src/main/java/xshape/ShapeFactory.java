package xshape;

public class ShapeFactory 
{
    public ShapeFactory() {
    }
    public Rectangle createRectangle(int posX, int posY, int height, int width) {
        return new Rectangle(posX, posY, height, width);
    }

    public Cercle createCircle(int posX, int posY, int height, int width) {
        return new Cercle(new CerclePrototype(posX, posY, height, width));
    }
}
