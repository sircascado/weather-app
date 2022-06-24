package com.cascado.server;

import com.cascado.server.error.GettingCityException;
import net.aksingh.owmjapis.api.APIException;
import net.aksingh.owmjapis.core.OWM;
import net.aksingh.owmjapis.model.CurrentWeather;

public class WeatherInfo {

    private OWM owm;
    private String currentWeather;
    private final String API_KEY = "061c88a24ac0ad18ae22534accea424a";

    public static void main(String[] args) {
        WeatherInfo weather = new WeatherInfo();
        System.out.println(weather);
    }

    // configure owm: api key, lang, accuracy, etc
    private OWM config(){
        owm = new OWM(API_KEY);
        owm.setUnit(OWM.Unit.METRIC);

//        if (Application.askLang().equals("ru")) owm.setLanguage(OWM.Language.RUSSIAN);
//        else owm.setLanguage(OWM.Language.ENGLISH);
        owm.setLanguage(OWM.Language.RUSSIAN);

        owm.setAccuracy(OWM.Accuracy.ACCURATE);
        return owm;
    }

    // sending city name to owm api
    private String setCity(){
        return "Stupino";
    }

    // getting weather by city name
    private CurrentWeather getCity() {
        try {
            return config().currentWeatherByCityName(setCity());
        } catch (APIException e){
            if (setCity().equals("") || setCity().equals(" ")) {
                throw new GettingCityException("Input the city.");
            } else {
                throw new GettingCityException("City is not found.");
            }
        }
    }

    // getting temperature
    private int getTemperature() {
        return (int)Math.round(getCity().getMainData().getTemp());
    }

    // getting time
    private String getTime() {
        int hours, minutes, seconds;
        hours = getCity().getDateTime().getHours();
        minutes = getCity().getDateTime().getMinutes();
        seconds = getCity().getDateTime().getSeconds();
        return String.format("%d:%d:%d.", hours, minutes, seconds);
    }

    // getting current weather
    private String getCurrentWeather() {
        try {
            currentWeather = getCity().getWeatherList().get(0).getMoreInfo();
            // return weather with capitalized first letter
            return currentWeather.substring(0, 1).toUpperCase() + currentWeather.substring(1);

        } catch (NullPointerException e){
            e.printStackTrace();
            return null;
        }
    }

    // sending weather info to user
    @Override
    public String toString() {
        String msgTime, msgCity, msgWeather, message;
        msgTime = "Погода на " + getTime();
        msgCity = "Город: " + getCity().getCityName();
        msgWeather = String.format("Температура: %d°C. %s.", getTemperature(), getCurrentWeather());
        message = msgTime + "\n" + msgCity + "\n" + msgWeather;
        return message;

    }

}
