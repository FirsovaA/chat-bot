import java.io.IOException;
import java.util.Scanner;

public class ConsoleApplication {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        JsonUserRepository manager = new JsonUserRepository();
        JokeGenerator generator = new WebJokeGenerator();
//        JokeGenerator generator = new LocalStorageJokesGenerator();
        ChatBot chatBot = new ChatBot(generator);
        User user = new User(0L);
        System.out.println(chatBot.reply("", user));

        while (!chatBot.over) {
            String input = scanner.nextLine();
            if (!input.isBlank()) {
                System.out.println(chatBot.reply(input, user));
                try {
                    manager.saveData(user);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
