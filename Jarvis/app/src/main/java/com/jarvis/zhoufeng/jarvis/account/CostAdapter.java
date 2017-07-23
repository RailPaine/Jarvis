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
        CostAdapter.ViewHolder viewholder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item_cost, null);
            viewholder = new CostAdapter.ViewHolder(convertView);
            convertView.setTag(viewholder);
        } else {
            viewholder = (CostAdapter.ViewHolder) convertView.getTag();
        }
        Cost cost = getItem(position);
        viewholder.textCostName.setText(cost.getCostName());
        viewholder.textCostScore.setText(cost.getCostScore());
        switch (cost.getCostState()) {
            case Cost.COST_STATE_OPEN:
                viewholder.btnCostState.setText(R.string.cost_state_open);
                cost.setCostState(Cost.COST_STATE_COMPLETE);
                break;
            case Cost.COST_STATE_COMPLETE:
                viewholder.btnCostState.setText(R.string.cost_state_complete);
                break;
        }
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
}
