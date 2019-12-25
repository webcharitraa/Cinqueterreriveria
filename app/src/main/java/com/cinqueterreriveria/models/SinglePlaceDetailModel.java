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
        @SerializedName("Product Tags")
        @Expose
        private List<String> productTags = null;
        @SerializedName("details")
        @Expose
        private Details details;
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
        private Extras extras;
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

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
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

        public Extras getExtras() {
            return extras;
        }

        public void setExtras(Extras extras) {
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


    public class Details {

        @SerializedName("Property Type")
        @Expose
        private String propertyType;

        public String getPropertyType() {
            return propertyType;
        }

        public void setPropertyType(String propertyType) {
            this.propertyType = propertyType;
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



    public class Extras {

        @SerializedName("Cleaning Fees")
        @Expose
        private String cleaningFees;
        @SerializedName("Security Deposit")
        @Expose
        private String securityDeposit;
        @SerializedName("City Tax")
        @Expose
        private String cityTax;
        @SerializedName("Deposit")
        @Expose
        private String deposit;
        @SerializedName("Extra Guest (over 2)")
        @Expose
        private String extraGuestOver2;
        @SerializedName("Pet (max.1)")
        @Expose
        private String petMax1;
        @SerializedName("Beach Towels (each)")
        @Expose
        private String beachTowelsEach;
        @SerializedName("Baby Cot")
        @Expose
        private String babyCot;
        @SerializedName("Extra Towels (each)")
        @Expose
        private String extraTowelsEach;
        @SerializedName("Change Linen (per bed)")
        @Expose
        private String changeLinenPerBed;
        @SerializedName("Daily Cleaning (1hour)")
        @Expose
        private String dailyCleaning1hour;

        public String getCleaningFees() {
            return cleaningFees;
        }

        public void setCleaningFees(String cleaningFees) {
            this.cleaningFees = cleaningFees;
        }

        public String getSecurityDeposit() {
            return securityDeposit;
        }

        public void setSecurityDeposit(String securityDeposit) {
            this.securityDeposit = securityDeposit;
        }

        public String getCityTax() {
            return cityTax;
        }

        public void setCityTax(String cityTax) {
            this.cityTax = cityTax;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getExtraGuestOver2() {
            return extraGuestOver2;
        }

        public void setExtraGuestOver2(String extraGuestOver2) {
            this.extraGuestOver2 = extraGuestOver2;
        }

        public String getPetMax1() {
            return petMax1;
        }

        public void setPetMax1(String petMax1) {
            this.petMax1 = petMax1;
        }

        public String getBeachTowelsEach() {
            return beachTowelsEach;
        }

        public void setBeachTowelsEach(String beachTowelsEach) {
            this.beachTowelsEach = beachTowelsEach;
        }

        public String getBabyCot() {
            return babyCot;
        }

        public void setBabyCot(String babyCot) {
            this.babyCot = babyCot;
        }

        public String getExtraTowelsEach() {
            return extraTowelsEach;
        }

        public void setExtraTowelsEach(String extraTowelsEach) {
            this.extraTowelsEach = extraTowelsEach;
        }

        public String getChangeLinenPerBed() {
            return changeLinenPerBed;
        }

        public void setChangeLinenPerBed(String changeLinenPerBed) {
            this.changeLinenPerBed = changeLinenPerBed;
        }

        public String getDailyCleaning1hour() {
            return dailyCleaning1hour;
        }

        public void setDailyCleaning1hour(String dailyCleaning1hour) {
            this.dailyCleaning1hour = dailyCleaning1hour;
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
