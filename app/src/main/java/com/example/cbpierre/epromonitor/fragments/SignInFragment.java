package com.example.cbpierre.epromonitor.fragments;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.LoginActivity;
import com.example.cbpierre.epromonitor.R;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SignInFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SignInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SignInFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // fragment ui parameters
    private EditText username;
    private EditText password;
    private EditText confirmPass;
    private Button btnSignIn;

    // Declaration ViewModel variable
    LoginViewModel mLoginViewModel;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SignInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SignInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SignInFragment newInstance(String param1, String param2) {
        SignInFragment fragment = new SignInFragment();
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
        // ViewModelProviders
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_in, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        username = view.findViewById(R.id.etUsername);
        password = view.findViewById(R.id.etPassword);
        confirmPass = view.findViewById(R.id.etConfirmPassword);
        btnSignIn = view.findViewById(R.id.btnSignIn);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertUserToLocal();
            }
        });
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

    public void insertUserToLocal() {
        String mUsername = username.getText().toString().trim();
        String mPassword = password.getText().toString().trim();
        String mConfirmPass = confirmPass.getText().toString().trim();

        if (!(TextUtils.isEmpty(mUsername) && TextUtils.isEmpty(mPassword) && TextUtils.isEmpty(mConfirmPass))) {
            if (!mPassword.equals(mConfirmPass)) {

                Toast.makeText(getContext(), " The Confirmation Password doesn't match the Password, Please try again !!! ", Toast.LENGTH_LONG).show();
            } else {
                mLoginViewModel.insertLogin(new Login(mUsername, mPassword));
                getActivity().getSupportFragmentManager().popBackStack(getActivity().getSupportFragmentManager().getBackStackEntryAt(0).getId(), FragmentManager.POP_BACK_STACK_INCLUSIVE);
                //getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.flContent, new UserFragment()).addToBackStack(null).commit();
                username.setText("");
                password.setText("");
                confirmPass.setText("");
                Toast.makeText(getContext(), "Sign In !!!", Toast.LENGTH_LONG).show();
            }


        } else {
            Toast.makeText(getContext(), "Please enter your Username , Password and Confirm your Password", Toast.LENGTH_LONG).show();
        }
    }

}
