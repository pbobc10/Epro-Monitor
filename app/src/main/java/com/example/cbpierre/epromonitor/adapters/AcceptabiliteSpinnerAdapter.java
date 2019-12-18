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
import com.example.cbpierre.epromonitor.models.AcceptabiliteRef;

import java.util.List;

public class AcceptabiliteSpinnerAdapter extends ArrayAdapter<AcceptabiliteRef> {
    private List<AcceptabiliteRef> acceptabiliteRefList;
    private Context context;

    public AcceptabiliteSpinnerAdapter(@NonNull Context context, int resource, @NonNull List<AcceptabiliteRef> objects) {
        super(context, resource, objects);
        this.acceptabiliteRefList = objects;
        this.context = context;
    }

    public static class ViewHolder {
        private TextView textView;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        AcceptabiliteRef acceptabiliteRef = acceptabiliteRefList.get(position);
        // view lookup cache stored in tag
        ViewHolder viewHolder = new ViewHolder();
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.spinner_rows, parent, false);
            viewHolder.textView = convertView.findViewById(R.id.txtSpinnerRows);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.textView.setText(acceptabiliteRef.getNom());
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
