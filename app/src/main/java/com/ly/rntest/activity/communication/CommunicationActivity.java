package com.ly.rntest.activity.communication;

import android.content.Intent;
import com.ly.rntest.activity.BaseActivity;
import java.util.concurrent.ArrayBlockingQueue;

public class CommunicationActivity extends BaseActivity {
    //构建一个阻塞的单一数据的队列
    public static ArrayBlockingQueue<String> mQueue = new ArrayBlockingQueue<String>(1);
    /**
     * 打开 带返回的Activity
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 200) {
            String result = data.getStringExtra("three_result");
            if (result != null && !result.equals("")) {
                mQueue.add(result);
            } else {
                mQueue.add("无数据啦");
            }
        } else {
            mQueue.add("没有回调...");
        }
    }

    @Override
    protected String getModuleName() {
        return "MCommunication";
    }
}
