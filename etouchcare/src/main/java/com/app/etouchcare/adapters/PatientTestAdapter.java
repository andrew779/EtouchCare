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
import com.app.etouchcare.datamodel.Test;

import java.util.ArrayList;
import java.util.HashMap;
import static com.app.etouchcare.extra.Keys.EndPointPatientTest.*;

/**
 * Created by wenzhongzheng on 2016-11-22.
 */

public class PatientTestAdapter extends RecyclerView.Adapter<PatientTestAdapter.MyViewHolder>{
    private LayoutInflater inflater;
    private ArrayList<Test> data = new ArrayList<>();
    private Context context=null;

    public PatientTestAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void setTestList(ArrayList<Test> testList){
        data = testList;
    }

    @Override
    public PatientTestAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.row_test_list, parent,false );
        PatientTestAdapter.MyViewHolder holder = new PatientTestAdapter.MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(PatientTestAdapter.MyViewHolder holder, int position) {
//        HashMap<String,String> current = data.get(position);
        Test current = data.get(position);
        holder.name.setText(current.getName());
        holder.result.setText(current.getResult());
        holder.id.setText(current.get_id());
        holder.date.setText(current.getDate());

        AnimationUtil.animate(holder);


    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,result,id,date;

        MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.test_name);
            id = (TextView) itemView.findViewById(R.id.test_id);
            result = (TextView) itemView.findViewById(R.id.test_result);
            date = (TextView) itemView.findViewById(R.id.tests_date);

            //approach one to handle item onClick within a RecyclerView
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(context,getAdapterPosition()+" get Clicked",Toast.LENGTH_SHORT).show();
//                }
//            });

        }


    }

}