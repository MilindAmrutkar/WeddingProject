package com.spottechnician.a12_01_2016weddingproject;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class GroomDetailsActivity extends AppCompatActivity {

    private TextView txtGroom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groom_details);
        txtGroom = (TextView) findViewById(R.id.activity_groom_details_groom_name);
        Typeface cursive_font = Typeface.createFromAsset(getAssets(), "fonts/Precious.ttf");
        txtGroom.setTypeface(cursive_font);
    }
}
