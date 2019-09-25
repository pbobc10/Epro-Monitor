package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.ChoiceContactGH;

import java.util.List;

public class ChoiceContactGHAdapter extends RecyclerView.Adapter<ChoiceContactGHAdapter.ViewHolder> {
    private List<ChoiceContactGH> choiceContactGHS;
    private LayoutInflater inflater;
    private OnChoiceContactItemListener choiceListener;

    public ChoiceContactGHAdapter(Context context) {
        this.inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.recyclerview_choice_gh__contact, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        ChoiceContactGH contactGH = choiceContactGHS.get(i);
        viewHolder.nomContact.setText(contactGH.getNom_ratio());
        if (viewHolder.checkBox.isChecked())
            if (choiceListener != null)
                choiceListener.onChoiceContact(contactGH.getCon_id());
    }

    @Override
    public int getItemCount() {
        return choiceContactGHS != null ? choiceContactGHS.size() : 0;
    }

    public void setChoiceContactGHS(List<ChoiceContactGH> choiceContactGHS) {
        this.choiceContactGHS = choiceContactGHS;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nomContact;
        private final CheckBox checkBox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nomContact = itemView.findViewById(R.id.txtNomContactGHChoice);
            checkBox = itemView.findViewById(R.id.cbSelectedGHChoice);
        }
    }

    public interface OnChoiceContactItemListener {
        void onChoiceContact(int conId);
    }

    public void setChoiceListener(OnChoiceContactItemListener choiceListener) {
        this.choiceListener = choiceListener;
    }
}
