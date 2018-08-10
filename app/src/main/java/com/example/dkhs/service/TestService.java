package com.example.dkhs.service;

import android.app.Notification;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.example.dkhs.ui.PixelActivity;

/**
 * Created by dkhs on 2018/4/9.
 */

public class TestService extends Service {

    private static final int GRAY_SERVICE_ID = 1001;

    private LockReceiver lockReceiver;
    private static PixelActivity mActivity;

    public static void startTestService(Context context) {
        Intent intent = new Intent(context, TestService.class);
        context.startService(intent);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerReceiver(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unRegisterReceiver(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //设置service为前台服务，提高优先级
        if (Build.VERSION.SDK_INT < 18) {
            //Android4.3以下 ，此方法能有效隐藏Notification上的图标
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else if (Build.VERSION.SDK_INT > 18 && Build.VERSION.SDK_INT < 25) {
            //Android4.3 - Android7.0，此方法能有效隐藏Notification上的图标
            Intent innerIntent = new Intent(this, GrayInnerService.class);
            startService(innerIntent);
            startForeground(GRAY_SERVICE_ID, new Notification());
        } else {
            //Android7.1 google修复了此漏洞，暂无解决方法（现状：Android7.1以上app启动后通知栏会出现一条"正在运行"的通知消息）
            startForeground(GRAY_SERVICE_ID, new Notification());
        }
        return START_STICKY;
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static class GrayInnerService extends Service {

        @Nullable
        @Override
        public IBinder onBind(Intent intent) {
            return null;
        }

        @Override
        public int onStartCommand(Intent intent, int flags, int startId) {
            startForeground(GRAY_SERVICE_ID, new Notification());
            stopForeground(true);
            stopSelf();
            return super.onStartCommand(intent, flags, startId);
        }
    }

    class LockReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()) {
                case Intent.ACTION_SCREEN_OFF:
                    startLiveActivity(context);
                    break;
                case Intent.ACTION_USER_PRESENT:
                    destroyLiveActivity();
                    break;
            }
        }
    }

    private void startLiveActivity(Context context) {
        Intent intent = new Intent(context, PixelActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private void destroyLiveActivity() {
        if (mActivity != null) {
            mActivity.finish();
            mActivity = null;
        }
    }

    /**
     * 传入1像素的透明Activity实例
     *
     * @param activity
     */
    public static void setKeepLiveActivity(PixelActivity activity) {
        mActivity = activity;
    }

    /**
     * 注册锁屏/解锁广播
     *
     * @param context
     */
    public void registerReceiver(Context context) {
        lockReceiver = new LockReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_USER_PRESENT);
        context.registerReceiver(lockReceiver, filter);
    }

    /**
     * 注销锁屏/解锁广播
     *
     * @param context
     */
    public void unRegisterReceiver(Context context) {
        if (lockReceiver != null) {
            context.unregisterReceiver(lockReceiver);
        }
    }

}
