package com.webscare.livenewsnow.OtherClasses;

import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.webscare.livenewsnow.MainActivity;

public class AlertDialogClass {

    public  void alertDialog() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(MainActivity.getContext());
        alertDialogBuilder
                .setMessage("Please try later")
                .setCancelable(true);


        alertDialogBuilder.setNeutralButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    }
