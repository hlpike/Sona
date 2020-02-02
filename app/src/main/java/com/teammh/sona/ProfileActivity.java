package com.teammh.sona;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    private Button logOut;
    private Button homeButton;
    private Button profileButton;
    private Button infoButton;
    private TextView userTextView;
    private ImageView profileImage;
    private ImageView graphImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logOut = (Button)findViewById(R.id.profile_log_out);
        homeButton = (Button)findViewById(R.id.hub_home);
        profileButton = (Button)findViewById(R.id.hub_profile);
        infoButton = (Button)findViewById(R.id.hub_info);
        userTextView = (TextView)findViewById(R.id.profile_user);
        profileImage = (ImageView)findViewById(R.id.imageView);
        graphImage = (ImageView)findViewById(R.id.profile_graph);

    }
}
