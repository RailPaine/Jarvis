package com.jarvis.zhoufeng.jarvis.account;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.jarvis.zhoufeng.jarvis.R;

/**
 * Created by Mypc on 2017/7/22 0022.
 */

public class CostAdapter extends ArrayAdapter<Cost> {
    public SPUtil spUtil = SPUtil.getSPUtil();
    private LayoutInflater inflater;
    private Context mContext;
    private int mResourceId;

    public CostAdapter(Context context, int resourceId) {
        super(context, resourceId);
        inflater = LayoutInflater.from(context);
        mContext = context;
        mResourceId = resourceId;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final CostAdapter.ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_cost, null);
            viewholder = new CostAdapter.ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (CostAdapter.ViewHolder) convertView.getTag();
        }
        final Cost cost = getItem(position);
        viewholder.textCostName.setText(cost.getCostName());
        viewholder.textCostScore.setText(cost.getCostScore());
        switch (cost.getCostState()) {
            case Cost.COST_STATE_OPEN:
                viewholder.btnCostState.setText(R.string.cost_state_open);
                break;
            case Cost.COST_STATE_COMPLETE:
                viewholder.btnCostState.setText(R.string.cost_state_complete);
                break;
        }
        viewholder.btnCostState.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (cost.getCostState()) {
                    case Cost.COST_STATE_OPEN:
                        cost.setCostState(Cost.COST_STATE_COMPLETE);
                        int score = (int) spUtil.getNormalData(mContext, SPKeyConst.ACCOUNT_SCORE, -999999) - Integer.parseInt(cost.getCostScore());
                        spUtil.setNormalData(mContext, SPKeyConst.ACCOUNT_SCORE, score);
                        break;
                    case Cost.COST_STATE_COMPLETE:
                        removeCostItem(cost);
                        remove(cost);
                        break;
                }
                notifyDataSetChanged();
            }
        });
        return convertView;
    }

    static class ViewHolder {

        TextView textCostName;

        TextView textCostScore;

        Button btnCostState;

        ViewHolder(View view) {
            textCostName = (TextView) view.findViewById(R.id.text_cost_name);
            textCostScore = (TextView) view.findViewById(R.id.text_cost_score);
            btnCostState = (Button) view.findViewById(R.id.btn_cost_state);
        }
    }

    private void removeCostItem(Cost cost) {
        CostList costList = (CostList) spUtil.getObjectData(SPKeyConst.COST_LIST);
        for (Cost costItem : costList.getCostList()) {
            if (costItem.getCostName().equals(cost.getCostName())) {
                costList.getCostList().remove(costItem);
            }
        }
        spUtil.setObjectData(SPKeyConst.COST_LIST, costList);
    }

}
