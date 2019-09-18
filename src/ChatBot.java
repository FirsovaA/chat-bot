public class ChatBot {
    public boolean over = false;

    public String reply(String input){
        switch (input.toLowerCase()){
            case "/help":
                return "Hi I'm simple chat bot that kinda doesn't know what it's purpose in life is..\n"
                        + "But I know that i can do the following stuff:\n"
                        + "\t/help  -- i'll tell you what I can do\n"
                        + "\t/exit -- I'll go away and leave you alone\n";

            case "/exit":
                over = true;
                return "Bye bye c:";
            case "tell a joke":
                return JokeGenerator.getJoke();
            default:
                return "wow, i didn't know it did that";
        }
    }
}