/**
 * TeamOne
 */
package com.app.etouchcare.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.etouchcare.R;
import com.app.etouchcare.anim.AnimationUtil;
import com.app.etouchcare.datamodel.Diagnosis;

import java.util.ArrayList;
import java.util.HashMap;
import static com.app.etouchcare.extra.Keys.EndPointPatientDiagnosis.*;

/**
 * Created by wenzhongzheng on 2016-11-23.
 */

public class PatientDiagnosisAdapter extends RecyclerView.Adapter<PatientDiagnosisAdapter.MyViewHolder> {
    private LayoutInflater inflater;
    private ArrayList<Diagnosis> data = new ArrayList<>();
    private Context context=null;

    public PatientDiagnosisAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void setDiagnosisList(ArrayList<Diagnosis> testList){
        data = testList;

    }

    @Override
    public PatientDiagnosisAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_diagnosis_list, parent,false );
        PatientDiagnosisAdapter.MyViewHolder holder = new PatientDiagnosisAdapter.MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(PatientDiagnosisAdapter.MyViewHolder holder, int position) {

        Diagnosis current = data.get(position);
        holder.tvID.setText(current.get_id());
        holder.tvDetail.setText(current.getDescription());
        holder.tvDate.setText(current.getDate());

        AnimationUtil.animate(holder);

    }



    @Override
    public int getItemCount() {
        return data.size();
    }



    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvID,tvDetail,tvDate;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvID = (TextView) itemView.findViewById(R.id.diagnosis_id);
            tvDate = (TextView) itemView.findViewById(R.id.diagnosis_date);
            tvDetail = (TextView) itemView.findViewById(R.id.diagnosis_detail);

        }
    }
}
