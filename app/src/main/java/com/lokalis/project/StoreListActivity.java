package com.lokalis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class StoreListActivity extends AppCompatActivity {

    //declaring variables
    EditText searchStore;
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
        LoadData("");

        searchStore.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.toString()!=null) {
                    LoadData(s.toString());
                }
                else {
                    LoadData("");
                }
            }
        });

    }

    private void LoadData(String data) {

        Query query = reference.orderByChild("search").startAt(data.toUpperCase()).endAt(data.toLowerCase() + "\uf88f");

        storeData = new FirebaseRecyclerOptions.Builder<StoreHelperClass>().setQuery(query, StoreHelperClass.class).build();
        adapter = new FirebaseRecyclerAdapter<StoreHelperClass, StoreViewHolder>(storeData) {
            @Override
            protected void onBindViewHolder(@NonNull StoreViewHolder holder, int position, @NonNull StoreHelperClass model) {

                holder.storeName.setText(model.getStoreName());
                holder.storeRate.setText(model.getRating());
                holder.storeDistance.setText(model.getDistance());
                Picasso.get().load(model.getImages()).into(holder.storePic);
                holder.storeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(StoreListActivity.this, StoreFrontActivity.class);
                        intent.putExtra("storeKey",getRef(position).getKey());
                        startActivity(intent);

                    }
                });

            }

            @NonNull
            @Override
            public StoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.store_list_layout, parent, false);
                return new StoreViewHolder(v);
            }
        };
        adapter.startListening();
        storeList.setAdapter(adapter);
    }


}