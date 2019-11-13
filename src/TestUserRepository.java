import java.io.IOException;
import java.util.HashMap;

public class TestUserRepository extends UserRepository {
    HashMap<String, User> storage = new HashMap<>();

    @Override
    public User Load(String name) {
        try {
            return storage.get(name);
        }
        catch (Exception e) {
            return new User(name);
        }
    }

    @Override
    public void saveData(User user) throws IOException {
        storage.put(user.name, user);
    }
}
