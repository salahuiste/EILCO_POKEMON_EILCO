package com.example.pokdexcreando;

import androidx.appcompat.app.AppCompatActivity;
import com.airbnb.lottie.LottieAnimationView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;

public class lottie extends AppCompatActivity {
  public  static  int SPLASH_time =2000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lottie);
        LottieAnimationView lottieview= findViewById(R.id.lottie_layer_name);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent MainActivity = new Intent( lottie.this, MainActivity.class);
                startActivity(MainActivity);
                lottieview.setVisibility(View.GONE);
                lottie.this.finish();




            }
        },SPLASH_time);
        // Thread.sleep(1000);
       /* for(int i=0; i<=  10000; i++) {
         if(i==10000)   {
         try {Intent MainActivity = new Intent( this,MainActivity.class);
           //  View annimation = findViewById(R.id.lottie_layer_name);
             //annimation.setFil

             startActivity(MainActivity);}finally {
             finish();
         }
         }
        }*/
    }
}