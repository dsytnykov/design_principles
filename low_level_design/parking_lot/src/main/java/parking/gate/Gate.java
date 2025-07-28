package parking.gate;

public abstract class Gate {
    private final String id;

    public abstract GateType getType();

    public Gate(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
