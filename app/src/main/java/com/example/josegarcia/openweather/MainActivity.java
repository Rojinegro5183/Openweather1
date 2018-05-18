package com.example.josegarcia.openweather;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "OpenWeather";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ImageView iv_weather = (ImageView) findViewById(R.id.iv_weather);
        final TextView tv_temp = (TextView) findViewById(R.id.tv_temp);
        final TextView tv_country = (TextView) findViewById(R.id.tv_country);


        RequestQueue queue = Volley.newRequestQueue(this);
        String url="http://192.168.86.39/ejemplos/clima";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
                    @Override
                    public void onResponse(JSONObject response) {

                        try {

                            if (response.has("weather")) {

                                JSONArray weatherArray = response.getJSONArray("weather");
                                JSONObject weather = weatherArray.getJSONObject(0);


                                if (weather.has("icon")) {

                                    String icon = weather.getString("icon");
                                    int identificador = getResources().getIdentifier("imagen" + icon, "drawable", getPackageName());
                                    iv_weather.setImageDrawable(getResources().getDrawable(identificador, null));

                                    Log . d ( TAG , icon);
                                }


                            }

                            if (response.has("main")) {
                                JSONObject main = response.getJSONObject("main");

                                Double temp = main.getDouble("temp");

                                tv_temp.setText("" + temp + "\u00b0");

                            }

                            if (response.has("sys")) {
                                JSONObject main = response.getJSONObject("sys");

                                String country = main.getString("country");

                                tv_country.setText("La temperatura de " + country + " es");

                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, error.getMessage());


                    }
                });
        queue.add(jsonObjectRequest);


    }

}
