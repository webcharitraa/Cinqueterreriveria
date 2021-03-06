package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;

public class PaymentTypeActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_back,tv_app_bar_title;
    Button bt_direct_book,bt_preBooking,bt_request_booking;
    LinearLayout ll_back;

    View v;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_payment_type);

        initUis();
    }

    private void initUis()
    {
        tv_back=findViewById(R.id.tv_back);
        bt_direct_book=findViewById(R.id.bt_direct_book);
        bt_preBooking=findViewById(R.id.bt_preBooking);
        bt_request_booking=findViewById(R.id.bt_request_booking);
        tv_app_bar_title=findViewById(R.id.tv_app_bar_title);
        ll_back=findViewById(R.id.ll_back);
        v=findViewById(R.id.v);
        v.setVisibility(View.VISIBLE);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Payment Types");

        bt_direct_book.setOnClickListener(this);
        bt_preBooking.setOnClickListener(this);
        bt_request_booking.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.bt_direct_book:
                Intent intent=new Intent(PaymentTypeActivity.this,PaymentMethodActivity.class);
                Bundle bundle = getIntent().getExtras();
                if (bundle != null) {
                    intent.putExtras(bundle);
                }
                startActivity(intent);
                break;
            case R.id.bt_preBooking:
                Intent intent1=new Intent(PaymentTypeActivity.this,PaymentMethodActivity.class);
                Bundle bundle1 = getIntent().getExtras();
                if (bundle1 != null) {
                    intent1.putExtras(bundle1);
                }
                startActivity(intent1);
                break;
                case R.id.bt_request_booking:
                startActivity(new Intent(PaymentTypeActivity.this,BookingSuccessfulActivity.class));
                break;
            case R.id.ll_back:
                finish();
                break;
        }
    }


}
