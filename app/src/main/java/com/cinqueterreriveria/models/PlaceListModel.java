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
    @SerializedName("propertyType")
    @Expose
    private List<PropertyType> propertyType = null;
    @SerializedName("message")
    @Expose
    private String message;

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

    public List<PropertyType> getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(List<PropertyType> propertyType) {
        this.propertyType = propertyType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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
        @SerializedName("slug")
        @Expose
        private String slug;
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

    public class PropertyType {

        @SerializedName("term_id")
        @Expose
        private Integer termId;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("slug")
        @Expose
        private String slug;
        @SerializedName("term_group")
        @Expose
        private Integer termGroup;
        @SerializedName("term_taxonomy_id")
        @Expose
        private Integer termTaxonomyId;
        @SerializedName("taxonomy")
        @Expose
        private String taxonomy;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("parent")
        @Expose
        private Integer parent;
        @SerializedName("count")
        @Expose
        private Integer count;
        @SerializedName("filter")
        @Expose
        private String filter;
        @SerializedName("term_order")
        @Expose
        private String termOrder;

        public Integer getTermId() {
            return termId;
        }

        public void setTermId(Integer termId) {
            this.termId = termId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSlug() {
            return slug;
        }

        public void setSlug(String slug) {
            this.slug = slug;
        }

        public Integer getTermGroup() {
            return termGroup;
        }

        public void setTermGroup(Integer termGroup) {
            this.termGroup = termGroup;
        }

        public Integer getTermTaxonomyId() {
            return termTaxonomyId;
        }

        public void setTermTaxonomyId(Integer termTaxonomyId) {
            this.termTaxonomyId = termTaxonomyId;
        }

        public String getTaxonomy() {
            return taxonomy;
        }

        public void setTaxonomy(String taxonomy) {
            this.taxonomy = taxonomy;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public Integer getParent() {
            return parent;
        }

        public void setParent(Integer parent) {
            this.parent = parent;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public String getFilter() {
            return filter;
        }

        public void setFilter(String filter) {
            this.filter = filter;
        }

        public String getTermOrder() {
            return termOrder;
        }

        public void setTermOrder(String termOrder) {
            this.termOrder = termOrder;
        }

    }
}
