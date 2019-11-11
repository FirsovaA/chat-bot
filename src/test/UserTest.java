import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class UserTest {
    private User user;

    @BeforeEach
    void setUp() {
        this.user = new User("");
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
        User tester = new User("tester");
        tester.addToHistory("The joke about a hat");
        tester.saveJokes(1);
        Assertions.assertEquals(tester.getFavourites(),"The joke about a hat");
    }

//    @Test
//    void saveExistingUserData() {
//
//    }
//
//    @Test
//    void loadNewUser() {
//
//    }
//
//    @Test
//    void loadUserFromFile(){
//
//    }
}