package com.rockatoo.helloapplication;

import android.app.AppComponentFactory;
import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service{
    public IBinder onBind(Intent intent) {
        return binder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MyService", "There is something to do yeah?");
        return Service.START_STICKY;
    }

    // Binder Definition
    private final IBinder binder = new MyBinder();

    public class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }

    public void logGreeting(){
        Log.d("MyService", "Hello Service!");
    }
}
