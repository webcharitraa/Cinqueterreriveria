package com.cinqueterreriveria.fragments;


import android.app.AlertDialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.cinqueterreriveria.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class YourInfoFragment extends Fragment implements View.OnClickListener {

    AlertDialog dialog1, dialog;
    Button bt_edit_card;
    Spinner state_spinner,country_spinner;
    String[] states = {"MONTEROSSO", "MANAROLA", "PORTOVENERE", "VERNAZZA", "LA SPEZIA"};
    String[] country = {"India", "Canada"};

    public YourInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_your_info, container, false);
        state_spinner = view.findViewById(R.id.state_spinner);
        country_spinner = view.findViewById(R.id.country_spinner);
        bt_edit_card = view.findViewById(R.id.bt_edit_card);
        ArrayAdapter<String> a = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, states);
        ArrayAdapter<String> a1 = new ArrayAdapter<String>(getActivity(), R.layout.item_spinner, country);
        state_spinner.setPrompt("Select");
        //search_place_spinner.setPopupBackgroundDrawable(getResources().getDrawable(R.drawable.solid_orange_rectangle));
        state_spinner.setAdapter(a);
        country_spinner.setAdapter(a1);

        bt_edit_card.setOnClickListener(this);
        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_edit_card:
                final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_editcard, null);
                ImageView iv_cancel_card = view2.findViewById(R.id.iv_cancel_card);
                TextView tv_add_new = view2.findViewById(R.id.tv_add_new);
                builder2.setView(view2);

                iv_cancel_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                    }
                });

                tv_add_new.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog1.dismiss();
                        final AlertDialog.Builder builder2 = new AlertDialog.Builder(getActivity());
                        View view2 = LayoutInflater.from(getActivity()).inflate(R.layout.popup_add_card, null);
                        ImageView iv_cancel_car=view2.findViewById(R.id.iv_cancel_car);
                        builder2.setView(view2);


                        iv_cancel_car.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                        dialog = builder2.create();
                        dialog.setCancelable(true);
                        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                        dialog.show();
                    }
                });
                dialog1 = builder2.create();
                dialog1.setCancelable(true);
                dialog1.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog1.show();

                break;
        }
    }
}
