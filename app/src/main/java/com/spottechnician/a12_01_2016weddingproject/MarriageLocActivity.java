package com.spottechnician.a12_01_2016weddingproject;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MarriageLocActivity extends AppCompatActivity {

    private TextView txtMarriageLocation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_marriage_loc);

        txtMarriageLocation = (TextView) findViewById(R.id.activity_marriage_loc_marriage_heading_txtview);
        Typeface cursive_font = Typeface.createFromAsset(getAssets(), "fonts/Precious.ttf");
        txtMarriageLocation.setTypeface(cursive_font);
    }

    public void startNavigation(View view) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("https://www.google.co.in/maps/place/Durvankur+Lawns/@19.9909438,73.7702532,17z/data=!3m1!4b1!4m5!3m4!1s0x3bddeb0cdf4a7b77:0xa76a13754f57451a!8m2!3d19.9909388!4d73.7724419"));
        startActivity(intent);
    }
}
