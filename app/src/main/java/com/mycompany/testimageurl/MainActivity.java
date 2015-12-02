package com.mycompany.testimageurl;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

public class MainActivity extends AppCompatActivity {
    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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


    public void gotoModels(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent3 = new Intent(this, ModelSelect.class);
        startActivity(intent3);
    }

    public void openconvective(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent4 = new Intent(this, ConvectiveActivity.class);
        startActivity(intent4);

    }

    public void openstormlist(View view) {
        // We create an intent for the DisplayMessageActivity activity
        System.gc();
        Intent intent5 = new Intent(this, TropicalStormsActivity.class);
        startActivity(intent5);

    }
}
