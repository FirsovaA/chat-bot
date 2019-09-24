import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.google.gson.*;

public class JokeGenerator {
    class Joke{
        public String content;
    }
    public  static String getJoke() {
        String query = "http://rzhunemogu.ru/RandJSON.aspx?CType=1";
        HttpURLConnection connection = null;
        String out = "no joke todey";
        try {
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
                Joke joke = new Gson().fromJson(sb.toString(), Joke.class);
                out = joke.content;
            } else {
                out = "fail: " + connection.getResponseCode() + ", " + connection.getResponseMessage();
            }
        } catch(Throwable cause){
            //cause.printStackTrace();
        } finally{
            if (connection != null) {
                connection.disconnect();
            }
        }
        return out;
    }
}
