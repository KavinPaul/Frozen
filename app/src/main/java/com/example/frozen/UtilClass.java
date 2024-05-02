package com.example.frozen;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.snackbar.Snackbar;
import java.io.Serializable;

public final class UtilClass {
    private UtilClass(){

    }

    public static void screenNavigation(Context context, Class<?> destination) {

        Intent intent = new Intent(context, destination);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void screenNavigationWithDataPassing(Context context, Class<?> destination, String key, String value) {
        Intent intent = new Intent(context, destination);
        intent.putExtra(key,value);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void screenNavigationWithDataPassing(Context context,Class<?> destination, String key, Parcelable value) {

        Intent intent = new Intent(context,destination);
        intent.putExtra(key,value);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
    }

    public static void screenNavigationWithDataPassingSerialize(Context context, Class<?> destination, String key, Serializable value) {

        Intent intent = new Intent(context,destination);
        intent.putExtra(key,value);
        intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
        context.startActivity(intent);
    }

    public static void snackBar(View view, String stringText) {
        Snackbar.make(view,stringText,Snackbar.LENGTH_SHORT).show();
    }

    public static void setUpCustomToolbar (AppCompatActivity activity) {
        Toolbar toolbar = activity.findViewById((R.id.toolbar_custom));
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = activity.getSupportActionBar();
        if (actionBar!=null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
        }
        toolbar.setNavigationOnClickListener(v -> {
            activity.getOnBackPressedDispatcher().onBackPressed();
            activity.finish();
        });
    }


    public static void screenNavigationWithoutBackStack(Context context, Class<?> destination) {
        Intent intent = new Intent(context, destination);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK
                | Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }
    // this is to work with individual toolbar
    public static void setUpToolBar(AppCompatActivity activity, Toolbar toolBar) {

        activity.setSupportActionBar(toolBar);

        ActionBar actionBar = activity.getSupportActionBar();

        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("");
                   }
        toolBar.setNavigationOnClickListener(view -> {
            activity.getOnBackPressedDispatcher().onBackPressed();
        });

    }

}
