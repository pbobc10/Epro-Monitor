package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.repositories.LoginRepository;

import java.util.List;

public class LoginViewModel extends AndroidViewModel {

    private LoginRepository loginRepository;
    private LiveData<List<Login>> mAllUsers;

    private LoginRepository.OnFinishedListener listener;

    public LoginViewModel(@NonNull Application application) {
        super(application);
        loginRepository = new LoginRepository(application);
        mAllUsers = loginRepository.getmAllUsers();
    }


    public LiveData<List<Login>> getAllUsers() {
        return mAllUsers;
    }

    public void insertLogin(Login login) {

        loginRepository.insertLogin(login);
    }

    public void setOnFinishedListener(LoginRepository.OnFinishedListener listener) {
        this.listener = listener;
    }

    public void findCountUser(String username, String password) {
        loginRepository.setOnFinishedListener(listener);
        loginRepository.findCountUser(username, password);
    }
}
