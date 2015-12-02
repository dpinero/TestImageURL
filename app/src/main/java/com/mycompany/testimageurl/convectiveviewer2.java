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

public class convectiveviewer2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_convectiveviewer2);
        WebView web = (WebView) findViewById(R.id.webview10);
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);

        Intent intent = getIntent();
        String nexturl = intent.getStringExtra(TropicalStormsActivity.EXTRA_MESSAGE);

        web.loadUrl(nexturl);
        Log.i("conv2url",nexturl);
    }

}
