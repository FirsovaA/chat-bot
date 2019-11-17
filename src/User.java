import java.util.ArrayList;
import java.util.HashSet;


public class User {
    String name;
    private ArrayList<String> history;
    private HashSet<String> favourites;
    public ChatBot.State state;

    User(String inpName){
        name = inpName;
        history = new ArrayList<>();
        favourites = new HashSet<>();
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
