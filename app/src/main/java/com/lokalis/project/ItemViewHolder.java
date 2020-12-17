package com.lokalis.project;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder{

    //declaring variables
    ImageView itemImage;
    TextView itemName, itemPrice;
    RelativeLayout itemLayout;

    public ItemViewHolder(@NonNull View itemView) {
        super(itemView);

        //hooks
        itemImage = itemView.findViewById(R.id.itemImage);
        itemName = itemView.findViewById(R.id.itemName);
        itemPrice = itemView.findViewById(R.id.itemPrice);
        itemLayout = (RelativeLayout) itemView;
    }
}
