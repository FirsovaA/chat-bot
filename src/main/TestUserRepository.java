import java.util.HashMap;

public class TestUserRepository extends UserRepository {
    HashMap<String, User> storage;

    TestUserRepository(){
        storage = new HashMap<>();
    }

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
    public void saveData(User user) {
        storage.put(user.name, user);
    }
}
