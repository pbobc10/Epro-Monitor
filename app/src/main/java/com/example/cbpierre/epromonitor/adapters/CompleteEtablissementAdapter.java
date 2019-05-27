package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.CompleteEtablissement;

import java.util.List;

public class CompleteEtablissementAdapter extends RecyclerView.Adapter<CompleteEtablissementAdapter.CompleteEtablissementViewHolder> {
    private final LayoutInflater mInflater;
    // Cached copy of CompleteEtablissement
    private List<CompleteEtablissement> mCompleteEtablissement;

    private OnCompleteEtablissementListener listener;

    // listener interface
    public interface OnCompleteEtablissementListener {
        void onContactClick(List<CompleteEtablissement> _completeEtablissement, int position);
    }

    public void setOnCompleteEtablissementListener(OnCompleteEtablissementListener listener) {
        this.listener = listener;
    }

    public class CompleteEtablissementViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom;
        private final TextView txtLocalite;
        private final TextView txtAdresse;


        public CompleteEtablissementViewHolder(@NonNull final View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNomTablissement);
            txtAdresse = itemView.findViewById(R.id.txtAdressef);
            txtLocalite = itemView.findViewById(R.id.txtLocalite);

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
                            listener.onContactClick(mCompleteEtablissement, position);
                            //  Toast.makeText(view.getContext(), "===test " + mCompleteEtablissement.get(position).getNom(), Toast.LENGTH_SHORT).show();
                            Log.d("=====testclick 4", "yes");

                        }
                    }
                    Log.d("=====testclick 5", "yes");

                }
            });
        }

    }

    public CompleteEtablissementAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public CompleteEtablissementAdapter.CompleteEtablissementViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View itemView = mInflater.inflate(R.layout.recyclerview_etablissement_item, viewGroup, false);
        // Return a new holder instance
        return new CompleteEtablissementViewHolder(itemView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull CompleteEtablissementAdapter.CompleteEtablissementViewHolder completeEtablissementViewHolder, int i) {
        if (mCompleteEtablissement != null) {
            // Get the data model based on position
            CompleteEtablissement current = mCompleteEtablissement.get(i);
            completeEtablissementViewHolder.txtNom.setText(current.getNom_Etablissement());
            completeEtablissementViewHolder.txtLocalite.setText("Localite:  " + current.getDescription_zone_ht());
            completeEtablissementViewHolder.txtAdresse.setText("Adresse:  " + current.getAdresse());
        } else {
            // Covers the case of data not being ready yet.
            completeEtablissementViewHolder.txtNom.setText(R.string.contact_nom);
            completeEtablissementViewHolder.txtAdresse.setText(R.string.contact_tel);
            completeEtablissementViewHolder.txtLocalite.setText(R.string.contact_specialite);
        }

    }


    public void setEtablissement(List<CompleteEtablissement> completeEtablissement) {
        mCompleteEtablissement = completeEtablissement;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCompleteEtablissement != null ? mCompleteEtablissement.size() : 0;
    }
}
