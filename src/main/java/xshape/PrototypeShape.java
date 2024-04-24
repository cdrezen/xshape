package xshape;

import java.awt.Point;

public interface PrototypeShape extends ShapeOperation{
    PrototypeShape clone();
    PrototypeShape translate(Point vec);
}
