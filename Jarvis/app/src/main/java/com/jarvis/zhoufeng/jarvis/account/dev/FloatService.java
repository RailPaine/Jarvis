package com.jarvis.zhoufeng.jarvis.account.dev;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

/**
 * Created by zhoufeng on 2017/7/27.
 */

public class FloatService extends Service {
    @Override
    public void onCreate() {
        super.onCreate();
        CustomViewManager.getInstance(this).showFloatViewOnWindow();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}