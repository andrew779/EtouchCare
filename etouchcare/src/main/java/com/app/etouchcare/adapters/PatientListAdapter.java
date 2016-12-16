/**
 * TeamOne
 */
package com.app.etouchcare.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;



import com.app.etouchcare.R;
import com.app.etouchcare.anim.AnimationUtil;
import com.app.etouchcare.datamodel.Patients;

import java.util.ArrayList;

//import com.app.etouchcare.R;

/**
 * Created by wenzhongzheng on 2016-11-18.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<Patients> data = new ArrayList<>();
    private Context context=null;

    public PatientListAdapter(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }
    public void setPatientList(ArrayList<Patients> listPatients){
        data = listPatients;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_patientlist, parent,false );

        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Patients current = data.get(position);
        holder.pName.setText(String.format("Name: %s", current.getName()));
        holder.diagnosis.setText(String.format("Diagnosis: %s", current.getDiagnosis()));
        if ( current.getGender().equalsIgnoreCase("male")){
            holder.imageView.setImageResource(R.drawable.male);
        }else if (current.getGender().equalsIgnoreCase("female")){
            holder.imageView.setImageResource(R.drawable.female);
        }else{
            holder.imageView.setImageResource(R.drawable.na);
        }

        holder.id.setText(String.format("ID: %s", current.getId()));
        holder.room.setText(String.format("Room: %s", current.getRoom()));

        AnimationUtil.animate(holder);
    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView pName,diagnosis,id,room;

        MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.patientlist_row_img);
            pName = (TextView) itemView.findViewById(R.id.patientlist_row_name);
            diagnosis = (TextView) itemView.findViewById(R.id.patientlist_row_des);
            id = (TextView) itemView.findViewById(R.id.patientlist_row_id);
            room = (TextView) itemView.findViewById(R.id.patientlist_row_room);

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
