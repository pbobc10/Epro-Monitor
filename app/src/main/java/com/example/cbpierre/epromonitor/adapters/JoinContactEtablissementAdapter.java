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
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;

import java.util.List;

public class JoinContactEtablissementAdapter extends RecyclerView.Adapter<JoinContactEtablissementAdapter.JoinContactEtablissementDataViewHolder> {
    private final LayoutInflater mInflater;
    // Cached copy of JoinContactEtablissementData
    private List<JoinContactEtablissementData> mEtablissement;

    private OnEtablissementListener listener;

    // listener interface
    public interface OnEtablissementListener {
        void onContactClick(List<JoinContactEtablissementData> _etablissement, int position);
    }

    public void setOnContactClickListener(OnEtablissementListener listener) {
        this.listener = listener;
    }

    public class JoinContactEtablissementDataViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom;
        private final TextView txtLocalite;
        private final TextView txtAdresse;


        public JoinContactEtablissementDataViewHolder(@NonNull final View itemView) {
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
                            listener.onContactClick(mEtablissement, position);
                            //  Toast.makeText(view.getContext(), "===test " + mEtablissement.get(position).getNom(), Toast.LENGTH_SHORT).show();
                            Log.d("=====testclick 4", "yes");

                        }
                    }
                    Log.d("=====testclick 5", "yes");

                }
            });
        }

    }

    public JoinContactEtablissementAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public JoinContactEtablissementAdapter.JoinContactEtablissementDataViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View itemView = mInflater.inflate(R.layout.recyclerview_etablissement_item, viewGroup, false);
        // Return a new holder instance
        return new JoinContactEtablissementDataViewHolder(itemView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull JoinContactEtablissementAdapter.JoinContactEtablissementDataViewHolder viewHolder, int i) {
        if (mEtablissement != null) {
            // Get the data model based on position
            JoinContactEtablissementData current = mEtablissement.get(i);
            viewHolder.txtNom.setText(current.getNom_Etablissement());
            viewHolder.txtLocalite.setText(current.getDescription_zone_ht());
            viewHolder.txtAdresse.setText(current.getAdresse());
        } else {
            // Covers the case of data not being ready yet.
            viewHolder.txtNom.setText(R.string.contact_nom);
            viewHolder.txtAdresse.setText(R.string.hint_tel);
            viewHolder.txtLocalite.setText(R.string.contact_specialite);
        }

    }


    public void setEtablissement(List<JoinContactEtablissementData> etablissement) {
        mEtablissement = etablissement;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mEtablissement != null ? mEtablissement.size() : 0;
    }
}
