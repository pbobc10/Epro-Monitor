package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Secteur;

import java.util.List;

public class SecteurSpinnerAdapter extends ArrayAdapter<Secteur> {
    private List<Secteur> secteurs;
    private Context context;

    public static class ViewHolder {
        TextView textView;
    }

    public SecteurSpinnerAdapter(Context context, int resource, List<Secteur> secteurs) {
        super(context,resource,secteurs);
        this.context = context;
        this.secteurs = secteurs;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Secteur secteur = secteurs.get(position);

        // view lookup cache stored in tag
        ViewHolder viewHolder;

        if (convertView == null) {

            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_rows, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.txtSpinnerRows);

            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);

        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.textView.setText(secteur.getNomSecteur());
        // Return the completed view to render on screen
        return convertView;
        //return super.getView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }
}
