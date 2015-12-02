package com.mycompany.testimageurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;

public class Openstormcharts extends AppCompatActivity {
    WebView web = (WebView) findViewById(R.id.webview1);
    String chartlink1="";
    String chartlink2="";
    String chartlink3="";
    String chartlink4="";
    String chartlink5="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_openstormcharts);


        // web.setVerticalScrollBarEnabled(true);
        // web.setHorizontalScrollBarEnabled(true);
        //set image url size to match screen size
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        //get intent with html data attached as an extra message
        Intent intent = getIntent();
        String rawhtml = intent.getStringExtra(TropicalStormsActivity.EXTRA_MESSAGE);
        int[] index1 = new int[8];
        int[] index2 = new int[8];
        int counter =0;

        //find end and beginning indices for chart links
        for (int i = -1; (i = rawhtml.indexOf("<img src=", i + 1)) != -1; ) {
            index1[counter]=i;
            counter=counter+1;}
        //parse out the relevant link string
       index2[0]=rawhtml.indexOf(" width=\"60\" height=\"48\" border=\"1\" alt=\"34-knot Wind Speed Probability");
        index2[1]=rawhtml.indexOf(" width=\"60\" height=\"48\"border=\"1\" alt=\"Wind History");
        index2[2]=rawhtml.indexOf(" width=\"60\" height=\"48\" border=\"1\" alt=\"Google Maps API Warnings and Track Forecast Cone");
        index2[3]=rawhtml.indexOf(" width=\"60\" height=\"48\"border=\"1\" alt=\"Warnings and 5-Day Cone");
        index2[4]=rawhtml.indexOf(" width=\"60\" height=\"48\"border=\"1\" alt=\"Surface Wind Field");

        String prelink1 = rawhtml.substring(index1[0]+10, index2[0]-1);
        String prelink2 = rawhtml.substring(index1[1]+10, index2[1]-1);
        String prelink3 = rawhtml.substring(index1[2]+10, index2[2]-1);
        String prelink4 = rawhtml.substring(index1[3]+10, index2[3]-1);
        String prelink5 = rawhtml.substring(index1[4]+10, index2[4]-1);

        //form full link
        chartlink1="http://www.nhc.noaa.gov"+prelink1;
        chartlink2="http://www.nhc.noaa.gov"+prelink2;
        chartlink3="http://www.nhc.noaa.gov"+prelink3;
        chartlink4="http://www.nhc.noaa.gov"+prelink4;
        chartlink5="http://www.nhc.noaa.gov"+prelink5;

        // Verify chartlinks in monitor
       // Log.i("chartlink",chartlink1);
       // Log.i("chartlink",chartlink2);
       // Log.i("chartlink",chartlink3);
       // Log.i("chartlink",chartlink4);
       // Log.i("chartlink",chartlink5);

    }

    public void displayChart1(View view){
        web.loadUrl(chartlink1);

    }

    public void displayChart2(View view){
        web.loadUrl(chartlink2);

    }

    public void displayChart3(View view){
        web.loadUrl(chartlink3);

    }

    public void displayChart4(View view){
        web.loadUrl(chartlink4);

    }

    public void displayChart5(View view){
        web.loadUrl(chartlink5);

    }

}
