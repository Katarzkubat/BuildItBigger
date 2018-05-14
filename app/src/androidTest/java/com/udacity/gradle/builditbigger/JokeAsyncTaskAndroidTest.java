package com.udacity.gradle.builditbigger;


import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.concurrent.CountDownLatch;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)

public class JokeAsyncTaskAndroidTest {

   @Test
    public void doInBackground() throws Exception {

        final CountDownLatch signal = new CountDownLatch(1);

        MyCallback mockedActivity = new MyCallback() {

            @Override
            public void setAsyncTaskResults(String result) {
                assertNotNull(result);
                assertEquals("Joke", result);
                signal.countDown();
            }
        };
        new JokeAsyncTask().execute(mockedActivity);
        signal.await();
    }
}