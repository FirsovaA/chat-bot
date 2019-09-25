package main.java;

public class ChatBot {
    public boolean over = false;
    private User user;
    private String state = "start";

    public String reply(String input){
        switch (state){
            case "start":
                state = "load user";
                return "Hi I'm Anekbot that'll tell u jokes if u want\n"
                        + "What's your name?\n";
            case "load user":
                user = User.createUser(input.trim());
                state = "";
                return "hi "+ user.name + "!\n"
                        + "enter '/help' to find out what I can do";
            case "save jokes":
                state = "";
                user.saveJokes(Integer.parseInt(input));
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
                user.saveData();
                return "Bye bye c:";
            case "tell a joke":
                String joke = JokeGenerator.getJoke();
                user.addToHistory(joke);
                return joke;
            case "save to favourites":
                state = "save jokes";
                return "how much?";
            case "show favourites":
                return user.getFavourites();
            default:
                return "uh sorry i don't understand you\ntry entering /help";
        }
    }
}
