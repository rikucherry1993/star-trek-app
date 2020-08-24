package com.rikucherry.startrek.ui.adapter;

import android.view.View;

import androidx.databinding.BindingAdapter;

public class CustomerBindingAdapter {

    @BindingAdapter("showOrHide")
    public static void setVisibility(View view, boolean isLoading){
        view.setVisibility(isLoading? View.INVISIBLE : View.VISIBLE);
    }

}
