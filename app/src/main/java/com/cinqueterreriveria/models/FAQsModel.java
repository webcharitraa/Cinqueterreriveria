package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class FAQsModel {

    public class Detail {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("content")
        @Expose
        private String content;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
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
        @SerializedName("banner_title")
        @Expose
        private String bannerTitle;
        @SerializedName("banner_sub_title")
        @Expose
        private String bannerSubTitle;
        @SerializedName("banner_image")
        @Expose
        private String bannerImage;
        @SerializedName("detail")
        @Expose
        private List<Detail> detail = null;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
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

        public List<Detail> getDetail() {
            return detail;
        }

        public void setDetail(List<Detail> detail) {
            this.detail = detail;
        }


}
