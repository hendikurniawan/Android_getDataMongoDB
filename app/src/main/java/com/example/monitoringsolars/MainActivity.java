package com.example.monitoringsolars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.monitoringsolars.data.DataSolars;
import com.example.monitoringsolars.server.Config_URL;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;


public class MainActivity extends AppCompatActivity {

    TextView suhus, kelembabans, tanggals;
    private BarChart mBarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();
        ButterKnife.bind(this);

//        suhus = (TextView) findViewById(R.id.txtSuhu);
//        kelembabans = (TextView) findViewById(R.id.txtKelembaban);
//        tanggals = (TextView) findViewById(R.id.txtTanggal);
//
//        getData();


//    }
//
//    private void getData() {
//
//        // Instantiate the RequestQueue.
//        RequestQueue queue = Volley.newRequestQueue(this);
//
//        final JSONObject jsonBody = new JSONObject();
//        final String requestBody = jsonBody.toString();
//
//        // Request a string response from the provided URL.
//        StringRequest stringRequest = new StringRequest(Request.Method.GET, Config_URL.dataRealtime,
//                new Response.Listener<String>() {
//
//                    @Override
//                    public void onResponse(String response) {
////                        hideDialog();
//                        try {
//                            JSONObject jObj = new JSONObject(response);
//                            boolean status = jObj.getBoolean("success");
//
//                            if (status == true) {
//
//                                String getObject = jObj.getString("message");
//                                JSONArray jsonArray = new JSONArray(getObject);
//
//                                for (int i = 0; i < jsonArray.length(); i++) {
//                                    final JSONObject jsonObject = jsonArray.getJSONObject(i);
//
//                                    String dataSuhu = jsonObject.getString("suhu");
//                                    String dataKelem = jsonObject.getString("kelembaban");
////                                String dataTgl = jsonObject.getString("tanggal");
//
//                                    JSONObject suhu = new JSONObject(dataSuhu);
//                                    JSONObject kelem = new JSONObject(dataKelem);
////                                JSONObject tgl = new JSONObject(dataTgl);
//
//                                    suhus.setText(suhu.getString("data"));
//                                    kelembabans.setText(kelem.getString("data"));
//                                    tanggals.setText(jsonObject.getString("tanggal"));
//
//                                    mBarChart = findViewById(R.id.chart);
//
//                                    float groupSpace = 0.08f;
//                                    float barSpace = 0.02f;
//                                    float barWidth = 0.45f;
//                                    float tahunAwal = 2019;
//
//                                    // Data-data yang akan ditampilkan di Chart
//                                    List<BarEntry> suhu = new JSONArray<BarEntry>();
//                                    suhu.add(new BarEntry(2019, 30));
////        dataPemasukan.add(new BarEntry(2017, 1430000));
//
//                                    List<BarEntry> kelem = new ArrayList<BarEntry>();
//                                    kelem.add(new BarEntry(2019, 90));
////        dataPengeluaran.add(new BarEntry(2017, 430000));
//
//                                    // Pengaturan atribut bar, seperti warna dan lain-lain
//                                    BarDataSet dataSet1 = new BarDataSet(dataSuhu, "Suhu");
//                                    dataSet1.setColor(ColorTemplate.JOYFUL_COLORS[0]);
//
//                                    BarDataSet dataSet2 = new BarDataSet(dataKelembaban, "Kelembaban");
//                                    dataSet2.setColor(ColorTemplate.JOYFUL_COLORS[1]);
//
//                                    // Membuat Bar data yang akan di set ke Chart
//                                    BarData barData = new BarData(dataSet1, dataSet2);
//
//                                    // Pengaturan sumbu X
//                                    XAxis xAxis = mBarChart.getXAxis();
//                                    xAxis.setPosition(XAxis.XAxisPosition.BOTTOM.BOTTOM);
//                                    xAxis.setCenterAxisLabels(true);
//
//                                    // Agar ketika di zoom tidak menjadi pecahan
//                                    xAxis.setGranularity(1f);
//
//                                    // Diubah menjadi integer, kemudian dijadikan String
//                                    // Ini berfungsi untuk menghilankan koma, dan tanda ribuah pada tahun
//                                    xAxis.setValueFormatter(new IAxisValueFormatter() {
//                                        @Override
//                                        public String getFormattedValue(float value, AxisBase axis) {
//                                            return String.valueOf((int) value);
//                                        }
//                                    });
//
//                                    //Menghilangkan sumbu Y yang ada di sebelah kanan
//                                    mBarChart.getAxisRight().setEnabled(false);
//
//                                    // Menghilankan deskripsi pada Chart
//                                    mBarChart.getDescription().setEnabled(false);
//
//                                    // Set data ke Chart
//                                    // Tambahkan invalidate setiap kali mengubah data chart
//                                    mBarChart.setData(barData);
//                                    mBarChart.getBarData().setBarWidth(barWidth);
//                                    mBarChart.getXAxis().setAxisMinimum(tahunAwal);
//                                    mBarChart.getXAxis().setAxisMaximum(tahunAwal + mBarChart.getBarData().getGroupWidth(groupSpace, barSpace) * 1);
//                                    mBarChart.groupBars(tahunAwal, groupSpace, barSpace);
//                                    mBarChart.setDragEnabled(true);
//                                    mBarChart.invalidate();
//                                }
//                            }
//
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError error){
//                Log.e(String.valueOf(getApplication()), "Error : " + error.getMessage());
//                error.printStackTrace();
////                hideDialog();
//            }
//        });
//        // Add the request to the RequestQueue.
//        queue.add(stringRequest);
//
    }
        @Override
        public void onBackPressed () {
            Intent a = new Intent(MainActivity.this, Menu.class);
            startActivity(a);
            finish();
        }
    }
