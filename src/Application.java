import java.util.Scanner;

public class Application {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        JsonUserRepository manager = new JsonUserRepository();
        JokeGenerator generator = new WebJokeGenerator();
        ChatBot chatBot = new ChatBot(manager, generator, ChatBot.State.START);
        System.out.println(chatBot.reply(""));

        while (!chatBot.over){
            String input = scanner.nextLine();
            if (!input.isBlank()){
               System.out.println(chatBot.reply(input));
            }
        }
    }
}
