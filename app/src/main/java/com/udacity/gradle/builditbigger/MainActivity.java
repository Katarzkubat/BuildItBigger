package com.udacity.gradle.builditbigger;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.mylibrary.DisplayJoke;

public class MainActivity extends AppCompatActivity implements MyCallback, OnButtonClicked{

    private static final String JOKE = "joke";
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progressBar);
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

    public void setAsyncTaskResults(String result) {

        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        launchLibraryActivity(result);
    }

    private void launchLibraryActivity(String result) {

        Intent passJoke = new Intent(this, DisplayJoke.class);
        passJoke.putExtra(JOKE, result);
        startActivity(passJoke);
    }

    @Override
    public void TellJoke() {

        new JokeAsyncTask().execute(this);
        progressBar.setVisibility(View.VISIBLE);

    }
}
