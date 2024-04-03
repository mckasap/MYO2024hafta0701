package com.example.myo2024hafta0701;

import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

public class GetJsonWebAPI extends AsyncTask<String,Void,String> {



    @Override
    protected String doInBackground(String... strings) {
        String TrabzonEnlem="41.00316922382727";
        String TrabzonBoylam="39.716839672674055";
        StringBuilder sb= new StringBuilder("");
        try {
            URL url;
            if (strings[0]=="")
                url= new URL("https://api.openweathermap.org/data/2.5/weather?lat=41.00316922382727&lon=39.716839672674055&unit=metric&appid=689e9049b1a24724ceca784c484685d3");
            else  {
                url= new URL("https://api.openweathermap.org/data/2.5/weather?q="+strings[0]+"&appid=689e9049b1a24724ceca784c484685d3");
            }
            HttpsURLConnection con=(HttpsURLConnection) url.openConnection();
            con.connect();
            InputStream in= con.getInputStream();
            InputStreamReader reader = new InputStreamReader(in);
            int data= reader.read();
            while(data!=-1){
                sb.append((char)data);
                data=reader.read();
            }
            return sb.toString();
        } catch (MalformedURLException e) {
            // return "UYGUNSUZ URL bilgisi "+e.getMessage();
           throw new RuntimeException(e);
        } catch (IOException e) {
            //return "BAŞARISIZ BAĞLANTI" +e.getMessage();
            throw new RuntimeException(e);
        }




    }
}
