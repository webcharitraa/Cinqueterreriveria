package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;

public class PaymentMethodActivity extends AppCompatActivity {
    TextView tv_back,tv_app_bar_title;
    LinearLayout ll_back;
    View v;

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
        ll_back=findViewById(R.id.ll_back);
        tv_app_bar_title=findViewById(R.id.tv_app_bar_title);
        v=findViewById(R.id.v);
        v.setVisibility(View.VISIBLE);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Payment Method");

        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
