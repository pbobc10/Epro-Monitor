package com.example.cbpierre.epromonitor;

import android.Manifest;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.models.PostLogin;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;
import com.example.cbpierre.epromonitor.viewModels.PostViewModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

public class SignInActivity extends AppCompatActivity {
    // Declaration ViewModel variable
    LoginViewModel mLoginViewModel;
    PostViewModel mPostViewModel;

    private Context context;

    // activity ui parameters
    private EditText username;
    private EditText password;
    private EditText codMob;
    private Button btnSignIn;

    private String mUsername, mPassword, mCodMob, imei1, imei2;

    private UserSessionPreferences userSessionPreferences;

    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        //user session
        userSessionPreferences = new UserSessionPreferences(SignInActivity.this);

        // ViewModelProviders
        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mPostViewModel = ViewModelProviders.of(this).get(PostViewModel.class);

        //Progress Dialog
        pDialog = new ProgressDialog(SignInActivity.this, ProgressDialog.STYLE_SPINNER);
        pDialog.setCancelable(false);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        codMob = findViewById(R.id.etCodMob);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mUsername = username.getText().toString().trim();
                mPassword = password.getText().toString().trim();
                mCodMob = codMob.getText().toString().trim();
                imei1 = getImeiId();
                imei2 = "";

                if (isValidate()) {
                    //set title of the dialog
                    pDialog.setTitle("Sign in ...");
                    //set message of the dialog
                    pDialog.setMessage("Please wait.");
                    //show dialog
                    showDialog();

                    if (checkNetworkConnection()) {
                        if (isOnline()) {
                            insertUserToLocal();
                            Log.d("networkcheck", "pass2");
                        } else {
                            Toast.makeText(getApplicationContext(), "You Don't have Network Connection ...", Toast.LENGTH_LONG).show();
                            //dismiss dialog
                            hideDialog();
                        }
                    } else {
                        Toast.makeText(getApplicationContext(), "Check if your Data or Wifi is Opened ...", Toast.LENGTH_LONG).show();
                        //dismiss dialog
                        hideDialog();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "Please enter your Username or Password or Mobile Code", Toast.LENGTH_LONG).show();
                    //dismiss dialog
                    hideDialog();
                }

            }
        });

    }

    public boolean isValidate() {
        boolean validate = true;
        if (TextUtils.isEmpty(mUsername)) {
            username.setError("Merci d'entrer votre nom D'utilisateur!");
            validate = false;
        } else if (TextUtils.isEmpty(mPassword)) {
            password.setError("Merci d'entrer votre mot de passe!");
            validate = false;
        } else if (TextUtils.isEmpty(mCodMob)) {
            codMob.setError("Merci d'entrer le code du mobile!");
            validate = false;
        }
        return validate;
    }

    public void insertUserToLocal() {
        //HashMap param Volley request
        HashMap<String, String> params = new HashMap<>();
        params.put("UserName", mUsername);
        params.put("Password", mPassword);
        params.put("CodeMob", mCodMob);
        params.put("Imei1", imei1);
        params.put("Imei2", imei2);

        // Formulate the request and handle the response.
        CustomRequest customRequest = new CustomRequest(Request.Method.POST, AppConfig.URL_LOGIN, params, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                Log.d("volleyTest", "pass1");
                Log.d("volleyResponse", response.toString());
                //test validate
                try {
                    if (response.getString("msg").equals("SUCCÈS")) {
                        userSessionPreferences.createUserLoginSession(mUsername);
                        mLoginViewModel.insertLogin(new Login(mUsername, mPassword, mCodMob, imei1, null));
                        mPostViewModel.insertPostLogin(new PostLogin(response));

                        //dismiss dialog
                        hideDialog();

                        //start MainActivity
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        //dismiss dialog
                        hideDialog();
                        Toast.makeText(getApplicationContext(), response.getString("msg"), Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("volleyTest", "JSONOBJECT ERROR :" + error.toString());
                if (error instanceof AuthFailureError)
                    Toast.makeText(getApplicationContext(), "Username or Password  or Code Mobile incorrect :", Toast.LENGTH_SHORT).show();
                else if (error instanceof NetworkError)
                    Toast.makeText(getApplicationContext(), "Network Error! Can't reach https://disprophar.net", Toast.LENGTH_SHORT).show();
                else if (error instanceof ParseError)
                    Toast.makeText(getApplicationContext(), "JSON Parse Error!", Toast.LENGTH_SHORT).show();
                else if (error instanceof ServerError)
                    Toast.makeText(getApplicationContext(), "https://disprophar.net responded with an error response", Toast.LENGTH_SHORT).show();
                else if (error instanceof TimeoutError)
                    Toast.makeText(getApplicationContext(), "Connection or the socket timed out", Toast.LENGTH_LONG).show();
                //dismiss dialog
                hideDialog();
            }
        });
        // customRequest.setRetryPolicy(new DefaultRetryPolicy(0, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        AppVolleySingleton.getInstance(getApplicationContext()).addToRequestQueue(customRequest);
    }

    public String getImeiId() {
        //following code to get the IMEI
        TelephonyManager telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            // return;
            Log.d("IMEI_INFO", "pass1");
        }
        String device_id = telephonyManager.getDeviceId();
        // Log.d("IMEI_INFO", device_id);
        Log.d("IMEI_INFO", "pass2");

        return device_id;
    }

    public void showDialog() {
        if (!pDialog.isShowing()) {
            pDialog.show();
        }
    }

    public void hideDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
        }
    }

    public boolean checkNetworkConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        Log.d("networkcheck", "pass1");
        return (networkInfo != null && networkInfo.isConnected());
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.4.4");
            Integer exitValue = ipProcess.waitFor();
            Log.d("networkcheck", exitValue.toString());
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return false;
    }
}

