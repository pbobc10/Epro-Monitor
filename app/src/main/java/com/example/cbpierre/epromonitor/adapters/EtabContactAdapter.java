package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.EtabContact;

import java.util.List;

public class EtabContactAdapter extends RecyclerView.Adapter<EtabContactAdapter.ViewHolder> {
    private List<EtabContact> etabContactList;
    private LayoutInflater inflater;

    public EtabContactAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public EtabContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.recyclerview_etab_contact, viewGroup, false);
        // Return a new holder instance
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EtabContactAdapter.ViewHolder viewHolder, int i) {
        if (etabContactList != null) {
            EtabContact etabContact = etabContactList.get(i);
            String prenomComplet = etabContact.getPrenom() == null ? "" : etabContact.getPrenom();
            String nomComplet = etabContact.getTid().equals("NA") ? etabContact.getNom() + " " + prenomComplet : etabContact.getTid() + ". " + etabContact.getNom() + " " + etabContact.getPrenom();
            viewHolder.nom.setText(nomComplet);
            viewHolder.specialite.setText(etabContact.getNomSpecialite());
        }

    }

    @Override
    public int getItemCount() {
        return etabContactList != null ? etabContactList.size() : 0;
    }

    public void setEtabContactList(List<EtabContact> etabContactList) {
        this.etabContactList = etabContactList;
        notifyDataSetChanged();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView nom;
        private TextView specialite;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nom = itemView.findViewById(R.id.txtNomEtabContact);
            specialite = itemView.findViewById(R.id.txtSpecialiteEtabContact);
        }
    }
}
