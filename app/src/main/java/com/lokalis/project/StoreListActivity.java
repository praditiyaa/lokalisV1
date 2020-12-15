package com.lokalis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class StoreListActivity extends AppCompatActivity {

    //declaring variables
    TextInputLayout searchStore;
    RecyclerView storeList;
    FirebaseRecyclerOptions<StoreHelperClass> storeData;
    FirebaseRecyclerAdapter<StoreHelperClass, StoreViewHolder> adapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_list);

        //firebase
        reference = FirebaseDatabase.getInstance().getReference("Stores");

        //hooks
        searchStore = findViewById(R.id.searchStore);
        storeList = findViewById(R.id.storeList);
        storeList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        storeList.setHasFixedSize(true);

        //load data
        LoadData();

    }

    private void LoadData() {

        storeData = new FirebaseRecyclerOptions.Builder<StoreHelperClass>().setQuery(reference, StoreHelperClass.class).build();
        adapter = new FirebaseRecyclerAdapter<StoreHelperClass, StoreViewHolder>(storeData) {
            @Override
            protected void onBindViewHolder(@NonNull StoreViewHolder holder, int position, @NonNull StoreHelperClass model) {

                holder.storeName.setText(model.getStoreName());
                holder.storeRate.setText(model.getRating());
                holder.storeDistance.setText(model.getDistance());
                Picasso.get().load(model.getImages()).into(holder.storePic);

            }

            @NonNull
            @Override
            public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_layout, parent,false);
                return new StoreViewHolder(v);
            }
        };
        adapter.startListening();
        storeList.setAdapter(adapter);
    }


}