package com.jarvis.zhoufeng.jarvis.account;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class MissionList implements Serializable{
    private static final long serialVersionUID = 1000001;
    private List<Mission> missionList = new ArrayList<>();

    public List<Mission> getMissionList() {
        return missionList;
    }

    public void setMissionList(List<Mission> missionList) {
        this.missionList = missionList;
    }
}
