// From https://firebase.google.com/docs/auth/android/firebaseui#java
// And https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#sign-out
package com.example.beekeeping;

import android.content.Intent;
import android.os.Bundle;
import android.os.Debug;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {
    private static final int RC_SIGN_IN = 123; //what's this?


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createSignInIntent();
    }

    /*
        Onclick function which redirects user to their profile
     */
    public void goToProfileOnClick(View view) {
        startActivity(new Intent(LoginActivity.this, MeActivity.class));
    }

    /*
        Create and launch sign-in intent
     */
    public void createSignInIntent() {
        startActivityForResult( AuthUI.getInstance().createSignInIntentBuilder().build(), RC_SIGN_IN );
        
    }


}