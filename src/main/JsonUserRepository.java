import com.google.gson.Gson;

import java.io.*;

public class JsonUserRepository extends UserRepository {
    @Override
    public User Load(Long id) {
        try {
            BufferedReader br = new BufferedReader(new FileReader("users/" + id + ".json"));
            return new Gson().fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            return new User(id);
        }
    }

    @Override
    public void saveData(User user) throws IOException {
        File file = new File(String.format("users/%s.json", user.id));
        new File("users/").mkdirs();
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(new Gson().toJson(user));
        }
    }
}
