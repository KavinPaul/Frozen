package com.example.frozen;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class AllProducts implements Parcelable, FireStoreProducts{
    private String productId;
    private String productName;
    private String productDescription;
    private String productImageUrl;
    private String categoryType;


    private int productPrice;
    private int productQty;

    private float productRating;

    public AllProducts() {
    }

    protected AllProducts(Parcel in) {
        productId = in.readString();
        productName = in.readString();
        productDescription = in.readString();
        productImageUrl = in.readString();
        categoryType = in.readString();
        productPrice = in.readInt();
        productQty = in.readInt();
        productRating = in.readFloat();
    }

    public static final Creator<AllProducts> CREATOR = new Creator<AllProducts>() {
        @Override
        public AllProducts createFromParcel(Parcel in) {
            return new AllProducts(in);
        }

        @Override
        public AllProducts[] newArray(int size) {
            return new AllProducts[size];
        }
    };

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImageUrl() {
        return productImageUrl;
    }

    public void setProductImageUrl(String productImageUrl) {
        this.productImageUrl = productImageUrl;
    }

    public String getCategoryType() {
        return categoryType;
    }

    public void setCategoryType(String categoryType) {
        this.categoryType = categoryType;
    }

    public int getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(int productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public float getProductRating() {
        return productRating;
    }

    public void setProductRating(float productRating) {
        this.productRating = productRating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(productId);
        dest.writeString(productName);
        dest.writeString(productDescription);
        dest.writeString(productImageUrl);
        dest.writeString(categoryType);
        dest.writeInt(productPrice);
        dest.writeInt(productQty);
        dest.writeFloat(productRating);
    }

    @Override
    public String getFireStoreProductId() {
        return productId;
    }

    @Override
    public void setFireStoreProductId(String setId) {
        this.productId = setId;
    }

    @Override
    public String getFireStoreProductPrice() {
        return String.valueOf(productPrice);
    }

    @Override
    public String getFireStoreProductImageUrl() {
        return productImageUrl;
    }

    @Override
    public String getFireStoreProductName() {
        return productName;
    }

    @Override
    public String getFireStoreProductQty() {
        return String.valueOf(productQty);
    }
}
