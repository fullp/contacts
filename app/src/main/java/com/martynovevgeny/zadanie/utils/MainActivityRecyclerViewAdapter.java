package com.martynovevgeny.zadanie.utils;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.martynovevgeny.zadanie.R;
import com.martynovevgeny.zadanie.model.dao.contact.Contact;

import java.util.ArrayList;

/**
 * Created by websu on 30.05.2017.
 */

public class MainActivityRecyclerViewAdapter extends  RecyclerView.Adapter<MainActivityRecyclerViewAdapter.ProductViewHolder> {


    private final ArrayList<Contact> mItems;

    public MainActivityRecyclerViewAdapter(ArrayList<Contact> mItems) {
        this.mItems = mItems;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_activity, parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.name.setText(mItems.get(position).getName());
        holder.surname.setText(mItems.get(position).getSurname());
        holder.age.setText(String.valueOf(mItems.get(position).getAge()));
        holder.city.setText(mItems.get(position).getCity());
        holder.email.setText(mItems.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder{

        final TextView name;
        final TextView surname;
        final TextView age;
        final TextView city;
        final TextView email;

        public ProductViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.main_activity_name);
            surname = (TextView) view.findViewById(R.id.main_activity_surname);
            age = (TextView) view.findViewById(R.id.main_activity_age);
            city = (TextView) view.findViewById(R.id.main_activity_city);
            email = (TextView) view.findViewById(R.id.main_activity_email);
        }
    }
}
