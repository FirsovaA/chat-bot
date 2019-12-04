
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


class ChatBotTest {
    private ChatBot chatBot;
    private JokeGenerator generator = new TestJokeGenerator();
    private User user = new User(-1L);

    @BeforeEach
    void setUp(){
        chatBot = new ChatBot(generator);
    }

    @Test
    void replyToStart() {
        user.setState(ChatBot.State.START);
        String reply = chatBot.reply("hello", user);
        Assertions.assertEquals(reply, "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "What's your name?\n" );
    }

    @Test
    void replyToIntroducing() {
        chatBot = new ChatBot(generator);
        user.setState(ChatBot.State.SET_NAME);
        String reply = chatBot.reply("testUser", user);
        Assertions.assertEquals(reply, "hi testUser!\n"
                + "enter '/help' to find out what I can do" );
    }

    @Test
    void replyToInvalidUsername() {
        user.setState(ChatBot.State.SET_NAME);
        String reply = chatBot.reply("?*Username", user);
        Assertions.assertEquals(reply, "invalid username. please try again");
    }

    @Test
    void replyToDefaultHelp() {
        user.setState(ChatBot.State.DEFAULT);
        String reply = chatBot.reply("/help", user);
        Assertions.assertEquals(reply, "Hi I'm Anekbot that'll tell u jokes if u want\n"
                + "U can ask me the following stuff:\n"
                + "\t/joke - i'll tell u a joke\n"
                + "\t/save_favourites - i'll save a given amount of jokes(or less if i hadn't told u that much)\n"
                + "\t/favourites - i'll show u ur saved jokes\n"
                + "\t/set_name - change ur current name\n"
                + "\t/help - i'll tell u what I can do\n");
    }

//    @Test
//    void replyToDefaultExit() {
//        chatBot = new ChatBot(generator); //a little bit kostyl
//        user.setState(ChatBot.State.LOAD_USER);
//        chatBot.reply("test", user);
//        String reply = chatBot.reply("/exit", user);
//        Assertions.assertEquals(reply, "Bye bye c:");
//    }

    //TODO default exit with exception(?)

//    @Test
//    void replyToJokeRequest() {
//        String reply = chatBot.reply("tell a joke");
//        System.out.println(reply);
//        Assertions.assertEquals(reply, );
//    }

    //TODO joke request with exception

    @Test
    void replyToDefaultSaveToFavourites() {
        user.setState(ChatBot.State.DEFAULT);
        String reply = chatBot.reply("/save_favourites", user);
        Assertions.assertEquals(reply, "how many?");
    }

    @Test
    void replyToSaveJokes() {
        user.setState(ChatBot.State.SET_NAME);
        chatBot.reply("", user);
        chatBot.reply("/save_favourites", user);
        String reply = chatBot.reply("42", user);
        Assertions.assertEquals(reply, "saved!");
    }

    @Test
    void replyToSaveJokesWithNaNJokes() {
        user.setState(ChatBot.State.SET_NAME);
        chatBot.reply("", user);
        chatBot.reply("/save_favourites", user);
        String reply = chatBot.reply(" ", user);
        Assertions.assertEquals(reply, "please enter a number");
    }

    @Test
    void replyDefault(){
        user.setState(ChatBot.State.DEFAULT);
        String reply = chatBot.reply("you are a joker", user);
        Assertions.assertEquals(reply, "uh sorry i don't understand you\ntry entering /help");
    }
}