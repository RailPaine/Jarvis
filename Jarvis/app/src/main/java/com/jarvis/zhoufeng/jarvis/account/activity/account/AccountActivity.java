package com.jarvis.zhoufeng.jarvis.account.activity.account;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jarvis.zhoufeng.jarvis.R;
import com.jarvis.zhoufeng.jarvis.account.util.sp.SPKeyConst;
import com.jarvis.zhoufeng.jarvis.account.util.sp.SPUtil;

/**
 * Created by Mypc on 2017/7/15 0015.
 */

public class AccountActivity extends Activity {

    private TextView textName;
    private TextView textScore;
    private ImageView imagePhoto;
    SPUtil spUtil = SPUtil.getSPUtil();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        initScore();
    }


    private void initView() {
        textName = (TextView) findViewById(R.id.text_base_image_text_item);
        textName.setText("breakingsword");
        imagePhoto = (ImageView) findViewById(R.id.image_base_image_text_item);
        imagePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(AccountActivity.this);

                final View viewDialog = li.inflate(R.layout.dialog_score, null);
                AlertDialog dlg = new AlertDialog.Builder(AccountActivity.this)

                        .setTitle("设置积分")
                        .setView(viewDialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText editScore = (EditText) viewDialog.findViewById(R.id.edit_dialog_set_score);
                                spUtil.setNormalData(AccountActivity.this, SPKeyConst.ACCOUNT_SCORE, Integer.parseInt(editScore.getText().toString()));
                                initScore();
                            }
                        })
                        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            }
                        })
                        .create();
                dlg.show();

            }
        });
        textScore = (TextView) findViewById(R.id.text_base_text_item);
    }

    private void initScore() {
        int score = (int) spUtil.getNormalData(this, SPKeyConst.ACCOUNT_SCORE, -999999);
        if (score == -999999) {
            spUtil.setNormalData(this, SPKeyConst.ACCOUNT_SCORE, 0);
        } else {
            textScore.setText(score + "");
        }
    }
}
