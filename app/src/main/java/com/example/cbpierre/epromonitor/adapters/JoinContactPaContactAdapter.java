package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.JoinContactPaContact;

import java.util.List;
import java.util.Random;

public class JoinContactPaContactAdapter extends RecyclerView.Adapter<JoinContactPaContactAdapter.JoinContactPaContactHolder> {
    private List<JoinContactPaContact> joinContactPaContacts;
    private LayoutInflater inflater;
    private OnContactPaClickLIstener paClickLIstener;
    private Random rnd;

    public JoinContactPaContactAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    public class JoinContactPaContactHolder extends RecyclerView.ViewHolder {
        private final TextView txtNom, txtNature, txtSpecialite, txtForce, txtQuota;
        private final RelativeLayout colorPa;

        public JoinContactPaContactHolder(@NonNull View itemView) {
            super(itemView);
            txtNom = itemView.findViewById(R.id.txtNomPa);
            txtNature = itemView.findViewById(R.id.txtNaturePa);
            txtSpecialite = itemView.findViewById(R.id.txtSpecialitePa);
            txtForce = itemView.findViewById(R.id.txtForcePa);
            txtQuota = itemView.findViewById(R.id.txtQuotaPa);
            colorPa = itemView.findViewById(R.id.rlColorPa);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (paClickLIstener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION)
                            paClickLIstener.onClickPaContact(joinContactPaContacts.get(position));
                    }

                }
            });
        }
    }

    @NonNull
    @Override
    public JoinContactPaContactAdapter.JoinContactPaContactHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_contact_pa, viewGroup, false);
        return new JoinContactPaContactHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JoinContactPaContactAdapter.JoinContactPaContactHolder joinContactPaContactHolder, int i) {
        if (joinContactPaContacts != null) {
            JoinContactPaContact paContact = joinContactPaContacts.get(i);
            joinContactPaContactHolder.txtNom.setText(paContact.getNom_ratio());
            joinContactPaContactHolder.txtSpecialite.setText(paContact.getNomSpecialite().equals("NON APPLICABLE") ? "" : paContact.getNomSpecialite());
            joinContactPaContactHolder.txtNature.setText(paContact.getNomNature());
            joinContactPaContactHolder.txtForce.setText("Force: " + paContact.getForce());
            joinContactPaContactHolder.txtQuota.setText("Quota: " + paContact.getQuota());

            //random color
            rnd = new Random();
            joinContactPaContactHolder.colorPa.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
        }
    }

    @Override
    public int getItemCount() {
        return joinContactPaContacts != null ? joinContactPaContacts.size() : 0;
    }

    public void setJoinContactPaContacts(List<JoinContactPaContact> joinContactPaContacts) {
        this.joinContactPaContacts = joinContactPaContacts;
        notifyDataSetChanged();
    }

    public interface OnContactPaClickLIstener {
        void onClickPaContact(JoinContactPaContact paContact);
    }

    public void setPaClickLIstener(OnContactPaClickLIstener paClickLIstener) {
        this.paClickLIstener = paClickLIstener;
    }

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
            //red orange green purple dark blue
    };
}
