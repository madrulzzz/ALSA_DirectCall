package com.alsaeng.android.alsa;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class ALSA extends Activity {

    public EditText Name,File_No;
    private static final String TAG1 = "ALSA_LOG";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_als);

        Log.i(TAG1, "Set Title Bar to ALSA and New LOGO");
        setTitle("ALSA Directory");
        getActionBar().setIcon(R.drawable.ic_launcher);

        Name = (EditText) findViewById(R.id.editText);
        File_No = (EditText) findViewById(R.id.editText2);
        final Button Search = (Button) findViewById(R.id.button);

        Search.setOnTouchListener(new View.OnTouchListener() {
            public boolean onTouch(View v, MotionEvent mt) {
                // Do something in response to button click

                switch(mt.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        // PRESSED
                        Search.setBackgroundColor(getResources().getColor(R.color.Search_Pressed));
                        return true;

                    case MotionEvent.ACTION_UP:
                        // RELEASED.
                        Search.setBackgroundColor(getResources().getColor(R.color.Search_Not_Pressed));
                        Log.i(TAG1, "Search Button Clicked.. ");
                        String name = Name.getText().toString();
                        String file = File_No.getText().toString();
                        Intent to_list = new Intent(v.getContext(),result_activity.class);
                        Log.i(TAG1, "Keywords are being sent to the result_activity");
                        to_list.putExtra("NAME",name);
                        to_list.putExtra("FILENO",file);
                        startActivity(to_list);
                        return true;
                }
                return true;

            }
        });


        //Click on Logo to go to website
        Log.i(TAG1, "Someone clicked on the Logo. Taking them to alsaeng.com");
        ImageView logo = (ImageView)findViewById(R.id.imageView);
        logo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Do something in response to button click

                Intent to_alsaeng = new Intent(Intent.ACTION_VIEW);
                to_alsaeng.setData(Uri.parse("http://alsaeng.com"));
                startActivity(to_alsaeng);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_al, menu);
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
            Toast.makeText(this.getBaseContext(), "Developed by Madhav Kishore, IT Engineer, ALSA Engineering and Construction L.L.C madhav.kishore@alsaeng.com",
                    Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
