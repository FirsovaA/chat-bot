import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot chatBot = new ChatBot();
        System.out.println(chatBot.reply("/help"));

        while (true){
            String input = scanner.next();
            if (!input.isBlank()){
               System.out.println(chatBot.reply(input));
            }
        }
    }
}
