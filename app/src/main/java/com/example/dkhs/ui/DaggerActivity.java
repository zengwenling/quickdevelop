package com.example.dkhs.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.dkhs.commonlib.ui.BaseDKHSActivity;
import com.example.dkhs.R;
import com.example.dkhs.dagger.ZhaiNan;
import com.example.dkhs.dagger.component.DaggerPlatform;
import com.example.dkhs.dagger.component.DaggerWaiMaiPingTai;

/**
 * Created by lijunjia on 2018/8/28.
 */

public class DaggerActivity extends BaseDKHSActivity {
    Button mButton;
    Button btn_test2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger);
        mButton = (Button) findViewById(R.id.btn_test);
        btn_test2 = (Button) findViewById(R.id.btn_test2);
        final ZhaiNan zainan = DaggerPlatform.builder().build().waimain();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DaggerActivity.this, zainan.eat(), Toast.LENGTH_LONG).show();
            }
        });
        final ZhaiNan zainan2 = DaggerWaiMaiPingTai.builder().build().waimai();
        btn_test2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DaggerActivity.this, zainan2.eat(), Toast.LENGTH_LONG).show();
            }
        });


    }
}
