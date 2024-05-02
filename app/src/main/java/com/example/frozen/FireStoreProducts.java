package com.example.frozen;

import java.io.Serializable;

public interface FireStoreProducts extends Serializable {
    String getFireStoreProductId();

    void setFireStoreProductId(String setId);

    String getFireStoreProductPrice();

    String getFireStoreProductImageUrl();

    String getFireStoreProductName();

    String getFireStoreProductQty();

}
