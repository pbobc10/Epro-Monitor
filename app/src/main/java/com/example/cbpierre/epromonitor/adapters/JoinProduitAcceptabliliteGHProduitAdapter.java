package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.JoinProduitAcceptabliliteGHProduit;

import java.util.List;

public class JoinProduitAcceptabliliteGHProduitAdapter extends RecyclerView.Adapter<JoinProduitAcceptabliliteGHProduitAdapter.JoinProduitAcceptabliliteGHProduitViewHolder> {
    private LayoutInflater inflater;
    private OndeleteGHJourContactProduit ondeleteGHJourContactProduit;
    private List<JoinProduitAcceptabliliteGHProduit> joinProduitAcceptabliliteGHProduitList;

    public JoinProduitAcceptabliliteGHProduitAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public JoinProduitAcceptabliliteGHProduitViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View view = inflater.inflate(R.layout.recyclerview_produit_promotionne, viewGroup, false);
        // Return a new holder instance
        return new JoinProduitAcceptabliliteGHProduitViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinProduitAcceptabliliteGHProduitViewHolder joinProduitAcceptabliliteGHProduitViewHolder, int i) {
        if (joinProduitAcceptabliliteGHProduitList != null) {
            JoinProduitAcceptabliliteGHProduit produitAcceptabliliteGHProduit = joinProduitAcceptabliliteGHProduitList.get(i);
            joinProduitAcceptabliliteGHProduitViewHolder.nomProduit.setText(produitAcceptabliliteGHProduit.getNom_produit());
            joinProduitAcceptabliliteGHProduitViewHolder.acceptabilite.setText(produitAcceptabliliteGHProduit.getNom_acceptabilite());
            joinProduitAcceptabliliteGHProduitViewHolder.noteProduitPromo.setText(produitAcceptabliliteGHProduit.getNote());
        } else {
            joinProduitAcceptabliliteGHProduitViewHolder.nomProduit.setText("Nom Produit Promotionne");
            joinProduitAcceptabliliteGHProduitViewHolder.acceptabilite.setText("TRES INTERESSE");
            joinProduitAcceptabliliteGHProduitViewHolder.noteProduitPromo.setText("No internet Checking the network cables, modem, and router");
        }
    }

    @Override
    public int getItemCount() {
        return joinProduitAcceptabliliteGHProduitList != null ? joinProduitAcceptabliliteGHProduitList.size() : 0;
    }

    public void setJoinProduitAcceptabliliteGHProduitList(List<JoinProduitAcceptabliliteGHProduit> joinProduitAcceptabliliteGHProduitList) {
        this.joinProduitAcceptabliliteGHProduitList = joinProduitAcceptabliliteGHProduitList;
        notifyDataSetChanged();
    }

    public class JoinProduitAcceptabliliteGHProduitViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomProduit, acceptabilite, noteProduitPromo;

        public JoinProduitAcceptabliliteGHProduitViewHolder(@NonNull View itemView) {
            super(itemView);
            nomProduit = itemView.findViewById(R.id.txtNomProduitPromotionne);
            acceptabilite = itemView.findViewById(R.id.txtAcceptabilite);
            noteProduitPromo = itemView.findViewById(R.id.txtNoteProduitPromo);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    Toast.makeText(v.getContext(), "Long Click Listener", Toast.LENGTH_LONG).show();
                    if (ondeleteGHJourContactProduit != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            ondeleteGHJourContactProduit.onDeleteProduit(joinProduitAcceptabliliteGHProduitList.get(position).getGh_id(), joinProduitAcceptabliliteGHProduitList.get(position).getCon_id(), joinProduitAcceptabliliteGHProduitList.get(position).getJour(), joinProduitAcceptabliliteGHProduitList.get(position).getProduit_id());
                    }
                    return false;
                }
            });
        }
    }

    public interface OndeleteGHJourContactProduit {
        void onDeleteProduit(int ghId, int conId, String jour, int produitId);
    }

    public void setOndeleteGHJourContactProduit(OndeleteGHJourContactProduit ondeleteGHJourContactProduit) {
        this.ondeleteGHJourContactProduit = ondeleteGHJourContactProduit;
    }
}
