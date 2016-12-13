package com.ly.rntest.activity;


import com.facebook.react.ReactActivity;

import javax.annotation.Nullable;

public class ReactBaseActivity extends ReactActivity {

    @Nullable
    @Override
    protected String getMainComponentName() {
        return "MBase";
    }
}