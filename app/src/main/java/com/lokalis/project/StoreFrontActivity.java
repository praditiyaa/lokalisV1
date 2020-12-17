package com.lokalis.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class StoreFrontActivity extends AppCompatActivity {

    //declaring variables
    TextView storeName, storeDistance;
    RecyclerView categoriesList;
    RecyclerView.LayoutManager manager;
    FirebaseRecyclerAdapter<CategoryHelperClass, CategoryViewHolder> adapter;
    FirebaseRecyclerAdapter<ItemHelperClass, ItemViewHolder> adapter2;
    FirebaseRecyclerOptions<CategoryHelperClass> categoryOption;
    DatabaseReference reference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_front);

        //firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Stores");

        //hooks
        storeName = findViewById(R.id.storeName);
        storeDistance = findViewById((R.id.storeDis));
        manager = new LinearLayoutManager(this);
        categoriesList = findViewById(R.id.itemCatalog);

        String storeKey = getIntent().getStringExtra("storeKey");
        reference.child(storeKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if (snapshot.exists())
                {
                    String strNames = snapshot.child("storeName").getValue().toString();
                    String strDistances = snapshot.child("distance").getValue().toString();
                    storeName.setText(strNames);
                    storeDistance.setText(strDistances);

                    LoadData();
                }

            }

            private void LoadData() {
                categoryOption = new FirebaseRecyclerOptions.Builder<CategoryHelperClass>().setQuery(reference,CategoryHelperClass.class).build();
                adapter = new FirebaseRecyclerAdapter<CategoryHelperClass, CategoryViewHolder>(categoryOption) {
                    @Override
                    protected void onBindViewHolder(@NonNull CategoryViewHolder holder, int position, @NonNull CategoryHelperClass model) {

                        holder.categoriesName.setText(model.getCategoryName());
                        FirebaseRecyclerOptions<ItemHelperClass> options2 = new FirebaseRecyclerOptions.Builder<ItemHelperClass>().
                                setQuery(reference.child(model.getCategoryName()).child("itemCategories"),ItemHelperClass.class).build();

                        adapter2 = new FirebaseRecyclerAdapter<ItemHelperClass, ItemViewHolder>(options2) {
                            @Override
                            protected void onBindViewHolder(@NonNull ItemViewHolder holder, int position, @NonNull ItemHelperClass model) {
                                holder.itemName.setText(model.getItemName());
                                holder.itemPrice.setText(model.getItemPrice());
                                Picasso.get().load(model.getItemImages()).into(holder.itemImage);

                            }

                            @NonNull
                            @Override
                            public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                                View v2= LayoutInflater.from(getBaseContext()).inflate(R.layout.item_list_layout,parent,false);
                                return new ItemViewHolder(v2);
                            }
                        };
                        adapter2.startListening();
                        adapter2.notifyDataSetChanged();
                        holder.categoriesLayout.setAdapter(adapter2);
                    }

                    @NonNull
                    @Override
                    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                        View v = LayoutInflater.from(getBaseContext()).inflate(R.layout.activity_item_category,parent,false);
                        return new CategoryViewHolder(v);
                    }
                };
                adapter.startListening();
                categoriesList.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}