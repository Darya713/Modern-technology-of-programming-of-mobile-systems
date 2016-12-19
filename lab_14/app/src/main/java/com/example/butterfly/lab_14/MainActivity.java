package com.example.butterfly.lab_14;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private String TAG = MainActivity.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;

    private static String url = "http://www.nbrb.by/API/ExRates/Rates?Periodicity=0";

    ArrayList<HashMap<String, String>> currencyList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        currencyList = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetContacts().execute();
    }

    private class GetContacts extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Please wait...");
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            String jsonStr = sh.makeServiceCall(url);
            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null) {
                try {
                    JSONArray currencies = new JSONArray(jsonStr);
                    for (int i = 0; i < currencies.length(); i++) {
                        JSONObject jsonObj = currencies.getJSONObject(i);
                        String Cur_Abbreviation = jsonObj.getString("Cur_Abbreviation");
                        String Cur_Name = jsonObj.getString("Cur_Name");
                        String Cur_OfficialRate = jsonObj.getString("Cur_OfficialRate");
                        HashMap<String, String> currency = new HashMap<>();
                        currency.put("Cur_Abbreviation", Cur_Abbreviation);
                        currency.put("Cur_Name", Cur_Name);
                        currency.put("Cur_OfficialRate", Cur_OfficialRate);
                        currencyList.add(currency);
                    }
                } catch (final JSONException e) {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();
            ListAdapter adapter = new SimpleAdapter(
                    MainActivity.this, currencyList,
                    R.layout.list_item, new String[]{"Cur_Abbreviation", "Cur_Name",
                    "Cur_OfficialRate"}, new int[]{R.id.abbreviation,
                    R.id.name, R.id.officialRate});
            lv.setAdapter(adapter);
        }
    }
}
