package xshape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

public class Cercle implements Shape {

    private PrototypeShape _c;

    public Cercle(CerclePrototype c){
        _c = c;
    }
    public Cercle(PrototypeShape c){
        _c = c;
    }

    @Override
    public void setPos(int posX, int posY) {
        _c.setPos(posX, posY);
    }

    @Override
    public Dimension getSize() {
        return _c.getSize();
    }

    @Override
    public boolean isIn(int x, int y) {
        return _c.isIn(x, y);
    }

    @Override
    public void draw(Graphics g) {
        _c.draw(g);
    }

    @Override
    public void drawAt(Graphics g, int x, int y) {
        _c.drawAt(g, x, y);
    }

    @Override
    public void drawSelectionRect(Graphics g) {
        _c.drawSelectionRect(g);
    }


    @Override
    public Shape translate(Point vec) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'translate'");
    }


    // @Override
    // public void add(Shape s) {
    //     throw new UnsupportedOperationException("Unimplemented method 'add'");
    // }

    public Shape clone() {
        throw new UnsupportedOperationException("Unimplemented method 'clone'");
    }


    // @Override
    // public PrototypeShape translateP(Point vec) {
    //     // TODO Auto-generated method stub
    //     throw new UnsupportedOperationException("Unimplemented method 'translateP'");
    // }
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
