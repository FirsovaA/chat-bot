import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class TgHandler extends TelegramLongPollingBot {
    private ChatBot bot;
    private UserRepository userRepository;


    public TgHandler(ChatBot bot, UserRepository repository){
        this.bot = bot;
        userRepository= repository;
    }


    @Override
    public void onUpdateReceived(Update update) {
        Message msg = update.getMessage();
        String txt = msg.getText();
        User user = userRepository.Load(msg.getChatId());
        sendMsg(msg, bot.reply(txt, user));
        userRepository.saveData(user);
    }

    private synchronized void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e) {
            MyLogger.log(TgHandler.class, e);
        }
    }

    @Override
    public String getBotUsername() {
        return "Anekbot";
    }

    @Override
    public String getBotToken() {
        return System.getenv("TOKEN");
    }
}
