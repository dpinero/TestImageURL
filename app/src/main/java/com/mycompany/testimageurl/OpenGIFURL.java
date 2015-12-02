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

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.logging.Handler;

public class OpenGIFURL extends AppCompatActivity {
    boolean handle=false;

    String[] allurls={"a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a",
            "a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a","a"};
    int[] packedcount = {0,0};
    int counter1= packedcount[0];
    int count60 = packedcount[1];
    int countend = 0;
    Date currentDate1;
    String currentTime1;
    String nexturl;
    int allurlcount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_gifurl);
        //point view of interest to established view in layout
        final WebView web = (WebView) findViewById(R.id.webview2);
        //set image url size to match screen size
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        //find time

        int loopcount=0;
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));

        //loop sets time to latest current HRRR imagery
        for(int i=1;i<13;i++) {
            handle = false;
            currentDate1 = new Date(System.currentTimeMillis() - 3600 * 1000 * i);
            currentTime1 = sdf.format(currentDate1);
            String testurl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime1+"/full/1ref15min_sfc_f0045.png";
            MyTask task = new MyTask();
            try{
                // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
                handle = task.execute(testurl).get();}
            catch (Exception e) {
            }
            //  Log.i("testimageurl", "CHECK THIS OUT BLAHBLAHBLDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDHABLHAH " + i +handle);
            if (handle) {
                loopcount = i;
                break;
            }
        }

        //loop the images from 0000 to the end
        while(count60<=15 && allurlcount<40) {

            final int countlength = String.valueOf(counter1).length();
            final int count60length = String.valueOf(count60).length();



                        //if statement to adjust appended url based on time
                        if (countlength == 1 && count60length == 1) {
                            nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/" + currentTime1 + "/full/1ref15min_sfc_f0" + count60 + "0" + counter1 + ".png";
                        } else if (countlength == 2 && count60length == 1) {
                            nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/" + currentTime1 + "/full/1ref15min_sfc_f0" + count60 + counter1 + ".png";
                        } else if (countlength == 2 && count60length == 2) {
                            nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/" + currentTime1 + "/full/1ref15min_sfc_f" + count60 + counter1 + ".png";
                        } else {
                            nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/" + currentTime1 + "/full/1ref15min_sfc_f" + count60 + "0" + counter1 + ".png";
                        }
                        allurls[allurlcount]=nexturl;
            allurlcount=allurlcount+1;
                        if (counter1 == 0 && count60 == 15) {
                            counter1 = counter1;
                            count60 = count60;
                            countend = 1;
                        } else {
                            counter1 = counter1 + 15;
                            if (counter1 == 60) {
                                count60 = count60 + 1;
                                counter1 = 0;
                            }
                        }




        }

        //the following time the display of the images
        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[0]);
            }
        }, 100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[4]);
            }
        }, 1100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[8]);
            }
        }, 2100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[12]);
            }
        }, 3100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[16]);
            }
        }, 4100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[20]);
            }
        }, 5100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[24]);
            }
        }, 6100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[28]);
            }
        }, 7100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[32]);
            }
        }, 8100);

        new android.os.Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                web.loadUrl(allurls[36]);
            }
        }, 9100);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_open_gifurl, menu);
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
    private class MyTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {

                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection con =  (HttpURLConnection) new URL(params[0]).openConnection();
                con.setRequestMethod("HEAD");
                Log.i("Async", "show async url " + params[0]);
                System.out.println(con.getResponseCode());
                return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            }
            catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            boolean bResponse = result;
            //   myMethod(bResponse);
        }

    }

// this repeats the looping after the repeat gif button is pressed
    public void repeatgif(View view) {
        Intent intent = getIntent();
        finish();
        startActivity(intent);
        overridePendingTransition(0, 0);
    }


    private boolean myMethod(Boolean bResponse) {
        handle=bResponse;
        //  Log.i("myMethod","show handle "+handle);
        return handle;}


}
