package com.cascado.server;

import java.io.*;
import java.net.URL;
import java.net.*;

import com.cascado.client.Panel;
import com.cascado.common.MessageConstants;
import org.json.*;
import static com.cascado.common.MessageConstants.REGEX;

public class WeatherInfo {

    private final static String API_KEY = "19ba505622c747712dd5f351a3351e78";
    private String city;

    // setting city name from TextField to OWM API
    public void setCity(String city) {
        this.city = city;
    }

    private String makeUrl(String city) {
        return String.format(("https://api.openweathermap.org/data/2.5/weather?q=%s&units=metric&lang=en&appid=%s"),
                        city, WeatherInfo.API_KEY);
    }

    // connecting to api with url
    private StringBuilder connectToApi() {
        Panel panel = new Panel();
        try {
            URL url = new URL(makeUrl(getCity()));
            URLConnection request = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            return sb;
        } catch (FileNotFoundException e) {
            panel.showError(MessageConstants.CITY_IS_NOT_FOUND);
        } catch (IOException e){
            panel.showError(MessageConstants.INVALID_CITY);
        }
        return null;
    }

    private JSONObject jsonObject(){
        String jsonString = connectToApi().toString();
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject;
    }

    public String parseJSON(){
        return parseCity() + REGEX + parseTemperature() + REGEX + parseWeather();
    }

    private String parseWeather(){
        JSONArray weatherArr = jsonObject().getJSONArray("weather");
        for (int i = 0; i < weatherArr.length(); i++) {
            return weatherArr.getJSONObject(i).getString("main");
        }
        return null;
    }

    private int parseTemperature(){
        return (int) (jsonObject().getJSONObject("main").getFloat("temp"));
    }

    private String parseCity(){
        return jsonObject().getString("name");
    }

    public void sendWeatherInfo(){
        makeUrl(city);
        connectToApi();
        jsonObject();
    }

    public String getCity() {
        return city;
    }
}
