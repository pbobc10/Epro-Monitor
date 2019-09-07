package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Produit;

import java.util.List;
import java.util.Random;

public class ProduitContactAdapter extends RecyclerView.Adapter<ProduitContactAdapter.ProduitContactViewHolder> {
    private List<Produit> produits;
    private LayoutInflater inflater;
    private Random rnd;

    public ProduitContactAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ProduitContactViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_pa_contact_prouit, viewGroup, false);
        return new ProduitContactViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProduitContactViewHolder produitContactViewHolder, int i) {
        if (produits != null) {
            Produit produit = produits.get(i);
            produitContactViewHolder.nomProduit.setText(produit.getProduitId() + " - " + produit.getNomProduit());
            //random color
            rnd = new Random();
            produitContactViewHolder.viewcolor1.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
            produitContactViewHolder.viewcolor2.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
            produitContactViewHolder.viewcolor3.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
            produitContactViewHolder.viewcolor4.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
        }
    }

    public void setProduits(List<Produit> produits) {
        this.produits = produits;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return produits != null ? produits.size() : 0;
    }

    public class ProduitContactViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomProduit;
        private final View viewcolor1;
        private final View viewcolor2;
        private final View viewcolor3;
        private final View viewcolor4;

        public ProduitContactViewHolder(@NonNull View itemView) {
            super(itemView);
            nomProduit = itemView.findViewById(R.id.txtNomProduit);
            viewcolor1 = itemView.findViewById(R.id.viewColor1);
            viewcolor2 = itemView.findViewById(R.id.viewColor2);
            viewcolor3 = itemView.findViewById(R.id.viewColor3);
            viewcolor4 = itemView.findViewById(R.id.viewColor4);
        }
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
