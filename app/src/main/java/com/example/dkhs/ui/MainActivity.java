package com.example.dkhs.ui;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Process;
import android.provider.MediaStore;
import android.support.v4.view.LayoutInflaterCompat;
import android.support.v4.view.LayoutInflaterFactory;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.util.AttributeSet;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.dkhs.commonlib.ui.BaseDKHSActivity;
import com.dynamic.it.IDynamic;
import com.example.dkhs.R;
import com.example.dkhs.bean.iface.Buy;
import com.example.dkhs.bean.User;
import com.example.dkhs.bean.UserDynamicProxy;
import com.example.dkhs.bean.Consumer;
import com.example.dkhs.bean.Producer;
import com.example.dkhs.service.TestService;
import com.example.mylibrary.TestPrint;

import java.io.File;
import java.lang.reflect.Proxy;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;

import dalvik.system.DexClassLoader;

public class MainActivity extends BaseDKHSActivity implements View.OnClickListener {
    public static final String TAG = MainActivity.class.getSimpleName();
    private Typeface typeface;
    private String mCurrentPhotoPath;
    public static final int REQUEST_CODE_TAKE_PHOTO = 1001;
    private ImageView iv_photo;
    private ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue(1);
    private IDynamic lib;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (typeface == null) {
            typeface = Typeface.createFromAsset(getAssets(), "li.ttf");
        }
        LayoutInflaterCompat.setFactory(LayoutInflater.from(this), new LayoutInflaterFactory() {
            @Override
            public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
                AppCompatDelegate delegate = getDelegate();
                View view = delegate.createView(parent, name, context, attrs);
                if (view != null && view instanceof TextView) {
                    TextView textView = (TextView) view;
                    textView.setTypeface(typeface);
                }
                return view;
            }
        });
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_call = (Button) findViewById(R.id.btn_call);
        btn_call.setOnClickListener(this);
        Button btn_jxjava = (Button) findViewById(R.id.btn_jxjava);
        btn_jxjava.setOnClickListener(this);
        Log.e(TAG, "button:" + btn_call.getClass().getName());
        User user = new User();
        Buy userProxy = (Buy) Proxy.newProxyInstance(User.class.getClassLoader(), User.class.getInterfaces(), new UserDynamicProxy(user));
        userProxy.buyHouse(1000);
        String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        iv_photo = (ImageView) findViewById(R.id.iv_photo);
        Glide.with(this).load(url).into(iv_photo);
        testClassLoaderName();
    }

    private void testClassLoaderName() {
        Log.i("DEMO", "Context的类加载加载器:" + Context.class.getClassLoader());
        Log.i("DEMO", "ListView的类加载器:" + ListView.class.getClassLoader());
        Log.i("DEMO", "应用程序默认加载器:" + getClassLoader());
        Log.i("DEMO", "系统类加载器:" + ClassLoader.getSystemClassLoader());
        Log.i("DEMO", "系统类加载器和Context的类加载器是否相等:" + (Context.class.getClassLoader() == ClassLoader.getSystemClassLoader()));
        Log.i("DEMO", "系统类加载器和应用程序默认加载器是否相等:" + (getClassLoader() == ClassLoader.getSystemClassLoader()));

        Log.i("DEMO", "打印应用程序默认加载器的委派机制:");
        ClassLoader classLoader = getClassLoader();
        while (classLoader != null) {
            Log.i("DEMO", "类加载器:" + classLoader);
            classLoader = classLoader.getParent();
        }

        Log.i("DEMO", "打印系统加载器的委派机制:");
        classLoader = ClassLoader.getSystemClassLoader();
        while (classLoader != null) {
            Log.i("DEMO", "类加载器:" + classLoader);
            classLoader = classLoader.getParent();
        }

    }


    private void multiThreadDemo() {
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Producer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
        new Thread(new Consumer(blockingQueue)).start();
    }


    @Override
    public void onClick(View view) {
        int id=view.getId();
        switch (id){
            case R.id.btn_call:
                multiThreadDemo();
                break;
            case R.id.btn_jxjava:
                startActivity(new Intent(this,RxjavaActivity.class));
                break;
        }
    }

    private void startTestService() {
        TestService.startTestService(this);
    }

    private void testLibrayJar() {
        new TestPrint().sout();
    }


    public void takePhotoNoCompress() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {

            String filename = new SimpleDateFormat("yyyyMMdd-HHmmss", Locale.CHINA)
                    .format(new Date()) + ".png";
            File file = new File(Environment.getExternalStorageDirectory(), filename);
            mCurrentPhotoPath = file.getAbsolutePath();

            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(takePictureIntent, REQUEST_CODE_TAKE_PHOTO);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == REQUEST_CODE_TAKE_PHOTO) {
            iv_photo.setImageBitmap(BitmapFactory.decodeFile(mCurrentPhotoPath));
        }
    }

    private long mExitTime;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN) {
            if ((System.currentTimeMillis() - mExitTime) > 2000) {
                Toast.makeText(getApplicationContext(), getResources().getString(R.string.toast_click_once_more), Toast.LENGTH_SHORT).show();
                mExitTime = System.currentTimeMillis();
                return true;
            } else {
                Process.killProcess(Process.myPid());
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
