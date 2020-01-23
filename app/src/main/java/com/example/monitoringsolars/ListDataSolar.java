package com.example.monitoringsolars;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Html;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.monitoringsolars.adapter.AdapterSolars;
import com.example.monitoringsolars.data.DataSolars;
import com.example.monitoringsolars.server.AppController;
import com.example.monitoringsolars.server.Config_URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ListDataSolar extends AppCompatActivity {

    ListView list;

    ArrayList<DataSolars> newsList = new ArrayList<DataSolars>();
    AdapterSolars adapter;

    int socketTimeout = 30000;
    RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

    ProgressDialog pDialog;

    @BindView(R.id.cariSuhu)
    EditText edtCari;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data_solar);

        getSupportActionBar().hide();//hide action bar
        ButterKnife.bind(this);//buat button backnya

        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);
        list = (ListView) findViewById(R.id.array_list);
        newsList.clear();

        adapter = new AdapterSolars(ListDataSolar.this, newsList);
        list.setAdapter(adapter);
        list.setEmptyView(findViewById(R.id.textNodata));

        getDataSuhu();
        searchData();
    }

    // Fungsi get JSON Data
    private void getDataSuhu() {

        pDialog.setMessage("Loading.....");
        showDialog();

        String tag_json_obj = "json_obj_req";
        StringRequest strReq = new StringRequest(Request.Method.GET,
                Config_URL.dataHistori,
                new Response.Listener<String>() {

                    @Override
                    public void onResponse(String response) {
                        Log.e("Response: ", response.toString());
                        hideDialog();

                        try {
                            JSONObject jObj = new JSONObject(response);
                            boolean status = jObj.getBoolean("success");

                            if(status == true){

                                String getObject = jObj.getString("message");
                                JSONArray jsonArray = new JSONArray(getObject);

                                for (int i = 0; i < jsonArray.length(); i++) {
                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);

                                    final DataSolars news = new DataSolars();
                                    news.setSuhu(jsonObject.getString("suhu"));
                                    news.setKelembaban(jsonObject.getString("kelembaban"));
                                    news.setTanggal(jsonObject.getString("tanggal"));
                                    newsList.add(news);
                                }
                            }

                        } catch (JSONException e) {
                            // JSON error
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                }, new Response.ErrorListener(){

            @Override
            public void onErrorResponse(VolleyError error){
                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
                error.printStackTrace();
                hideDialog();
            }
        });

        AppController.getInstance().addToRequestQueue(strReq, tag_json_obj);
    }
    //search data
    public void searchData(){
        edtCari.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {}

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            public void onTextChanged(CharSequence query, int start, int before, int count) {

                query = query.toString().toLowerCase();

                final List<DataSolars> filteredList = new ArrayList<DataSolars>();

                for (int i = 0; i < newsList.size(); i++) {

                    final String text = newsList.get(i).getTanggal().toLowerCase();
                    if (text.contains(query)) {
                        filteredList.add(newsList.get(i));
                    }
                }

                adapter = new AdapterSolars(ListDataSolar.this, filteredList);
                list.setAdapter(adapter);
            }
        });
    }
    private void showDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    private void hideDialog() {
        if (pDialog.isShowing())
            pDialog.dismiss();
    }

    @Override
    public void onBackPressed() { //button back
        Intent a = new Intent(ListDataSolar.this, Menu.class);
        startActivity(a);
        finish();
    }
}