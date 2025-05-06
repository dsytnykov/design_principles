package command;

import receiver.Door;

public class GarageDoorUpCommand implements Command {
    Door door;

    public GarageDoorUpCommand(Door door) {
        this.door = door;
    }
    @Override
    public void execute() {
        door.up();
    }

    @Override
    public void undo() {
        door.down();
    }
}
