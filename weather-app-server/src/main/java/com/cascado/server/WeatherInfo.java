package com.cascado.server;

import com.cascado.client.Window;
import com.cascado.server.error.GettingCityException;
import com.cascado.client.Panel;

import java.awt.*;
import java.io.*;
import java.net.URL;
import java.net.*;
import java.util.ArrayList;
import java.util.Arrays;
import org.json.*;
import static com.cascado.common.MessageConstants.REGEX;

public class WeatherInfo {

    private final static String API_KEY = "19ba505622c747712dd5f351a3351e78";
    private static WeatherInfo weatherInfo = new WeatherInfo();
    private String weather;
    private String time;
    private String temperature;
    private String city;

    // sending city name to owm api
    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    private String makeUrl(String city, String apiKey) {
        String url =
                String.format(("http://api.openweathermap.org/geo/1.0/direct?q=%s&limit=5&appid=%s"), city, apiKey);
        return url;
    }

    // connecting to api with url
    private StringBuilder connectApi() {
        try {
            URL url = new URL(makeUrl(getCity(), API_KEY));
            URLConnection request = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
            StringBuilder sb = new StringBuilder();

            String inputLine;
            while ((inputLine = br.readLine()) != null) {
                sb.append(inputLine);
            }
            br.close();
            return sb;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private ArrayList<String> apiAnswerToArray() {
        JSONArray json = new JSONArray(connectApi().toString());
        ArrayList<String> list = new ArrayList<String>();
        JSONArray jsonArray = (JSONArray) json;
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++) {
                list.add(jsonArray.get(i).toString());
            }
            return list;
        }
        return null;
    }

    public void makingArrayFromJSONArray(){
        makeUrl(city, API_KEY);
        connectApi();
        System.out.println(apiAnswerToArray());
    }

//     getting weather by city name
//    private CurrentWeather inputCity() {
//        try {
//            return config().currentWeatherByCityName(setCity());
//        } catch (APIException e){
//            if (setCity().equals("") || setCity().equals(" ")) {
//                throw new GettingCityException("Input the city.");
//            } else {
//                throw new GettingCityException("City is not found.");
//            }
//        }
//    }

//     getting temperature
//    private void setTemperature() {
//        temperature = ((int)Math.round(inputCity().getMainData().getTemp())) + "";
//    }

//     getting time
//    private void setTime() {
//        int hours, minutes, seconds;
//        hours = inputCity().getDateTime().getHours();
//        minutes = inputCity().getDateTime().getMinutes();
//        seconds = inputCity().getDateTime().getSeconds();
//        time = String.format("%d:%d:%d.", hours, minutes, seconds);
//    }

//    public String toSend(){
//        setCurrentWeather();
//        setTemperature();
//        setTime();
//        return time + REGEX + setCity() + REGEX + temperature + REGEX + currentWeather;
//    }

//     getting current weather
//    private void setCurrentWeather() {
//        currentWeather = inputCity().getWeatherList().get(0).getMoreInfo();
//        // return weather with capitalized first letter
//        currentWeather = currentWeather.substring(0, 1).toUpperCase() + currentWeather.substring(1);
//    }

    public String getWeather() {
        return weather;
    }

    public String getTime() {
        return time;
    }

    public String getTemperature() {
        return temperature;
    }
}
