package com.lokalis.project;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class StoreViewHolder extends RecyclerView.ViewHolder {

    //declaring variables
    ImageView storePic;
    TextView storeName, storeDistance, storeRate;
    RelativeLayout storeLayout;

    public StoreViewHolder(@NonNull View itemView) {
        super(itemView);

        //hooks
        storePic  = itemView.findViewById(R.id.storeImage);
        storeName  = itemView.findViewById(R.id.storeName);
        storeDistance  = itemView.findViewById(R.id.storeDistance);
        storeRate  = itemView.findViewById(R.id.storeRating);
        storeLayout = (RelativeLayout) itemView;
    }
}
