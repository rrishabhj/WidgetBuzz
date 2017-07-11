package com.androstock.myweatherapp.model;

import com.androstock.myweatherapp.WeatherWidget;

/**
 * Created by DMI on 11-07-2017.
 */

public class WeatherData {
    String weather_city;
    String weather_description;
    String weather_temperature;
    String weather_humidity;
    String weather_pressure;
    String weather_updatedOn;
    String weather_iconText;
    String sun_rise;

    public WeatherData(){

    }

    public WeatherData(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {
        this.weather_city = weather_city;
        this.weather_description = weather_description;
        this.weather_temperature = weather_temperature;
        this.weather_humidity = weather_humidity;
        this.weather_pressure = weather_pressure;
        this.weather_updatedOn = weather_updatedOn;
        this.weather_iconText = weather_iconText;
        this.sun_rise = sun_rise;
    }

    public String getWeather_city() {
        return weather_city;
    }

    public void setWeather_city(String weather_city) {
        this.weather_city = weather_city;
    }

    public String getWeather_description() {
        return weather_description;
    }

    public void setWeather_description(String weather_description) {
        this.weather_description = weather_description;
    }

    public String getWeather_temperature() {
        return weather_temperature;
    }

    public void setWeather_temperature(String weather_temperature) {
        this.weather_temperature = weather_temperature;
    }

    public String getWeather_humidity() {
        return weather_humidity;
    }

    public void setWeather_humidity(String weather_humidity) {
        this.weather_humidity = weather_humidity;
    }

    public String getWeather_pressure() {
        return weather_pressure;
    }

    public void setWeather_pressure(String weather_pressure) {
        this.weather_pressure = weather_pressure;
    }

    public String getWeather_updatedOn() {
        return weather_updatedOn;
    }

    public void setWeather_updatedOn(String weather_updatedOn) {
        this.weather_updatedOn = weather_updatedOn;
    }

    public String getWeather_iconText() {
        return weather_iconText;
    }

    public void setWeather_iconText(String weather_iconText) {
        this.weather_iconText = weather_iconText;
    }

    public String getSun_rise() {
        return sun_rise;
    }

    public void setSun_rise(String sun_rise) {
        this.sun_rise = sun_rise;
    }
}
