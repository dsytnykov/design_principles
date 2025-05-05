import java.io.Serial;
import java.io.Serializable;

public class SerializationSingleton implements Serializable {

    public static SerializationSingleton instance = new SerializationSingleton();

    private SerializationSingleton() {}

    @Serial
    protected Object readResolve() { return instance; }// or throw an exception
}
