package com.udacity.gradle.builditbigger.free;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import com.udacity.gradle.builditbigger.OnButtonClicked;
import com.udacity.gradle.builditbigger.R;


public class MainActivityFragment extends Fragment{

    public MainActivityFragment() {}

    OnButtonClicked mCallback;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            mCallback = (OnButtonClicked) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString());
        }
    }

    private InterstitialAd mInterstitialAd;

    private static final String JOKE = "joke";

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_main, container, false);
        final AdView mAdView = (AdView) root.findViewById(R.id.adView);

        MobileAds.initialize(getContext(), "ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd = new InterstitialAd(getContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-3940256099942544/1033173712");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        Button button = (Button)  root.findViewById(R.id.button_show_joke);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();


                } else {
                    Log.d("TAG", "The interstitial wasn't loaded yet.");
                }
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {

            @Override
            public void onAdOpened() {
                Toast.makeText(getContext(), "Such a nice ad", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAdClosed() {
                mCallback.TellJoke();
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }
        });

        // Create an ad request. Check logcat output for the hashed device ID to
        // get test ads on a physical device. e.g.
        // "Use AdRequest.Builder.addTestDevice("ABCDEF012345") to get test ads on this device."
       /* AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest); */

        return root;
    }

}
