package com.ly.rntest.activity;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.Toast;

import com.facebook.react.JSCConfig;
import com.facebook.react.ReactActivity;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactRootView;
import com.facebook.react.common.LifecycleState;
import com.facebook.react.cxxbridge.JSBundleLoader;
import com.facebook.react.cxxbridge.JSCJavaScriptExecutor;
import com.facebook.react.cxxbridge.JavaScriptExecutor;
import com.facebook.react.modules.core.DefaultHardwareBackBtnHandler;
import com.facebook.react.shell.MainReactPackage;
import com.ly.rntest.util.FileUtil;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.Nullable;


/**
 * Created by Administrator on 2016/12/16 0016.
 */
public class ReactUpdateActivity extends ReactActivity {


    private static final String TAG = "ly";

    public static final String JS_BUNDLE_REMOTE_URL = "http://192.168.65.77:8080/JavawebTest/ResponseTest";
    public static final String JS_BUNDLE_LOCAL_FILE = "index.android.bundle";
    public static final String JS_BUNDLE_LOCAL_PATH = Environment.getExternalStorageDirectory().toString()
            + File.separator + JS_BUNDLE_LOCAL_FILE;

    private ReactInstanceManager mReactInstanceManager;
    private ReactRootView mReactRootView;
    private CompleteReceiver mDownloadCompleteReceiver;
    private long mDownloadId;

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MUpdate";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initDownloadManager();

        mReactInstanceManager=getReactInstanceManager();
        Handler h=new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                updateBundle();
            }
        },1000);

    }

    private void initDownloadManager() {
        mDownloadCompleteReceiver = new CompleteReceiver();
        registerReceiver(mDownloadCompleteReceiver, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mDownloadCompleteReceiver);
    }

    private void updateBundle() {

        // Should add version check here, if bundle file
        // is the newest , we do not need to update

        File file = new File(JS_BUNDLE_LOCAL_PATH);
        if(file.exists()){
            FileUtil.deleteFile(file);
            Log.i(TAG, "delete old version");
        }

        //Toast.makeText(BaseReactActivity.this, "Start downloading update", Toast.LENGTH_SHORT).show();

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(JS_BUNDLE_REMOTE_URL));
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI);
        request.setDestinationUri(Uri.parse("file://" + JS_BUNDLE_LOCAL_PATH));
        DownloadManager dm = (DownloadManager) getSystemService(Context.DOWNLOAD_SERVICE);
        mDownloadId = dm.enqueue(request);

        Log.i(TAG, "start download remote bundle");
    }

    private class CompleteReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            long completeDownloadId = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, -1);
            if(completeDownloadId == mDownloadId){
                onJSBundleLoadedFromServer();
            }
        }
    };

    private void onJSBundleLoadedFromServer() {
        File file = new File(JS_BUNDLE_LOCAL_PATH);
        if(!file.exists()){
            Log.i(TAG, "download error, check URL or network state");
            return;
        }

        Log.i(TAG, "download success, reload js bundle");

        Toast.makeText(ReactUpdateActivity.this, "Downloading complete", Toast.LENGTH_SHORT).show();
//        try {
//            Class<?> RIManagerClazz = mReactInstanceManager.getClass();
//            Method method = RIManagerClazz.getDeclaredMethod("recreateReactContextInBackground",
//                    JavaScriptExecutor.class, JSBundleLoader.class);
//            method.setAccessible(true);
//            method.invoke(mReactInstanceManager,
//                    new JSCJavaScriptExecutor.Factory(JSCConfig.EMPTY.getConfigMap()),
//                    JSBundleLoader.createFileLoader(JS_BUNDLE_LOCAL_PATH));
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        } catch (IllegalArgumentException e) {
//            e.printStackTrace();
//        }
    }

    @Override
    public boolean onKeyUp(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU && mReactInstanceManager != null) {
            mReactInstanceManager.showDevOptionsDialog();
            return true;
        }
        return super.onKeyUp(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        if (mReactInstanceManager != null) {
            mReactInstanceManager.onBackPressed();
        } else {
            super.onBackPressed();
        }
    }

}
