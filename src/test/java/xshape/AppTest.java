package xshape;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import xshape.Command.ColorCommand;
import xshape.Command.CommandManager;
import xshape.Model.Cercle;
import xshape.Model.Dimension;
import xshape.Model.Polygon;
import xshape.Model.Rectangle;
import xshape.Model.Shape;
import xshape.Model.ShapeAbstact;
import xshape.Model.ShapeGroup;
import xshape.Save.JsontoShape;
import xshape.Save.SaveToJson;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Scanner;

import javax.sound.midi.Patch;

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

    @Test
    public void shapeTest()
    {
        
        ShapeGroup group = new ShapeGroup(new Point(0, 0), new Dimension(500, 500));
        group.add(new Rectangle(new Point(100, 100), new Dimension(50, 50)));
        group.add(new Cercle(new Point(200, 200), new Dimension(50, 50)));
        group.add(new Polygon(new Point(300, 300), 3, 50));

        //save to json
        SaveToJson sauveur = new SaveToJson();
        sauveur.save(group.getShapeGroup(), "testshape.json");

        final String resFileName = "testref.json";

        File f = new File(resFileName);
        if(!f.exists()) 
        { 
            InputStream stream = App.class.getResourceAsStream('/' + resFileName);
            //Scanner scanner = new Scanner(stream, "UTF-8");
            //String resStr = scanner.next();
            //scanner.close();
            assertNotNull(stream);
            try 
            {
                f.createNewFile();
                Files.copy(stream, Paths.get(resFileName), StandardCopyOption.REPLACE_EXISTING);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        //compare json
        try {
            assertTrue(compareJsonFiles("testshape.json", resFileName));
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Test
    public void undoTest(){
        CommandManager commandManager = CommandManager.getInstance();
        Canvas c = new Canvas();
        SaveToJson stj = new SaveToJson();
        ShapeGroup shapeGroup = new ShapeGroup();
        shapeGroup.add(new Rectangle(0, 0, 10, 10));
        shapeGroup.add(new Cercle(0, 0, 0, 0));
        c.set_shapes(shapeGroup);
        c._selectedShapes = c._shapes;
        stj.save(c.getShapes().getShapeGroup(), "test.json");
        commandManager.executeCommand(new ColorCommand(c, Color.red));
        stj.save(c.getShapes().getShapeGroup(), "test2.json");
        commandManager.undo();
        stj.save(c.getShapes().getShapeGroup(), "test3.json");
        try {
            assertFalse(compareJsonFiles("test.json", "test2.json"));
            assertTrue(compareJsonFiles("test.json", "test3.json"));
        } catch (Exception e) {
            System.out.println("Erreur lecture");
        }
        

    }

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

    public static boolean compareJsonFiles(String filePath1, String filePath2) throws Exception {
        BufferedReader reader1 = new BufferedReader(new FileReader(filePath1));
        BufferedReader reader2 = new BufferedReader(new FileReader(filePath2));

        String line1, line2;
        while ((line1 = reader1.readLine()) != null && (line2 = reader2.readLine()) != null) {
            if (!line1.equals(line2)) {
                return false;
            }
        }

        // Check if remaining lines are equal (if any)
        return (reader1.readLine() == null && reader2.readLine() == null);
    }
}
