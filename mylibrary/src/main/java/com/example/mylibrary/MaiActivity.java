package com.example.mylibrary;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by dkhs on 2017/10/30.
 */

public class MaiActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);
        Button button= (Button) findViewById(R.id.btn_log);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        logE();
    }

    private void logE(){
        UserBean bean=new UserBean();
        bean.setName("hello");
        LogUtils.e(bean.getName());
    }
}
