package com.jieleo.xmly_plus;

import android.app.Application;
import android.content.Context;

/**
 * Created by liuHao on 17/3/9.
 */
public  class MyApp extends Application{

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getContext(){
        return context;
    }
}
