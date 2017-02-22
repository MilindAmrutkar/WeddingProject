package com.spottechnician.a12_01_2016weddingproject;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class BrideDetailsActivity extends AppCompatActivity {

    private TextView txtBride;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bride_details);
        txtBride = (TextView) findViewById(R.id.activity_bride_details_bride_name);
        Typeface cursive_font = Typeface.createFromAsset(getAssets(), "fonts/Precious.ttf");
        txtBride.setTypeface(cursive_font);

    }
}
