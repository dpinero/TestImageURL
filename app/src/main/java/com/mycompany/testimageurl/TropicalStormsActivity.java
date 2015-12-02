package com.mycompany.testimageurl;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class TropicalStormsActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.mycompany.testimageurl.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tropical_storms);

        String html ="";
        String stormstring;

        List<TextView> textList = new ArrayList<TextView>();
        int index = 0;
        String url="http://www.nhc.noaa.gov";


        LinearLayout layout = (LinearLayout)findViewById(R.id.stormlayout);
        RetrieveSiteData task = new RetrieveSiteData();
        try{
            // the get at the end makes sure that the async task completes before the next lines are completed. however, this stalls the main thread...
            html = task.execute(url).get();}
        catch (Exception e) {
        }
        int stormcount=1;
        Log.i("htmldata", "Here:" + html);
        for (int i=1;i<=5;i++){
            //break up the string based on the location of the wallet and create a string array with any space as a delimiter
            String imgaddress="NHC Atlantic Wallet "+i;
            index = html.indexOf(imgaddress);
            //Log.i("index", "Here:" + index);
            String imgstorm = html.substring(index, index+90);
            String[] split = imgstorm.split("\\s+");
            if(!imgstorm.contains("No current")){
                TextView newStorm = new TextView(this);
                //adjust the parsing based on if its a tropical storm or hurricane, prepare string stormstring to derive storm information
                if(split[5].equals("Hurricane")) {
                    newStorm.setText(stormcount+". "+split[5]+" "+split[6]+System.getProperty("line.separator"));
                    newStorm.setTextSize(24);
                    newStorm.setTypeface(null, Typeface.BOLD);
                    String split7 = split[7].replace("(", "");
                    split7 = split7.replace(")", "");
                    split7 = split7.replace("\"", "");
                    stormstring=split7+" "+split[5]+" "+split[6];
                    stormcount=stormcount+1;
                }
                else{
                    newStorm.setText(stormcount + ". " + split[5] + " " + split[6] + " " + split[7] + System.getProperty("line.separator"));
                    newStorm.setTextSize(24);
                    newStorm.setTypeface(null, Typeface.BOLD);
                    String split8 = split[8].replace("(", "");
                    split8 = split8.replace(")", "");
                    split8 = split8.replace("\"", "");
                    stormstring=split8+" "+split[5]+" "+split[6]+" "+split[7];
                    stormcount=stormcount+1;
                }
                int index2 = html.indexOf("!--storm identification: "+stormstring);
                //the following gets the storm data
                //storm summary starts at +800
                //stormtime and stats start at 1100
                Log.i("addtext", "Here:" + imgstorm);
                Log.i("addtext", "Here:" + index2);
                String substorminfo1 = html.substring(index2, index2 + 1300);
                int index3 = substorminfo1.indexOf("<strong style=\"font");
                String substorminfo2 = substorminfo1.substring(index3+34, index3 + 206);
                substorminfo2 = substorminfo2.replace("</strong>", "");
                substorminfo2 = substorminfo2.replace("<br>", " ");
                substorminfo2 = substorminfo2.replace("&deg;", " ");
                substorminfo2 = substorminfo2.replace("<i>", " ");
                substorminfo2 = substorminfo2.replace("</i>", " ");
                String[] infosplit = substorminfo2.split("\\s+");
                TextView newStorm2 = new TextView(this);
                newStorm2.setText(infosplit[0] + " " + infosplit[1] + " " + infosplit[2] + " " + infosplit[3] + " " + infosplit[4] + " " + infosplit[5] + "\n" +
                        infosplit[6] + " " + infosplit[7] + " " + infosplit[8] + " " + infosplit[9] + " " + infosplit[10] + "\n" +
                        infosplit[11] + " " + infosplit[12] + " " + infosplit[13] + " " + infosplit[14] + " " + infosplit[15] + "\n" +
                        infosplit[16] + " " + infosplit[17] + " " + infosplit[18] + " " + infosplit[19] + "\n" +
                        infosplit[20] + " " + infosplit[21] + " " + infosplit[22] + " " + infosplit[23] + System.getProperty("line.separator"));
                newStorm2.setTextSize(18);
                int chartindex=html.indexOf("<!-- BEGIN graphcrosslink thumbnails section-->");
                final String substormchartinfo = html.substring(chartindex, chartindex + 1601);
                Log.i("addtext", "Here:" + chartindex);
                //add views to the layout and text array
                // add button for storm data
                final Button stormdata = new Button(this);
                stormdata.setOnClickListener( new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        System.gc();
                        Intent intent6 = new Intent(stormdata.getContext(), Openstormcharts.class);
                        intent6.putExtra(EXTRA_MESSAGE,substormchartinfo);
                        startActivity(intent6);
                    }
                });
                stormdata.setText("View Storm Charts");
                //format the button layout
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                //set button margins (not sure if this works......)
                layoutParams.setMargins(0, 0, 0, 20);
                layout.addView(newStorm);
                textList.add(newStorm);
                layout.addView(newStorm2);
                textList.add(newStorm2);
                layout.addView(stormdata, layoutParams);
                //textList.add(stormdata);
            }
        }
        //display NO CURRENT STORMS if no storms are found in the textlist
        if(textList.size()<1){
            TextView noStorm = new TextView(this);
            noStorm.setTextSize(18);
            noStorm.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            noStorm.setText("No Tropical Storms in the Atlantic Basin");
            layout.addView(noStorm);
            textList.add(noStorm);

        }

        //Log.i("index", "Here:" + index);

        //Log.i("address string", "Here:" + imgtime);

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
}
