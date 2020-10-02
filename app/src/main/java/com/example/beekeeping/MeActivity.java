package com.example.beekeeping;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();

        // if new user, push new user data to DB


        TextView displayName = findViewById(R.id.display_name);
        displayName.setText(FBuser.getDisplayName());
        TextView displayEmail = findViewById(R.id.display_email);
        displayEmail.setText(FBuser.getEmail());
        ImageView profilePicture = findViewById(R.id.profile_picture);
        /*
        URL url = null;
        try {
            url = new URL("https://homepages.cae.wisc.edu/~ece533/images/cat.png");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Bitmap bmp = null;
        try {
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        profilePicture.setImageBitmap(bmp);

        //profilePicture.setImageURI(user.getPhotoUrl());

         */



        Button logout_button = (Button) findViewById(R.id.logout_button);
        logout_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onLogOutClick();
                startActivity(new Intent(MeActivity.this, MainActivity.class));
            }
        });





    }

    public void onLogOutClick() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MeActivity.this, MainActivity.class));
    }




}