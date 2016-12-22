package com.ly.rntest.app;

import android.app.Application;
import android.os.Environment;

import com.facebook.react.ReactApplication;
import com.facebook.react.ReactInstanceManager;
import com.facebook.react.ReactNativeHost;
import com.facebook.react.ReactPackage;
import com.facebook.react.shell.MainReactPackage;
import com.ly.rntest.BuildConfig;
import com.ly.rntest.activity.MainActivity;
import com.ly.rntest.module.intent.IntentReactPackage;
import com.ly.rntest.module.toast.MToastReactPackage;

import java.io.File;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class MainApplication extends Application implements ReactApplication {
    private ReactNativeHost mReactNativeHost;
    public static final String JS_BUNDLE_LOCAL_FILE = "index.android.bundle";
    public static final String JS_BUNDLE_LOCAL_PATH = Environment.getExternalStorageDirectory().toString() + File.separator + JS_BUNDLE_LOCAL_FILE;

//    /**
//     * 已由内部维护，不建议单独使用
//     */
//    @Deprecated
//    public ReactInstanceManager getReactInstanceManager() {
//        return mReactInstanceManager;
//    }

    @Override
    public void onCreate() {
        super.onCreate();
        initRN();
    }

    private void initRN() {
        mReactNativeHost = new ReactNativeHost(this) {

            @Override
            protected boolean getUseDeveloperSupport() {
                return BuildConfig.DEBUG;
            }

            @Override
            protected String getBundleAssetName() {
                return super.getBundleAssetName();
            }

            @Override
            protected String getJSBundleFile() {
                return JS_BUNDLE_LOCAL_PATH;
            }

            @Override
            protected String getJSMainModuleName() {
                return super.getJSMainModuleName();
            }

            @Override
            protected List<ReactPackage> getPackages() {
                return Arrays.asList(new MainReactPackage(),
                        new MToastReactPackage(),
                        new IntentReactPackage()
                      );
            }
        };
    }

    @Override
    public ReactNativeHost getReactNativeHost() {
        return mReactNativeHost;
    }
}
