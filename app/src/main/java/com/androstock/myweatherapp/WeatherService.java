package com.androstock.myweatherapp;

import android.app.IntentService;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.text.Html;

import com.androstock.myweatherapp.model.WeatherData;
import com.androstock.myweatherapp.utilities.GPSTracker;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

/**
 * Created by DMI on 10-07-2017.
 */

public class WeatherService extends IntentService {

    public static final String ACTION_UPDATE_PLANT_WIDGETS = "com.androstock.myweatherapp.action.update_weather_widget";
    private String temp;
    private double lat;
    private double lon;
    private FusedLocationProviderClient mFusedLocationClient;


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

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(getBaseContext());
        GPSTracker gpsTracker = new GPSTracker(getBaseContext());

        if (gpsTracker.canGetLocation()) {
            lat = gpsTracker.getLatitude();
            lon = gpsTracker.getLongitude();
        }else {


            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return;
            }
            mFusedLocationClient.getLastLocation().addOnSuccessListener(new OnSuccessListener<Location>() {
                @Override
                public void onSuccess(Location location) {

                    if (location != null) {
                        lat = location.getLatitude();
                        lon = location.getLongitude();
                    }
                }
            });
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
