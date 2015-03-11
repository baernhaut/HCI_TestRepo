package com.example.bernhard.currencycalc;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DownloadWebPageTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... urls) {

        String response = "";

        DefaultHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet(urls[0]);
        try {
            HttpResponse execute = client.execute(httpGet);


            InputStream content = execute.getEntity().getContent();

            BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
            String s = "";
            while ((s = buffer.readLine()) != null) {
                response += s;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }


        return response;
    }





    @Override
    protected void onPostExecute(String result) {
        System.out.println(result);




    }


}