package com.jarvis.zhoufeng.jarvis.account;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class Mission {
    public static final int MISSION_STATE_OPEN = 0;
    public static final int MISSION_STATE_ON = 1;
    public static final int MISSION_STATE_COMPLETE = 2;
    public static final int MISSION_STATE_NOT_COMPLETE = 3;

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
