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
import com.example.cbpierre.epromonitor.models.Recommandation;

import java.util.List;
import java.util.Random;

public class RecommandationAdapter extends RecyclerView.Adapter<RecommandationAdapter.ViewHoler> {
    private List<Recommandation> recommandationList;
    private final LayoutInflater mInflater;
    private Random rnd;
    private OnRecommandationListener listener;

    public RecommandationAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public RecommandationAdapter.ViewHoler onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View item = mInflater.inflate(R.layout.recyclerview_recommandation_item, viewGroup, false);
        // Return a new holder instance
        return new ViewHoler(item);
    }

    @Override
    public void onBindViewHolder(@NonNull RecommandationAdapter.ViewHoler viewHoler, int i) {
        if (recommandationList != null) {
            Recommandation recommandation = recommandationList.get(i);
            viewHoler.tvGHID.setText(recommandation.getGhId().toString());
            viewHoler.tvNote.setText(recommandation.getNote());
            viewHoler.tvJour.setText(recommandation.getJour());

            // set color
            rnd = new Random();
            viewHoler.tvGHID.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
        }
    }

    @Override
    public int getItemCount() {
        return recommandationList != null ? recommandationList.size() : 0;
    }

    public void setRecommandationList(List<Recommandation> recommandationList) {
        this.recommandationList = recommandationList;
        notifyDataSetChanged();
    }

    /**
     * ViewHolder class for cash
     */
    public class ViewHoler extends RecyclerView.ViewHolder {
        private final TextView tvGHID;
        private final TextView tvNote;
        private final TextView tvJour;


        public ViewHoler(@NonNull View itemView) {
            super(itemView);
            tvGHID = itemView.findViewById(R.id.tvGhId);
            tvNote = itemView.findViewById(R.id.tvNote);
            tvJour = itemView.findViewById(R.id.tvJour);
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        Recommandation rc = recommandationList.get(position);
                        listener.onRecommadationClick(rc);
                    }

                    return false;
                }
            });
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
    };

    public interface OnRecommandationListener {
        void onRecommadationClick(Recommandation recommandation);
    }

    public void setListener(OnRecommandationListener listener) {
        this.listener = listener;
    }
}
