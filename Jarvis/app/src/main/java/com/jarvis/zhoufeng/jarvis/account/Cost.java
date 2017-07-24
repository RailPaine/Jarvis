package com.jarvis.zhoufeng.jarvis.account;

import java.io.Serializable;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class Cost implements Serializable{
    private static final long serialVersionUID = 1000003;
    public static final int COST_STATE_OPEN = 0;
    public static final int COST_STATE_COMPLETE = 1;
    private String costName;
    private String costScore;
    private int costState;

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostScore() {
        return costScore;
    }

    public void setCostScore(String costScore) {
        this.costScore = costScore;
    }

    public int getCostState() {
        return costState;
    }

    public void setCostState(int costState) {
        this.costState = costState;
    }

}
