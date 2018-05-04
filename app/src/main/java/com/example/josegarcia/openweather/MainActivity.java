package com.example.josegarcia.openweather;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView iv_weather = (ImageView)findViewById(R.id.iv_weather);
        int identificador = getResources().getIdentifier("imagen02d","drawable",getPackageName());
        iv_weather.setImageDrawable(getResources().getDrawable(identificador,null));


    }

}
