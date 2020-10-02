// From https://firebase.google.com/docs/auth/android/firebaseui#java
// And https://github.com/firebase/FirebaseUI-Android/blob/master/auth/README.md#sign-out
package com.example.beekeeping;

import android.content.Intent;
import android.os.Bundle;
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

    private static final int RC_SIGN_IN = 123;
    FirebaseUser user;
    FirebaseAuth auth;
    Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //if (userSignedIn()) {
            // redirect to homepage
       // } else {
            createSignInIntent();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            TextView greet_user = (TextView) findViewById((R.id.greet_user));
            if (user != null) {
                greet_user.setText("Email: " + user.getEmail());
            }

            Button logout = (Button) findViewById(R.id.logout);
            logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                onSignOutClick(v);
            }
        });



       // }


    }

    /*
        Create and launch sign-in intent
     */
    public void createSignInIntent() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder().build(), RC_SIGN_IN);
    }

    /*
        Checks if there is already a user signed in.
        @return true if user signed in
        @return false if no user signed in
     */
    boolean userSignedIn() {
        Log.i("LoginActivity", "entering userSignedIn()");
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            Log.i("user logged in", auth.getCurrentUser().getEmail());
            return true;
        } else {
            return false;
        }
    }


    /* code for signing out */
    public void onSignOutClick(View v) {
        if (v.getId() == R.id.logout) {
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                            finish();
                        }
                    });
        }
    }



    // [START auth_fui_result]
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                // ...
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
            }
        }
    }
    // [END auth_fui_result]


}