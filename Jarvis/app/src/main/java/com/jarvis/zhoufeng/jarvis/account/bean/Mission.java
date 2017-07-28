package com.jarvis.zhoufeng.jarvis.account.bean;

import java.io.Serializable;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class Mission implements Serializable {
    private static final long serialVersionUID = 1000002;
    public static final int MISSION_STATE_OPEN = 0;
    public static final int MISSION_STATE_ON = 1;
    public static final int MISSION_STATE_COMPLETE = 2;
    public static final int MISSION_STATE_NOT_COMPLETE = 3;


    public static final int MISSION_TYPE_NORMAL = 11;//普通任务，只有一次。
    public static final int MISSION_TYPE_REPEAT = 12;//重复任务
    public static final int MISSION_TYPE_CYCLE_DAY = 13;//周期任务，每日，每周，每月，每年。
    public static final int MISSION_TYPE_CYCLE_WEEK = 14;
    public static final int MISSION_TYPE_CYCLE_MONTH = 15;
    public static final int MISSION_TYPE_CYCLE_YEAR = 16;
    public static final int MISSION_TYPE_TIMES = 17;//有次数限制的任务

    private String missionName;
    private String missionScore;
    private int missionState;

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionScore() {
        return missionScore;
    }

    public void setMissionScore(String missionScore) {
        this.missionScore = missionScore;
    }

    public int getMissionState() {
        return missionState;
    }

    public void setMissionState(int missionState) {
        this.missionState = missionState;
    }

}
