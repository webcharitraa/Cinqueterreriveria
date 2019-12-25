package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.cinqueterreriveria.R;

public class PaymentMethodActivity extends AppCompatActivity {
    TextView tv_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_method);

        initUis();
    }

    private void initUis()
    {
        tv_back=findViewById(R.id.tv_back);
        tv_back.setText("Payment Method");
    }
}
