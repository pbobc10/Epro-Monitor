package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.CompleteContact;

import java.util.List;
import java.util.Random;

public class CompleteContactAdapter extends RecyclerView.Adapter<CompleteContactAdapter.CompleteContactViewHolder> {
    private final LayoutInflater mInflater;
    // Cached copy of CompleteContact
    private List<CompleteContact> mCompleteContact;

    private OnContactClickListener listener;

    private Random rnd;

    // Member variables (properties about the object)
    public String[] mColors = {
            "#39add1", // light blue
            "#3079ab", // dark blue
            "#c25975", // mauve
            "#e15258", // red
            "#f9845b", // orange
            "#838cc7", // lavender
            "#7d669e", // purple
            "#53bbb4", // aqua
            "#51b46d", // green
            "#e0ab18", // mustard
            "#637a91", // dark gray
            "#f092b0", // pink
            "#b7c0c7"  // light gray
    };

    // listener interface
    public interface OnContactClickListener {
        void onContactClick(List<CompleteContact> _completeContact, int position);
    }

    public void setOnContactClickListener(OnContactClickListener listener) {
        this.listener = listener;
    }

    public class CompleteContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom;
        private final TextView txtSpecialite;
        private final TextView txtNature;
        private final TextView txtSecteur;
        private final TextView txtTel;
        private final TextView txtEmail;
        private final View colorContact;


        public CompleteContactViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNom);
            txtSpecialite = itemView.findViewById(R.id.txtSpecialite);
            txtNature = itemView.findViewById(R.id.txtNature);
            txtSecteur = itemView.findViewById(R.id.txtSecteur);
            txtTel = itemView.findViewById(R.id.txtTel);
            txtEmail = itemView.findViewById(R.id.txtEmail);
            colorContact = itemView.findViewById(R.id.colorLineContact);


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
                            listener.onContactClick(mCompleteContact, position);
                            Toast.makeText(view.getContext(), "===test " + mCompleteContact.get(position).getModifie_le(), Toast.LENGTH_SHORT).show();
                            Log.d("=====testclick 4", "yes");

                        }
                    }
                    Log.d("=====testclick 5", "yes");

                }
            });
        }

    }

    public CompleteContactAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CompleteContactAdapter.CompleteContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View itemView = mInflater.inflate(R.layout.recyclerview_contact_item, viewGroup, false);
        // Return a new holder instance
        return new CompleteContactViewHolder(itemView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull CompleteContactAdapter.CompleteContactViewHolder completeContactViewHolder, int i) {
        if (mCompleteContact != null) {
            // Get the data model based on position
            CompleteContact current = mCompleteContact.get(i);
            String phone1 = current.getPhone1() == null ? "" : current.getPhone1();
            String prenom = current.getPrenom() == null ? "" : current.getPrenom();
            String titre = current.getTitre().equals("NA") ? "" : current.getTitre() + ".";
            completeContactViewHolder.txtNom.setText(titre + " " + current.getNom() + " " + prenom);
            completeContactViewHolder.txtSpecialite.setText(current.getNomSpecialite());
            completeContactViewHolder.txtSecteur.setText(current.getNomSecteur());
            completeContactViewHolder.txtNature.setText(current.getNomNature());
            completeContactViewHolder.txtTel.setText("Tel: " + phone1);
            completeContactViewHolder.txtEmail.setText(current.getEmail());

            //random color
            rnd = new Random();
            completeContactViewHolder.colorContact.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
        } else {
            // Covers the case of data not being ready yet.
            completeContactViewHolder.txtNom.setText(R.string.contact_nom);
            completeContactViewHolder.txtTel.setText(R.string.contact_tel);
            completeContactViewHolder.txtSpecialite.setText(R.string.contact_specialite);
            completeContactViewHolder.txtNature.setText(R.string.contact_nature);
            completeContactViewHolder.txtTel.setText(R.string.contact_tel);
            completeContactViewHolder.txtEmail.setText(R.string.contact_email);

        }

    }


    public void setContact(List<CompleteContact> completeContact) {
        mCompleteContact = completeContact;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCompleteContact != null ? mCompleteContact.size() : 0;
    }
}
