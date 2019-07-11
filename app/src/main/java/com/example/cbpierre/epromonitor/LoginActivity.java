package com.example.cbpierre.epromonitor;

import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.cbpierre.epromonitor.repositories.LoginRepository;
import com.example.cbpierre.epromonitor.viewModels.LoginViewModel;


public class LoginActivity extends AppCompatActivity {

    // Declaration ViewModel variable
    LoginViewModel mLoginViewModel;
    UserSessionPreferences userSession;
    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin, btnSign;
    private String username, password;
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edtUsername = findViewById(R.id.etUsername);
        edtPassword = findViewById(R.id.etPassword);
        btnLogin = findViewById(R.id.btnLogin);

        //user session
        userSession = new UserSessionPreferences(getApplicationContext());
        //sign in btn
        btnSign = findViewById(R.id.btnSign);
        btnSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);
                edtUsername.setText("");
                edtPassword.setText("");
                startActivity(intent);
            }
        });

        //log in check
        if (userSession.checkLogin()) finish();

        //Progress Dialog
        pDialog = new ProgressDialog(this);
        pDialog.setCancelable(false);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginFromLocal();
            }
        });

        mLoginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        mLoginViewModel.setOnFinishedListener(new LoginRepository.OnFinishedListener() {
            @Override
            public void OnFinished(int count) {
                if (count == 1) {

                    //Create login session
                    userSession.createUserLoginSession(edtUsername.getText().toString());

                    // Start MainActivity
                    Intent i = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                    // Clear inputs
                    /*edtUsername.setText("");
                    edtPassword.setText("");*/
                } else {
                    Toast.makeText(getApplicationContext(), " Invalid User or Password", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void loginFromLocal() {
        pDialog.setTitle("Logging in ...");
        pDialog.setMessage("Please wait.");
        showDialog();

        username = edtUsername.getText().toString().trim();
        password = edtPassword.getText().toString().trim();

        //mLoginViewModel.findLoginUser(username, password);
        if (isValidate()) {
            mLoginViewModel.findCountUser(username, password);
        } else {
            Toast.makeText(getApplicationContext(), "Please enter your Username and Password ", Toast.LENGTH_LONG).show();
        }
        hideDialog();
    }

    public boolean isValidate() {
        boolean validate = true;
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("Merci d'entrer votre nom D'utilisateur!");
            validate = false;
        } else if (TextUtils.isEmpty(password)) {
            edtPassword.setError("Merci d'entrer votre mot de passe!");
            validate = false;
        }
        return validate;
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

}
