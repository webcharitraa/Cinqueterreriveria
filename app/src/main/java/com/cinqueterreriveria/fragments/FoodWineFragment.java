package com.cinqueterreriveria.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.cinqueterreriveria.R;
import com.cinqueterreriveria.adapters.BlogAccommodationAdapter;
import com.cinqueterreriveria.apis.ApiConstents;
import com.cinqueterreriveria.apis.Rest;
import com.cinqueterreriveria.models.BlogCategoryModel;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FoodWineFragment extends Fragment {

    RecyclerView rv_blogs_food_wine;
    TextView tv_blog_title,tv_blog_des;
    ImageView iv_blogcategory;
    ShimmerFrameLayout shimmer_blog_food_wine;
    LinearLayout ll_blod_food_wine;

    public FoodWineFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_food_wine, container, false);
        rv_blogs_food_wine=view.findViewById(R.id.rv_blogs_food_wine);
        iv_blogcategory=view.findViewById(R.id.iv_blogcategory);
        tv_blog_title=view.findViewById(R.id.tv_blog_title);
        tv_blog_des=view.findViewById(R.id.tv_blog_des);
        shimmer_blog_food_wine=view.findViewById(R.id.shimmer_blog_food_wine);
        ll_blod_food_wine=view.findViewById(R.id.ll_blod_food_wine);
        rv_blogs_food_wine.setLayoutManager(new LinearLayoutManager(getActivity()));
        //rv_blogs_food_wine.setAdapter(new FoodWineAdapter(getActivity()));

        BlogCategoryAPI();
        return view;
    }
    private void BlogCategoryAPI()
    {
        Call<BlogCategoryModel> call= Rest.getRetrofit().blogCategory(ApiConstents.SECRET_KEY,
                "food-wine");
        shimmer_blog_food_wine.startShimmerAnimation();
        call.enqueue(new Callback<BlogCategoryModel>() {
            @Override
            public void onResponse(Call<BlogCategoryModel> call, Response<BlogCategoryModel> response) {
                if (response.isSuccessful())
                {
                    if (response.body().getSuccess() == true)
                    {
                        shimmer_blog_food_wine.stopShimmerAnimation();
                        shimmer_blog_food_wine.setVisibility(View.GONE);
                        ll_blod_food_wine.setVisibility(View.VISIBLE);
                        tv_blog_title.setText(response.body().getDetail().getBannerTitle());
                        tv_blog_des.setText(Html.fromHtml(response.body().getDetail().getContent()));
                        RequestOptions simpleOptions = new RequestOptions()
                                .centerCrop()

                                .diskCacheStrategy(DiskCacheStrategy.RESOURCE);
                        Glide.with(getActivity()).load(response.body().getDetail().getBannerImage()).placeholder(R.drawable.placeholder).apply(simpleOptions).into(iv_blogcategory);

                        rv_blogs_food_wine.setAdapter(new BlogAccommodationAdapter(getActivity(),response.body().getDetail().getBlogDescription()));

                    }
                    else {
                        shimmer_blog_food_wine.stopShimmerAnimation();
                        shimmer_blog_food_wine.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<BlogCategoryModel> call, Throwable t) {
                shimmer_blog_food_wine.stopShimmerAnimation();
                shimmer_blog_food_wine.setVisibility(View.GONE);
            }
        });
    }

}
