package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchDealModel {


    public class Details {

        @SerializedName("check_in_date")
        @Expose
        private String checkInDate;
        @SerializedName("check_out_date")
        @Expose
        private String checkOutDate;
        @SerializedName("total_nights")
        @Expose
        private Integer totalNights;
        @SerializedName("price")
        @Expose
        private Integer price;
        @SerializedName("cleaning_fee")
        @Expose
        private String cleaningFee;
        @SerializedName("city_tax")
        @Expose
        private String cityTax;
        @SerializedName("full_payment")
        @Expose
        private Integer fullPayment;
        @SerializedName("deposit")
        @Expose
        private String deposit;
        @SerializedName("min_deposit")
        @Expose
        private Double minDeposit;
        @SerializedName("property_price")
        @Expose
        private Integer propertyPrice;
        @SerializedName("cleaning_price")
        @Expose
        private Integer cleaningPrice;
        @SerializedName("remaining_amount")
        @Expose
        private Double remainingAmount;

        public String getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(String checkInDate) {
            this.checkInDate = checkInDate;
        }

        public String getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(String checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

        public Integer getTotalNights() {
            return totalNights;
        }

        public void setTotalNights(Integer totalNights) {
            this.totalNights = totalNights;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        public String getCleaningFee() {
            return cleaningFee;
        }

        public void setCleaningFee(String cleaningFee) {
            this.cleaningFee = cleaningFee;
        }

        public String getCityTax() {
            return cityTax;
        }

        public void setCityTax(String cityTax) {
            this.cityTax = cityTax;
        }

        public Integer getFullPayment() {
            return fullPayment;
        }

        public void setFullPayment(Integer fullPayment) {
            this.fullPayment = fullPayment;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public Double getMinDeposit() {
            return minDeposit;
        }

        public void setMinDeposit(Double minDeposit) {
            this.minDeposit = minDeposit;
        }

        public Integer getPropertyPrice() {
            return propertyPrice;
        }

        public void setPropertyPrice(Integer propertyPrice) {
            this.propertyPrice = propertyPrice;
        }

        public Integer getCleaningPrice() {
            return cleaningPrice;
        }

        public void setCleaningPrice(Integer cleaningPrice) {
            this.cleaningPrice = cleaningPrice;
        }

        public Double getRemainingAmount() {
            return remainingAmount;
        }

        public void setRemainingAmount(Double remainingAmount) {
            this.remainingAmount = remainingAmount;
        }

    }



        @SerializedName("success")
        @Expose
        private String success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("details")
        @Expose
        private Details details;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }

        public Details getDetails() {
            return details;
        }

        public void setDetails(Details details) {
            this.details = details;
        }



}
