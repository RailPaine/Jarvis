package com.jarvis.zhoufeng.jarvis.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jarvis.zhoufeng.jarvis.R;

import java.util.ArrayList;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class MissionAdapter extends ArrayAdapter<Mission> {
    public SPUtil spUtil = SPUtil.getSPUtil();
    private LayoutInflater inflater;
    private Context mContext;
    private int mResourceId;

    public MissionAdapter(Context context, int resourceId) {
        super(context, resourceId);
        inflater = LayoutInflater.from(context);
        mContext = context;
        mResourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_mission, null);
            viewholder = new ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (ViewHolder) convertView.getTag();
        }
        final Mission mission = getItem(position);
        viewholder.textMissionName.setText(mission.getMissionName());
        viewholder.textMissionScore.setText(mission.getMissionScore());
        switch (mission.getMissionState()) {
            case Mission.MISSION_STATE_OPEN:
                viewholder.btnMissionState.setText(R.string.mission_state_open);
                break;
            case Mission.MISSION_STATE_ON:
                viewholder.btnMissionState.setText(R.string.mission_state_on);
                break;
            case Mission.MISSION_STATE_COMPLETE:
                viewholder.btnMissionState.setText(R.string.mission_state_complete);
                break;
        }
        viewholder.btnMissionState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (mission.getMissionState()) {
                    case Mission.MISSION_STATE_OPEN:
                        mission.setMissionState(Mission.MISSION_STATE_ON);
                        break;
                    case Mission.MISSION_STATE_ON:
                        mission.setMissionState(Mission.MISSION_STATE_COMPLETE);
                        int score = (int) spUtil.getNormalData(mContext, SPKeyConst.ACCOUNT_SCORE, -999999) + Integer.parseInt(mission.getMissionScore());
                        spUtil.setNormalData(mContext, SPKeyConst.ACCOUNT_SCORE, score);
                        break;
                    case Mission.MISSION_STATE_COMPLETE:
                        removeMissionItem(mission);
                        remove(mission);
                        break;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {

        TextView textMissionName;

        TextView textMissionScore;

        Button btnMissionState;

        ViewHolder(View view) {
            textMissionName = (TextView) view.findViewById(R.id.text_mission_name);
            textMissionScore = (TextView) view.findViewById(R.id.text_mission_score);
            btnMissionState = (Button) view.findViewById(R.id.btn_mission_state);
        }
    }

    private void removeMissionItem(Mission mission) {
        MissionList missionListRemove = new MissionList();
        MissionList missionList = (MissionList) spUtil.getObjectData(SPKeyConst.MISSION_LIST);
        for (Mission missionItem : missionList.getMissionList()) {
            if (missionItem.getMissionName().equals(mission.getMissionName())) {
                missionListRemove.getMissionList().add(missionItem);
            }
        }
        missionList.getMissionList().removeAll(missionListRemove.getMissionList());
        spUtil.setObjectData(SPKeyConst.MISSION_LIST, missionList);
    }
}
