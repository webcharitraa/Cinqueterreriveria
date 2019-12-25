package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LuxuryCollectionModel {


    public class Detail {

        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("location")
        @Expose
        private String location;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("short_desc")
        @Expose
        private String shortDesc;
        @SerializedName("slug")
        @Expose
        private String slug;

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getShortDesc() {
            return shortDesc;
        }

        public void setShortDesc(String shortDesc) {
            this.shortDesc = shortDesc;
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
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("detail")
        @Expose
        private List<Detail> detail = null;

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

        public List<Detail> getDetail() {
            return detail;
        }

        public void setDetail(List<Detail> detail) {
            this.detail = detail;
        }


}
