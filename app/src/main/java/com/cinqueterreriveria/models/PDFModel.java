package com.cinqueterreriveria.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PDFModel {


    public class Detail {

        @SerializedName("pdf")
        @Expose
        private String pdf;

        public String getPdf() {
            return pdf;
        }

        public void setPdf(String pdf) {
            this.pdf = pdf;
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
