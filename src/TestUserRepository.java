import java.io.IOException;
import java.util.HashMap;

public class TestUserRepository extends UserRepository {
    HashMap<String, User> storage;

    TestUserRepository(){
        storage = new HashMap<>();
    }

    @Override
    public User Load(String name) {
        try {
            return storage.get(name);
        }
        catch (Exception e) {
            User user =  new User(name);
            storage.put(name, user);
            return user;
        }
    }

    @Override
    public void saveData(User user) throws IOException {
        storage.put(user.name, user);
    }
}
