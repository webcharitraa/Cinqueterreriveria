package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.cinqueterreriveria.R;

public class BookingSuccessfulActivity extends AppCompatActivity {
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_booking_successful);

        initUis();
    }

    private void initUis() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                AlertDialog dialog1 = null;
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(context).inflate(R.layout.item_booking_successfully, null);
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(new Intent(BookingSuccessfulActivity.this, DashboardActivity.class));
                    }
                }, 5000);
                builder2.setView(view2);

                dialog1 = builder2.create();
                dialog1.setCancelable(true);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();
            }
        }, 3000);
    }
}
