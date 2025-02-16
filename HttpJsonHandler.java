import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;
import org.json.JSONArray;
import org.json.JSONException;

public class HttpJsonHandler {

    public static void main(String[] args) throws IOException, JSONException {

        String apiUrl = "https://jsonplaceholder.typicode.com/posts"; 

        try {
            String jsonResponse = getJsonResponse(apiUrL);
            if (jsonResponse != null) {
                JSONArray jsonArray = new JSONArray(jsonResponse);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    int userId = jsonObject.getInt("userId");
                    int id = jsonObject.getInt("id");
                    String title = jsonObject.getString("title");
                    String body = jsonObject.getString("body");

                    System.out.println("Post " + (i + 1) + ":");
                    System.out.println("User ID: " + userId);
                    System.out.println("ID: " + id);
                    System.out.println("Title: " + title);
                    System.out.println("Body: " + body);
                    System.out.println("--------------------");
                }
            } else {
                System.out.println("Failed to get JSON response.");
            }

        } catch (IOException | JSONException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }


    private static String getJsonResponse(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "application/json"); // Important for JSON APIs

        int responseCode = connection.getResponseCode();

        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
            reader.close();
            connection.disconnect();

            return response.toString();
        } else {
            System.err.println("HTTP Error: " + responseCode);
            connection.disconnect();
            return null; 
        }
    }
}