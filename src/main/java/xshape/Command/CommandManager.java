package xshape.Command;

import java.util.Stack;

import xshape.Canvas;

public class CommandManager{ // Singleton, Caretaker, Invoker
    private Originator originator;
    private Memento memento;
    private Stack<Memento> commandStack;
    private Stack<Memento> undoStack;

    private static CommandManager instance; 

    // private class Pair{
    //     Memento memento; Command command;
    //     public Pair(Memento m, Command c){
    //         this.memento = m;
    //         this.command = c;
    //     }
    //     public Memento memento() {
    //         return memento;
    //     }
    //     public Command command() {
    //         return command;
    //     }
    // }

    private CommandManager() {
        this.commandStack = new Stack<>();
        this.undoStack = new Stack<>();
        this.originator = new Originator();
    } 

    public static CommandManager getInstance() {
        if (instance == null) {
        instance = new CommandManager();
        }
        return instance;
    }

    public void executeCommand(Command command) {
        originator.setState(command);
        Memento m = originator.createMemento();
        commandStack.push(m);
        command.execute();
        undoStack.clear();
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Memento memento = commandStack.pop();
            undoStack.push(memento);
            originator.restoreFromMemento(memento);
            Command c = (Command)originator.getState();
            // c.getCanvas().repaint();
            // System.out.println(c.canvas.getSelection().getColor());
            c.undo();
            System.out.println("undo Dans CommandManager");
            
        }
    }

    public void redo() {
        if (!undoStack.isEmpty()) {
            Memento m = undoStack.pop();
            commandStack.push(m);
            originator.restoreFromMemento(m);
            Command c = (Command)originator.getState();
            c.execute();
            System.out.println("Redo Dans CommandManager");
        }
    }
}
    

