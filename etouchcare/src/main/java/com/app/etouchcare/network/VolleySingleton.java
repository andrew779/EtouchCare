/**
 * TeamOne
 */
package com.app.etouchcare.network;


import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.app.etouchcare.activity.MyApplication;

/**
 * Created by wenzhongzheng on 2016-11-19.
 */

public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
    }

    public static VolleySingleton getInstance(){
        if(sInstance == null){
            sInstance = new VolleySingleton();
        }

        return sInstance;
    }
    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }


}
