import java.io.BufferedReader;ss
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class WeatherApp {

    public static void main(String[] args) {
        String apiKey = "YOUR_API_KEY"; 
        String city = "London"; 

        try {
            String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey + "&units=metric"; // Example: OpenWeatherMap

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                StringBuilder response = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                JSONObject jsonObject = new JSONObject(response.toString());
                String cityName = jsonObject.getString("name");
                JSONObject main = jsonObject.getJSONObject("main");
                double temperature = main.getDouble("temp");
                double humidity = main.getDouble("humidity");
                String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                double windSpeed = jsonObject.getJSONObject("wind").getDouble("speed");

                System.out.println("Weather in " + cityName + ":");
                System.out.println("Temperature: " + temperature + " °C");
                System.out.println("Humidity: " + humidity + "%");
                System.out.println("Description: " + description);
                System.out.println("Wind Speed: " + windSpeed + " m/s");

            } else {
                System.out.println("Error: " + responseCode);
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()))) {
                    String line;
                    StringBuilder errorResponse = new StringBuilder();
                    while ((line = reader.readLine()) != null) {
                        errorResponse.append(line);
                    }
                    System.err.println("Error details from API: " + errorResponse.toString());
                } catch (IOException ex) {
                    System.err.println("Error reading error stream: " + ex.getMessage());
                }
            }

            connection.disconnect();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
