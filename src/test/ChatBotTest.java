
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

//TODO main.ChatBot tests
class ChatBotTest {
    private ChatBot chatBot;
    JsonDataManager manager = new JsonDataManager();
    JokeGenerator generator = new WebJokeGenerator();

    @Test
    void replyToStart() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.START);
        String reply = chatBot.reply("hello");
        Assertions.assertEquals(reply, "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "What's your name?\n" );
    }

    @Test
    void replyToIntroducing() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.LOAD_USER);
        String reply = chatBot.reply("testUser");
        Assertions.assertEquals(reply, "hi testUser!\n"
                + "enter '/help' to find out what I can do" );
    }

    @Test
    void replyToInvalidUsername() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.LOAD_USER);
        String reply = chatBot.reply("?*Username");
        Assertions.assertEquals(reply, "invalid username? please try again");
    }

    @Test
    void replyToDefaultHelp() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.DEFAULT);
        String reply = chatBot.reply("/help");
        Assertions.assertEquals(reply, "Hi I'm Anekbot that'll tell u jokes if u want\n"
                + "U can ask me the following stuff:\n"
                + "\ttell a joke - i'll tell u a joke\n"
                + "\tsave to favourites - i'll save a given amount of jokes(or less if i hadn't told u that much)\n"
                + "\tshow favourites - i'll show u ur saved jokes\n"
                + "\t/help - i'll tell u what I can do\n"
                + "\t/exit - I'll go away and leave you alone\n");
    }

    @Test
    void replyToDefaultExit() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.LOAD_USER); //a little bit kostyl
        chatBot.reply("test");
        String reply = chatBot.reply("/exit");
        Assertions.assertEquals(reply, "Bye bye c:");
    }

    //TODO default exit with exception(?)

//    @Test
//    void replyToJokeRequest() {
//        chatBot = new ChatBot(manager, generator, ChatBot.State.DEFAULT);
//        String reply = chatBot.reply("tell a joke");
//        System.out.println(reply);
//        Assertions.assertEquals(reply, );
//    }

    //TODO joke request with exception

    @Test
    void replyToDefaultSaveToFavourites() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.DEFAULT);
        String reply = chatBot.reply("save to favourites");
        Assertions.assertEquals(reply, "how many?");
    }

    @Test
    void replyToSaveJokes() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.LOAD_USER); //a more than a little bit kostyl
        chatBot.reply("test");
        chatBot.reply("save to favourites");
        String reply = chatBot.reply("42");
        Assertions.assertEquals(reply, "saved!");
    }

    @Test
    void replyToSaveJokesWithNaNJokes() {
        chatBot = new ChatBot(manager, generator, ChatBot.State.LOAD_USER); //a more than a little bit kostyl
        chatBot.reply("test");
        chatBot.reply("save to favourites");
        String reply = chatBot.reply(" ");
        Assertions.assertEquals(reply, "please enter a number");
    }

    @Test
    void replyDefault(){
        chatBot = new ChatBot(manager, generator, ChatBot.State.DEFAULT);
        String reply = chatBot.reply("you are a joker");
        Assertions.assertEquals(reply, "uh sorry i don't understand you\ntry entering /help");
    }
}