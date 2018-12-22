package com.example.cbpierre.epromonitor.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Login;

import java.util.List;

public class LoginAdapter extends RecyclerView.Adapter<LoginAdapter.LoginViewHolder> {


    public class LoginViewHolder extends RecyclerView.ViewHolder {
        private final TextView etUsername;

        public LoginViewHolder(@NonNull View itemView) {
            super(itemView);
            etUsername = itemView.findViewById(R.id.etUserLogin);
        }
    }

    private final LayoutInflater mInflater;
    // Cached copy of Users
    private List<Login> mUser;

    public LoginAdapter(Context mContext) {
        mInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public LoginAdapter.LoginViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        // Inflate the custom layout
        View itemView = mInflater.inflate(R.layout.recyclerview_login_item, viewGroup, false);
        // Return a new holder instance
        return new LoginViewHolder(itemView);
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(@NonNull LoginAdapter.LoginViewHolder loginViewHolder, int i) {
        if (mUser != null) {
            // Get the data model based on position
            Login current = mUser.get(i);
            loginViewHolder.etUsername.setText(current.getUsername());
        } else {
            // Covers the case of data not being ready yet.
            loginViewHolder.etUsername.setText("No User");
        }
    }


    public void setUser(List<Login> users) {
        mUser = users;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mUser != null)
            return mUser.size();
        else
            return 0;
    }
}
