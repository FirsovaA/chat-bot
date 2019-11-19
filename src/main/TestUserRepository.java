import java.io.IOException;
import java.util.HashMap;

public class TestUserRepository extends UserRepository {
    HashMap<String, User> storage = new HashMap<>();

    @Override
    public User Load(Long id) {
        try {
            return storage.get(id);
        }
        catch (Exception e) {
            return new User(id);
        }
    }

    @Override
    public void saveData(User user) throws IOException {
        storage.put(user.name, user);
    }
}