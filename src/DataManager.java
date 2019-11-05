import java.io.IOException;

public abstract class DataManager {
    public abstract User Load(String name);
    abstract public void saveData(User user) throws IOException;
}
