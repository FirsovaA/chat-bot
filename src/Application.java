import java.util.Scanner;

public class Application {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        JsonDataManager manager = new JsonDataManager();
        JokeGenerator generator = new WebJokeGenerator();
        ChatBot chatBot = new ChatBot(manager, generator);
        System.out.println(chatBot.reply(""));

        while (!chatBot.over){
            String input = scanner.nextLine();
            if (!input.isBlank()){
               System.out.println(chatBot.reply(input));
            }
        }
    }
}
