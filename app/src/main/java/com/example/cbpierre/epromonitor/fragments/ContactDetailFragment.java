package com.example.cbpierre.epromonitor.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Contact;



/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactDetailFragment extends Fragment {
    TextView titre,nom,prenom, specialite, nature, secteur, tel, email, adresse;

    private OnFragmentInteractionListener mListener;

    public ContactDetailFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
        titre = view.findViewById(R.id.txtContactTitre);
        nom = view.findViewById(R.id.txtContactNom);
        prenom = view.findViewById(R.id.txtContactPrenom);
        specialite = view.findViewById(R.id.txtContactSpecialite);
        nature = view.findViewById(R.id.txtContactNature);
        secteur = view.findViewById(R.id.txtContactSecteur);
        tel = view.findViewById(R.id.txtContactTel);
        email = view.findViewById(R.id.txtContactEmail);
        adresse = view.findViewById(R.id.txtContactAddress);


        Bundle args = this.getArguments();
        if (args != null) {
            Contact contact = (Contact) args.getSerializable("CONTACT");
            Toast.makeText(getContext(), "===test " + contact.getNom(), Toast.LENGTH_SHORT).show();
            titre.setText(contact.getTitre());
            nom.setText(contact.getNom());
            prenom.setText(contact.getPrenom());
            specialite.setText(contact.getSpecialite());
            nature.setText(contact.getNature());
            secteur.setText(contact.getSecteur());
            tel.setText(contact.getPhone1());
            email.setText(contact.getEmail());
            //adresse.setText(contact.getAdress);
        } else
            Toast.makeText(getContext(), "bundle null", Toast.LENGTH_SHORT).show();

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
