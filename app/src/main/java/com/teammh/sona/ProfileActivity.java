package com.teammh.sona;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.teammh.sona.model.User;
import com.teammh.sona.view.MainActivity;

public class ProfileActivity extends AppCompatActivity {

    private Button logOut;
    private Button homeButton;
    private Button profileButton;
    private Button infoButton;
    private TextView userTextView;
    private ImageView profileImage;
    private ImageView graphImage;

    private User currUserInfo;

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

        Intent intent = getIntent();
        if (intent != null) {
            currUserInfo = (User)intent.getSerializableExtra(MainActivity.USER_CODE);
            userTextView.setText(currUserInfo.getUsername());
            if (!currUserInfo.getPicURL().isEmpty()) {
                Picasso.get().load(currUserInfo.getPicURL()).into(profileImage);
            } else {
                profileImage.setImageDrawable(getDrawable(R.drawable.sona_transparent_big));
            }
        }

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProfileActivity.this.finish();
            }
        });
    }
}
