public class ChatBot {
    public boolean over = false;
    private JokeGenerator jokeGenerator;

    public enum State{
        DEFAULT,
        START,
        SET_NAME,
        LOAD_USER,
        SAVE_JOKES
    }

    ChatBot(JokeGenerator generator){
        jokeGenerator = generator;
    }

    public String reply(String input, User user){
        switch (user.getState()){
            case START:
                user.setState(State.DEFAULT);
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "enter '/help' to find out what I can do";
            case SET_NAME:
                if (User.isValidUsername(input)) {
                    user.name = input.trim();
                    user.setState(State.DEFAULT);
                    return "hi " + user.name + "!";
                }
                else {
                    return "invalid username. please try again";
                }
            case SAVE_JOKES:
                try {
                    user.saveJokes(Integer.parseInt(input));
                }
                catch (java.lang.NumberFormatException e){
                    return "please enter a number";
                }
                user.setState(State.DEFAULT);
                return "saved!";
            default:
                return getDefaultStateReply(input.trim().toLowerCase(), user);
        }
    }

    private String getDefaultStateReply(String input, User user){
        switch (input){
            case "/help":
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "U can ask me the following stuff:\n"
                        + "\t/joke - i'll tell u a joke\n"
                        + "\tsave to favourites - i'll save a given amount of jokes(or less if i hadn't told u that much)\n"
                        + "\tshow favourites - i'll show u ur saved jokes\n"
                        + "\t/set_name - change ur current name\n"
                        + "\t/help - i'll tell u what I can do\n";
            case "/set_name":
                user.setState(State.SET_NAME);
                return "what is ur new name?";
            case "/joke":
                String joke;
                try {
                    joke = jokeGenerator.Generate();
                } catch (NoJokeException e) {
                    //TODO log(e);
                    return "something went wrong, no joke for today.. sorry :с";
                }
                user.addToHistory(joke);
                return joke;
            case "save to favourites":
                //TODO как человек сразу с кол-вом и 1 по-умолчанию сохранять
                user.setState(State.SAVE_JOKES);
                return "how many?";
            case "show favourites":
                return user.getFavourites();
            default:
                return "uh sorry i don't understand you\ntry entering /help";
        }
    }
}
