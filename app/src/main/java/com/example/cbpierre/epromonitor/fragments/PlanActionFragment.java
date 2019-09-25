package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.PlanAction;
import com.example.cbpierre.epromonitor.viewModels.PlanActionViewModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link PlanActionFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link PlanActionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PlanActionFragment extends Fragment {
    private TextView txtCapitalVisites, txtTotalVisites, txtDateDebut, txtDateFin;
    private Button btnContactPa;
    private PlanActionViewModel planActionViewModel;
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public PlanActionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PlanActionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PlanActionFragment newInstance(String param1, String param2) {
        PlanActionFragment fragment = new PlanActionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_plan_action, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        txtCapitalVisites = view.findViewById(R.id.txtCapitalVisites);
        txtTotalVisites = view.findViewById(R.id.txtTotalVisites);
        txtDateDebut = view.findViewById(R.id.txtDateDebut);
        txtDateFin = view.findViewById(R.id.txtDateFin);
        btnContactPa = view.findViewById(R.id.btnPaContact);

        //ViewModelProviders
        planActionViewModel = ViewModelProviders.of(this).get(PlanActionViewModel.class);

        planActionViewModel.getAllPlanAction().observe(this, new Observer<List<PlanAction>>() {
            @Override
            public void onChanged(@Nullable List<PlanAction> planActions) {
                if (planActions != null) {
                    if (planActions.size() > 0) {
                        PlanAction planAction = planActions.get(0);
                        populatePa(planAction);
                    } else
                        Toast.makeText(getContext(), "S'il vous plaît Téléchargement le PA", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnContactPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PaContactFragment fragment = new PaContactFragment();
                replaceFragment(fragment);
            }
        });
    }

    public void populatePa(PlanAction planAction) {
        txtCapitalVisites.setText(planAction.getCapVisite().toString());
        txtTotalVisites.setText(planAction.getTotalVisite().toString());
        txtDateDebut.setText(date(planAction.getDebut()));
        txtDateFin.setText(date(planAction.getFin()));
        Log.d("--date", date("2019-09-27T00:00:00").toString());
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

    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
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
