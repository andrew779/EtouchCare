package com.app.etouchcare;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import java.util.Collections;
import java.util.List;

//import com.app.etouchcare.R;

/**
 * Created by wenzhongzheng on 2016-11-18.
 */

public class PatientListAdapter extends RecyclerView.Adapter<PatientListAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    List<Data> data = Collections.emptyList();

    public PatientListAdapter(Context context,List<Data> data) {
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.patientlist_row, parent,false );
        MyViewHolder holder = new MyViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Data current = data.get(position);
        holder.pName.setText(current.getpName());
        holder.description.setText(current.getDescription());
        holder.imageView.setImageResource(R.drawable.profile) ;

    }

    @Override
    public int getItemCount() {
        return data.size() ;
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView pName,description;

        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.patientlist_row_img);
            pName = (TextView) itemView.findViewById(R.id.patientlist_row_name);
            description = (TextView) itemView.findViewById(R.id.patientlist_row_des);


        }
    }
}
