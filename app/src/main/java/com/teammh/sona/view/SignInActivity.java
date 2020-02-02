package com.teammh.sona.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.teammh.sona.R;

import java.util.Arrays;
import java.util.List;

public class SignInActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 123;
    public static final String TAG = SignInActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser currUser;

    private Button signOut;
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(this,
                    "Firebase connection success",
                    Toast.LENGTH_LONG).show();

        createSignInIntent();

        signOut.setEnabled(false);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });

        /*
        //create variable to interact with database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        // Write a message to the database
        myRef.setValue("Hello, World!");

        // add listener to variable to read from database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
         */
    /*}

    @Override
    public void onStart() {
        super.onStart();
        if (currUser != null) {
            currUser = mAuth.getCurrentUser();
            Toast.makeText(SignInActivity.this,
                    "User Name: " + currUser.getDisplayName(),
                    Toast.LENGTH_LONG);
        }
    }

    public void updateUI() {
        String username = "";
        if (currUser != null) {
            username = currUser.getDisplayName();
        }
        Toast.makeText(SignInActivity.this,
                "User Name: " + username,
                Toast.LENGTH_LONG);
    }

    public void createSignInIntent() {
        // Choose authentication providers
        List<AuthUI.IdpConfig> providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.GoogleBuilder().build());

        // Create and launch sign-in intent
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(providers)
                        .build(),
                RC_SIGN_IN);
    }

    public void signOut() {
        // [START auth_fui_signout]
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                    }
                });
        // [END auth_fui_signout]
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            if (resultCode == RESULT_OK) {
                // Successfully signed in
                currUser = FirebaseAuth.getInstance().getCurrentUser();
                updateUI();
                signOut.setEnabled(true);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Log.d(TAG, "Sign-in failed");
            }
        }
    }*/
}
