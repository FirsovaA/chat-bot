package main.java;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class JokeGenerator {
    class Joke{
        String content;
    }

    public  static String getJoke() throws IOException {
        String query = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
        HttpURLConnection connection = null;
        String out = "";
        //try {
            connection = (HttpURLConnection) new URL(query).openConnection();

            connection.setRequestMethod("GET");
            connection.setUseCaches(false);
            connection.setConnectTimeout(250);
            connection.setReadTimeout(250);

            connection.connect();

            StringBuilder sb = new StringBuilder();

            if (HttpURLConnection.HTTP_OK == connection.getResponseCode()) {
                BufferedReader anekdot = new BufferedReader(new InputStreamReader(connection.getInputStream(), "cp1251"));

                String line;
                while ((line = anekdot.readLine()) != null) {
                    sb.append(line);
                    sb.append("\n");
                }

                //System.out.println(sb.toString());
                Joke joke = new Gson().fromJson(replaceQuotes(sb), Joke.class);
                out = joke.content;
            } else {
                out = "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
//        } catch(Throwable cause){
//            //cause.printStackTrace();
//        } finally{
//            if (connection != null) {
                connection.disconnect();
//            }
//        }
        return out;
    }
    private static String replaceQuotes(StringBuilder input){
        for (int i = 12; i < input.length() - 3; i++){
            if (input.charAt(i) == '\"'){
                input.setCharAt(i, '\'');
            }
        }
        return input.toString();
    }
}
