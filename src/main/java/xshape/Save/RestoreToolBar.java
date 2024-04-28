package xshape.Save;

import java.io.FileReader;

import com.google.gson.Gson;

import xshape.ShapeToolBar;

public class RestoreToolBar {
  String jsonString;
  final String filePath = "dataToolBar.json";
  Gson gson;

  public RestoreToolBar() {
    gson = new Gson();
  }

  public ShapeToolBar getShapeToolBar() {
    try (FileReader reader = new FileReader(filePath)) {
      StringBuilder sb = new StringBuilder();
      int ch;
      while ((ch = reader.read()) != -1) {
        sb.append((char) ch);
      }
      jsonString = sb.toString();
      return gson.fromJson(jsonString, ShapeToolBar.class);
    } catch (Exception e) {
      return new ShapeToolBar();
    }

  }
}
