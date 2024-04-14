package xshape;

import java.awt.Graphics;

import javax.management.RuntimeErrorException;
/*
* Singleton to share Graphics between all AWT implementation and to 
* prevent to poluate the Shape interface.
*/
public class AwtContext {
    static private AwtContext _singleton = null;
    private Graphics _graphics = null;
    static public AwtContext instance() {
        if (_singleton == null)
            _singleton = new AwtContext();
        return _singleton;
    }
    private AwtContext() {
    }

    public Graphics graphics() {
        if (_graphics == null) 
            throw new RuntimeErrorException(null, "Graphics has not been set contex is not valid");
        return _graphics;
    }
  
    public AwtContext graphics(Graphics g) {
        _graphics = g;
        return this;
    }
}
