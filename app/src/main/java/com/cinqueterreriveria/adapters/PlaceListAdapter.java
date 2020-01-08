package com.cinqueterreriveria.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.cinqueterreriveria.R;
import com.cinqueterreriveria.activities.SinglePlaceDetailActivity;
import com.cinqueterreriveria.models.PlaceListModel;

import java.util.List;

public class PlaceListAdapter extends RecyclerView.Adapter<PlaceListAdapter.MyViewHolder> {

    Context context;
    List<PlaceListModel.LocationProperty> locationProperties;


    public PlaceListAdapter(Context context, List<PlaceListModel.LocationProperty> locationProperties) {
        this.context = context;
        this.locationProperties = locationProperties;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_places_list, parent, false);

        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.cv_place_list.setBackgroundResource(R.drawable.solid_white);
        holder.tv_place_list_title.setText(locationProperties.get(position).getTitle());
        holder.tv_bathrooms.setText("Bathrooms("+locationProperties.get(position).getIcons().getBathrooms()+")");
        holder.tv_guest.setText("Guests("+locationProperties.get(position).getIcons().getGuests()+")");
        holder.tv_rooms.setText("Rooms("+locationProperties.get(position).getIcons().getRooms()+")");
        holder.tv_price.setText(locationProperties.get(position).getPrice());
        holder.viewpager.setAdapter(new ImageSlideAdapter(context, locationProperties.get(position).getGallery(),
                locationProperties.get(position).getStatus(),locationProperties.get(position).getSlug()));
        holder.ratingBar.setRating(Float.parseFloat(locationProperties.get(position).getRating()));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, SinglePlaceDetailActivity.class)
                        .putExtra("rating",locationProperties.get(position).getRating())
                .putExtra("slug",locationProperties.get(position).getSlug()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return locationProperties.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        CardView cv_place_list;
        ViewPager viewpager;
        RatingBar ratingBar;
        TextView tv_place_list_title,tv_price,tv_bathrooms,tv_guest,tv_rooms;
        RelativeLayout rl_left_nav, rl_right_nav;

        public MyViewHolder(View view) {
            super(view);
            cv_place_list = view.findViewById(R.id.cv_place_list);
            viewpager = view.findViewById(R.id.viewpager);
            rl_left_nav = view.findViewById(R.id.rl_left_nav);
            rl_right_nav = view.findViewById(R.id.rl_right_nav);
            tv_place_list_title = view.findViewById(R.id.tv_place_list_title);
            ratingBar = view.findViewById(R.id.ratingBar);
            tv_price = view.findViewById(R.id.tv_price);
            tv_bathrooms = view.findViewById(R.id.tv_bathrooms);
            tv_guest = view.findViewById(R.id.tv_guest);
            tv_rooms = view.findViewById(R.id.tv_rooms);


            // Images left navigation
            rl_left_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tab = viewpager.getCurrentItem();
                    if (tab > 0) {
                        tab--;
                        viewpager.setCurrentItem(tab);
                    } else if (tab == 0) {
                        viewpager.setCurrentItem(tab);
                    }
                }
            });

            // Images right navigatin
            rl_right_nav.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int tab = viewpager.getCurrentItem();
                    tab++;
                    viewpager.setCurrentItem(tab);
                }
            });
        }
    }
}

