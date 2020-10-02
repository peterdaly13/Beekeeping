package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    ImageView imageView;
    EditText editTextUserName;
    EditText editTextPassword;
    Button buttonLogin;
    Button buttonSignup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        imageView = (ImageView) findViewById(R.id.imageView3);
        imageView.setImageResource(R.drawable.beelogo);

        editTextUserName = (EditText) findViewById(R.id.editTextUserName);
        editTextPassword = (EditText) findViewById(R.id.editTextPassword);

        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonSignup = (Button) findViewById(R.id.buttonSignup);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //just an animation
                Animation animation = new AlphaAnimation(1.0f, 0.6f);
                animation.setDuration(500); //miliseconds
                buttonLogin.startAnimation(animation);


            }
        });
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //just an animation
                Animation animation = new AlphaAnimation(1.0f, 0.6f);
                animation.setDuration(500); //miliseconds
                buttonSignup.startAnimation(animation);


            }
        });



    }
}