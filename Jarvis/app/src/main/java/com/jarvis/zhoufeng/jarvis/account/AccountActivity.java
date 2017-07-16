package com.jarvis.zhoufeng.jarvis.account;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.jarvis.zhoufeng.jarvis.R;

/**
 * Created by Mypc on 2017/7/15 0015.
 */

public class AccountActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        firstSp();
        initView();
    }

    private void firstSp() {
        SharedPreferences.Editor editor = SharepreferenceUtil.Save(this, getString(R.string.account_score), Context.MODE_PRIVATE);
        if (editor == null) {
            editor.putInt(getString(R.string.score), 0);
            editor.commit();
        }
    }

    private void initView() {

    }
}
