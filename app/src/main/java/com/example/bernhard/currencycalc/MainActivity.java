package com.example.bernhard.currencycalc;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.loopj.android.http.*;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {
    private Button btn_send;
    private EditText amount;
    private Spinner spinner;
    private Context context;
    private static final String QUERY_URL = "http://api.fixer.io/latest?base=";
    private String from;
    private String curr;
    private Spinner spinner_from;
    private static String help;
    private JSONObject json;
    public static String end_msg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_send = (Button) findViewById(R.id.send);
        amount = (EditText) findViewById(R.id.editText);
        context = getApplicationContext();
        spinner = (Spinner) findViewById(R.id.spinner);
        spinner_from = (Spinner) findViewById(R.id.spinner2);
        spinner_from.setSelection(1);
        spinner.setSelection(0);
        btn_send.setOnClickListener(this);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v == btn_send) {

            curr = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
            from = spinner_from.getItemAtPosition(spinner_from.getSelectedItemPosition()).toString();
            if (amount.getText().toString() == null) amount.setText("1");

            if(curr == from){
                Toast.makeText(context, "Choose different currencies", Toast.LENGTH_LONG).show();
                }
            else {
                int am = Integer.parseInt(amount.getText().toString());
                try {
                    calculateCurr(curr, from, am);
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private void calculateCurr(String curr, String from, int am) throws IOException, JSONException, ExecutionException, InterruptedException {

         help = new DownloadWebPageTask().execute(QUERY_URL + from).get();


        json = new JSONObject(help);
        String curr_value = json.getJSONObject("rates").getString(curr);

        Double value = Double.parseDouble(curr_value);
        Double multiplied_value = value * am;

        end_msg = (am + " " + from + " are " + multiplied_value + " " + curr + " \n \n \n \n \n \nActual exchange rate: 1 " + from + " are " + value + " " + curr );


        startActivity(new Intent(this, Success.class));

    }




}
