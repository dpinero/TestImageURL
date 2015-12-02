package com.mycompany.testimageurl;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ConvectiveActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE2 = "com.mycompany.testimageurl.MESSAGE";
    String imgtime1;
    String imgtime2;
    String imgtime3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String html1 ="";
        String html2 ="";
        String html3 ="";
        String imgaddress1="<title>";
        String imgaddress2="<title>";
        String imgaddress3="<title>";

        int index1 = 0;
        int index2 = 0;
        int index3 = 0;
        // get advisory time for convective outlook day 1
        String url1="http://www.spc.noaa.gov/products/outlook/day1otlk.html";
        setContentView(R.layout.activity_convective);
        RetrieveSiteData task1 = new RetrieveSiteData();
        try{
            // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
            html1 = task1.execute(url1).get();}
        catch (Exception e) {
        }
        //Log.i("htmldata", "Here:" + html1);
        index1=html1.indexOf(imgaddress1);
        //Log.i("index", "Here:" + index);
        imgtime1 = html1.substring(index1+44, index1+48);
        Log.i("address string", "Here:" + imgtime1);

        // get advisory time for convective outlook day 2
        String url2="http://www.spc.noaa.gov/products/outlook/day2otlk.html";
        ;
        RetrieveSiteData task2 = new RetrieveSiteData();
        try{
            // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
            html2 = task2.execute(url2).get();}
        catch (Exception e) {
        }

        index2=html2.indexOf(imgaddress2);
        imgtime2 = html2.substring(index2+44, index2+48);

        // get advisory time for convective outlook day 3
        String url3="http://www.spc.noaa.gov/products/outlook/day3otlk.html";
        RetrieveSiteData task3 = new RetrieveSiteData();
        try{
            // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
            html3 = task3.execute(url3).get();}
        catch (Exception e) {
        }
        index3=html3.indexOf(imgaddress3);
        imgtime3 = html3.substring(index3+44, index3+48);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_convective, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }



    private class RetrieveSiteData extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String...params) {

                try {
                    HttpURLConnection connection = (HttpURLConnection)new URL(params[0]).openConnection();
                    connection.setConnectTimeout(5000);
                    connection.setReadTimeout(5000);
                    connection.connect();

                    // Read and store the result line by line then return the entire string.
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder html = new StringBuilder();
                    for (String line; (line = reader.readLine()) != null; ) {
                        html.append(line);
                    }
                    in.close();

                    return html.toString();
                }

                 catch (Exception e) {
                    e.printStackTrace();
                     return "it broke";
                }

        }

        @Override
        protected void onPostExecute(String result) {

        }
    }
    //take parsed imgtime and send string to convective viewer
    public void openconv1(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent11 = new Intent(this, convectiveviewer.class);
        intent11.putExtra(EXTRA_MESSAGE2,imgtime1);
        startActivity(intent11);

    }
    //create new class for day 2 through 8 image because only categorical forecast exists
    public void openconv2(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        String day2msg = "day2"+imgtime2;
        Intent intent12 = new Intent(this, convectiveviewer3.class);
        intent12.putExtra(EXTRA_MESSAGE2,day2msg);
        startActivity(intent12);

    }

    public void openconv3(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        String day3msg = "day3"+imgtime3;
        Intent intent13 = new Intent(this, convectiveviewer3.class);
        intent13.putExtra(EXTRA_MESSAGE2,day3msg);
        startActivity(intent13);

    }
    //create new class for day 4 through 8 image because that image does not have a gif
    public void openconv4t8(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        String nexturl="http://www.spc.noaa.gov/products/exper/day4-8/day48prob.gif";
        Intent intent14 = new Intent(this, convectiveviewer2.class);
        intent14.putExtra(EXTRA_MESSAGE2,nexturl);
        startActivity(intent14);

    }


}
