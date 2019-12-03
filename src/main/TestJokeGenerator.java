import java.util.ArrayList;

public class TestJokeGenerator extends JokeGenerator {
    public ArrayList jokes = new ArrayList();

    @Override
    public String Generate() throws NoJokeException {
        return "spider is the only web developer that loves finding bugs";
    }
}
