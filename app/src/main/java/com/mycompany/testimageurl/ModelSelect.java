package com.mycompany.testimageurl;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class ModelSelect extends AppCompatActivity {
    String radarf="1ref_sfc";
    String tempf="temp_2m";
    String windf="wind_10m";
    String accprecipf="totp_sfc";
    public final static String EXTRA_MESSAGE = "com.mycompany.testimageurl";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_model_select);
        Spinner timeselect = (Spinner)findViewById(R.id.spinner1);
        String[] spinnerlist = new String[]{//"00:00", "00:15", "00:30", "00:45",
                "01:00",// "01:15", "01:30", "01:45",
                "02:00", //"02:15", "02:30", "02:45",
                "03:00", //"03:15", "03:30", "03:45",
                "04:00", //"04:15", "04:30", "04:45",
                "05:00", //"05:15", "05:30", "05:45",
                "06:00", //"06:15", "06:30", "06:45",
                "07:00", //"07:15", "07:30", "07:45",
                "08:00", //"08:15", "08:30", "08:45",
                "09:00", //"09:15", "09:30", "09:45",
                "10:00", //"10:15", "10:30", "10:45",
                "11:00",// "11:15", "11:30", "11:45",
                "12:00", //"12:15", "12:30", "12:45",
                "13:00", //"13:15", "13:30", "13:45",
                "14:00",//, "14:15", "14:30", "14:45", "15:00"};
        "15:00","16:00","17:00","18:00","19:00","20:00"};
        ArrayAdapter<String> timeadapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, spinnerlist);
        timeselect.setAdapter(timeadapter);

    }
    public void displayradarf(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent2 = new Intent(this, OpenImageURL.class);
        Spinner timeselect = (Spinner) findViewById(R.id.spinner1);
        Integer timeposition = timeselect.getSelectedItemPosition();
        String[] timearray = new String[]{//"00:00", "00:15", "00:30", "00:45",
                "01:00", //"01:15", "01:30", "01:45",
                "02:00", //"02:15", "02:30", "02:45",
                "03:00", //"03:15", "03:30", "03:45",
                "04:00", //"04:15", "04:30", "04:45",
                "05:00", //"05:15", "05:30", "05:45",
                "06:00", //"06:15", "06:30", "06:45",
                "07:00", //"07:15", "07:30", "07:45",
                "08:00", //"08:15", "08:30", "08:45",
                "09:00", //"09:15", "09:30", "09:45",
                "10:00", //"10:15", "10:30", "10:45",
                "11:00", //"11:15", "11:30", "11:45",
                "12:00", //"12:15", "12:30", "12:45",
                "13:00", //"13:15", "13:30", "13:45",
                "14:00",//, "14:15", "14:30", "14:45", "15:00"};
        "15:00","16:00","17:00","18:00","19:00","20:00"};
        // get conversion factors based on spinner locations
        String timeString = timearray[timeposition];

        // Log.i("clickextra", "the chosen time int is " + timeString);
        intent2.putExtra(EXTRA_MESSAGE, timeString + ";" + radarf);
        startActivity(intent2);

        //  public void startFreqtoWave(View view) {
        //     Intent intent1 = new Intent(this, freqtowave.class);
        //     startActivity(intent1);
        // }



    }

    public void displaytempf(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent3 = new Intent(this, OpenImageURL.class);
        Spinner timeselect = (Spinner) findViewById(R.id.spinner1);
        Integer timeposition = timeselect.getSelectedItemPosition();
        String[] timearray = new String[]{//"00:00", "00:15", "00:30", "00:45",
                "01:00", //"01:15", "01:30", "01:45",
                "02:00", //"02:15", "02:30", "02:45",
                "03:00", //"03:15", "03:30", "03:45",
                "04:00", //"04:15", "04:30", "04:45",
                "05:00", //"05:15", "05:30", "05:45",
                "06:00", //"06:15", "06:30", "06:45",
                "07:00", //"07:15", "07:30", "07:45",
                "08:00", //"08:15", "08:30", "08:45",
                "09:00", //"09:15", "09:30", "09:45",
                "10:00", //"10:15", "10:30", "10:45",
                "11:00", //"11:15", "11:30", "11:45",
                "12:00", //"12:15", "12:30", "12:45",
                "13:00", //"13:15", "13:30", "13:45",
                "14:00",//, "14:15", "14:30", "14:45", "15:00"};
                "15:00","16:00","17:00","18:00","19:00","20:00"};
        // get conversion factors based on spinner locations
        String timeString = timearray[timeposition];

        // Log.i("clickextra", "the chosen time int is " + timeString);
        intent3.putExtra(EXTRA_MESSAGE, timeString+";"+tempf);
        startActivity(intent3);

        //  public void startFreqtoWave(View view) {
        //     Intent intent1 = new Intent(this, freqtowave.class);
        //     startActivity(intent1);
        // }



    }

    public void displaywindf(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent4 = new Intent(this, OpenImageURL.class);
        Spinner timeselect = (Spinner) findViewById(R.id.spinner1);
        Integer timeposition = timeselect.getSelectedItemPosition();
        String[] timearray = new String[]{//"00:00", "00:15", "00:30", "00:45",
                "01:00", //"01:15", "01:30", "01:45",
                "02:00", //"02:15", "02:30", "02:45",
                "03:00", //"03:15", "03:30", "03:45",
                "04:00", //"04:15", "04:30", "04:45",
                "05:00", //"05:15", "05:30", "05:45",
                "06:00", //"06:15", "06:30", "06:45",
                "07:00", //"07:15", "07:30", "07:45",
                "08:00", //"08:15", "08:30", "08:45",
                "09:00", //"09:15", "09:30", "09:45",
                "10:00", //"10:15", "10:30", "10:45",
                "11:00", //"11:15", "11:30", "11:45",
                "12:00", //"12:15", "12:30", "12:45",
                "13:00", //"13:15", "13:30", "13:45",
                "14:00",//, "14:15", "14:30", "14:45", "15:00"};
                "15:00","16:00","17:00","18:00","19:00","20:00"};
        // get conversion factors based on spinner locations
        String timeString = timearray[timeposition];

        // Log.i("clickextra", "the chosen time int is " + timeString);
        intent4.putExtra(EXTRA_MESSAGE, timeString+";"+windf);
        startActivity(intent4);

        //  public void startFreqtoWave(View view) {
        //     Intent intent1 = new Intent(this, freqtowave.class);
        //     startActivity(intent1);
        // }



    }

    public void displayaccprecipf(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent5 = new Intent(this, OpenImageURL.class);
        Spinner timeselect = (Spinner) findViewById(R.id.spinner1);
        Integer timeposition = timeselect.getSelectedItemPosition();
        String[] timearray = new String[]{//"00:00", "00:15", "00:30", "00:45",
                "01:00", //"01:15", "01:30", "01:45",
                "02:00", //"02:15", "02:30", "02:45",
                "03:00", //"03:15", "03:30", "03:45",
                "04:00", //"04:15", "04:30", "04:45",
                "05:00", //"05:15", "05:30", "05:45",
                "06:00", //"06:15", "06:30", "06:45",
                "07:00", //"07:15", "07:30", "07:45",
                "08:00", //"08:15", "08:30", "08:45",
                "09:00", //"09:15", "09:30", "09:45",
                "10:00", //"10:15", "10:30", "10:45",
                "11:00", //"11:15", "11:30", "11:45",
                "12:00", //"12:15", "12:30", "12:45",
                "13:00", //"13:15", "13:30", "13:45",
                "14:00",//, "14:15", "14:30", "14:45", "15:00"};
                "15:00","16:00","17:00","18:00","19:00","20:00"};
        // get conversion factors based on spinner locations
        String timeString = timearray[timeposition];

        // Log.i("clickextra", "the chosen time int is " + timeString);
        intent5.putExtra(EXTRA_MESSAGE, timeString+";"+accprecipf);
        startActivity(intent5);

        //  public void startFreqtoWave(View view) {
        //     Intent intent1 = new Intent(this, freqtowave.class);
        //     startActivity(intent1);
        // }



    }

    public void displayGIF(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent3 = new Intent(this, OpenGIFURL.class);
        startActivity(intent3);
    }
}
