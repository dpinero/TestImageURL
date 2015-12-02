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

public class convectiveviewer3 extends AppCompatActivity {
    String nexturl;
    String proburl;

    WebView web;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convectiveviewer3);
        web = (WebView) findViewById(R.id.webview11);
        //set image url size to match screen size
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        Intent intent = getIntent();
        String daymsg = intent.getStringExtra(TropicalStormsActivity.EXTRA_MESSAGE);
        String day = daymsg.substring(0, 4);
        String imgtime = daymsg.substring(4, 8);
        String daynum=day.substring(3,4);
        int dayint =Integer.parseInt(daynum);
        if(dayint==2){
        nexturl = "http://www.spc.noaa.gov/products/outlook/"+day+"otlk_" + imgtime + ".gif";
        proburl = "http://www.spc.noaa.gov/products/outlook/"+day+"probotlk_" + imgtime + "_any.gif";}
        else {
            nexturl = "http://www.spc.noaa.gov/products/outlook/"+day+"otlk_" + imgtime + ".gif";
            proburl = "http://www.spc.noaa.gov/products/outlook/"+day+"prob_" + imgtime + ".gif";}
        web.loadUrl(nexturl);
        // Log.i("day2or3link", nexturl);
       Log.i("day2or3link", proburl);
    }
    public void displaycategorical(View view) {
        web.loadUrl(nexturl);

    }

    public void displayprob(View view) {
        web.loadUrl(proburl);

    }
}
