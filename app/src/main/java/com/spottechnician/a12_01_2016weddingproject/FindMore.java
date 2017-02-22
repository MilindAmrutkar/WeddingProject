package com.spottechnician.a12_01_2016weddingproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class FindMore extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_more);
    }

    public void brideActivity(View view) {
        Intent i = new Intent(this, BrideDetailsActivity.class);
        startActivity(i);
    }

    public void brideGroomActivity(View view) {
        Intent i = new Intent(this, GroomDetailsActivity.class);
        startActivity(i);
    }

    public void attendingActivity(View view) {
        Intent i = new Intent(this, WhosAttendingListActivity.class);
        startActivity(i);
    }

    public void receptionLocActivity(View view) {
        Intent i = new Intent(this, ReceptionLocActivity.class);
        startActivity(i);
    }

    public void marriageLocActivity(View view) {
        Intent i = new Intent(this, MarriageLocActivity.class);
        startActivity(i);
    }

    public void residenceDetailsActivity(View view) {
        Intent i = new Intent(this, ResidenceAddressActivity.class);
        startActivity(i);

    }

    public void viewPatrika(View view) {
        Intent i = new Intent(this, ViewPatrikaActivity.class);
        startActivity(i);

    }

}
