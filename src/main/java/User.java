package main.java;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;


public class User {
    String name;
    //TODO в теории в очередь превратить, чтобы не переполнилось
    private ArrayList<String> history;
    private HashSet<String> favourites;

    public static User createUser(String inpName){
        try {
            BufferedReader br = new BufferedReader(new FileReader("users/" + inpName +".json"));
            return new  Gson().fromJson(br, User.class);
        } catch (FileNotFoundException e) {
            return new User(inpName);
        }
    }

    private User(String inpName){
        //TODO смотреть, чтобы не было проблем с именем файла
        name = inpName;
        history = new ArrayList<>();
        favourites = new HashSet<>();
    }

    public void saveJokes(int count){
        //TODO не сохранять ошибки
        favourites.addAll(history.subList(Math.max(0,history.size()-count), history.size()));
    }

    public String getFavourites(){
        StringBuilder res = new StringBuilder();
        res.append("***\n");
        for (String joke: favourites) {
            res.append(joke);
            res.append("\n***\n");
        }
        return res.toString();
    }

    public void addToHistory(String joke){
        history.add(joke);
    }

    public void saveData() {
        File file = new File("users/" + name +".json");;
        new File("users/").mkdirs();
        try {
            file.createNewFile();
            FileWriter writer = new FileWriter(file);
            writer.write(new Gson().toJson(this));
            writer.close();
        } catch (IOException e) {
            //TODO убрать вывод на консоль
            System.out.println("something went wrong, sorry");
        }
    }
}
