package com.lokalis.project;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class CategoryViewHolder extends RecyclerView.ViewHolder {

    //declaring variables
    TextView categoriesName;
    RecyclerView categoriesLayout;
    RecyclerView.LayoutManager manager;

    public CategoryViewHolder(@NonNull View itemView) {
        super(itemView);
        //hooks
        categoriesName = itemView.findViewById(R.id.categoryName);
        manager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
        categoriesLayout = itemView.findViewById(R.id.itemCatalog);
        categoriesLayout.setLayoutManager(manager);
    }
}
