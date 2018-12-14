package com.example.cbpierre.epromonitor;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cbpierre.epromonitor.repositories.LoginRepository;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity  {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;
    private ProgressDialog pDialog;

    // Declaration ViewModel variable
    LoginViewModel mLoginViewModel;
    LoginRepository loginRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.etUsername);
        edtPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkNetworkConnection()) {
                    loginFromNetWork();
                } else {
                    loginFromLocal();
                }

            }
        });
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.setOnFinishedListener(new LoginRepository.OnFinishedListener() {
            @Override
            public void OnFinished(int count) {
                if (count == 1) {
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                } else {
                    Toast.makeText(getApplicationContext(), " Invalid User", Toast.LENGTH_LONG).show();
                }
            }
        });
        // Login login =new Login("bob","bob123");
        //  mLoginViewModel.insertLogin(login);
        //  loginRepository.populateLogin();
    }

    public void loginFromNetWork() {

        pDialog.setMessage("Logging in ...");
        showDialog();

        // Formulate the request and handle the response.
        StringRequest stringRequest = new StringRequest(Request.Method.POST, AppConfig.URL_LOGIN, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                hideDialog();
                Log.d("volleyTest", "test1");
                if (response.trim().equals("success")) {
                    Toast.makeText(getApplicationContext(), "Login Successfully", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_LONG).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                hideDialog();
                Toast.makeText(getApplicationContext(), " Error: " + error.getMessage(), Toast.LENGTH_LONG).show();
                Log.d("volleyTest", "test2");
            }
        }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                String username = edtUsername.getText().toString().trim();
                String password = edtPassword.getText().toString().trim();

                params.put("username", username);
                params.put("password", password);
                Log.d("volleyTest", username);
                //return super.getParams();
                return params;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));

        AppVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(stringRequest);
    }


    public void loginFromLocal() {
        hideDialog();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        //mLoginViewModel.findLoginUser(username, password);



        if (!(TextUtils.isEmpty(username) && TextUtils.isEmpty(password))) {
            mLoginViewModel.findCountUser(username, password);
        }else {
            Toast.makeText(getApplicationContext(),"Please enter your Username and Password ",Toast.LENGTH_LONG).show();
        }

    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return (networkInfo != null && networkInfo.isConnected());
    }

    public void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.hide();
        }
    }

}
