package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AccountBookingModel {

    public class Detail {

        @SerializedName("check_in_month")
        @Expose
        private String checkInMonth;
        @SerializedName("check_in_date")
        @Expose
        private String checkInDate;
        @SerializedName("check_out_month")
        @Expose
        private String checkOutMonth;
        @SerializedName("check_out_date")
        @Expose
        private String checkOutDate;
        @SerializedName("destination")
        @Expose
        private String destination;
        @SerializedName("booking_order")
        @Expose
        private String bookingOrder;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("payment")
        @Expose
        private String payment;
        @SerializedName("important_note")
        @Expose
        private String importantNote;

        public String getCheckInMonth() {
            return checkInMonth;
        }

        public void setCheckInMonth(String checkInMonth) {
            this.checkInMonth = checkInMonth;
        }

        public String getCheckInDate() {
            return checkInDate;
        }

        public void setCheckInDate(String checkInDate) {
            this.checkInDate = checkInDate;
        }

        public String getCheckOutMonth() {
            return checkOutMonth;
        }

        public void setCheckOutMonth(String checkOutMonth) {
            this.checkOutMonth = checkOutMonth;
        }

        public String getCheckOutDate() {
            return checkOutDate;
        }

        public void setCheckOutDate(String checkOutDate) {
            this.checkOutDate = checkOutDate;
        }

        public String getDestination() {
            return destination;
        }

        public void setDestination(String destination) {
            this.destination = destination;
        }

        public String getBookingOrder() {
            return bookingOrder;
        }

        public void setBookingOrder(String bookingOrder) {
            this.bookingOrder = bookingOrder;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getPayment() {
            return payment;
        }

        public void setPayment(String payment) {
            this.payment = payment;
        }

        public String getImportantNote() {
            return importantNote;
        }

        public void setImportantNote(String importantNote) {
            this.importantNote = importantNote;
        }

    }


        @SerializedName("success")
        @Expose
        private String success;
        @SerializedName("message")
        @Expose
        private String message;
        @SerializedName("detail")
        @Expose
        private List<Detail> detail = null;

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

        public List<Detail> getDetail() {
            return detail;
        }

        public void setDetail(List<Detail> detail) {
            this.detail = detail;
        }


}
