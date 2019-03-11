package com.example.cbpierre.epromonitor.adapters;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.fragments.ContactDetailFragment;
import com.example.cbpierre.epromonitor.models.Contact;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ContactViewHolder> {
    private final LayoutInflater mInflater;
    // Cached copy of Contact
    private List<Contact> mContact;

    private OnContactClickListener listener;

    // listener interface
    public interface OnContactClickListener {
        void onContactClick(List<Contact> _contact, int position);
    }

    public void setOnContactClickListener(OnContactClickListener listener) {
        this.listener = listener;
    }

    public class ContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom;
        private final TextView txtSpecialite;
        private final TextView txtNature;
        private final TextView txtSecteur;
        private final TextView txtTel;
        private final TextView txtEmail;


        public ContactViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNom);
            txtSpecialite = itemView.findViewById(R.id.txtSpecialite);
            txtNature = itemView.findViewById(R.id.txtNature);
            txtSecteur = itemView.findViewById(R.id.txtSecteur);
            txtTel = itemView.findViewById(R.id.txtTel);
            txtEmail = itemView.findViewById(R.id.txtEmail);

            // Attach a click listener to the entire row view
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.d("=====testclick 1", "yes");

                    if (listener != null) {
                        Log.d("=====testclick 2", "yes");
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Log.d("=====testclick 3", "yes");
                            listener.onContactClick(mContact, position);
                            //  Toast.makeText(view.getContext(), "===test " + mContact.get(position).getNom(), Toast.LENGTH_SHORT).show();
                            Log.d("=====testclick 4", "yes");

                        }
                    }
                    Log.d("=====testclick 5", "yes");

                }
            });
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
