package com.abhinav.covid19tracker;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView mlist;
    EditText etCountry;
    ProgressDialog progressDialog;
    ArrayList<AllReportInfo>list=new ArrayList<>();
    Adapter_all_report adapter=null;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressDialog=new ProgressDialog(this);
        progressDialog.setMessage("On Process...Please wait");

        mlist = findViewById(R.id.listView);
        etCountry = findViewById(R.id.et_country);
        toolbar=findViewById(R.id.toolbar);


        if (CheckNetwork.isConnected(MainActivity.this))
        {
            progressDialog.show();
            makeApiRequest();
        }else Toast.makeText(this, "No Internet Connection.", Toast.LENGTH_SHORT).show( );


    }
    public void makeApiRequest()
    {
        JsonArrayRequest req = new JsonArrayRequest(AppUrl.SubMainUrl,
                new Response.Listener<JSONArray>( ) {
                    @Override
                    public void onResponse(JSONArray response) {
                        Log.i("vvv", String.valueOf(response));
                        try {
                            list.clear( );
                            for (int i = 0; i < response.length( ); i++) {
                                JSONObject jsonObject = (JSONObject) response.get(i);
                                String countryName = jsonObject.getString("country");
                                int cases = jsonObject.getInt("cases");
                                int death = jsonObject.getInt("deaths");
                                int recovered = jsonObject.getInt("recovered");
                                AllReportInfo info = new AllReportInfo( );
                                info.setCountry(countryName);
                                info.setDeath(String.valueOf(death));
                                info.setCase(String.valueOf(cases));
                                info.setRecovered(String.valueOf(recovered));
                                list.add(info);
                            }
                            adapter = new Adapter_all_report(list, MainActivity.this);
                            mlist.setAdapter(adapter);
                            progressDialog.dismiss( );
                        } catch (Exception ex) {
                            Log.i("vvdsf",ex.getMessage());
                            progressDialog.dismiss( );
                        }
                    }
                }, new Response.ErrorListener( ) {
            @Override
            public void onErrorResponse(VolleyError response) {
                Toast.makeText(MainActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show( );
            }
        });


        //adding request
        AppController.getInstance( ).addToRequestQueue(req);
    }
}
