package xshape.Memento;

import com.google.gson.Gson;

import xshape.Whiteboard;

public class Memento {

    // private class Pair{
    //     Whiteboard whiteboard; ShapeToolBar shapeToolBar;
    //     public Pair(Whiteboard whiteboard, ShapeToolBar shapeToolBar){
    //         this.whiteboard = whiteboard;
    //         this.shapeToolBar = shapeToolBar;
    //     }
    // }
    // Pair pair;
    private String state;
    
    public Memento(Object state) {
        Gson gson = new Gson();
        this.state = gson.toJson(state);
    }

    public Memento(Whiteboard whiteboard) {
        Gson gson = new Gson();
        this.state = gson.toJson(whiteboard);
    }

    public Memento(String state) {
        this.state = state;
    }

    public String getState(){
        return state;
    }

    public void setState(String state){
        this.state = state;
    }

    public void setState(Object state){
        Gson gson = new Gson();
        this.state = gson.toJson(state);
    }
}
