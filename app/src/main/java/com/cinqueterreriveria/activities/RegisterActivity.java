package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.apis.TransparentDialog;
import com.cinqueterreriveria.common.PrefStore;
import com.cinqueterreriveria.models.RegisterModel;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_register_login;
    TextInputEditText login_email, login_password;
    LinearLayout ll_register;
    TransparentDialog dialog = new TransparentDialog();
    PrefStore prefStore;
    Context context=this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        initUis();
    }

    private void initUis() {
        prefStore = new PrefStore(this);
        tv_register_login = findViewById(R.id.tv_register_login);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);
        ll_register = findViewById(R.id.ll_register);

        tv_register_login.setOnClickListener(this);
        ll_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_register_login:
                startActivity(new Intent(this, LoginActivity.class));
                break;
            case R.id.ll_register:
                validation();
                break;
        }
    }

    private void validation() {
        if (!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()) {
            Toast.makeText(this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
        } else if (login_password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();

        } else {
            registerApi();
        }
    }

    void registerApi() {
        Call<RegisterModel> call = Rest.getRetrofit().register(ApiConstents.SECRET_KEY,
                login_email.getText().toString(), login_password.getText().toString());

        dialog.progressDialog(context);

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.isSuccessful()) {

                    dialog.stopProgressDialog();

                   if (response.body().getSuccess().equals("true")) {
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG",response.body().getMessage());
                        prefStore.setBoolean("login_status", true);
                        prefStore.saveString("user_id",response.body().getUserId());
                        startActivity(new Intent(RegisterActivity.this, DashboardActivity.class));

                    } else if (response.body().getSuccess().equals("false")){
                        Log.d("TAG",response.body().getMessage());
                        Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                dialog.stopProgressDialog();
                Log.d("TAG",t.getMessage());
                Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }


}
