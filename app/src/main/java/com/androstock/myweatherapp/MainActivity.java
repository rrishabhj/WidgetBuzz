package com.androstock.myweatherapp;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.widget.TextView;

import com.androstock.myweatherapp.utilities.GPSTracker;
import com.androstock.myweatherapp.utilities.Utilities;

public class MainActivity extends AppCompatActivity {

    // Project Created by Ferdousur Rahman Shajib
    // www.androstock.com

    private FusedLocationProviderClient mFusedLocationClient;


    TextView cityField, detailsField, currentTemperatureField, humidity_field, pressure_field, weatherIcon, updatedField;

    Typeface weatherFont;
    private double lat = -1;
    private double lon = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        //getting location permission
        Utilities.getLocationPermission(MainActivity.this);

        weatherFont = Typeface.createFromAsset(getApplicationContext().getAssets(), "fonts/weathericons-regular-webfont.ttf");

        cityField = (TextView)findViewById(R.id.city_field);
        updatedField = (TextView)findViewById(R.id.updated_field);
        detailsField = (TextView)findViewById(R.id.details_field);
        currentTemperatureField = (TextView)findViewById(R.id.current_temperature_field);
        humidity_field = (TextView)findViewById(R.id.humidity_field);
        pressure_field = (TextView)findViewById(R.id.pressure_field);
        weatherIcon = (TextView)findViewById(R.id.weather_icon);
        weatherIcon.setTypeface(weatherFont);

        GPSTracker gpsTracker=new GPSTracker(getBaseContext());

        if (gpsTracker.canGetLocation()){
            lat = gpsTracker.getLatitude();
            lon = gpsTracker.getLongitude();
        }else {
            gpsTracker.showSettingsAlert();
            gpsTracker.stopUsingGPS();

        }

        Log.d("LocationTracker","lat:"+lat+"lon:"+lon);

        if (lat>0 && lon >0) {
            Function.placeIdTask asyncTask = new Function.placeIdTask(new Function.AsyncResponse() {
                public void processFinish(String weather_city, String weather_description, String weather_temperature, String weather_humidity, String weather_pressure, String weather_updatedOn, String weather_iconText, String sun_rise) {

                    cityField.setText(weather_city);
                    updatedField.setText(weather_updatedOn);
                    detailsField.setText(weather_description);
                    currentTemperatureField.setText(weather_temperature);
                    humidity_field.setText("Humidity: " + weather_humidity);
                    pressure_field.setText("Pressure: " + weather_pressure);
                    weatherIcon.setText(Html.fromHtml(weather_iconText));
                    WeatherService.startActionWeatherUpdate(MainActivity.this);

                }
            });
            asyncTask.execute(String.valueOf(lat), String.valueOf(lon));

        }else{
            // can't get the gps location
            weatherIcon.setText("Can't get Your Location");
        }
    }


}
