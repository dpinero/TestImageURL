package com.mycompany.testimageurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

public class convectiveviewer extends AppCompatActivity {
    String nexturl;
    String torurl;
    String windturl;
    String hailturl;
    WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convectiveviewer);
        web = (WebView) findViewById(R.id.webview9);
        //set image url size to match screen size
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        Intent intent = getIntent();
        String imgtime = intent.getStringExtra(TropicalStormsActivity.EXTRA_MESSAGE);
        nexturl = "http://www.spc.noaa.gov/products/outlook/day1otlk_" + imgtime + ".gif";
        torurl = "http://www.spc.noaa.gov/products/outlook/day1probotlk_" + imgtime + "_torn.gif";
        windturl = "http://www.spc.noaa.gov/products/outlook/day1probotlk_" + imgtime + "_wind.gif";
        hailturl = "http://www.spc.noaa.gov/products/outlook/day1probotlk_" + imgtime + "_hail.gif";
        web.loadUrl(nexturl);

    }

    public void displaycategorical(View view) {
        web.loadUrl(nexturl);

    }

    public void displaytornado(View view) {
        web.loadUrl(torurl);

    }

    public void displaywindt(View view) {
        web.loadUrl(windturl);

    }

    public void displayhailt(View view) {
        web.loadUrl(hailturl);
    }
}
