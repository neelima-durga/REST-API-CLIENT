# REST-API-CLIENT

*company*:CODETCH IT SOLUTIONS

*NAME*:CH.NEELIMA DURGA

*INTERN ID*:CT6WRBE

DOMAIN: JAVA PROGRAMMING

DURATION : 6 WEEKS

MENTOR: NEELA SANTHOSH

DESCRIPTION:
This Java application retrieves weather data from the OpenWeatherMap API and displays it in a user-friendly format.  Here's a breakdown:

API Key: You'll need to sign up for a free account at OpenWeatherMap (https://openweathermap.org/) to get an API key.  Replace "YOUR_OPENWEATHERMAP_API_KEY" with your actual key.

API URL: The apiUrl is constructed using your API key and the city you want weather information for.  You can change the city variable. The units=metric parameter ensures that the temperature is returned in Celsius.

HTTP Request: The getJsonResponse method makes a GET request to the OpenWeatherMap API.  It handles the HTTP connection, reads the JSON response, and returns it as a string.

JSON Parsing: The JSONObject from the org.json library (you must include it in your project) parses the JSON response.  The code then extracts the city name, country, weather description, temperature, humidity, and wind speed.

Data Display: The extracted weather information is then displayed in a structured and readable format.

To run this application:

Get an API Key: Sign up at OpenWeatherMap and get your API key.

Add JSON Library: You need the org.json library.  If you're using Maven, add this to your pom.xml:

XML

<dependency>
    <groupId>org.json</groupId>
    <artifactId>json</artifactId>
    <version>20230227</version>  </dependency>
If not using Maven, download the json.jar file and add it to your project's classpath.

Replace Placeholders: Replace "YOUR_OPENWEATHERMAP_API_KEY" with your API key and change the city variable if needed.

Compile and Run: Compile and run the Java code.

Example Output:

Weather in London, GB:
------------------------------------
Description: clear sky
Temperature: 15.25 °C
Humidity: 63%
Wind Speed: 4.6 m/s
