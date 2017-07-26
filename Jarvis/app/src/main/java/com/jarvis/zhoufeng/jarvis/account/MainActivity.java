package com.jarvis.zhoufeng.jarvis.account;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.jarvis.zhoufeng.jarvis.R;

/**
 * Created by zhoufeng on 2017/7/14.
 */

public class MainActivity extends Activity {

    private ConstraintLayout header;
    private ImageView headerBack;
    private TextView headerTitle;


    private ConstraintLayout navigationbar;
    private ConstraintLayout navigationbarLeft;
    private ImageView navigationbarLeftImage;
    private TextView navigationbarLeftText;
    private ConstraintLayout navigationbarMiddle;
    private ImageView navigationbarMiddleImage;
    private TextView navigationbarMiddleText;
    private ConstraintLayout navigationbarRight;
    private ImageView navigationbarRightImage;
    private TextView navigationbarRightText;

    private TextView textMission;
    private TextView textCost;

    private ListView listMission;
    private ListView listCost;

    private MissionAdapter missionAdapter;
    private CostAdapter costAdapter;
    private MissionList missionList;
    private CostList costList;

    private SPUtil spUtil;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        initList();
        initAccount();
    }

    private void initView() {
        header = findViewById(R.id.base_header_activity_main);
        headerBack = header.findViewById(R.id.image_base_header_back);
        headerBack.setVisibility(View.GONE);
        headerTitle = header.findViewById(R.id.text_base_header_title);
        headerTitle.setText(R.string.market);

        navigationbar = findViewById(R.id.base_navigation_activity_main);

        navigationbarLeft = navigationbar.findViewById(R.id.layout_left);
        navigationbarLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        navigationbarLeftImage = navigationbarLeft.findViewById(R.id.image_base_image_text_item);
        navigationbarLeftImage.setImageResource(R.drawable.navigationbar_market);
        navigationbarLeftText = navigationbarLeft.findViewById(R.id.text_base_image_text_item);
        navigationbarLeftText.setText(R.string.market);

        navigationbarMiddle = navigationbar.findViewById(R.id.layout_middle);
        navigationbarMiddle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        navigationbarMiddleImage = navigationbarMiddle.findViewById(R.id.image_base_image_text_item);
        navigationbarMiddleImage.setImageResource(R.drawable.navigationbar_calander);
        navigationbarMiddleText = navigationbarMiddle.findViewById(R.id.text_base_image_text_item);
        navigationbarMiddleText.setText(R.string.calander);

        navigationbarRight = navigationbar.findViewById(R.id.layout_right);
        navigationbarRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, AccountActivity.class);
                startActivity(intent);
            }
        });
        navigationbarRightImage = navigationbarRight.findViewById(R.id.image_base_image_text_item);
        navigationbarRightImage.setImageResource(R.drawable.navigationbar_account);
        navigationbarRightText = navigationbarRight.findViewById(R.id.text_base_image_text_item);
        navigationbarRightText.setText(R.string.account);


        textMission = (TextView) findViewById(R.id.text_mission);
        textMission.setText(R.string.mission);
        textCost = (TextView) findViewById(R.id.text_cost);
        textCost.setText(R.string.cost);

        listMission = (ListView) findViewById(R.id.list_mission);
        listCost = (ListView) findViewById(R.id.list_cost);
        listCost.setVisibility(View.GONE);
        textMission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listMission.getVisibility() == View.GONE) {
                    listMission.setVisibility(View.VISIBLE);
                    listCost.setVisibility(View.GONE);
                }
            }
        });

        textCost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listCost.getVisibility() == View.GONE) {
                    listCost.setVisibility(View.VISIBLE);
                    listMission.setVisibility(View.GONE);
                }
            }
        });
    }

    private void initData() {
        spUtil = SPUtil.getSPUtil();
        spUtil.init(this);

        missionList = new MissionList();
        costList = new CostList();
        missionList = (MissionList) spUtil.getObjectData(SPKeyConst.MISSION_LIST);
        costList = (CostList) spUtil.getObjectData(SPKeyConst.COST_LIST);

        if (missionList == null) {
            missionList = new MissionList();
            spUtil.setObjectData(SPKeyConst.MISSION_LIST, missionList);
         }else{
            Log.e("list",((MissionList) spUtil.getObjectData(SPKeyConst.MISSION_LIST)).getMissionList().size()+"");

        }
        if (costList == null) {
            costList = new CostList();
            spUtil.setObjectData(SPKeyConst.COST_LIST, costList);
        }
    }

    private void initList() {
        missionAdapter = new MissionAdapter(this, R.layout.list_item_mission);
        missionAdapter.addAll(missionList.getMissionList());
        listMission.addFooterView(getMissionFooterView());
        listMission.setAdapter(missionAdapter);

        costAdapter = new CostAdapter(this, R.layout.list_item_cost);
        costAdapter.addAll(costList.getCostList());
        listCost.addFooterView(getCostFooterView());
        listCost.setAdapter(costAdapter);

    }

    private View getMissionFooterView() {


        TextView textView = new TextView(this);
        textView.setText("添加任务");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(MainActivity.this);

                final View viewDialog = li.inflate(R.layout.dialog_add, null);
                AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)

                        .setTitle("添加任务")
                        .setView(viewDialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText editName = (EditText) viewDialog.findViewById(R.id.edit_dialog_name);
                                EditText editScore = (EditText) viewDialog.findViewById(R.id.edit_dialog_score);
                                Mission mission = new Mission();
                                mission.setMissionName(editName.getText().toString());
                                mission.setMissionScore(editScore.getText().toString());
                                mission.setMissionState(Mission.MISSION_STATE_OPEN);
                                missionAdapter.add(mission);
                                missionList.getMissionList().add(mission);
                                spUtil.setObjectData(SPKeyConst.MISSION_LIST, missionList);
                                missionAdapter.notifyDataSetChanged();
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
        return textView;
    }

    private View getCostFooterView() {


        TextView textView = new TextView(this);
        textView.setText("添加花费");
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(MainActivity.this);

                final View viewDialog = li.inflate(R.layout.dialog_add, null);
                AlertDialog dlg = new AlertDialog.Builder(MainActivity.this)

                        .setTitle("添加花费")
                        .setView(viewDialog)
                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                EditText editName = (EditText) viewDialog.findViewById(R.id.edit_dialog_name);
                                EditText editScore = (EditText) viewDialog.findViewById(R.id.edit_dialog_score);
                                Cost cost = new Cost();
                                cost.setCostName(editName.getText().toString());
                                cost.setCostScore(editScore.getText().toString());
                                cost.setCostState(Cost.COST_STATE_OPEN);
                                costAdapter.add(cost);
                                costList.getCostList().add(cost);
                                spUtil.setObjectData(SPKeyConst.COST_LIST, costList);
                                costAdapter.notifyDataSetChanged();
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
        return textView;
    }

    private void initAccount() {
        Log.e("sp",spUtil.getNormalData(this, SPKeyConst.ACCOUNT_SCORE, -999999)+"");
        if ((int) spUtil.getNormalData(this, SPKeyConst.ACCOUNT_SCORE, -999999) == -999999) {
            spUtil.setNormalData(this, SPKeyConst.ACCOUNT_SCORE, 0);
        }
    }
}
