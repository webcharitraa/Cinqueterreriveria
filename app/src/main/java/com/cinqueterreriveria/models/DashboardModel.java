package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DashboardModel {


    public class Activity {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("image")
        @Expose
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }


    public class Blog {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("slug")
        @Expose
        private String slug;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

    }




        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("location")
        @Expose
        private List<Location> location = null;
        @SerializedName("activity")
        @Expose
        private List<Activity> activity = null;
        @SerializedName("whatToDo")
        @Expose
        private List<WhatToDo> whatToDo = null;
        @SerializedName("videos")
        @Expose
        private List<Video> videos = null;
        @SerializedName("howReach")
        @Expose
        private List<HowReach> howReach = null;
        @SerializedName("blogs")
        @Expose
        private List<Blog> blogs = null;
        @SerializedName("lat")
        @Expose
        private String lat;
        @SerializedName("long")
        @Expose
        private String _long;
        @SerializedName("banner_title")
        @Expose
        private String bannerTitle;
        @SerializedName("banner_sub_title")
        @Expose
        private String bannerSubTitle;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public List<Location> getLocation() {
            return location;
        }

        public void setLocation(List<Location> location) {
            this.location = location;
        }

        public List<Activity> getActivity() {
            return activity;
        }

        public void setActivity(List<Activity> activity) {
            this.activity = activity;
        }

        public List<WhatToDo> getWhatToDo() {
            return whatToDo;
        }

        public void setWhatToDo(List<WhatToDo> whatToDo) {
            this.whatToDo = whatToDo;
        }

        public List<Video> getVideos() {
            return videos;
        }

        public void setVideos(List<Video> videos) {
            this.videos = videos;
        }

        public List<HowReach> getHowReach() {
            return howReach;
        }

        public void setHowReach(List<HowReach> howReach) {
            this.howReach = howReach;
        }

        public List<Blog> getBlogs() {
            return blogs;
        }

        public void setBlogs(List<Blog> blogs) {
            this.blogs = blogs;
        }

        public String getLat() {
            return lat;
        }

        public void setLat(String lat) {
            this.lat = lat;
        }

        public String getLong() {
            return _long;
        }

        public void setLong(String _long) {
            this._long = _long;
        }

        public String getBannerTitle() {
            return bannerTitle;
        }

        public void setBannerTitle(String bannerTitle) {
            this.bannerTitle = bannerTitle;
        }

        public String getBannerSubTitle() {
            return bannerSubTitle;
        }

        public void setBannerSubTitle(String bannerSubTitle) {
            this.bannerSubTitle = bannerSubTitle;
        }

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }




    public class HowReach {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("image")
        @Expose
        private String image;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }


    public class Location {

        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("price")
        @Expose
        private String price;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

    }


    public class Video {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("video")
        @Expose
        private String video;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

    }


    public class WhatToDo {

        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("image")
        @Expose
        private String image;

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

    }
}