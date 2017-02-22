package com.spottechnician.a12_01_2016weddingproject;

/**
 * Created by MyPC on 17-01-2017.
 */

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

/**
 * Created by Admin on 1/15/2017.
 */

public class SendValues extends AsyncTask<String, String, String> {

    private Context context;
    private String firstName, count, lastName, phoneNumber, message, relationType, attendStatus;
    ProgressDialog progressDialog;

    public SendValues(Context context, String firstName, String lastName, String phoneNumber, String count, String relationType, String message, String attendStatus) {
        this.context = context;
        this.attendStatus = attendStatus;
        this.firstName = firstName;
        this.lastName = lastName;
        this.relationType = relationType;
        this.count = count;
        this.message = message;
        this.phoneNumber = phoneNumber;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Please wait..");
        progressDialog.setMessage("You are being added..");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    protected String doInBackground(String... args) {
        String id = args[0];
        firstName = args[1];
        lastName = args[2];
        phoneNumber = args[3];
        count = args[4];
        relationType = args[5];
        message = args[6];
        attendStatus = args[7];
        Log.d("Testing", "id: " + id + " firstName " + firstName + " lastName " + lastName + " phoneNumber " + phoneNumber +
                " count " + count +" relationType " +
                relationType + " message" + message + " attendStatus " + attendStatus);
        //String link = "http://weddingapp.spottechnician.com/InsertData.asmx?op=insert";
        String link = "http://UmeshWedsShubhangi.spottechnician.com/InsertData.asmx/insert";
        try {
            String data = URLEncoder.encode("fname", "UTF-8") + "=" +
                    URLEncoder.encode(firstName, "UTF-8");
            data += "&" + URLEncoder.encode("lname", "UTF-8") + "=" +
                    URLEncoder.encode(lastName, "UTF-8");
            data += "&" + URLEncoder.encode("pno", "UTF-8") + "=" +
                    URLEncoder.encode(phoneNumber, "UTF-8");
            data += "&" + URLEncoder.encode("astatus", "UTF-8") + "=" +
                    URLEncoder.encode(attendStatus, "UTF-8");
            data += "&" + URLEncoder.encode("pcoming", "UTF-8") + "=" +
                    URLEncoder.encode(count, "UTF-8");
            data += "&" + URLEncoder.encode("rtype", "UTF-8") + "=" +
                    URLEncoder.encode(relationType, "UTF-8");
            data += "&" + URLEncoder.encode("msg", "UTF-8") + "=" +
                    URLEncoder.encode(message, "UTF-8");


            URL url = new URL(link);
            URLConnection conn = url.openConnection();

            conn.setDoOutput(true);
            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

            wr.write(data);
            wr.flush();

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            StringBuilder sb = new StringBuilder();
            String line = null;

            while ((line = reader.readLine()) != null) {
                sb.append(line);
                break;
            }
            return sb.toString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            return new String("Exception: " + e.getMessage());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPreExecute();
        /*progressDialog.setTitle("You've been added successfully");
        progressDialog.setMessage("Check member's list");
        progressDialog.show();*/
        Toast.makeText(context, "You're successfully added to the list.. Please check the Member's list", Toast.LENGTH_LONG).show();

        progressDialog.dismiss();


    }


}

