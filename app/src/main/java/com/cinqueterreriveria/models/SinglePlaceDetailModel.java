package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SinglePlaceDetailModel {

    public class Comment {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("comment")
        @Expose
        private String comment;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getComment() {
            return comment;
        }

        public void setComment(String comment) {
            this.comment = comment;
        }

    }

    public class Detail {

        @SerializedName("amenties")
        @Expose
        private List<String> amenties = null;
        @SerializedName("Property Type")
        @Expose
        private String propertyType;
        @SerializedName("Property Location")
        @Expose
        private String propertyLocation;
        @SerializedName("Title")
        @Expose
        private String title;
        @SerializedName("Description")
        @Expose
        private String description;

        public String getRating() {
            return rating;
        }

        public void setRating(String rating) {
            this.rating = rating;
        }

        @SerializedName("rating")
        @Expose
        private String rating;
        @SerializedName("Product Tags")
        @Expose
        private List<String> productTags = null;
        @SerializedName("product_id")
        @Expose
        private String productId;
        @SerializedName("property_id")
        @Expose
        private String propertyId;
        @SerializedName("details")
        @Expose
        private List<Detail_> details = null;
        @SerializedName("nearby")
        @Expose
        private List<Nearby> nearby = null;
        @SerializedName("floorPlanImage")
        @Expose
        private String floorPlanImage;
        @SerializedName("icons")
        @Expose
        private Icons icons;
        @SerializedName("Our Rules")
        @Expose
        private OurRules ourRules;
        @SerializedName("Citra Code")
        @Expose
        private String citraCode;
        @SerializedName("extras")
        @Expose
        private List<Extra> extras = null;
        @SerializedName("Property Rates")
        @Expose
        private List<PropertyRate> propertyRates = null;
        @SerializedName("Location")
        @Expose
        private Location location;
        @SerializedName("comments")
        @Expose
        private List<Comment> comments = null;
        @SerializedName("gallery")
        @Expose
        private List<String> gallery = null;

        public List<String> getAmenties() {
            return amenties;
        }

        public void setAmenties(List<String> amenties) {
            this.amenties = amenties;
        }

        public String getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
        }

        public String getPropertyLocation() {
            return propertyLocation;
        }

        public void setPropertyLocation(String propertyLocation) {
            this.propertyLocation = propertyLocation;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public List<String> getProductTags() {
            return productTags;
        }

        public void setProductTags(List<String> productTags) {
            this.productTags = productTags;
        }

        public String getProductId() {
            return productId;
        }

        public void setProductId(String productId) {
            this.productId = productId;
        }

        public String getPropertyId() {
            return propertyId;
        }

        public void setPropertyId(String propertyId) {
            this.propertyId = propertyId;
        }

        public List<Detail_> getDetails() {
            return details;
        }

        public void setDetails(List<Detail_> details) {
            this.details = details;
        }

        public List<Nearby> getNearby() {
            return nearby;
        }

        public void setNearby(List<Nearby> nearby) {
            this.nearby = nearby;
        }

        public String getFloorPlanImage() {
            return floorPlanImage;
        }

        public void setFloorPlanImage(String floorPlanImage) {
            this.floorPlanImage = floorPlanImage;
        }

        public Icons getIcons() {
            return icons;
        }

        public void setIcons(Icons icons) {
            this.icons = icons;
        }

        public OurRules getOurRules() {
            return ourRules;
        }

        public void setOurRules(OurRules ourRules) {
            this.ourRules = ourRules;
        }

        public String getCitraCode() {
            return citraCode;
        }

        public void setCitraCode(String citraCode) {
            this.citraCode = citraCode;
        }

        public List<Extra> getExtras() {
            return extras;
        }

        public void setExtras(List<Extra> extras) {
            this.extras = extras;
        }

        public List<PropertyRate> getPropertyRates() {
            return propertyRates;
        }

        public void setPropertyRates(List<PropertyRate> propertyRates) {
            this.propertyRates = propertyRates;
        }

        public Location getLocation() {
            return location;
        }

        public void setLocation(Location location) {
            this.location = location;
        }

        public List<Comment> getComments() {
            return comments;
        }

        public void setComments(List<Comment> comments) {
            this.comments = comments;
        }

        public List<String> getGallery() {
            return gallery;
        }

        public void setGallery(List<String> gallery) {
            this.gallery = gallery;
        }

    }


    public class Detail_ {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
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



    public class Extra {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

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

    public class Location {

        @SerializedName("Lat")
        @Expose
        private String lat;
        @SerializedName("Long")
        @Expose
        private String _long;

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

    }


    public class Nearby {

        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("image")
        @Expose
        private String image;
        @SerializedName("slug")
        @Expose
        private String slug;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

    public class OurRules {

        @SerializedName("Minimum Stay")
        @Expose
        private String minimumStay;
        @SerializedName("Garbage Collection")
        @Expose
        private String garbageCollection;
        @SerializedName("Smoking")
        @Expose
        private String smoking;
        @SerializedName("Pets")
        @Expose
        private String pets;
        @SerializedName("Check-In Time")
        @Expose
        private String checkInTime;
        @SerializedName("Check-Out Time")
        @Expose
        private String checkOutTime;

        public String getMinimumStay() {
            return minimumStay;
        }

        public void setMinimumStay(String minimumStay) {
            this.minimumStay = minimumStay;
        }

        public String getGarbageCollection() {
            return garbageCollection;
        }

        public void setGarbageCollection(String garbageCollection) {
            this.garbageCollection = garbageCollection;
        }

        public String getSmoking() {
            return smoking;
        }

        public void setSmoking(String smoking) {
            this.smoking = smoking;
        }

        public String getPets() {
            return pets;
        }

        public void setPets(String pets) {
            this.pets = pets;
        }

        public String getCheckInTime() {
            return checkInTime;
        }

        public void setCheckInTime(String checkInTime) {
            this.checkInTime = checkInTime;
        }

        public String getCheckOutTime() {
            return checkOutTime;
        }

        public void setCheckOutTime(String checkOutTime) {
            this.checkOutTime = checkOutTime;
        }

    }

    public class PropertyRate {

        @SerializedName("Property Rates")
        @Expose
        private String propertyRates;
        @SerializedName("Daily")
        @Expose
        private String daily;
        @SerializedName("Min Stay")
        @Expose
        private String minStay;

        public String getPropertyRates() {
            return propertyRates;
        }

        public void setPropertyRates(String propertyRates) {
            this.propertyRates = propertyRates;
        }

        public String getDaily() {
            return daily;
        }

        public void setDaily(String daily) {
            this.daily = daily;
        }

        public String getMinStay() {
            return minStay;
        }

        public void setMinStay(String minStay) {
            this.minStay = minStay;
        }

    }
}
