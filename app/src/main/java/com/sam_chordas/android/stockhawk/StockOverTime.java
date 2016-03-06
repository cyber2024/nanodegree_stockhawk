package com.sam_chordas.android.stockhawk;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class StockOverTime extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock_over_time);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView tvStockCode = (TextView)findViewById(R.id.tvStockCode);
        TextView tvValue = (TextView)findViewById(R.id.tvValue);
        TextView tvChange = (TextView)findViewById(R.id.tvChange);
        String stockCode = getIntent().getExtras().getString("stockcode");

        tvStockCode.setText(stockCode);
    }

}
