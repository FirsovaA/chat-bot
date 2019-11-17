public class ChatBot {
    public boolean over = false;
    private User user;
    private UserRepository userRepository;
    private JokeGenerator jokeGenerator;
    private State state;

    public enum State{
        DEFAULT,
        START,
        LOAD_USER,
        SAVE_JOKES
    }

    ChatBot(UserRepository repository, JokeGenerator generator, State stateFrom){
        userRepository = repository;
        jokeGenerator = generator;
        state = stateFrom;
    }

    public String reply(String input){
        switch (state){
            case START:
                state = State.LOAD_USER;
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "What's your name?\n";
            case LOAD_USER:
                if (User.isValidUsername(input)) {
                    user = userRepository.Load(input.trim());
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
                    userRepository.saveData(user);
                }
                catch (java.lang.NumberFormatException e){
                    return "please enter a number";
                }
                catch (Exception e){
                    ErrLogger.log(e);
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
                return "Bye bye c:";
            case "tell a joke":
                String joke;
                try {
                    joke = jokeGenerator.Generate();
                } catch (NoJokeException e) {
                    ErrLogger.log(e.cause);
                    return "something went wrong, no joke for today.. sorry :с";
                }
                user.addToHistory(joke);
                try {
                    userRepository.saveData(user);
                }
                catch (Exception e){}
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
