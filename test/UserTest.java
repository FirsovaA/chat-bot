import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User(0L);
    }

    @Test
    void saveJokes() {
        String[] test_strings = {"a", "b", "c"};
        for (String test_string: test_strings) {
            user.addToHistory(test_string);
        }
        user.saveJokes(3);
        Assertions.assertEquals(user.getFavourites(), String.join("\n***\n", test_strings));
    }

    @Test
    void saveLessThanGiven(){
        String[] test_strings = {"a", "b", "c"};
        for (String test_string: test_strings) {
            user.addToHistory(test_string);
        }
        user.saveJokes(2);
        Assertions.assertEquals(user.getFavourites(), String.join("\n***\n", new String[] {"b", "c"}));
    }


    @Test
    void saveRepetitive() {
        user.addToHistory("a");
        user.addToHistory("a");
        user.saveJokes(2);
        Assertions.assertEquals(user.getFavourites(), "a");
    }

    @Test
    void saveMoreThanAvailable() {
        user.addToHistory("a");
        user.saveJokes(5);
        Assertions.assertEquals(user.getFavourites(), "a");
    }

    @Test
    void saveNewUserData() {
        user.addToHistory("The joke about a hat");
        user.saveJokes(1);
        Assertions.assertEquals(user.getFavourites(),"The joke about a hat");
    }
}