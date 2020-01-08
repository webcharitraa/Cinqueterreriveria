package com.cinqueterreriveria.apis;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.ColorFilter;

import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.Window;

import androidx.core.content.ContextCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.cinqueterreriveria.R;

public class TransparentDialog {
    public static Dialog dialog;


    @SuppressLint("RestrictedApi")
    public static void progressDialog(Context context) {

        dialog = new Dialog(context);
        View view = View.inflate(context, R.layout.progress_dialog, null);
        LottieAnimationView lav_actionBar=view.findViewById(R.id.lav_actionBar);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(view);

        int yourColor = ContextCompat.getColor(context,R.color.colorPrimary);

        SimpleColorFilter filter = new SimpleColorFilter(yourColor);
        KeyPath keyPath = new KeyPath("**");
        LottieValueCallback<ColorFilter> callback = new LottieValueCallback<ColorFilter>(filter);
        lav_actionBar.addValueCallback(keyPath, LottieProperty.COLOR_FILTER, callback);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setCancelable(false);
        dialog.show();

    }



    public static void stopProgressDialog() {
        if (dialog != null && dialog.isShowing()) {
            try {
                dialog.dismiss();
            } catch (Exception e) {
            }
        }
    }
}
