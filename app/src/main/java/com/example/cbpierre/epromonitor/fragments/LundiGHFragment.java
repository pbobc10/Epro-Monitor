package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.UserSessionPreferences;
import com.example.cbpierre.epromonitor.adapters.JoinContactGhSVAdapter;
import com.example.cbpierre.epromonitor.models.JoinContactGhSV;
import com.example.cbpierre.epromonitor.models.JoinGHJourStatutRef;
import com.example.cbpierre.epromonitor.viewModels.GHJourContactViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHJourViewModel;
import com.example.cbpierre.epromonitor.viewModels.GHViewModel;
import com.example.cbpierre.epromonitor.viewModels.ShareGHId;
import com.example.cbpierre.epromonitor.viewModels.ShareJoinContactGhSV;
import com.example.cbpierre.epromonitor.viewModels.ShareJourInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LundiGHFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class LundiGHFragment extends Fragment {
    private GHViewModel ghViewModel;
    private GHJourViewModel ghJourViewModel;
    private GHJourContactViewModel ghJourContactViewModel;
    private ShareJoinContactGhSV shareJoinContactGhSV;
    private ShareGHId shareGHId;
    private ShareJourInfo shareJourInfo;
    private TextView jour, statutJour, rapportComplete;
    private String modifieLe, modifiePar;
    private RecyclerView rvContactGH;
    private JoinContactGhSVAdapter joinContactGhSVAdapter;
    private JoinGHJourStatutRef day;
    private UserSessionPreferences userSessionPreferences;

    private OnFragmentInteractionListener mListener;

    public LundiGHFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ghViewModel = ViewModelProviders.of(this).get(GHViewModel.class);
        ghJourViewModel = ViewModelProviders.of(this).get(GHJourViewModel.class);
        ghJourContactViewModel = ViewModelProviders.of(this).get(GHJourContactViewModel.class);
        shareGHId = ViewModelProviders.of(getActivity()).get(ShareGHId.class);
        shareJoinContactGhSV = ViewModelProviders.of(getActivity()).get(ShareJoinContactGhSV.class);
        shareJourInfo = ViewModelProviders.of(getActivity()).get(ShareJourInfo.class);

        //SharePreference
        userSessionPreferences = new UserSessionPreferences(getContext());
        modifiePar = userSessionPreferences.getUserDetails();

        //Date
        String parttern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(parttern);
        modifieLe = simpleDateFormat.format(new Date());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lundi_gh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        FloatingActionButton fabChoiceContactGH = view.findViewById(R.id.fabChoiceContactGH);
        rvContactGH = view.findViewById(R.id.rvJourContact);
        jour = view.findViewById(R.id.txtJour);
        statutJour = view.findViewById(R.id.txtStatutJour);
        rapportComplete = view.findViewById(R.id.txtRapportComplete);
        rapportComplete.setVisibility(View.GONE);

        joinContactGhSVAdapter = new JoinContactGhSVAdapter(getContext());
        rvContactGH.setLayoutManager(new LinearLayoutManager(getContext()));
        rvContactGH.setAdapter(joinContactGhSVAdapter);

        ghJourContactViewModel.getAllJourContact().observe(this, new Observer<List<JoinContactGhSV>>() {
            @Override
            public void onChanged(@Nullable List<JoinContactGhSV> joinContactGhSVS) {
                joinContactGhSVAdapter.setJoinContactGhSVS(joinContactGhSVS);
            }
        });

        shareGHId.getGHId().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer integer) {
                ghJourViewModel.setGhId(integer);
            }
        });
        ghJourViewModel.getAllJour().observe(this, new Observer<List<JoinGHJourStatutRef>>() {
            @Override
            public void onChanged(@Nullable List<JoinGHJourStatutRef> joinGHJourStatutRefs) {
                if (joinGHJourStatutRefs != null) {
                    day = joinGHJourStatutRefs.get(0);
                    jour.setText(date(day.getJour()));
                    statutJour.setText(day.getNom());
                    if (day.getRapport_complete())
                        rapportComplete.setVisibility(View.VISIBLE);
                    ghJourContactViewModel.setAllJourContactMutable(day.getJour());
                }
            }
        });


        //delete gh_jour_contact
        joinContactGhSVAdapter.setOnGHJourContactListener(new JoinContactGhSVAdapter.OnGHJourContactListener() {
            @Override
            public void onGhJourContactClick(int ghId, int conId) {
                ghJourContactViewModel.deleteJourContact(ghId, conId);
                // update GH apres supression
                ghViewModel.updateGH(String.valueOf(ghId), modifiePar, modifieLe);
                Toast.makeText(getContext(), "contact suprime", Toast.LENGTH_SHORT).show();
            }
        });

        joinContactGhSVAdapter.setOnGhContactListener(new JoinContactGhSVAdapter.OnGHContactListener() {
            @Override
            public void onGhContactClick(JoinContactGhSV joinContactGhSV) {
                if (!joinContactGhSV.getStatut_temporel().equals("PROCHAIN")) {
                    shareJoinContactGhSV.setJoinContactGhSVMutableLiveData(joinContactGhSV);
                    replaceFragment(new RapportFragment());
                } else {
                    Toast.makeText(getContext(), "Pas de rapport pour le GH Prochain!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //fab
        fabChoiceContactGH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((!day.getGh_complete() && !day.getRapport_complete()) || ((day.getGh_complete() && !day.getRapport_complete()))) {
                    replaceFragment(new ChoiceContactGHFragment());
                    shareJourInfo.setGhJourInfo(day);
                } else
                    Toast.makeText(getContext(), "Impossible d'ajouter de nouveau Contact.\n  Le GH et le Rapport ont été Complété.", Toast.LENGTH_LONG).show();
                //Toast.makeText(getContext(), "jour: " + day.getJour(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
