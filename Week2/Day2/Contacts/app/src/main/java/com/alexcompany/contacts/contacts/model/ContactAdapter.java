package com.alexcompany.contacts.contacts.model;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.alexcompany.contacts.contacts.R;

import java.util.ArrayList;

/**
 * Created by Android1 on 8/4/2015.
 */
public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {

    private ArrayList<Contact> contactDataSet;

    public ContactAdapter(ArrayList<Contact> contactList){
        this.contactDataSet = contactList;
    }

    @Override
    public ContactViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contacts_layout, parent, false);
        return new ContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ContactViewHolder holder, final int position) {
        Contact c = contactDataSet.get(position);
        holder.tvFirstName.setText(c.getFirstName());
        holder.tvSurname.setText(c.getSurname());
        holder.tvAddress.setText(c.getAddress());
        holder.tvPhoneNumber.setText(c.getPhoneNumber());
        holder.tvEmail.setText(c.getEmail());
    }

    @Override
    public int getItemCount(){
        return contactDataSet.size();
    }

    public static class ContactViewHolder extends RecyclerView.ViewHolder{
        TextView tvFirstName;
        TextView tvSurname;
        TextView tvAddress;
        TextView tvPhoneNumber;
        TextView tvEmail;

        public ContactViewHolder(View itemView){
            super(itemView);

            this.tvFirstName = (TextView) itemView.findViewById(R.id.tv_first_name);
            this.tvSurname = (TextView) itemView.findViewById(R.id.tv_surname);
            this.tvAddress = (TextView) itemView.findViewById(R.id.tv_address);
            this.tvPhoneNumber = (TextView) itemView.findViewById(R.id.tv_phone_number);
            this.tvEmail = (TextView) itemView.findViewById(R.id.tv_email);
        }
    }
}
