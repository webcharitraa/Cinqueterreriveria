package com.cinqueterreriveria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.cinqueterreriveria.activities.DashboardActivity;
import com.cinqueterreriveria.activities.WalkThroughActivity;
import com.cinqueterreriveria.common.PrefStore;

public class Splash extends AppCompatActivity {
    ImageView iv_wave;
    PrefStore prefStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initUis();
    }

    void initUis()
    {
        prefStore=new PrefStore(this);
        iv_wave=findViewById(R.id.iv_wave);
        Glide.with(this).asGif().load(R.raw.splash_gif).into(iv_wave);

        new Handler().postDelayed(new Runnable() {
            // Using handler with postDelayed called runnable run method

            @Override
            public void run() {
                if (prefStore.getBoolean("login_status") == true)
                {
                    Intent i = new Intent(Splash.this, DashboardActivity.class);
                    startActivity(i);
                }
                else {
                    Intent i = new Intent(Splash.this, WalkThroughActivity.class);
                    startActivity(i);
                }
                // close this activity
                finish();
            }
        }, 2000);
    }

}
