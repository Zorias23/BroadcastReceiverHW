package com.example.admin.broadcastreceiverhw;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Admin on 11/21/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent)
    {
        String action = intent.getAction();
        String path = intent.getStringExtra("download_image");
        Log.d("RECEIVER", "onReceive: receiving broadcast");

        switch (action)
        {
            case "download":
            {

                Toast.makeText(context, "Your image was written to: " + path, Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
