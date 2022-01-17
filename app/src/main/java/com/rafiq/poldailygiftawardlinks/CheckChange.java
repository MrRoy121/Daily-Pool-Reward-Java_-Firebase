package com.rafiq.poldailygiftawardlinks;

import android.app.AlertDialog;
import android.app.job.JobScheduler;
import android.app.job.JobService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import androidx.cardview.widget.CardView;

public class CheckChange extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(!Check.isConnectedToInternet(context)){
            AlertDialog.Builder b = new AlertDialog.Builder(context);
            View c = LayoutInflater.from(context).inflate(R.layout.connection, null);
            b.setView(c);

            CardView cc = c.findViewById(R.id.fff);

            AlertDialog dialog = b.create();
            dialog.show();
            dialog.setCancelable(false);
            dialog.getWindow().setGravity(Gravity.CENTER);

            cc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                    onReceive(context,intent);
                }
            });
        }
    }
}
