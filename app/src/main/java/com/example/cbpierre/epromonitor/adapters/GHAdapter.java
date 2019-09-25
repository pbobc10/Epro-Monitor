package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.GH;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class GHAdapter extends RecyclerView.Adapter<GHAdapter.GHViewHolder> {
    private List<GH> ghs;
    private LayoutInflater inflater;
    private GHItemListener listener;
    private Random rnd;

    public GHAdapter(Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public GHViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_gh_item, viewGroup, false);
        return new GHViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GHViewHolder ghViewHolder, int i) {
        if (ghs != null) {
            GH gh = ghs.get(i);
            ghViewHolder.dateDebut.setText(date(gh.getDebut()));
            ghViewHolder.dateFin.setText(date(gh.getFin()));
            rnd = new Random();
            ghViewHolder.linearLayout.setBackgroundColor(Color.parseColor(mColors[rnd.nextInt(mColors.length)]));
        }
    }

    @Override
    public int getItemCount() {
        return ghs != null ? ghs.size() : 0;
    }

    public void setGhs(List<GH> ghs) {
        this.ghs = ghs;
        notifyDataSetChanged();
    }

    public class GHViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateDebut, dateFin;
        private final LinearLayout linearLayout;

        public GHViewHolder(@NonNull View itemView) {
            super(itemView);
            dateDebut = itemView.findViewById(R.id.txtDateDebutGH);
            dateFin = itemView.findViewById(R.id.txtDateFinGH);
            linearLayout = itemView.findViewById(R.id.llGH);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        GH gh = ghs.get(getAdapterPosition());
                        listener.onGHClick(gh.getGhId());
                        Toast.makeText(v.getContext(), "" + gh.getGhId(), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }
    }

    public String date(String x) {
        //2019-09-27T00:00:00
        Date dat = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd", Locale.FRENCH);
        try {
            dat = simpleDateFormat.parse(x);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return simpleDateFormat.format(dat);
    }

    // interface
    public interface GHItemListener {
        void onGHClick(int ghId);
    }

    public void setListener(GHItemListener listener) {
        this.listener = listener;
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
}
