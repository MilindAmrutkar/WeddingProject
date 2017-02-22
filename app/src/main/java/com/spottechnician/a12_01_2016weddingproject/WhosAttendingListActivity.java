package com.spottechnician.a12_01_2016weddingproject;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class WhosAttendingListActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "http://UmeshWedsShubhangi.spottechnician.com/Select.asmx/GetJSON";

    //private static String url = "http://api.androidhive.info/contacts/";
    //http://api.androidhive.info/contacts/

    ArrayList<HashMap<String, String>> contactList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_whos_attending_list);

        contactList = new ArrayList<>();

        lv = (ListView) findViewById(R.id.whosAttendingList);

        new GetContacts().execute();
    }


    //Async Task class to get json by making HTTP Call
    private class GetContacts extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            super.onPreExecute();

            pDialog = new ProgressDialog(WhosAttendingListActivity.this);
            pDialog.setMessage("Please Wait...");
            pDialog.show();
        }

        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();

            //Making a request to URL and getting response
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray contacts = jsonObj.getJSONArray("memberlist");


                    /*
                    "memberlist":[{"id":1,"firstname":"Mohit",
                    "lastname":"Jadhav","phoneno":"8987765654","attendstatus":"yes",
                    "peoplecoming":4,"relationtype":"Friend","message":"Congrats"}
                     */

                    for (int i = 0; i < contacts.length(); i++) {
                        JSONObject c = contacts.getJSONObject(i);

                        String id = c.getString("id");
                        String firstname = c.getString("firstname");
                        String lastname = c.getString("lastname");
                        String relationtype = c.getString("relationtype");


                        //Phone node is JSON Object
                       /* JSONObject phone = c.getJSONObject("phone");
                        String mobile = phone.getString("mobile");
                        String home = phone.getString("home");
                        String office = phone.getString("office");*/


                        //temporary hash map for single contact
                        HashMap<String, String> contact = new HashMap<>();

                        contact.put("id", id);
                        contact.put("firstname", firstname);
                        contact.put("lastname", lastname);
                        contact.put("relationtype", relationtype);

                        //adding contact to contact list
                        contactList.add(contact);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error please inform Milind Amrutkar about it 9029700369 " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Switch on your internet connection",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }


            return null;
        }

        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            if (pDialog.isShowing())
                pDialog.dismiss();

            ListAdapter adapter = new SimpleAdapter(
                    WhosAttendingListActivity.this, contactList,
                    R.layout.list_item, new String[]{"firstname", "lastname",
                    "relationtype"}, new int[]{R.id.txtViewListItemFirstName, R.id.txtViewListItemLastName, R.id.txtViewListItemRelationType});

            lv.setAdapter(adapter);
        }


    }
}
