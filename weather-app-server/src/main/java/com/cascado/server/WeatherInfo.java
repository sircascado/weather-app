package com.cascado.server;

import com.cascado.server.error.GettingCityException;
import com.cascado.client.Panel;
import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.nio.charset.*;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.*;
import static com.cascado.common.MessageConstants.REGEX;

public class WeatherInfo {

//    private OWM owm;
    private final static String API_KEY = "19ba505622c747712dd5f351a3351e78";
    private static WeatherInfo weatherInfo = new WeatherInfo();
    private String weather;
    private String time;
    private String temperature;
    private String city;
    private Panel panel = new Panel();
    private String url =
            "http://api.openweathermap.org/geo/1.0/direct?q=Stupino&limit=5&appid=19ba505622c747712dd5f351a3351e78";

    private String url2 =
            "http://api.openweathermap.org/geo/1.0/direct?q=Stupino,Moscow Oblast&limit=5&appid=19ba505622c747712dd5f351a3351e78";

    public static void main(String[] args) throws IOException {
        // Connect to the URL using java's native library
        URL url = new URL(weatherInfo.url);
        URLConnection request = url.openConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream()));
        StringBuilder sb = new StringBuilder();

        String inputLine;
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        br.close();
        JSONArray json = new JSONArray(sb.toString());

        ArrayList<String> list = new ArrayList<String>();
        JSONArray jsonArray = (JSONArray)json;
        if (jsonArray != null) {
            int len = jsonArray.length();
            for (int i = 0; i < len; i++){
                list.add(jsonArray.get(i).toString());
            }
        }

        System.out.println(list);

    }

    // configure owm: api key, lang, accuracy, etc
//    private OWM config(){
//        owm = new OWM(API_KEY);
//        owm.setUnit(OWM.Unit.METRIC);
//
////        if (Application.askLang().equals("ru")) owm.setLanguage(OWM.Language.RUSSIAN);
////        else owm.setLanguage(OWM.Language.ENGLISH);
//        owm.setLanguage(OWM.Language.RUSSIAN);
//
//        owm.setAccuracy(OWM.Accuracy.ACCURATE);
//        return owm;
//    }

    // sending city name to owm api
    public String setCity(){
        return panel.sendCityName();
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

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }
}
