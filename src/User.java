import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class User {
    String name;
    private ArrayList<String> history;
    private HashSet<String> favourites;

    public static User loadUser(String inpName){
        try {
            //TODO проверять, что к найденному файлу есть доступ
            BufferedReader br = new BufferedReader(new FileReader("users/" + inpName +".json"));
            return new Gson().fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            return new User(inpName);
        }
    }

    private User(String inpName){
        name = inpName;
        history = new ArrayList<>();
        favourites = new HashSet<>();
    }

    public void saveJokes(int count){
        favourites.addAll(history.subList(Math.max(0,history.size()-count), history.size()));
    }

    public String getFavourites(){
        if (favourites.isEmpty()){
            return "";
        }
        return String.join("\n***\n", favourites);
    }

    public void addToHistory(String joke){
        history.add(joke);
    }

    public void saveData() throws IOException {
        File file = new File("users/" + name +".json");
        new File("users/").mkdirs();
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(new Gson().toJson(this));
        writer.close();
    }

    public static Boolean isValidUsername(String username){
        return !username.matches(".*[\\\\/:*?\"<>|].*");
    }
}
