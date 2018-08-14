package com.developer.bianca.agendaapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ListContactAdapter extends RecyclerView.Adapter {

    List<ListContactCard> listContactsCardCard;

    public ListContactAdapter(List<ListContactCard> listContactsCardCard) {
        this.listContactsCardCard = listContactsCardCard;
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
        ListContactCard listContactCard = listContactsCardCard.get(position);

        ContactViewHolder vh = (ContactViewHolder) holder;
        vh.name.setText(listContactCard.getName());
        vh.email.setText(listContactCard.getEmail());
        vh.telephone.setText(listContactCard.getTelephone());
        vh.city.setText(listContactCard.getCity());

    }

    @Override
    public int getItemCount() {
        return listContactsCardCard.size();
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

        }
    }
}
