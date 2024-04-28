package xshape.Save;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import xshape.Model.Cercle;
import xshape.Model.Polygon;
import xshape.Model.Rectangle;
import xshape.Model.Shape;

public class JsontoShape {
    String filePath = "canvas.json";
    String jsonString;
    ArrayList<Object> shapeSaves = new ArrayList<>();
    ArrayList<Shape> shapes = new ArrayList<>();

    public String readFile(String filePath) throws IOException {
        byte[] bytes = Files.readAllBytes(Paths.get(filePath));
        return new String(bytes);
    }

    public ArrayList<Shape> getShape(String filePathL) throws IOException {
        ArrayList<Shape> shapes = new ArrayList<>();
        Gson gson = new Gson();
        jsonString = readFile(filePathL);
        TypeToken<ArrayList<ShapeSave>> typeToken = new TypeToken<ArrayList<ShapeSave>>() {
        };
        shapeSaves = gson.fromJson(jsonString, typeToken.getType());
        for (Object oj : shapeSaves) {
            ShapeSave o = (ShapeSave) oj;
            if (o.type.equals("Rectangle")) {
                shapes.add(new Rectangle(o.position, o.size));
            } else if (o.type.equals("Cercle")) {
                shapes.add(new Cercle(o.position, o.size));
            } else {
                shapes.add(new Polygon(o.position, o.nbSides, o.sideLength));
            }
        }
        return shapes;
    }

}
