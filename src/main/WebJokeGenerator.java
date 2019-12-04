import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class WebJokeGenerator extends JokeGenerator {

    public String Generate() throws NoJokeException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            HttpGet httpget = new HttpGet("http://api.icndb.com/jokes/random?");
            CloseableHttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                JSONObject jokeJson = new JSONObject(EntityUtils.toString(entity));
                return jokeJson.getJSONObject("value").getString("joke");
            } else {
                throw new NoJokeException("Joke from response is null");
            }
        } catch (Exception e) {
            throw new NoJokeException(e);
        }
    }

    private static String replaceQuotes(StringBuilder input) {
        for (int i = 12; i < input.length() - 3; i++) {
            if (input.charAt(i) == '\"') {
                input.setCharAt(i, '\'');
            }
        }
        return input.toString();
    }
}
