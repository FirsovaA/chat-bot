import com.google.gson.Gson;

import java.io.*;

public class JsonUserRepository extends UserRepository {
    @Override
    public User Load(String name) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("users/" + name + ".json"));
            return new Gson().fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            return new User(name);
        }
    }

    @Override
    public void saveData(User user) throws IOException {
        File file = new File(String.format("users/%s.json", user.name));
        new File("users/").mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new Gson().toJson(user));
        }
    }
}
