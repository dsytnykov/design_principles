public class CloneSingleton {

    public static CloneSingleton instance = new CloneSingleton();

    private CloneSingleton() {}

    @Override
    protected Object clone() throws CloneNotSupportedException
    {
        throw new CloneNotSupportedException(); //or just return instance
    }
}
