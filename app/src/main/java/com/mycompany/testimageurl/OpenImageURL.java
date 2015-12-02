package com.mycompany.testimageurl;



import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.Random;
import java.util.TimeZone;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.net.*;
import java.io.*;

public class OpenImageURL extends AppCompatActivity {
    int[] packedcount = {0,0};
    int counter1= packedcount[0];
    int count60 = packedcount[1];
    boolean handle=false;
    boolean checkhandle=false;
    int loopcount=0;
    float currentScale;
    final SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
    String nexturl="test";
    String forecasttype;
    String testurl2= "test";
    String[] testurl3= new String[20];

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //set the view to be used for this activity
        setContentView(R.layout.activity_open_image_url);

        //get time from the main activity spinner
        Intent intent = getIntent();
        String intentmessage = intent.getStringExtra(ModelSelect.EXTRA_MESSAGE);
        String[] intentmessarray = intentmessage.split(";");
        String timeselect = intentmessarray[0];
        forecasttype = intentmessarray[1];
        //split the string to get the selected counter1 and count60
        String[] timeselarray = timeselect.split(":");
        String count1 = timeselarray[1];
        String count60s = timeselarray[0];
        counter1 = Integer.parseInt(count1);
        count60 = Integer.parseInt(count60s);
        // Log.i("openimagemessage","the openimage time selected is "+timeselect);
        //point view of interest to established view in layout
        WebView web = (WebView) findViewById(R.id.webview1);
        // web.setVerticalScrollBarEnabled(true);
        // web.setHorizontalScrollBarEnabled(true);
        //set image url size to match screen size
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
        //allow pinch zoom on image
        web.getSettings().setBuiltInZoomControls(true);
        web.getSettings().setDisplayZoomControls(false);
        int default_zoom_level = 85;
        ;
        web.setInitialScale(default_zoom_level);

        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));


        //check url to find latest hrrr imagery

        for (int i = 1; i < 13; i++) {
            handle = false;
            Date currentDate1 = new Date(System.currentTimeMillis() - 3600 * 1000 * i);
            String currentTime1 = sdf.format(currentDate1);
            String testurl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/" + currentTime1 + "/full/1ref_sfc_f03.png";
            //Log.i("testurl", testurl);
            //http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime1+"/full/1ref_sfc_f04.png
            //http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime1+"/full/1ref15min_sfc_f0045.png
            MyTask task = new MyTask();
            try {
                // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
                handle = task.execute(testurl).get();
            } catch (Exception e) {
            }
            //  Log.i("testimageurl", "CHECK THIS OUT BLAHBLAHBLDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDHABLHAH " + i +handle);
            if (handle) {
                loopcount = i;
                break;
            }
        }

        Date currentDate2 = new Date(System.currentTimeMillis() - 3600 * 1000 * loopcount);
        String currentTime2 = sdf.format(currentDate2);
        //load the image url, adjust url based on count length

        int countlength = String.valueOf(counter1).length();
        int count60length = String.valueOf(count60).length();
        //ping the pre load page to access image, ,might need to add two other if conditions like the other if statements
        //store all potential urls and ping them
        for (int ii = 0; ii <= 19; ii++) {
            int iilength = String.valueOf(ii).length();
            if (iilength == 1) {
                testurl3[ii] = "http://rapidrefresh.noaa.gov/HRRR/displayMapLocalDiskDateDomainZipTZA.cgi?keys=hrrr_jet:&runtime=" + currentTime2 + "&plot_type=" + forecasttype + "&fcst=0" + ii + "&time_inc=60&num_times=25&model=hrrr&ptitle=HRRR%20Model%20Fields%20-%20Experimental&maxFcstLen=24&fcstStrLen=-1&domain=full&adtfn=1";
            } else {
                testurl3[ii] = "http://rapidrefresh.noaa.gov/HRRR/displayMapLocalDiskDateDomainZipTZA.cgi?keys=hrrr_jet:&runtime=" + currentTime2 + "&plot_type=" + forecasttype + "&fcst=" + ii + "&time_inc=60&num_times=25&model=hrrr&ptitle=HRRR%20Model%20Fields%20-%20Experimental&maxFcstLen=24&fcstStrLen=-1&domain=full&adtfn=1";
            }


            RetrieveSiteData task1 = new RetrieveSiteData();
            try {
                // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
                task1.execute(testurl3[ii]);
            } catch (Exception e) {
            }
        }
        //MyTask task1 = new MyTask();
        //try{
        //checkhandle = task1.execute(testurl2).get();
        //   }
        // catch(Exception e){}
        if (countlength == 1 && count60length == 1) {
            testurl2 = "http://rapidrefresh.noaa.gov/HRRR/displayMapLocalDiskDateDomainZipTZA.cgi?keys=hrrr_jet:&runtime=" + currentTime2 + "&plot_type=" + forecasttype + "&fcst=0" + count60 + "&time_inc=60&num_times=25&model=hrrr&ptitle=HRRR%20Model%20Fields%20-%20Experimental&maxFcstLen=24&fcstStrLen=-1&domain=full&adtfn=1";
        } else {
            testurl2 = "http://rapidrefresh.noaa.gov/HRRR/displayMapLocalDiskDateDomainZipTZA.cgi?keys=hrrr_jet:&runtime=" + currentTime2 + "&plot_type=" + forecasttype + "&fcst=" + count60 + "&time_inc=60&num_times=25&model=hrrr&ptitle=HRRR%20Model%20Fields%20-%20Experimental&maxFcstLen=24&fcstStrLen=-1&domain=full&adtfn=1";

            RetrieveSiteData task2 = new RetrieveSiteData();
            try {
                // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
                String html = task2.execute(testurl2).get();
            } catch (Exception e) {
            }
        }

            if (countlength == 1 && count60length == 1) {
                nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/" + currentTime2 + "/full/" + forecasttype + "_f0" + count60 + ".png";
                //Log.i("nexturl", nexturl);
            } else if (countlength == 2 && count60length == 1) {
                nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/" + currentTime2 + "/full/" + forecasttype + "_f0" + count60 + ".png";
            } else if (countlength == 2 && count60length == 2) {
                nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/" + currentTime2 + "/full/" + forecasttype + "_f" + count60 + ".png";
            } else {
                nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/" + currentTime2 + "/full/" + forecasttype + "_f" + count60 + ".png";
            }
            web.loadUrl(nexturl);
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

    public int[] displayPicture(View view){
        // Find current date and time
        System.gc();
        if(counter1==0 && count60==20) {
            counter1= counter1;
            count60=count60;
        }
        else{
            //counter1=counter1+15;
            //if(counter1==60){
                count60=count60+1;
                counter1=0;
            //}
        }
        Date currentDate = new Date(System.currentTimeMillis() - 3600 * 1000* loopcount);
        String currentTime = sdf.format(currentDate);
        // Log.i("testimageurl", "bleh "+loopcount);
        // Log.i("testimageurl",currentTime);


        int countlength = String.valueOf(counter1).length();
        int count60length = String.valueOf(count60).length();

        //if statement to adjust appended url based on time
        if(countlength==1 && count60length==1) {
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f0"+count60+".png";
             Log.i("nexturl", nexturl);
        }
        else if(countlength==2 && count60length==1){
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f0"+count60+".png";
        }
        else if(countlength==2 && count60length==2){
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f"+count60+".png";
        }
        else{
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f"+count60+".png";
        }
        WebView web = (WebView) findViewById(R.id.webview1);

        //////THE FOLLOWING COMMENTED OFF AREA CHECKS IF NEXT IMAGE 15 IN FUTURE EXISTS
        // MyTask task1 = new MyTask();
        //make sure that the models arent currently waiting, might replace with a "wait 3-5 min for site to load new time model data"
        //try{
        // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread..
        //    handle = task1.execute(nexturl).get();}
        // catch (Exception e) {
        // }
        // if (handle) {
        // }
        //  else{
        //   Date currentDate2 = new Date(System.currentTimeMillis() - 3600 * 1000* (loopcount-1));
        //   String currentTime2 = sdf.format(currentDate2);
        //   if(countlength==1 && count60length==1) {
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f0"+count60+"0"+counter1+".png";
        //   }
        //   else if(countlength==2 && count60length==1){
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f0"+count60+counter1+".png";
        //   }
        //    else if(countlength==2 && count60length==2){
        //        nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f"+count60+counter1+".png";
        //    }
        //   else{
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f"+count60+"0"+counter1+".png";
        //  }
        // }
        //   Log.i("testimageurl",nexturl);

        //////the next section attempts to keep consistent the zoom and position between images. work in progress

       //Point Scroll=new Point(0,0);
       // int current_scale_level=(int)web.getScale()*100;
       // Scroll.x=web.getScrollX();
       // Scroll.y=web.getScrollY();
       // int scrolltestx = web.getScrollX();
       // int scrolltesty = web.getScrollY();
       // String scrolltestxs = Integer.toString(scrolltestx);
       // String scrolltestys = Integer.toString(scrolltesty);
       // web.setInitialScale(current_scale_level);

       // web.scrollTo(Scroll.x, Scroll.y);
        web.loadUrl(nexturl);
       // int scrolltestx2 = web.getScrollX();
       // int scrolltesty2 = web.getScrollY();
       // String scrolltestxs2 = Integer.toString(scrolltestx2);
       // String scrolltestys2 = Integer.toString(scrolltesty2);

       // Log.i("scrolltest", scrolltestxs);
       // Log.i("scrolltest", scrolltestys);
       // Log.i("scrolltest2", scrolltestxs2);
      //  Log.i("scrolltest2", scrolltestys2);
        //stop counter once end of image data is reached, otherwise go to next image 15 minutes in the future

        packedcount[0]=counter1;
        packedcount[1]=count60;
        return packedcount;
    }

    public int[] displayPicture1(View view){
        // Find current date and time
        System.gc();
        if(counter1==0 && count60==1) {
            counter1= counter1;
            count60=count60;
        }
        else{
            if(counter1==0){
                count60=count60-1;
               // counter1=60;
            }
           // counter1=counter1-15;

        }
        Date currentDate = new Date(System.currentTimeMillis() - 3600 * 1000* loopcount);
        String currentTime = sdf.format(currentDate);
        // Log.i("testimageurl", "bleh "+loopcount);
        // Log.i("testimageurl",currentTime);

        String nexturl="test";
        int countlength = String.valueOf(counter1).length();
        int count60length = String.valueOf(count60).length();

        //if statement to adjust appended url based on time
        if(countlength==1 && count60length==1) {
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f0"+count60+".png";
            // Log.i("nexturl", nexturl);
        }
        else if(countlength==2 && count60length==1){
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f0"+count60+".png";
        }
        else if(countlength==2 && count60length==2){
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f"+count60+".png";
        }
        else{
            nexturl = "http://rapidrefresh.noaa.gov/HRRR/for_web/hrrr_jet/"+currentTime+"/full/"+forecasttype+"_f"+count60+".png";
        }
        WebView web = (WebView) findViewById(R.id.webview1);

        //THE FOLLOWING COMMENTED OFF AREA CHECKS IF NEXT IMAGE 15 IN FUTURE EXISTS
        // MyTask task1 = new MyTask();
        //make sure that the models arent currently waiting, might replace with a "wait 3-5 min for site to load new time model data"
        //try{
        // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread..
        //    handle = task1.execute(nexturl).get();}
        // catch (Exception e) {
        // }
        // if (handle) {
        // }
        //  else{
        //   Date currentDate2 = new Date(System.currentTimeMillis() - 3600 * 1000* (loopcount-1));
        //   String currentTime2 = sdf.format(currentDate2);
        //   if(countlength==1 && count60length==1) {
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f0"+count60+"0"+counter1+".png";
        //   }
        //   else if(countlength==2 && count60length==1){
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f0"+count60+counter1+".png";
        //   }
        //    else if(countlength==2 && count60length==2){
        //        nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f"+count60+counter1+".png";
        //    }
        //   else{
        //       nexturl = "http://rapidrefresh.noaa.gov/HRRRsubh/for_web/hrrr_subh_ncep_jet/"+currentTime2+"/full/1ref15min_sfc_f"+count60+"0"+counter1+".png";
        //  }
        // }
        //  Log.i("testimageurl",nexturl);

        //the next section attempts to keep consistent the zoom and position between images. work in progress
        //Point Scroll=new Point(0,0);
        //int current_scale_level=(int)web.getScale()*100;
        //Scroll.x=web.getScrollX();
        //Scroll.y=web.getScrollY();

        //web.setInitialScale(current_scale_level);
        web.loadUrl(nexturl);
       //web.scrollTo(Scroll.x,Scroll.y);

        //stop counter once end of image data is reached, otherwise go to next image 15 minutes in the future

        packedcount[0]=counter1;
        packedcount[1]=count60;
        return packedcount;
    }

//looks like I need to do some async task stuff, the following async task checks to see if the url exists and returns true or false

    private class MyTask extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected Boolean doInBackground(String... params) {

            try {

                HttpURLConnection.setFollowRedirects(false);
                HttpURLConnection con = (HttpURLConnection) new URL(params[0]).openConnection();
                con.setRequestMethod("HEAD");
                // Log.i("Async", "show async url " + params[0]);
                System.out.println(con.getResponseCode());
                return (con.getResponseCode() == HttpURLConnection.HTTP_OK);
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            boolean bResponse = result;
            //   myMethod(bResponse);
        }


        private boolean myMethod(Boolean bResponse) {
            handle = bResponse;
            //  Log.i("myMethod","show handle "+handle);
            return handle;
        }
    }

    private class RetrieveSiteData extends AsyncTask<String, Void, String> {

            @Override
            protected String doInBackground (String...params){

            try {

                HttpURLConnection connection = (HttpURLConnection) new URL(params[0]).openConnection();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                connection.connect();

                String html = "test";

                return html;
            } catch (Exception e) {
                e.printStackTrace();
                return "it broke";
            }

        }

            @Override
            protected void onPostExecute (String result){

        }
        }

}
