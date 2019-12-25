package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class WhatToDoDetailModel {

    public class Detail {

        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("banner_title")
        @Expose
        private String bannerTitle;
        @SerializedName("content")
        @Expose
        private String content;

        public String getBannerImage() {
            return bannerImage;
        }

        public void setBannerImage(String bannerImage) {
            this.bannerImage = bannerImage;
        }

        public String getBannerTitle() {
            return bannerTitle;
        }

        public void setBannerTitle(String bannerTitle) {
            this.bannerTitle = bannerTitle;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

    }


        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("detail")
        @Expose
        private Detail detail;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Detail getDetail() {
            return detail;
        }

        public void setDetail(Detail detail) {
            this.detail = detail;
        }


}
