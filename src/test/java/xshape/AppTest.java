package xshape;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xshape.Model.Cercle;
import xshape.Model.Dimension;
import xshape.Model.Polygon;
import xshape.Model.Rectangle;
import xshape.Model.Shape;
import xshape.Model.ShapeAbstact;
import xshape.Model.ShapeGroup;

import java.awt.Color;
import java.awt.Point;

/**
 * Unit test for simple App.
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue()
    {
        assertTrue( true );
    }

    @Test
    public void colorTest()
    {
        Cercle c = new Cercle(0, 0, 0, 0);
        assertTrue(c.getColor().equals(ShapeAbstact.DEFAULT_COLOR));
        c.setColor(Color.BLUE);
        assertTrue(c.getColor().equals(Color.BLUE));
    }

    // @Test
    // public void shapeTest()
    // {
    //     ShapeGroup group = new ShapeGroup(new Point(0, 0), new Dimension(500, 500));
    //     group.add(new Rectangle(new Point(100, 100), new Dimension(50, 50)));
    //     group.add(new Cercle(new Point(200, 200), new Dimension(50, 50)));
    //     group.add(new Polygon(new Point(300, 300), 3, 50));

        //save json
        //compare json

    // }

    @Test
    public void resizeTest()
    {
        Rectangle rec = new Rectangle(0, 0, 10, 10);
        assertTrue(rec.size().width == 10);
        assertTrue(rec.size().height == 10);
        rec.setSize(1,15);
        assertTrue(rec.size().width == 1);
        assertTrue(rec.size().height == 15);
    }
}
