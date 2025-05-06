package command;

import receiver.Door;

public class GarageDoorDownCommand implements Command {
    Door door;

    public GarageDoorDownCommand(Door door) {
        this.door = door;
    }
    @Override
    public void execute() {
        door.down();
    }

    @Override
    public void undo() {
        door.up();
    }
}
