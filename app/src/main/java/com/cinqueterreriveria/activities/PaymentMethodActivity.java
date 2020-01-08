package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cinqueterreriveria.R;

public class PaymentMethodActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv_back,tv_app_bar_title;
    LinearLayout ll_back;
    View v;
    RelativeLayout rl_next;
    RadioButton rb_wire_transfer,rb_debit_credit;

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
        rl_next=findViewById(R.id.rl_next);
        rb_wire_transfer=findViewById(R.id.rb_wire_transfer);
        v=findViewById(R.id.v);
        rb_debit_credit=findViewById(R.id.rb_debit_credit);
        v.setVisibility(View.VISIBLE);
        tv_app_bar_title.setVisibility(View.VISIBLE);
        tv_app_bar_title.setText("Payment Method");


        rl_next.setOnClickListener(this);
        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.rl_next:
                if (rb_wire_transfer.isChecked())
                {

                    Intent intent=new Intent(this,BankTransferActivity.class).
                            putExtra("flag","wire_tranfer");

                    Bundle bundle1 = getIntent().getExtras();
                    if (bundle1 != null) {
                        intent.putExtras(bundle1);
                    }
                    startActivity(intent);

                }
                else if (rb_debit_credit.isChecked()){
                    Intent intent=new Intent(this,BankTransferActivity.class).
                            putExtra("flag","credit/debit");

                    Bundle bundle1 = getIntent().getExtras();
                    if (bundle1 != null) {
                        intent.putExtras(bundle1);
                    }
                    startActivity(intent);
                }
                else {
                    Toast.makeText(this, "Select Atleast one", Toast.LENGTH_SHORT).show();
                }
            break;
            case R.id.ll_back:
          finish();
            break;
        }
    }
}
