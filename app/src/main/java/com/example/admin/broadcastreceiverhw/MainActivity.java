package com.example.admin.broadcastreceiverhw;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    public static final String IMAGE_URL = "https://avatars3.githubusercontent.com/u/13229219?v=4";
    private MyBroadcastReceiver myBroadcastReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myBroadcastReceiver = new MyBroadcastReceiver();


    }


    @Override
    protected void onStart()
    {
        super.onStart();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("download");
        intentFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(myBroadcastReceiver, intentFilter);
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        unregisterReceiver(myBroadcastReceiver);
    }
    public void downloadImage(View view) {

        Intent intent = new Intent(this, MyIntentService.class);
        intent.putExtra("image_url", IMAGE_URL);
        this.startService(intent);
    }
}
