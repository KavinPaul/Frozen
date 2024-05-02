package com.example.frozen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class BestSeller implements Parcelable, FireStoreProducts {
    private String bestsellerProductId;
    private String bestsellerProductName;
    private String bestsellerProductPrice;
    private String bestsellerImageUrl;
    private String bestsellerRating;
    private String bestsellerProductQty;
    private String bestsellerProductDescription;

    public BestSeller() {
    }

    protected BestSeller(Parcel in) {
        bestsellerProductId = in.readString();
        bestsellerProductName = in.readString();
        bestsellerProductPrice = in.readString();
        bestsellerImageUrl = in.readString();
        bestsellerRating = in.readString();
        bestsellerProductQty = in.readString();
        bestsellerProductDescription = in.readString();
    }

    public static final Creator<BestSeller> CREATOR = new Creator<BestSeller>() {
        @Override
        public BestSeller createFromParcel(Parcel in) {
            return new BestSeller(in);
        }

        @Override
        public BestSeller[] newArray(int size) {
            return new BestSeller[size];
        }
    };

    public String getBestsellerProductId() {
        return bestsellerProductId;
    }

    public void setBestsellerProductId(String bestsellerProductId) {
        this.bestsellerProductId = bestsellerProductId;
    }

    public String getBestsellerProductName() {
        return bestsellerProductName;
    }

    public void setBestsellerProductName(String bestsellerProductName) {
        this.bestsellerProductName = bestsellerProductName;
    }

    public String getBestsellerProductPrice() {
        return bestsellerProductPrice;
    }

    public void setBestsellerProductPrice(String bestsellerProductPrice) {
        this.bestsellerProductPrice = bestsellerProductPrice;
    }

    public String getBestsellerImageUrl() {
        return bestsellerImageUrl;
    }

    public void setBestsellerImageUrl(String bestsellerImageUrl) {
        this.bestsellerImageUrl = bestsellerImageUrl;
    }

    public String getBestsellerRating() {
        return bestsellerRating;
    }

    public void setBestsellerRating(String bestsellerRating) {
        this.bestsellerRating = bestsellerRating;
    }

    public String getBestsellerProductQty() {
        return bestsellerProductQty;
    }

    public void setBestsellerProductQty(String bestsellerProductQty) {
        this.bestsellerProductQty = bestsellerProductQty;
    }

    public String getBestsellerProductDescription() {
        return bestsellerProductDescription;
    }

    public void setBestsellerProductDescription(String bestsellerProductDescription) {
        this.bestsellerProductDescription = bestsellerProductDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(bestsellerProductId);
        dest.writeString(bestsellerProductName);
        dest.writeString(bestsellerProductPrice);
        dest.writeString(bestsellerImageUrl);
        dest.writeString(bestsellerRating);
        dest.writeString(bestsellerProductQty);
        dest.writeString(bestsellerProductDescription);
    }

    @Override
    public String getFireStoreProductId() {
        return bestsellerProductId;
    }

    @Override
    public void setFireStoreProductId(String setId) {
        this.bestsellerProductId = setId;
    }

    @Override
    public String getFireStoreProductPrice() {
        return bestsellerProductPrice;
    }

    @Override
    public String getFireStoreProductImageUrl() {
        return bestsellerImageUrl;
    }

    @Override
    public String getFireStoreProductName() {
        return bestsellerProductName;
    }

    @Override
    public String getFireStoreProductQty() {
        return bestsellerProductQty;
    }
}