import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebJokeGenerator extends JokeGenerator {

    private static class Joke{
        String content;
    }

    public String Generate() throws NoJokeException {
        try {
            String query = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
            HttpURLConnection connection;
            String out;

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

                WebJokeGenerator.Joke joke = new Gson().fromJson(replaceQuotes(sb), WebJokeGenerator.Joke.class);
                out = joke.content;
            } else {
                out = "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
            connection.disconnect();
            return out;
        }
        catch (Exception e){
            throw new NoJokeException(e);
        }
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
