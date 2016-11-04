package com.app.etouchcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wenzhongzheng on 2016-11-04.
 */

public class ConditionAdapter extends ArrayAdapter<String> {
    private Context context;
    private final String[] title,description,url;


    public ConditionAdapter(Context context, String[] title, String[] description, String[] url) {
        super(context, R.layout.condition_singlelist, title);
        this.context = context;
        this.title = title;
        this.description = description;
        this.url = url;
    }

    // View lookup cache
    private static class ViewHolder {
        TextView tvTitle;
        TextView tvDes;
        TextView tvIndex;
        Button btnDetail;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (view == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            view = inflater.inflate(R.layout.condition_singlelist, parent, false);
            viewHolder.tvTitle = (TextView) view.findViewById(R.id.condition_title);
            viewHolder.tvDes = (TextView) view.findViewById(R.id.condition_description);
            viewHolder.tvIndex = (TextView) view.findViewById(R.id.condition_index);
            viewHolder.btnDetail = (Button) view.findViewById(R.id.condition_btn_detail);
            // Cache the viewHolder object inside the fresh view
            view.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) view.getTag();
        }

        viewHolder.tvTitle.setText(title[position]);
        viewHolder.tvDes.setText(description[position]);
        viewHolder.tvIndex.setText("#"+position+1);
        viewHolder.btnDetail.setText("Detail");

        return view;
    }
}
