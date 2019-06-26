package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.CompleteContact;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.viewModels.SharedViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactDetailFragment.OnContactDetailInteractionListener} interface
 * to handle interaction events.
 */
public class ContactDetailFragment extends Fragment {
    TextView titre, specialite, nature, secteur, tel, tel2, tel3, email, creePar, creeLe, modifiePar, modifieLe, validePar, valideLe, adresse;

    private OnContactDetailInteractionListener mListener;
    private SharedViewModel sharedViewModel;

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedViewModel = ViewModelProviders.of(getActivity()).get(SharedViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_contact_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setHasOptionsMenu(true);
        titre = view.findViewById(R.id.txtContactTitre);
        specialite = view.findViewById(R.id.txtContactSpecialite);
        nature = view.findViewById(R.id.txtContactNature);
        secteur = view.findViewById(R.id.txtContactSecteur);
        tel = view.findViewById(R.id.txtContactTel);
        tel2 = view.findViewById(R.id.txtContactTel2);
        tel3 = view.findViewById(R.id.txtContactTel3);
        email = view.findViewById(R.id.txtContactEmail);
        creePar = view.findViewById(R.id.txtCreePar);
        creeLe = view.findViewById(R.id.txtCreatedDate);
        modifiePar = view.findViewById(R.id.txtModiePar);
        modifieLe = view.findViewById(R.id.txtModifiedDate);
        validePar = view.findViewById(R.id.txtValidePar);
        valideLe = view.findViewById(R.id.txtValidatedDate);

        sharedViewModel.getContactMutableLiveData().observe(this, new Observer<CompleteContact>() {
            @Override
            public void onChanged(@Nullable CompleteContact completeContact) {
                populateContactDetail(completeContact);
            }
        });
    }

    public void populateContactDetail(CompleteContact completeContact) {
        //Toast.makeText(getContext(), "===TEST TEST " + completeContact.getNom(), Toast.LENGTH_SHORT).show();
        String phone1 = completeContact.getPhone1() == null ? "" : completeContact.getPhone1();
        String phone2 = completeContact.getPhone2() == null ? "" : "/ " + completeContact.getPhone2();
        String phone3 = completeContact.getPhone3() == null ? "" : "/ " + completeContact.getPhone3();
        String prenom = completeContact.getPrenom() == null ? "" : completeContact.getPrenom();
        String titreNa = completeContact.getTitre().equals("NA") ? "" : completeContact.getTitre() + ".";
        String info = titreNa + " " + completeContact.getNom() + " " + prenom;
        titre.setText(info);
        specialite.setText(completeContact.getNomSpecialite());
        nature.setText(completeContact.getNomNature());
        secteur.setText(completeContact.getNomSecteur());
        tel.setText(phone1);
        tel2.setText(phone2);
        tel3.setText(phone3);
        email.setText(completeContact.getEmail());
        creePar.setText(completeContact.getCree_par());
        creeLe.setText(completeContact.getCree_le());
        modifiePar.setText(completeContact.getModifie_par());
        modifieLe.setText(completeContact.getModifie_le());
        validePar.setText(completeContact.getValidateur());
        valideLe.setText(completeContact.getDate_maj_valide());

        //test for passing new contact or old contact
        if (completeContact.getConId() != null)
            onButtonPressed(completeContact.getConId());
        else
            onButtonPressed(null);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Integer id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnContactDetailInteractionListener) {
            mListener = (OnContactDetailInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnContactDetailInteractionListener");
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
    public interface OnContactDetailInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Integer id);
    }
}
