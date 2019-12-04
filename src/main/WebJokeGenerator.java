import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class WebJokeGenerator extends JokeGenerator {

    private static class Joke{
        String content;
    }

    public String Generate() throws NoJokeException {
        StringBuilder rawJoke;
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet("http://rzhunemogu.ru/RandJSON.aspx?CType=1");
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                rawJoke = new StringBuilder(EntityUtils.toString(entity));
                WebJokeGenerator.Joke joke = new Gson().fromJson(replaceQuotes(rawJoke), WebJokeGenerator.Joke.class);
                return joke.content;
            } else {
                throw new NoJokeException("Joke from response is null");
            }
        }
        catch (Exception e) {
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
