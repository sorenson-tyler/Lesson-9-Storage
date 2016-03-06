package com.example.tylersorenson.groupworkstorage;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView number;
    Button advance, save;
    public static final String PREFS_NAME = "SavedInt";
    public static final String STARTING_POINT = "0";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setInputs();
        loadPreferences();

        advance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = number.getText().toString();
                int previous = Integer.parseInt(current);
                previous++;
                number.setText(Integer.toString(previous));
            }
        });

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String current = number.getText().toString();
                SharedPreferences settings = getPreferences(0);
                SharedPreferences.Editor editor = settings.edit();
                int numberSaved = Integer.parseInt(current);
                Log.e("Saving", "Number saved is: " + numberSaved);
                editor.putInt("Total", numberSaved);
                editor.commit();
            }
        });
    }

    public void setInputs() {
        number = (TextView) findViewById(R.id.number);
        number.setText("0");
        advance = (Button) findViewById(R.id.advance);
        save = (Button) findViewById(R.id.save);
    }

    public void loadPreferences() {
        SharedPreferences settings = getPreferences(0);
        int savedNumber = settings.getInt("Total", 0);
        String saved = Integer.toString(savedNumber);
        Log.e("Loading", "Number saved is " + saved);
        number.setText(Integer.toString(savedNumber));
    }
}
