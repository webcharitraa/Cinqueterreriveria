package com.cinqueterreriveria.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.SingleBoatTourAdapter;

public class SingleBoatTourActivity extends AppCompatActivity implements View.OnClickListener {
    Context context=this;
    RecyclerView rv_single_boat;
    TextView tv_back;
    LinearLayout ll_back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_boat_tour);

        initUis();
    }

    private void initUis() {
        rv_single_boat = findViewById(R.id.rv_single_boat);
        tv_back = findViewById(R.id.tv_back);
        ll_back = findViewById(R.id.ll_back);
        tv_back.setText("Back");
        rv_single_boat.setLayoutManager(new LinearLayoutManager(context));
        rv_single_boat.setAdapter(new SingleBoatTourAdapter(context));

        ll_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.ll_back:
                finish();
                break;
        }
    }
}
