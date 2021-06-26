package com.example.ebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ebook.Login_Activity;
import com.example.ebook.R;

public class WelcomeScreen extends AppCompatActivity {

    private static int TIME=3000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent homeIntent = new Intent(WelcomeScreen.this, Login_Activity.class);
                startActivity(homeIntent);
                finish();
            }
        },TIME);
    }
}
