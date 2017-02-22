package com.spottechnician.a12_01_2016weddingproject;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class AttendingRegistration extends AppCompatActivity implements AdapterView.OnItemSelectedListener,View.OnFocusChangeListener {

    private EditText edtTxtFirstName, edtTxtLastName, edtTxtPhone, edtTxtMessage;
    private Spinner spnrRelation, spnrMemberCount;
    private RadioButton rdBtnSelectedValue;
    private RadioGroup rdBtnGrpStatus;


    //Form Variables
    String count, relationType, firstName, lastName, phoneNumber, message, attendStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attending_registration);


        edtTxtFirstName = (EditText) findViewById(R.id.edtTxtRegisterFirstName);
        edtTxtLastName = (EditText) findViewById(R.id.edtTxtRegisterLastName);
        edtTxtPhone = (EditText) findViewById(R.id.edtTxtRegisterPhoneNumber);
        edtTxtMessage = (EditText) findViewById(R.id.edtTxtRegisterMessage);

        clearFields();
        spnrMemberCount = (Spinner) findViewById(R.id.spnrRegisterPeopleCount);
        spnrRelation = (Spinner) findViewById(R.id.spnrRegisterRelation);

        spnrMemberCount.setOnItemSelectedListener(this);
        spnrRelation.setOnItemSelectedListener(this);

        List<Integer> memberCount = new ArrayList<>();
        memberCount.add(0);
        memberCount.add(1);
        memberCount.add(2);
        memberCount.add(3);
        memberCount.add(4);
        memberCount.add(5);
        memberCount.add(6);
        memberCount.add(7);
        memberCount.add(8);
        memberCount.add(9);
        memberCount.add(10);

        List<String> relationList = new ArrayList<>();
        relationList.add("Family");
        relationList.add("Friend");
        relationList.add("Parent's Friend");
        relationList.add("Brother's Friend");
        relationList.add("Cousin's Friend");

        ArrayAdapter<Integer> spnrAdapterCount = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, memberCount);
        spnrAdapterCount.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrMemberCount.setAdapter(spnrAdapterCount);

        ArrayAdapter<String> spnrAdapterRelation = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, relationList);
        spnrAdapterRelation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spnrRelation.setAdapter(spnrAdapterRelation);

    }


    public void addMe(View view) {
        message = edtTxtMessage.getText().toString().trim();
        boolean checkMobile = isValidMobile(edtTxtPhone.getText().toString());
        if(edtTxtFirstName.getText().length()==0)
        {
            edtTxtFirstName.setOnFocusChangeListener(this);
            Toast.makeText(getApplicationContext(), "Please provide your First Name", Toast.LENGTH_SHORT).show();
        }
        else if(edtTxtLastName.getText().length()==0){
            edtTxtLastName.setOnFocusChangeListener(this);
            Toast.makeText(getApplicationContext(), "Please provide your Last Name", Toast.LENGTH_SHORT).show();
        }
        else if(edtTxtPhone.getText().length()==0 && !checkMobile) {
            edtTxtPhone.setOnFocusChangeListener(this);
            Toast.makeText(getApplicationContext(), "Please provide your Phone Number", Toast.LENGTH_SHORT).show();
        }
        else if(edtTxtMessage.getText().length()==0) {
            message = "";
        }
        else
        {
            // Check if no view has focus:
            view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
            String id = null;
            firstName = edtTxtFirstName.getText().toString().trim();
            lastName = edtTxtLastName.getText().toString().trim();
            phoneNumber = edtTxtPhone.getText().toString().trim();



            rdBtnGrpStatus = (RadioGroup) findViewById(R.id.rdGroupRegisterStatus);
            int selectedRadioButton = rdBtnGrpStatus.getCheckedRadioButtonId();

            rdBtnSelectedValue = (RadioButton) findViewById(selectedRadioButton);
            attendStatus = rdBtnSelectedValue.getText().toString();

            if (attendStatus.equalsIgnoreCase("I don't want to miss it")) {
                attendStatus = "yes";
            } else if (attendStatus.equalsIgnoreCase("I may miss it")) {
                attendStatus = "no";
            }

            new SendValues(this, firstName, lastName, phoneNumber, count, relationType, message, attendStatus).execute(id, firstName, lastName, phoneNumber, count, relationType, message, attendStatus);

            /*Toast.makeText(this,"Selected: "+ count + " " + relationType + " " +firstName+" "+lastName+" "+phoneNumber
                +" "+message+ " "+attendStatus, Toast.LENGTH_SHORT).show();*/
        /*Intent i = new Intent(this, AddedActivity.class);
        startActivity(i);*/
        }
    }

    public void checkMembersList(View view) {
        Intent i = new Intent(this, WhosAttendingListActivity.class);
        startActivity(i);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Spinner spinner = (Spinner) parent;
        if (spinner.getId() == R.id.spnrRegisterPeopleCount) {

            count = parent.getItemAtPosition(position).toString();
        } else if (spinner.getId() == R.id.spnrRegisterRelation) {

            relationType = parent.getItemAtPosition(position).toString();
        } else {
            count = "1"; //default value of member count is set to 1
            relationType = "Family"; //default value of relation is Family
        }


    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            if (edtTxtFirstName.getText().toString().trim().equals("")) {
                edtTxtFirstName.setError("Please enter First Name");
            }
            if(edtTxtLastName.getText().toString().trim().equals("")) {
                edtTxtLastName.setError("Please enter Last Name");
            }
            if(edtTxtPhone.getText().toString().trim().equals("")) {
                edtTxtPhone.setError("Please enter phone number");
            }

        } else {
            edtTxtFirstName.setError(null);
            edtTxtLastName.setError(null);
            edtTxtPhone.setError(null);
        }
    }

    public void clearFields() {
        edtTxtMessage.setText("");
        edtTxtFirstName.setText("");
        edtTxtLastName.setText("");
        edtTxtPhone.setText("");
    }

    private boolean isValidMobile(String phone) {
        return android.util.Patterns.PHONE.matcher(phone).matches();
    }
}
