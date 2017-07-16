package com.jarvis.zhoufeng.jarvis.account;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.View;

import com.jarvis.zhoufeng.jarvis.R;

/**
 * Created by zhoufeng on 2017/7/14.
 */

public class MainActivity extends Activity {
    private ConstraintLayout navigationbarRight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iniitView();
    }

    private void iniitView() {
        navigationbarRight = findViewById(R.id.layout_right);
        navigationbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
    }
}
