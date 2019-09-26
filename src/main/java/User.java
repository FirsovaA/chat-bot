package main.java;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


class User {
    String name;
    private ArrayList<String> history;
    private HashSet<String> favourites;

    static User createUser(String inpName){
        try {
            //TODO проверять, что к найденному файлу есть доступ
            BufferedReader br = new BufferedReader(new FileReader("users/" + inpName +".json"));
            return new  Gson().fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            return new User(inpName);
        }
    }

    private User(String inpName){
        name = inpName;
        history = new ArrayList<>();
        favourites = new HashSet<>();
    }

    void saveJokes(int count){
        favourites.addAll(history.subList(Math.max(0,history.size()-count), history.size()));
    }

    String getFavourites(){
        if (favourites.isEmpty()){
            return "";
        }

        StringBuilder res = new StringBuilder();
        res.append("***\n");
        for (String joke: favourites) {
            res.append(joke);
            res.append("\n***\n");
        }
        return res.toString();
    }

    void addToHistory(String joke){
        history.add(joke);
    }

    void saveData() throws IOException {
        File file = new File("users/" + name +".json");;
        new File("users/").mkdirs();
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(new Gson().toJson(this));
        writer.close();
    }

    static Boolean isValidUsername(String username){
        return !username.matches(".*[\\\\/:*?\"<>|].*");
    }
}
