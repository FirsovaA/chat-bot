import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class TgHandler extends TelegramLongPollingBot {
    private ChatBot bot;
    private UserRepository userRepository;


    public TgHandler(ChatBot bot, UserRepository repository){
        this.bot = bot;
        userRepository= repository;
    }


    @Override
    public void onUpdateReceived(Update update) {
        try {
            Message msg = update.getMessage();
            String txt = msg.getText();
            User user = userRepository.Load(msg.getChatId());
            sendMsg(msg, bot.reply(txt, user));
            userRepository.saveData(user);
        }
        catch (TelegramApiException e){
            MyLogger.log(TgHandler.class, e);
        }
        catch (IOException e){
            MyLogger.log(userRepository.getClass(), e);
        }
    }

    private synchronized void sendMsg(Message msg, String text) throws TelegramApiException{
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        execute(s);
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
