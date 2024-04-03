package com.example.myo2024hafta0701;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
TextView tv;
EditText etCity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv= (TextView) findViewById(R.id.tvHavaDurumu);
        etCity=(EditText) findViewById(R.id.etCirty);
    }

    private double cvrtCelc(double Kelvin){

        return Kelvin-273.15;
    }
    public void Getir(View V){
        GetJsonWebAPI myApi= new GetJsonWebAPI();
        String str= null;
        try {
            if (etCity.getText().toString()=="")
                     str = myApi.execute().get();
            else
                str = myApi.execute(java.net.URLEncoder.encode(etCity.getText().toString(),"UTF-8").replace("+","%20")).get();
            Log.d("HAVA DURUMU:", str);
            JSONObject all= new JSONObject(str);
            JSONObject main= all.getJSONObject("main");
              double temp= main.getDouble("temp")-273.15;
              double feels_like= main.getDouble("feels_like")-273.15;
              double temp_min=      main.getDouble("temp_min")-273.15;
              double temp_max= main.getDouble(     "temp_max")-273.15;
              double preasure=main.getDouble(      "pressure");
              double humidity =main.getDouble(      "humidity");

              tv.setText("Sıcaklık:"+temp+" C");


        } catch (ExecutionException e) {
            Log.d("HAVA DURUMU:", str);
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            Log.d("HAVA DURUMU:", str);

            throw new RuntimeException(e);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }


    }

}