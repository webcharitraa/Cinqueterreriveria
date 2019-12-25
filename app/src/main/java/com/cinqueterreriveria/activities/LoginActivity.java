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

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Context context = this;
    TextView tv_login_register, tv_forgot_password;
    TextInputEditText login_email, login_password;
    LinearLayout login;
    PrefStore prefStore;
    TransparentDialog dialog = new TransparentDialog();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_login);

        initUis();
    }

    private void initUis() {
        prefStore=new PrefStore(context);
        tv_login_register = findViewById(R.id.tv_login_register);
        login = findViewById(R.id.login);
        tv_forgot_password = findViewById(R.id.tv_forgot_password);
        login_email = findViewById(R.id.login_email);
        login_password = findViewById(R.id.login_password);

        tv_login_register.setOnClickListener(this);
        login.setOnClickListener(this);
        tv_forgot_password.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_login_register:
                startActivity(new Intent(this, RegisterActivity.class));
                break;
            case R.id.login:
                validation();
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(this, ForgetPasswordActivity.class));
                break;
        }
    }

    private void validation() {
        if (!Patterns.EMAIL_ADDRESS.matcher(login_email.getText().toString()).matches()) {
            Toast.makeText(this, "Please Enter Valid Email Address", Toast.LENGTH_SHORT).show();
        } else if (login_password.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please enter password", Toast.LENGTH_SHORT).show();

        } else {
            loginApi();
        }
    }


    void loginApi() {
        Call<RegisterModel> call = Rest.getRetrofit().login(ApiConstents.SECRET_KEY,
                login_email.getText().toString(), login_password.getText().toString());

        dialog.progressDialog(context);

        call.enqueue(new Callback<RegisterModel>() {
            @Override
            public void onResponse(Call<RegisterModel> call, Response<RegisterModel> response) {
                if (response.isSuccessful()) {

                    dialog.stopProgressDialog();

                    if (response.body().getSuccess().equals("true")) {
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("TAG", response.body().getMessage());
                        prefStore.setBoolean("login_status", true);
                        prefStore.saveString("user_id", response.body().getUserId());
                        startActivity(new Intent(context, DashboardActivity.class));


                    } else if (response.body().getSuccess().equals("false")) {
                        Log.d("TAG", response.body().getMessage());
                        Toast.makeText(context, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<RegisterModel> call, Throwable t) {
                dialog.stopProgressDialog();
                Log.d("TAG", t.getMessage());
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}
