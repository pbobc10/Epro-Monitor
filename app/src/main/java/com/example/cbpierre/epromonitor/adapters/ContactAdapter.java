package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    // Cached copy of Contact
    private List<Contact> mContact;

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom;
        private final TextView txtSpecialite;
        private final TextView txtNature;
        private final TextView txtSecteur;
        private final TextView txtTel;
        private final TextView txtEmail;


        public ContactViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNom);
            txtSpecialite = itemView.findViewById(R.id.txtSpecialite);
            txtNature = itemView.findViewById(R.id.txtNature);
            txtSecteur = itemView.findViewById(R.id.txtSecteur);
            txtTel = itemView.findViewById(R.id.txtTel);
            txtEmail = itemView.findViewById(R.id.txtEmail);
        }
    }

    public ContactAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public ContactAdapter.ContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View itemView = mInflater.inflate(R.layout.recyclerview_contact_item, viewGroup, false);
        // Return a new holder instance
        return new ContactViewHolder(itemView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ContactViewHolder contactViewHolder, int i) {
        if (mContact != null) {
            // Get the data model based on position
            Contact current = mContact.get(i);
            contactViewHolder.txtNom.setText(current.getTitre() + " " + current.getNom() + " " + current.getPrenom());
            contactViewHolder.txtSpecialite.setText(current.getSpecialite());
            contactViewHolder.txtSecteur.setText(current.getSecteur());
            contactViewHolder.txtNature.setText(current.getNature());
            contactViewHolder.txtTel.setText("Tel:(509)" + current.getPhone1());
            contactViewHolder.txtEmail.setText(current.getEmail());
        } else {
            // Covers the case of data not being ready yet.
            contactViewHolder.txtNom.setText(R.string.contact_nom);
            contactViewHolder.txtTel.setText(R.string.contact_tel);
            contactViewHolder.txtSpecialite.setText(R.string.contact_specialite);
            contactViewHolder.txtNature.setText(R.string.contact_nature);
            contactViewHolder.txtTel.setText(R.string.contact_tel);
            contactViewHolder.txtEmail.setText(R.string.contact_email);

        }

    }


    public void setContact(List<Contact> contact) {
        mContact = contact;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mContact != null ? mContact.size() : 0;
    }
}
