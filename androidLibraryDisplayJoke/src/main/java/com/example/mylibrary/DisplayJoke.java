package com.example.mylibrary;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DisplayJoke extends AppCompatActivity {

    private static final String JOKE = "joke" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_joke);

        TextView joke = findViewById(R.id.joke);

        if(joke != null) {
            joke.setText(getIntent().getStringExtra(JOKE));
            Log.d("RESULTS4", "DISPLAYJOKE" + JOKE);
        }

    }

}
