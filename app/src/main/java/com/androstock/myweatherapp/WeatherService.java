package com.androstock.myweatherapp;

import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Intent;

/**
 * Created by DMI on 10-07-2017.
 */

public class WeatherService extends IntentService {

    public static final String ACTION_UPDATE_PLANT_WIDGETS = "com.androstock.myweatherapp.action.update_weather_widget";

    WeatherService() {
        super("WeatherService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_UPDATE_PLANT_WIDGETS.equals(action)) {
                handleActionWeather();
            }

        }
    }

    private void handleActionWeather() {
        
    }

}
