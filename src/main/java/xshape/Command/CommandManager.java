package xshape.Command;

import java.util.Stack;

public class CommandManager{
    private Stack<Command> commandStack;
    private Stack<Command> undoStack;

    public CommandManager() {
        this.commandStack = new Stack<>();
        this.undoStack = new Stack<>();
    }

    public void executeCommand(Command command) {
        command.execute();
        commandStack.push(command);
        this.undoStack = new Stack<>();
    }

    public void undo() {
        if (!commandStack.isEmpty()) {
            Command command = commandStack.pop();
            undoStack.push(command);
            command.undo();
        }
    }

    public void redo() {
        if (!undoStack.isEmpty()) {
            Command command = undoStack.pop();
            command.execute();
        }
    }
}
    

