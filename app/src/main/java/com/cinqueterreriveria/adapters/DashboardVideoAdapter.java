package com.cinqueterreriveria.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.PlayVideoActivity;
import com.cinqueterreriveria.models.DashboardModel;

import java.util.List;

public class DashboardVideoAdapter extends RecyclerView.Adapter<DashboardVideoAdapter.MyViewHolder> {

    Context context;
    List<DashboardModel.Video> videosList;
    AlertDialog dialog;

    public DashboardVideoAdapter(Context context, List<DashboardModel.Video> videosList) {
        this.context = context;
        this.videosList = videosList;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_dashboard_videos, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        RequestOptions simpleOptions = new RequestOptions()
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
        Glide.with(context).load(videosList.get(position).getImage()).apply(simpleOptions).placeholder(R.drawable.logo).into(holder.iv_dashboard_video);
        holder.tv_dashboard_video_title.setText(videosList.get(position).getTitle());

        holder.bt_video_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(videosList.get(position).getVideo())));
                context.startActivity(new Intent(context, PlayVideoActivity.class).
                        putExtra("video", videosList.get(position).getVideo()));

                /*final AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                View view2 = LayoutInflater.from(context).inflate(R.layout.popup_video_view, null);
                ImageView iv_canecl_video = view2.findViewById(R.id.iv_canecl_video);
                WebView videoview = view2.findViewById(R.id.videoview);
                builder2.setView(view2);

               *//* String vimeoVideo = "<html><body><iframe width=\"420\" height=\"550\" src=" + videosList.get(position).getVideo() + "frameborder=\"0\" allowfullscreen></iframe></body></html>";
                videoview.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView webView, WebResourceRequest request) {

                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            webView.loadUrl(request.getUrl().toString());
                        }
                        return true;
                    }
                });
                WebSettings webSettings = videoview.getSettings();
                webSettings.setJavaScriptEnabled(true);
                videoview.loadData(vimeoVideo, "text/html", "utf-8");*//*


                // how plugin is enabled change in API 8
                if (Build.VERSION.SDK_INT < 8) {
                    //videoview.getSettings().setPluginsEnabled(true);
                } else {
                    videoview.getSettings().setPluginState(WebSettings.PluginState.ON);
                }
                videoview.loadUrl(videosList.get(position).getVideo());
               *//* MediaController videoMediaController = new MediaController(context);
                videoview.setVideoPath(videosList.get(position).getVideo());
                videoMediaController.setMediaPlayer(videoview);
                videoview.setMediaController(videoMediaController);
                videoview.requestFocus();
                videoview.start();*//*

                iv_canecl_video.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });
                dialog = builder2.create();
                dialog.setCancelable(true);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();
            }*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_dashboard_video;
        Button bt_video_detail;
        TextView tv_dashboard_video_title;

        public MyViewHolder(View view) {
            super(view);
            iv_dashboard_video = view.findViewById(R.id.iv_dashboard_video);
            tv_dashboard_video_title = view.findViewById(R.id.tv_dashboard_video_title);
            bt_video_detail = view.findViewById(R.id.bt_video_detail);
        }
    }
}


