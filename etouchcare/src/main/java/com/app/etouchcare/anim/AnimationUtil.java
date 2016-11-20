package com.app.etouchcare.anim;

import android.support.v7.widget.RecyclerView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

/**
 * Created by wenzhongzheng on 2016-11-20.
 */

public class AnimationUtil {


    public static void animate(RecyclerView.ViewHolder holder){
        YoYo.with(Techniques.RollIn)
                .duration(500)
                .playOn(holder.itemView);
    }
}
