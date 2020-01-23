package com.example.monitoringsolars;

import android.app.ProgressDialog;
import android.graphics.Color;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.monitoringsolars.server.Config_URL;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.DefaultValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Graph extends AppCompatActivity {

//    ProgressDialog progressDialog;
//    String a, b, c;
//    PieChart pieChart;
//    BarChart barChart;
//    LineChart lineChart;
//    SwipeRefreshLayout swipeRefreshLayout;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_graph);
//
//        pieChart = (PieChart) findViewById(R.id.barchart);
////         pieChart.setUsePercentValues(true);
//        barChart = (BarChart) findViewById(R.id.chart);
//        lineChart = (LineChart) findViewById(R.id.line);
//        RewardApi();
//    }
//
//    public void RewardApi() {
//        progressDialog = new ProgressDialog(this);
//        progressDialog.setMessage("Please wait...");
//        progressDialog.setCancelable(false);
//        progressDialog.setIndeterminate(true);
//
//        progressDialog.show();
//
//        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://codeplayon.com/service1.asmx/PointSummary", new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                try {
//                    JSONObject jsonObject = new JSONObject(response);
//                    progressDialog.dismiss();
//                    String status = jsonObject.getString("status");
//                    String mgs = jsonObject.getString("msg");
//                    if (status.equals("1")) {
//                        swipeRefreshLayout.setRefreshing(false);
//                        progressDialog.dismiss();
//                        // Toast.makeText(getApplicationContext(),mgs,Toast.LENGTH_LONG).show();
//                        a = jsonObject.getJSONObject("ot").getString("E");
//                        b = jsonObject.getJSONObject("ot").getString("R");
//                        c = jsonObject.getJSONObject("ot").getString("Balance");
//
//                        /*=======for pie chart=========*/
//
//                        ArrayList<Entry> yvalues = new ArrayList<Entry>();
//                        yvalues.add(new Entry(Float.parseFloat(a), 0));
//                        yvalues.add(new Entry(Float.parseFloat(b), 1));
//                        yvalues.add(new Entry(Float.parseFloat(c), 2));
//
//                        PieDataSet dataSet = new PieDataSet(yvalues, "");
//
//                        ArrayList<String> xVals = new ArrayList<String>();
//                        xVals.add("Earned");
//                        xVals.add("Redeemed");
//                        xVals.add("Balance");
//
//                        PieData data = new PieData(xVals, dataSet);
//                        data.setValueFormatter(new DefaultValueFormatter(0));
//                        pieChart.setData(data);
//                        //pieChart.setDescription("This is Pie Chart");
//
//                        pieChart.setDrawHoleEnabled(true);
//                        pieChart.setTransparentCircleRadius(25f);
//                        pieChart.setHoleRadius(25f);
//
//                        dataSet.setColors(ColorTemplate.LIBERTY_COLORS);
//                        data.setValueTextSize(8f);
//                        data.setValueTextColor(Color.DKGRAY);
//                        pieChart.setOnChartValueSelectedListener((OnChartValueSelectedListener) Graph.this);
//                        pieChart.animateXY(1400, 1400);
//
//                        /*========for barchart========*/
//
//                        ArrayList<BarEntry> entries = new ArrayList<>();
//                        entries.add(new BarEntry(Float.parseFloat(a), 0));
//                        entries.add(new BarEntry(Float.parseFloat(b), 1));
//                        entries.add(new BarEntry(Float.parseFloat(c), 2));
//
//                        BarDataSet dataset = new BarDataSet(entries, "");
//
//                        ArrayList<String> labels = new ArrayList<String>();
//                        labels.add("Earned");
//                        labels.add("Redeemed");
//                        labels.add("Balance");
//
//                        BarData bardata = new BarData(labels, dataset);
//                        dataset.setColors(ColorTemplate.JOYFUL_COLORS);
//                        barChart.setData(bardata);
//                        barChart.animateY(5000);
//                        barChart.animateX(3000);
//
//                        /*=======for line chart==========*/
//
////                        ArrayList<Entry> entries1 = new ArrayList<>();
////                        entries1.add(new Entry(Float.parseFloat(a), 0));
////                        entries1.add(new Entry(Float.parseFloat(b), 1));
////                        entries1.add(new Entry(Float.parseFloat(c), 2));
////                        entries1.add(new Entry(0, 3));
////
////                        LineDataSet dataset1 = new LineDataSet(entries1, "");
////
////                        ArrayList<String> labels1 = new ArrayList<String>();
////                        labels1.add("Earned");
////                        labels1.add("Redeemed");
////                        labels1.add("Balance");
////                        labels1.add("Others");
////
////                        LineData data1 = new LineData(labels1, dataset1);
////                        dataset1.setColors(ColorTemplate.COLORFUL_COLORS);
////                        lineChart.setData(data1);
////                        lineChart.animateY(5000);
////                        lineChart.animateX(3000);
////                        /*=====for cubic form========*/
////                        dataset1.setDrawCubic(true);
////                        /*========Fill the color below the line=========*/
////                        dataset1.setDrawFilled(true);
////                          lineChart.setDescription(&quot;Description&quot;);
//
//                    } else {
//                        // swipeRefreshLayout.setRefreshing(false);
//                        progressDialog.dismiss();
//                        Toast.makeText(getApplicationContext(), mgs, Toast.LENGTH_LONG).show();
//                    }
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        },
//                new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        //swipeRefreshLayout.setRefreshing(false);
//                        progressDialog.dismiss();
//                        Toast.makeText(Graph.this, "Internet Connection Lost,Please Try Again", Toast.LENGTH_LONG).show();
//                    }
//                }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("UserId", "2");
//                return map;
//            }
//        };
//        RequestQueue requestQueue = Volley.newRequestQueue(this);
//        requestQueue.add(stringRequest);
//    }
//
//    @Override
//    public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
//
//    }
//
//    @Override
//    public void onNothingSelected() {

//    }
}