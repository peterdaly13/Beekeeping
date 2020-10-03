package com.example.beekeeping;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class MeActivity extends AppCompatActivity {
    ImageView imageView;
    Button button_profile;
    private static final int RESULT_LOAD_IMAGE =1;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_me);

        imageView = (ImageView) findViewById(R.id.profile_picture);
        button_profile = (Button) findViewById(R.id.buttonProfile);

        final FirebaseUser FBuser = FirebaseAuth.getInstance().getCurrentUser();
        final String uid = FBuser.getUid();
        user_id = uid;
        //check to see if this user has a profile pic
        MainActivity.checkProfileImg(new CheckCallBack() {
            @Override
            public void onCallback(Boolean fileFound) {
                if (fileFound){
                    MainActivity.downloadProfileImg(new ImageCallBack() {
                        @Override
                        public void onCallback(byte[] bytes) {
                            Log.d("file", "Downloadign profile pictuere");
                            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
                            imageView.setImageBitmap(bitmap);
                        }
                    }, uid);
                } else{
                    Log.d("file", "File not found, no upload");
                }
            }
        }, uid);


        button_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, RESULT_LOAD_IMAGE);

            }
        });



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

    public void onApiariesClick(View v) {
        startActivity(new Intent(MeActivity.this, ApiariesActivity.class));
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && data != null){
            Uri selectedImage= data.getData();
            imageView.setImageURI(selectedImage);

            // Get the data from an ImageView as bytes
            imageView.setDrawingCacheEnabled(true);
            imageView.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();

            MainActivity.setProfileImg(user_id, bytes);
        }
    }
}