package com.platonicc.cantus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.LottieOnCompositionLoadedListener;
import com.crashlytics.android.Crashlytics;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.FirebaseDatabase;

import io.fabric.sdk.android.Fabric;
import org.w3c.dom.Text;

public class SplashScreen extends AppCompatActivity {

    private LottieAnimationView animationWaves;
    private TextView logoText;
    private int PRESISTANT_ENABLED_FLAG = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fabric.with(this, new Crashlytics());
        if(PRESISTANT_ENABLED_FLAG == 0){
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        PRESISTANT_ENABLED_FLAG = 1;
        }
        setContentView(R.layout.activity_splash_screen);

        animationWaves = findViewById(R.id.splash_waves);
        logoText = findViewById(R.id.splash_logo);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                getWindow().setNavigationBarColor(getResources().getColor(R.color.colorPrimaryLight));
                animateSplashScreen();
                endSplashScreen();
            }},600);

    }


    //Animates the splash screen elements
    private void animateSplashScreen() {
                animationWaves.setVisibility(View.VISIBLE);
                logoText.setVisibility(View.VISIBLE);
                YoYo.with(Techniques.FlipInX).duration(1500).repeat(0).playOn(findViewById(R.id.splash_logo));
                YoYo.with(Techniques.SlideInUp).duration(1500).repeat(0).playOn(findViewById(R.id.splash_waves));
    }

    // Decides which activity to go after the splash screen and navigates to the particular activity
    private void endSplashScreen() {
        if(!new check().isValidUser()) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainActivityIntent = new Intent(SplashScreen.this, Auth.class);
                    startActivity(mainActivityIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 5000); }
        else{
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent mainActivityIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainActivityIntent);
                    overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
                    finish();
                }
            }, 5000);
        }
    }
}
