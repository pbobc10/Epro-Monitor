package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;

import java.util.List;

public class JoinContactGhSVAdapter extends RecyclerView.Adapter<JoinContactGhSVAdapter.ViewHolder> {
    private List<JoinContactGhSV> joinContactGhSVS;
    private LayoutInflater inflater;
    private OnGHJourContactListener ghJourContactListener;
    private OnGHContactListener ghContactListener;

    public JoinContactGhSVAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_gh_jour_contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        if (joinContactGhSVS != null) {
            final JoinContactGhSV contactGhSV = joinContactGhSVS.get(i);
            viewHolder.nomContact.setText(contactGhSV.getNom_ratio());
            viewHolder.ibClear.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (ghJourContactListener != null)
                        ghJourContactListener.onGhJourContactClick(contactGhSV.getGh_id(), contactGhSV.getCon_id());
                }
            });
            if (contactGhSV.getGh_complete()) {
                viewHolder.checkBox.setChecked(true);
                viewHolder.checkBox.setEnabled(false);
                viewHolder.ibClear.setVisibility(View.GONE);
                viewHolder.statutVisite.setText(contactGhSV.getNom());
                if (contactGhSV.getNom().equals("VISITE PLANIFIÉE"))
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#39add1"));
                else if (contactGhSV.getNom().equals("VISITE EFFECTUÉE"))
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#3079ab"));
                else if (contactGhSV.getNom().equals("CONTACT ABSENT"))
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#c25975"));
                else if (contactGhSV.getNom().equals("VISITE NON EFFECTUÉE"))
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#f9845b"));
                else if (contactGhSV.getNom().equals("VISITE NON REALISÉE"))
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#838cc7"));
                else
                    viewHolder.statutVisite.setTextColor(Color.parseColor("#7d669e"));
            } else {
                viewHolder.checkBox.setVisibility(View.GONE);
                viewHolder.ibClear.setVisibility(View.VISIBLE);
                viewHolder.statutVisite.setVisibility(View.GONE);
                viewHolder.ibClear.setPadding(0, 0, 8, 0);
                viewHolder.nomContact.setPadding(24, 0, 0, 0);
            }
        }
    }

    @Override
    public int getItemCount() {
        return joinContactGhSVS != null ? joinContactGhSVS.size() : 0;
    }

    public void setJoinContactGhSVS(List<JoinContactGhSV> joinContactGhSVS) {
        this.joinContactGhSVS = joinContactGhSVS;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomContact, statutVisite;
        private final ImageButton ibClear;
        private final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomContact = itemView.findViewById(R.id.txtNomContact);
            ibClear = itemView.findViewById(R.id.ibclear);
            checkBox = itemView.findViewById(R.id.cbSelected);
            statutVisite = itemView.findViewById(R.id.txtStatutVisite);

            // on item click
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (ghContactListener != null)
                        ghContactListener.onGhContactClick(joinContactGhSVS.get(position));
                       // Toast.makeText(v.getContext(), "press", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    //interface
    public interface OnGHJourContactListener {
        void onGhJourContactClick(int ghId, int conId);
    }

    public void setOnGHJourContactListener(OnGHJourContactListener ghJourContactListener) {
        this.ghJourContactListener = ghJourContactListener;
    }

    public interface OnGHContactListener {
        void onGhContactClick(JoinContactGhSV joinContactGhSV);
    }

    public void setOnGhContactListener(OnGHContactListener ghContactListener) {
        this.ghContactListener = ghContactListener;
    }
}
