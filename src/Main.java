import java.util.Scanner;

public class Main {
    public static void main(String [] args) {
        Scanner scanner = new Scanner(System.in);
        ChatBot chatBot = new ChatBot();
        System.out.println(chatBot.reply(""));

        while (!chatBot.over){
            String input = scanner.nextLine();
            if (!input.isBlank()){
               System.out.println(chatBot.reply(input));
            }
        }
    }
}
