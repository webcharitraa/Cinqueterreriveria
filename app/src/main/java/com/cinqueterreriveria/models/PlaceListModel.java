package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceListModel {


        @SerializedName("success")
        @Expose
        private Boolean success;
        @SerializedName("locationProperties")
        @Expose
        private List<LocationProperty> locationProperties = null;

        public Boolean getSuccess() {
            return success;
        }

        public void setSuccess(Boolean success) {
            this.success = success;
        }

        public List<LocationProperty> getLocationProperties() {
            return locationProperties;
        }

        public void setLocationProperties(List<LocationProperty> locationProperties) {
            this.locationProperties = locationProperties;
        }




    public class Icons {

        @SerializedName("guests")
        @Expose
        private String guests;
        @SerializedName("rooms")
        @Expose
        private Integer rooms;
        @SerializedName("bathrooms")
        @Expose
        private Integer bathrooms;

        public String getGuests() {
            return guests;
        }

        public void setGuests(String guests) {
            this.guests = guests;
        }

        public Integer getRooms() {
            return rooms;
        }

        public void setRooms(Integer rooms) {
            this.rooms = rooms;
        }

        public Integer getBathrooms() {
            return bathrooms;
        }

        public void setBathrooms(Integer bathrooms) {
            this.bathrooms = bathrooms;
        }

    }


    public class LocationProperty {

        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("price")
        @Expose
        private String price;
        @SerializedName("icons")
        @Expose
        private Icons icons;
        @SerializedName("gallery")
        @Expose
        private List<String> gallery = null;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

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

        public Icons getIcons() {
            return icons;
        }

        public void setIcons(Icons icons) {
            this.icons = icons;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
        }

    }
}
