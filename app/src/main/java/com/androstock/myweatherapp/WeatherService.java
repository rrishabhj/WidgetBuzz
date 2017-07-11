package com.androstock.myweatherapp;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.text.Html;

import com.androstock.myweatherapp.model.WeatherData;
import com.androstock.myweatherapp.utilities.GPSTracker;

/**
 * Created by DMI on 10-07-2017.
 */

public class WeatherService extends IntentService {

    public static final String ACTION_UPDATE_PLANT_WIDGETS = "com.androstock.myweatherapp.action.update_weather_widget";
    private String temp;
    private double lat;
    private double lon;

    public WeatherService() {
        super("WeatherService");
    }


    public static void startActionWeatherUpdate(Context context) {
        Intent intent = new Intent(context, WeatherService.class);
        intent.setAction(ACTION_UPDATE_PLANT_WIDGETS);
        context.startService(intent);
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


        GPSTracker gpsTracker=new GPSTracker(getBaseContext());

        if (gpsTracker.canGetLocation()){
            lat = gpsTracker.getLatitude();
            lon = gpsTracker.getLongitude();
        }

        Function.placeIdTask asyncTask =new Function.placeIdTask(new Function.AsyncResponse() {
            public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                WeatherData weatherData=new WeatherData(weather_city, weather_description, weather_temperature, weather_humidity, weather_pressure, weather_updatedOn,weather_iconText,sun_rise);
//                cityField.setText(weather_city);
//                updatedField.setText(weather_updatedOn);
//                detailsField.setText(weather_description);
//                currentTemperatureField.setText(weather_temperature);
//                humidity_field.setText("Humidity: "+weather_humidity);
//                pressure_field.setText("Pressure: "+weather_pressure);
//                weatherIcon.setText(Html.fromHtml(weather_iconText));
                AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(getBaseContext());
                int[] appWidgetIds = appWidgetManager.getAppWidgetIds(new ComponentName(getBaseContext(), WeatherWidget.class));
                //Now update all widgets
                WeatherWidget.updatePlantWidgets(getBaseContext(), appWidgetManager, weatherData, appWidgetIds);

            }
        });
        asyncTask.execute(String.valueOf(lat), String.valueOf(lon)); //  asyncTask.execute("Latitude", "Longitude")

    }

}
