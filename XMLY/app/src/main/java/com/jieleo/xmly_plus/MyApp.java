package com.jieleo.xmly_plus;

import android.app.ActivityManager;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMOptions;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechUtility;


import java.util.Iterator;
import java.util.List;


import cn.jpush.android.api.JPushInterface;

import cn.sharesdk.framework.ShareSDK;

import cn.smssdk.SMSSDK;

/**
 * Created by liuHao on 17/3/9.
 */
public class MyApp extends Application {

    private static Context context;
    private static final String TAG = "MyApp";
    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
        ShareSDK.initSDK(context);
        SpeechUtility.createUtility(this, SpeechConstant.APPID+"=58ca851b");

        initEasamob();

        JPushInterface.setDebugMode(true);
        JPushInterface.init(context);

    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    public static Context getContext() {
        return context;
    }


    private  void initEasamob(){

        EMOptions options = new EMOptions();
// 默认添加好友时，是不需要验证的，改成需要验证
        options.setAcceptInvitationAlways(false);
//初始化


        int pid = android.os.Process.myPid();
        String processAppName = getAppName(pid);
// 如果APP启用了远程的service，此application:onCreate会被调用2次
// 为了防止环信SDK被初始化2次，加此判断会保证SDK被初始化1次
// 默认的APP会在以包名为默认的process name下运行，如果查到的process name不是APP的process name就立即返回

        if (processAppName == null ||!processAppName.equalsIgnoreCase(this.getPackageName())) {
            Log.e(TAG, "enter the service process!");

            // 则此application::onCreate 是被service 调用的，直接返回
            return;
        }



        EMClient.getInstance().init(context, options);
//在做打包混淆时，关闭debug模式，避免消耗不必要的资源
        EMClient.getInstance().setDebugMode(false);


    }
    private String getAppName(int pID) {
        String processName = null;
        ActivityManager am = (ActivityManager) this.getSystemService(ACTIVITY_SERVICE);
        List l = am.getRunningAppProcesses();
        Iterator i = l.iterator();
        PackageManager pm = this.getPackageManager();
        while (i.hasNext()) {
            ActivityManager.RunningAppProcessInfo info = (ActivityManager.RunningAppProcessInfo) (i.next());
            try {
                if (info.pid == pID) {
                    processName = info.processName;
                    return processName;
                }
            } catch (Exception e) {
                // Log.d("Process", "Error>> :"+ e.toString());
            }
        }
        return processName;
    }


}
