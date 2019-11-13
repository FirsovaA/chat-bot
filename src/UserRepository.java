import java.io.IOException;

public abstract class UserRepository {
    public abstract User Load(String name);
    abstract public void saveData(User user) throws IOException;
}
