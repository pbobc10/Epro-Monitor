package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.adapters.RecommandationAdapter;
import com.example.cbpierre.epromonitor.models.Recommandation;
import com.example.cbpierre.epromonitor.viewModels.RecommandationViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class RecommandationFragment extends Fragment {
    private RecommandationViewModel recommandationViewModel;
    private ArrayList<Recommandation> recommandationArrayList;
    private RecommandationAdapter recommandationAdapter;
    private RecyclerView recyclerView;

    public RecommandationFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        recommandationViewModel = ViewModelProviders.of(this).get(RecommandationViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_recommandation, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.rvRecommandation);
        recommandationAdapter = new RecommandationAdapter(getContext());
        recyclerView.setAdapter(recommandationAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recommandationViewModel.getAllRecommandation().observe(this, new Observer<List<Recommandation>>() {
            @Override
            public void onChanged(@Nullable List<Recommandation> recommandations) {
                if (recommandations != null)
                    if (recommandations.size() > 0)
                        recommandationAdapter.setRecommandationList(recommandations);
                    else
                        Toast.makeText(getContext(), "Pas de nouvelle recommandation", Toast.LENGTH_SHORT).show();
            }
        });
        recommandationAdapter.setListener(new RecommandationAdapter.OnRecommandationListener() {
            @Override
            public void onRecommadationClick(Recommandation recommandation) {
                recommandationViewModel.deleteRecommandation(recommandation);
            }
        });
    }
}
