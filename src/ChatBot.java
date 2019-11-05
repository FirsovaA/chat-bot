import java.io.IOException;

 public class ChatBot {
    public boolean over = false;
    private User user;
    private DataManager dataManager;
    private JokeGenerator jokeGenerator;

    private enum State{
        DEFAULT,
        START,
        LOAD_USER,
        SAVE_JOKES
    }

    ChatBot(DataManager manager, JokeGenerator generator){
        dataManager = manager;
        jokeGenerator = generator;
    }

    private State state = State.START;

    public String reply(String input){
        switch (state){
            case START:
                state = State.LOAD_USER;
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "What's your name?\n";
            case LOAD_USER:
                if (User.isValidUsername(input)) {
                    user = dataManager.Load(input.trim());
                    state = State.DEFAULT;
                    return "hi " + user.name + "!\n"
                            + "enter '/help' to find out what I can do";
                }
                else {
                    return "invalid username? please try again";
                }
            case SAVE_JOKES:
                try {
                    user.saveJokes(Integer.parseInt(input));
                }
                catch (java.lang.NumberFormatException e){
                    return "please enter a number";
                }
                state = State.DEFAULT;
                return "saved!";
            default:
                return getDefaultStateReply(input.trim().toLowerCase());
        }
    }

    private String getDefaultStateReply(String input){
        switch (input){
            case "/help":
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "U can ask me the following stuff:\n"
                        + "\ttell a joke - i'll tell u a joke\n"
                        + "\tsave to favourites - i'll save a given amount of jokes(or less if i hadn't told u that much)\n"
                        + "\tshow favourites - i'll show u ur saved jokes\n"
                        + "\t/help - i'll tell u what I can do\n"
                        + "\t/exit - I'll go away and leave you alone\n";
            case "/exit":
                over = true;
                try {
                    dataManager.saveData(user);
                    return "Bye bye c:";
                } catch (IOException e) {
                    //TODO просить сохранять еще раз или выйти без сохранения
                    return "Something went wrong, i didn't save anything sorry bye";
                }
            case "tell a joke":
                String joke;
                try {
                    joke = jokeGenerator.Generate();
                } catch (IOException e) {
                    return "something went wrong, no joke for today.. sorry";
                }
                user.addToHistory(joke);
                return joke;
            case "save to favourites":
                //TODO как человек сразу с кол-вом и 1 по-умолчанию сохранять
                state = State.SAVE_JOKES;
                return "how many?";
            case "show favourites":
                return user.getFavourites();
            default:
                return "uh sorry i don't understand you\ntry entering /help";
        }
    }
}
