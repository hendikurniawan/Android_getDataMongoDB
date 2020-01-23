package com.example.monitoringsolars;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class Menu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        getSupportActionBar().hide();
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btnHistory)
    void dataHistori() {
        Intent a = new Intent(Menu.this, ListDataSolar.class);
        startActivity(a);
        finish();
    }


    @OnClick(R.id.btnTemHum)
    void btnTemHum() {
        Intent b = new Intent(Menu.this, MonitorData.class);
        startActivity(b);
        finish();
    }

    @OnClick(R.id.btnChart)
    void btnChart() {
        Intent b = new Intent(Menu.this, MainActivity.class);
        startActivity(b);
        finish();
    }
}
