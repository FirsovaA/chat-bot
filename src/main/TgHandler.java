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
        Message msg = update.getMessage();
        String txt = msg.getText();
        User user = userRepository.Load(msg.getChatId());
        sendMsg(msg, bot.reply(txt, user));
        try {
            userRepository.saveData(user);
        }
        catch (IOException e){}
    }

    private synchronized void sendMsg(Message msg, String text) {
        SendMessage s = new SendMessage();
        s.setChatId(msg.getChatId());
        s.setText(text);
        try {
            execute(s);
        } catch (TelegramApiException e){
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "Anekbot";
    }

    @Override
    public String getBotToken() {
        return "INSERT_TOKEN";
    }
}
