package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.JoinContactEtablissementData;

import java.util.List;

public class ContactEtabSpinnerAdapter extends ArrayAdapter<JoinContactEtablissementData> {
    private List<JoinContactEtablissementData> contactEtablissementDataList;
    private Context context;

    public ContactEtabSpinnerAdapter(Context context, int resource, List<JoinContactEtablissementData> contactEtablissementData) {
        super(context, resource, contactEtablissementData);
        this.context = context;
        this.contactEtablissementDataList = contactEtablissementData;
    }

    public static class ViewHolder {
        private TextView nomEtab;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        JoinContactEtablissementData etablissementData = contactEtablissementDataList.get(position);
        // view lookup cache stored in tag
        ViewHolder viewHolder;
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_rows, parent, false);
            viewHolder.nomEtab = convertView.findViewById(R.id.txtSpinnerRows);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        viewHolder.nomEtab.setText(etablissementData.getNom_Etablissement());
        // Return the completed view to render on screen
        return convertView;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
