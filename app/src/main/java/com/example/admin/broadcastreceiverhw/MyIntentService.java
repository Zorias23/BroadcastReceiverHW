package com.example.admin.broadcastreceiverhw;

import android.app.IntentService;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Admin on 11/22/2017.
 */

public class MyIntentService extends IntentService {
    public static final String TAG = "MyIntentService";
    private String filepath;
    public MyIntentService()
    {
        super("MyIntentService");

    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        String URL = intent.getStringExtra("image_url");
        Log.d(TAG, "onHandleIntent: Hey, we got the image url! It's: " + URL);
        try {
            URL url = new URL(URL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);

            ContextWrapper cw = new ContextWrapper(getApplicationContext());
            File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
            File mypath=new File(directory,"image.jpg");

            FileOutputStream fos = null;
            fos = new FileOutputStream(mypath);

            myBitmap.compress(Bitmap.CompressFormat.PNG, 90, fos);
            fos.close();
            filepath = mypath.getAbsolutePath();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Intent intent1 = new Intent();
        intent1.setAction("download");
        intent1.putExtra("download_image", filepath);
        sendBroadcast(intent1);


    }
}
