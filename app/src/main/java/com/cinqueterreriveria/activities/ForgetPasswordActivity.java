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
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.cinqueterreriveria.R;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    Button bt_forget_next;
    AlertDialog dialog1 = null;
    LinearLayout ll_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_forget_password);

        initUis();
    }

    private void initUis() {

        bt_forget_next = findViewById(R.id.bt_forget_next);
        ll_back = findViewById(R.id.ll_back);

        bt_forget_next.setOnClickListener(this);
        ll_back.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_forget_next:

                final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(context).inflate(R.layout.popup_confirmation, null);
                RelativeLayout rl_confirm = view2.findViewById(R.id.rl_confirm);


                builder2.setView(view2);


                rl_confirm.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });
                dialog1 = builder2.create();
                dialog1.setCancelable(true);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();
                break;

            case R.id.ll_back:
                finish();
                break;
        }
    }
}
