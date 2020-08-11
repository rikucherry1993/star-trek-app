package com.rikucherry.startrek.view;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.rikucherry.startrek.R;

public class LoadingDialog {

    private Activity activity;
    private AlertDialog dialog;

    LoadingDialog(Activity myActivity) {
        this.activity = myActivity;
    }


    public void startLoadingDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.dialog_loading,null));
        builder.setCancelable(false);

        dialog = builder.create();
        dialog.show();
    }


    public void dismissDialog() {
        dialog.dismiss();
    }

}
