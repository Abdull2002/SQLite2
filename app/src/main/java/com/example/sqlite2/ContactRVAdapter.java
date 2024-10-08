package com.example.sqlite2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ContactRVAdapter extends RecyclerView.Adapter<ContactRVAdapter.ViewHolder> {

    // Variables for the contact list and context
    private ArrayList<ContactModal> contactModalArrayList;
    private Context context;

    // Constructor
    public ContactRVAdapter(ArrayList<ContactModal> contactModalArrayList, Context context) {
        this.contactModalArrayList = contactModalArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Inflating the layout for each item in the RecyclerView
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Setting data to views in each RecyclerView item
        ContactModal modal = contactModalArrayList.get(position);
        holder.firstNameTV.setText(modal.getFirstName());
        holder.lastNameTV.setText(modal.getLastName());
        holder.addressTV.setText(modal.getAddress());
        holder.cityTV.setText(modal.getCity());
        holder.ageTV.setText(String.valueOf(modal.getAge()));

        // Setting an onClickListener for the item in the RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Creating an intent to start UpdateContactActivity
                Intent i = new Intent(context, UpdateContactActivity.class);

                // Passing the contact data to UpdateContactActivity
                i.putExtra("first_name", modal.getFirstName());
                i.putExtra("last_name", modal.getLastName());
                i.putExtra("address", modal.getAddress());
                i.putExtra("city", modal.getCity());
                i.putExtra("age", modal.getAge());

                // Starting the UpdateContactActivity
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        // Returning the size of the contact list
        return contactModalArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // TextView variables for displaying contact details
        private TextView firstNameTV, lastNameTV, addressTV, cityTV, ageTV;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            // Initializing TextViews with their respective IDs
            firstNameTV = itemView.findViewById(R.id.idTVFirstName);
            lastNameTV = itemView.findViewById(R.id.idTVLastName);
            addressTV = itemView.findViewById(R.id.idTVAddress);
            cityTV = itemView.findViewById(R.id.idTVCity);
            ageTV = itemView.findViewById(R.id.idTVAge);
        }
    }
}

