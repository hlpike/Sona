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
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.teammh.sona.R;
import com.teammh.sona.model.Score;
import com.teammh.sona.model.User;

import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int RC_SIGN_IN = 123;
    public static final String TAG = MainActivity.class.getSimpleName();

    private FirebaseAuth mAuth;
    private FirebaseUser currUser;
    private FirebaseDatabase mDatabase;
    private User currUserInfo;

    private Button signOut;
    private Button increment;
    private int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        Toast.makeText(this,
                "Firebase connection success",
                Toast.LENGTH_LONG).show();

        createSignInIntent();

        //testing variable
        counter = -1;

        signOut = (Button)findViewById(R.id.sign_out);
        signOut.setEnabled(false);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Sign out");
                signOut();
            }
        });
        increment = (Button)findViewById(R.id.increment);
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "Increment");
                if (counter != -1) {
                    counter++;
                }
            }
        });

        //create variable to interact with database
        mDatabase = FirebaseDatabase.getInstance();

        /*
        DatabaseReference myRef = mDatabase.getReference("message");

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
    }

    public void updateUI() {
        String username = "";
        if (currUser != null) {
            username = currUser.getDisplayName();
        }
        Toast.makeText(MainActivity.this,
                "User Name: " + username,
                Toast.LENGTH_LONG).show();
    }

    public void createSignInIntent() {
        Log.d(TAG, "Signing in...");
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
                Log.d(TAG, "Sign in successful. Username: " + currUser.getDisplayName());

                updateUI();

                //update current user info
                currUserInfo = new User(currUser.getDisplayName(), currUser.getEmail());
                if (currUser.getPhotoUrl() != null) {
                    currUserInfo.setPicURL(currUser.getPhotoUrl().toString());
                }
                currUserInfo.addScore(0);

                //add user to database
                DatabaseReference usersRef = mDatabase.getReference().child("users");
                usersRef.child(currUser.getUid()).setValue(currUserInfo)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Write was successful!
                                Log.d(TAG, "Database write successful!");
                                Toast.makeText(MainActivity.this,
                                        "User successfully added",
                                        Toast.LENGTH_LONG).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Write failed
                                Log.d(TAG, "Database write failed.");
                                Toast.makeText(MainActivity.this,
                                        "User was not added",
                                        Toast.LENGTH_LONG).show();
                            }
                        });
                ValueEventListener userListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        /*
                        // Get Post object and use the values to update the UI
                        User userTemp = dataSnapshot.getValue(User.class);
                        if (userTemp.getEmail().contentEquals(currUserInfo.getEmail())) {
                            Log.d(TAG, "Database write successful!");
                            Toast.makeText(MainActivity.this,
                                    "User successfully added",
                                    Toast.LENGTH_LONG).show();
                            currUserInfo = userTemp;
                        }
                         */
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Getting Post failed, log a message
                        Log.w(TAG, "loadPost:onCancelled", databaseError.toException());
                        // [START_EXCLUDE]
                        Toast.makeText(MainActivity.this, "Failed to load post.",
                                Toast.LENGTH_LONG).show();
                        // [END_EXCLUDE]
                    }
                };
                usersRef.addValueEventListener(userListener);

                signOut.setEnabled(true);
            } else {
                // Sign in failed. If response is null the user canceled the
                // sign-in flow using the back button. Otherwise check
                // response.getError().getErrorCode() and handle the error.
                // ...
                Log.d(TAG, "Sign-in failed");
            }
        }
    }
}
