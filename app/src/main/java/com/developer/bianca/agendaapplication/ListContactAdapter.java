package com.developer.bianca.agendaapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListContactAdapter extends RecyclerView.Adapter {

    List<ListContactCard> listContacts;

    public ListContactAdapter(List<ListContactCard> listContacts) {
        this.listContacts = listContacts;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View card = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.list_contact_card, parent, false);

        return new ContactViewHolder(card);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListContactCard listContactCard = listContacts.get(position);

        ContactViewHolder vh = (ContactViewHolder) holder;
        vh.name.setText(listContactCard.getName());
        vh.email.setText(listContactCard.getEmail());
        vh.telephone.setText(listContactCard.getTelephone());
        vh.city.setText(listContactCard.getCity());

    }

    @Override
    public int getItemCount() {
        return listContacts.size();
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView telephone;
        public TextView email;
        public TextView city;

        public ContactViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.txtViewName);
            telephone = itemView.findViewById(R.id.txtViewTelephone);
            email = itemView.findViewById(R.id.txtViewEmail);
            city = itemView.findViewById(R.id.txtViewCity);

//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    int position = getAdapterPosition();
//                    Intent detailContact = new Intent(v.getContext(), DetailsContactActivity.class);
//                    Contact contact = contacts.get(position);
//
//                    String getName = contact.getName();
//                    detailContact.putExtra("name",getName);
//                    String getEmail = contact.getEmail();
//                    detailContact.putExtra("email", getEmail);
//                    String getCity = contact.getCity();
//                    detailContact.putExtra("city", getCity);
//                    String getPhone = contact.getPhone();
//                    detailContact.putExtra("phone", getPhone);
//
//                    v.getContext().startActivity(detailContact);
//                }
//            });

        }
    }
}
