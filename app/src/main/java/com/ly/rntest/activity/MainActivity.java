package com.ly.rntest.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.ly.rntest.R;
import com.ly.rntest.activity.communication.CommunicationActivity;
import com.ly.rntest.util.PermissionUtil;

/**
 * 加入新项流程
 * 1 在MainActivity中注册，在activity_main中注册
 * 2 创建**Activity，在AndroidManifest中注册
 * 3 创建**.js,导出，并在index.android.js中注册
 */
public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.rn_test).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactTestActivity.class));
            }
        });
        findViewById(R.id.rn_base).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactBaseActivity.class));
            }
        });
        findViewById(R.id.rn_animated).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactAnimActivity.class));
            }
        });
        findViewById(R.id.rn_layout_animation).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactLayoutAnimActivity.class));
            }
        });
        findViewById(R.id.rn_communication).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CommunicationActivity.class));
            }
        });
        findViewById(R.id.rn_net).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactNetActivity.class));
            }
        });
        findViewById(R.id.rn_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,ReactUpdateActivity.class));
            }
        });
        if(PermissionUtil.hasReadExternalStoragePermission(this)){
            Log.i("ly","has ExternalStoragePermission");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case PermissionUtil.REQUEST_READ_EXTERNAL_STORAGE:
                if(grantResults.length>0) {
                    if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                        Log.i("ly","success get permission");

                    } else {
                        Log.i("ly","not get permission");

                    }
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        System.exit(0);
    }
}
