package com.teammh.sona.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import com.teammh.sona.R;

public class HubActivity extends AppCompatActivity {

    private Button feelingButton;
    private Button homeButton;
    private Button profileButton;
    private Button infoButton;
    private ImageView hubImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        feelingButton = (Button)findViewById(R.id.hub_about_day2);
        homeButton = (Button)findViewById(R.id.hub_home2);
        profileButton = (Button)findViewById(R.id.hub_profile2);
        infoButton = (Button)findViewById(R.id.hub_info2);
        hubImage = (ImageView)findViewById(R.id.imageView2);
    }
}
