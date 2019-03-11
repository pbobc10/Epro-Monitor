package com.example.cbpierre.epromonitor.viewModels;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.example.cbpierre.epromonitor.models.PostLogin;
import com.example.cbpierre.epromonitor.repositories.PostLoginRepository;

import java.util.List;

public class PostViewModel extends AndroidViewModel {
    private PostLoginRepository postLoginRepository;
    private LiveData<List<PostLogin>> mAllPostLOgin;

    public PostViewModel(@NonNull Application application) {
        super(application);
        postLoginRepository = new PostLoginRepository(application);
        mAllPostLOgin = postLoginRepository.getmAllPostLogin();
    }

    public LiveData<List<PostLogin>> getAllPostLOgin() {
        return mAllPostLOgin;
    }

    public void insertPostLogin(PostLogin postLogin) {
        postLoginRepository.insertPost(postLogin);
    }
}
