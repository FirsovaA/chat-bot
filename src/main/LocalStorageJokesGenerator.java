import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

public class LocalStorageJokesGenerator extends JokeGenerator {
    @Override
    public String Generate() throws NoJokeException {
        try {
            BufferedReader br = new BufferedReader(new FileReader("jokes.json"));
            Random rand = new Random();
            var a = new Gson().fromJson(br, String[].class);
            return a[(rand.nextInt(a.length))];
        } catch (FileNotFoundException e) {
            throw new NoJokeException(e);
        }
    }
}
