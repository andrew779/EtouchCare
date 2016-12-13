/**
 * TeamOne
 */
package com.app.etouchcare.activity;

import android.app.Application;
import android.content.Context;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */

public class MyApplication extends Application{
    private static MyApplication sInstance;

    @Override
    public void onCreate(){
        super.onCreate();
        sInstance = this;

    }

    private static MyApplication getInstance(){
        return sInstance;
    }

    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
