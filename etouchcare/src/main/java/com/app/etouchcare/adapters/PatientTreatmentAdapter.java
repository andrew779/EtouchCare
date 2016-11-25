package com.app.etouchcare.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.anim.AnimationUtil;
import com.app.etouchcare.datamodel.Treatments;

import java.util.ArrayList;

/**
 * Created by wenzhongzheng on 2016-11-25.
 */

public class PatientTreatmentAdapter extends RecyclerView.Adapter<PatientTreatmentAdapter.MyViewHolder> {
    private ArrayList<Treatments> treatList = new ArrayList<>();
    private LayoutInflater inflater;

    public PatientTreatmentAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    public void setTreatList(ArrayList<Treatments> list) {
        treatList = list;
    }

    @Override
    public PatientTreatmentAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_treatment_list, parent,false);
        MyViewHolder holder = new MyViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(PatientTreatmentAdapter.MyViewHolder holder, int position) {
        Treatments treatments = treatList.get(position);
        holder.id.setText(treatments.get_id());
        holder.des.setText(treatments.getDescription());
        holder.date.setText(treatments.getDate());

        AnimationUtil.animate(holder);

    }

    @Override
    public int getItemCount() {
        return treatList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView id, des, date;

        MyViewHolder(View itemView) {
            super(itemView);
            id = (TextView) itemView.findViewById(R.id.treat_id);
            des = (TextView) itemView.findViewById(R.id.treat_detail);
            date = (TextView) itemView.findViewById(R.id.treat_date);

        }
    }
}
