package com.ly.rntest.module.intent;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.JSApplicationIllegalArgumentException;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.ly.rntest.activity.communication.CommunicationActivity;

public class IntentModule  extends ReactContextBaseJavaModule {

    public IntentModule(ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @Override
    public String getName() {
        return "IntentModule";
    }

    /**
     * Activtiy跳转到JS页面，传输数据
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void dataToJS(Callback successBack, Callback errorBack){
        try{
            Activity currentActivity = getCurrentActivity();
            String result = currentActivity.getIntent().getStringExtra("data");
            if (TextUtils.isEmpty(result)){
                result = "没有数据";
            }
            successBack.invoke(result);
        }catch (Exception e){
            errorBack.invoke(e.getMessage());
        }
    }
    /**
     * 从JS页面跳转到原生activity   同时也可以从JS传递相关数据到原生
     * @param name
     * @param params
     */
    @ReactMethod
    public void startActivityFromJS(String name, String params){
        try{
            Log.i("ly","startActivityFromJS");
            Activity currentActivity = getCurrentActivity();
            if(null!=currentActivity){
                Class toActivity = Class.forName(name);
                Intent intent = new Intent(currentActivity,toActivity);
                intent.putExtra("params", params);
                currentActivity.startActivity(intent);
            }
        }catch(Exception e){
            throw new JSApplicationIllegalArgumentException(
                    "不能打开Activity : "+e.getMessage());
        }
    }

    /**
     * 从JS页面跳转到Activity界面，并且等待从Activity返回的数据给JS
     * @param className
     * @param successBack
     * @param errorBack
     */
    @ReactMethod
    public void startActivityFromJSGetResult(String className, int requestCode, Callback successBack, Callback errorBack){
            try {
                Activity currentActivity=getCurrentActivity();
                if(currentActivity!=null) {
                    Class toActivity = Class.forName(className);
                    Intent intent = new Intent(currentActivity,toActivity);
                    currentActivity.startActivityForResult(intent,requestCode);
                    //进行回调数据
                    successBack.invoke(CommunicationActivity.mQueue.take());
                }
            } catch (Exception e) {
                errorBack.invoke(e.getMessage());
                e.printStackTrace();
            }

    }
}
