package xshape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class ShapeGroup implements Shape {

    @Override
    public void setPos(int posX, int posY) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPos'");
    }

    @Override
    public Dimension getSize() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getSize'");
    }

    @Override
    public boolean isIn(int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isIn'");
    }

    @Override
    public void draw(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'draw'");
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawAt'");
    }

    @Override
    public void drawSelectionRect(Graphics g) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'drawSelectionRect'");
    }

    @Override
    public Shape translate(Point vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }

    
    public Shape clone() {
        throw new UnsupportedOperationException("Unimplemented method 'clone'");
    }


    @Override
    public Point getPos() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPos'");
    }

    @Override
    public void setSize(int width, int height) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setSize'");
    }
}
