import java.util.ArrayList;
import java.util.HashSet;


public class User {
    String name;
    Long id;
    private ArrayList<String> history;
    private HashSet<String> favourites;
    private ChatBot.State state;

    public void setState(ChatBot.State newState){
        state = newState;
    }

    public ChatBot.State getState(){
        return state;
    }

    User(Long id){
        name = "User";
        this.id = id;
        history = new ArrayList<>();
        favourites = new HashSet<>();
        state = ChatBot.State.START;
    }

    public void saveJokes(int count){
        favourites.addAll(history.subList(Math.max(0,history.size()-count), history.size()));
    }

    public String getFavourites(){
        if (favourites.isEmpty()){
            return "no saved jokes";
        }
        return String.join("\n***\n", favourites);
    }

    public void addToHistory(String joke){
        history.add(joke);
    }


    public static Boolean isValidUsername(String username){
        return !username.matches(".*[\\\\/:*?\"<>|].*");
    }
}
