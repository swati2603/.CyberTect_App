package com.example.splash_screen;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

    public class SplashScrren extends AppCompatActivity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_ALLOW_LOCK_WHILE_SCREEN_ON);
            getSupportActionBar().hide();
            setContentView(R.layout.activity_splash_screen);
            Thread thread = new Thread()
            {
                public void run()
                {
                    try{
                        sleep(10000);
                    }
                    catch(Exception e){
                        e.printStackTrace();
                    }
                    finally
                    {
                        Intent intent= new Intent(SplashScrren.this , Register.class);
                        startActivity(intent);
                    }
                }
            }; thread.start();
        }
    }

