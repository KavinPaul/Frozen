package com.example.frozen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Featured implements Parcelable, FireStoreProducts {
    private String featuredProductId;
    private String featuredProductName;
    private String featuredProductPrice;
    private String featuredImageUrl;

    private String featuredProductRating;
    private String featuredProductQty;

    private String featuredProductDescription;

    public Featured() {
    }

    protected Featured(Parcel in) {
        featuredProductId = in.readString();
        featuredProductName = in.readString();
        featuredProductPrice = in.readString();
        featuredImageUrl = in.readString();
        featuredProductRating = in.readString();
        featuredProductQty = in.readString();
        featuredProductDescription = in.readString();
    }

    public static final Creator<Featured> CREATOR = new Creator<Featured>() {
        @Override
        public Featured createFromParcel(Parcel in) {
            return new Featured(in);
        }

        @Override
        public Featured[] newArray(int size) {
            return new Featured[size];
        }
    };

    public String getFeaturedProductId() {

        return featuredProductId;
    }

    public void setFeaturedProductId(String featuredProductId) {
        this.featuredProductId = featuredProductId;
    }

    public String getFeaturedProductName() {
        return featuredProductName;
    }

    public void setFeaturedProductName(String featuredProductName) {
        this.featuredProductName = featuredProductName;
    }

    public String getFeaturedProductPrice() {
        return featuredProductPrice;
    }

    public void setFeaturedProductPrice(String featuredProductPrice) {
        this.featuredProductPrice = featuredProductPrice;
    }

    public String getFeaturedImageUrl() {
        return featuredImageUrl;
    }

    public void setFeaturedImageUrl(String featuredImageUrl) {
        this.featuredImageUrl = featuredImageUrl;
    }

    public String getFeaturedProductRating() {
        return featuredProductRating;
    }

    public void setFeaturedProductRating(String featuredProductRating) {
        this.featuredProductRating = featuredProductRating;
    }

    public String getFeaturedProductQty() {
        return featuredProductQty;
    }

    public void setFeaturedProductQty(String featuredProductQty) {
        this.featuredProductQty = featuredProductQty;
    }

    public String getFeaturedProductDescription() {
        return featuredProductDescription;
    }

    public void setFeaturedProductDescription(String featuredProductDescription) {
        this.featuredProductDescription = featuredProductDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(featuredProductId);
        dest.writeString(featuredProductName);
        dest.writeString(featuredProductPrice);
        dest.writeString(featuredImageUrl);
        dest.writeString(featuredProductRating);
        dest.writeString(featuredProductQty);
        dest.writeString(featuredProductDescription);
    }

    @Override
    public String getFireStoreProductId() {
        return featuredProductId;
    }

    @Override
    public void setFireStoreProductId(String setId) {
        this.featuredProductId = setId;
    }

    @Override
    public String getFireStoreProductPrice() {
        return featuredProductPrice;
    }

    @Override
    public String getFireStoreProductImageUrl() {
        return featuredImageUrl;
    }

    @Override
    public String getFireStoreProductName() {
        return featuredProductName;
    }

    @Override
    public String getFireStoreProductQty() {
        return featuredProductQty;
    }
}
