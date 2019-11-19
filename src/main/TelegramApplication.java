import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TelegramApplication {
    public static void main(String[] args) {
        JsonUserRepository manager = new JsonUserRepository();
        ChatBot chatBot = new ChatBot(new WebJokeGenerator());
        ApiContextInitializer.init();
        TelegramBotsApi botApi = new TelegramBotsApi();
        try {
            botApi.registerBot(new TgHandler(chatBot, manager));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
