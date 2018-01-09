package com.elderapp.tekren.myapp;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;

/**
 * Created by Tekren on 09.01.2018.
 */

public class Menu extends Service {
    private LinearLayout ll;
    private WindowManager wm;

    @Override
    public void onCreate() {
        super.onCreate();
        wm=(WindowManager)getSystemService(WINDOW_SERVICE);
        ll=new LinearLayout(this);
        LinearLayout.LayoutParams llParameters=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
        ll.setBackgroundColor(Color.argb(66,255,0,0));
        ll.setLayoutParams(llParameters);
        WindowManager.LayoutParams parameters=new WindowManager.LayoutParams(400,150,WindowManager.LayoutParams.TYPE_PHONE,WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE, PixelFormat.TRANSLUCENT);
        parameters.x=0;
        parameters.y=0;
        parameters.gravity=Gravity.CENTER|Gravity.CENTER;
        wm.addView(ll, parameters);

    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}