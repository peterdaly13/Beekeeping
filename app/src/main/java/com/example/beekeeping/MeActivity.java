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
import java.util.List;

public class MeActivity extends AppCompatActivity {
    int numApiaries = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        final FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = FBuser.getUid();

        TextView displayName = findViewById(R.id.display_name);
        displayName.setText(FBuser.getDisplayName());
        TextView displayEmail = findViewById(R.id.display_email);
        displayEmail.setText(FBuser.getEmail());
        ImageView profilePicture = findViewById(R.id.profile_picture);

        // if new user, push new user data to DB
        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                if(user== null){
                    User newUser = new User(FBuser.getDisplayName(), FBuser.getUid());
                    MainActivity.pushData(newUser);
                }
            }
        },uid);





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



    void updateNumApiaries(String uid) {
        MainActivity.pullData(new DataCallback() {
            @Override
            public void onCallback(User user) {
                List<Apiary> aList = user.getApiaryList();
                updateNumApiaries(aList);
            }
        },uid);
    }

    void updateNumApiaries(List<Apiary> list) {
        this.numApiaries = list.size();
    }

    public void onLogOutClick() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MeActivity.this, MainActivity.class));
    }

    public void onApiariesClick(View v) {
        startActivity(new Intent(MeActivity.this, ApiariesActivity.class));
    }




}