package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.example.cbpierre.epromonitor.AsyncResult;
import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.models.Login;

import java.util.List;

public class LoginRepository {
    private static OnFinishedListener listener;
    private static OnSearchCodeMobListener mobListener;
    private LoginDao loginDao;
    private LiveData<List<Login>> mAllUsers;

    //constructor
    public LoginRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        loginDao = db.loginDao();
        mAllUsers = loginDao.getAllUusers();
    }

    public void setOnFinishedListener(OnFinishedListener listener) {
        LoginRepository.listener = listener;
    }

    public void setOnSearchCodeMobListener(OnSearchCodeMobListener mobListener) {
        LoginRepository.mobListener = mobListener;
    }

    public LiveData<List<Login>> getmAllUsers() {
        return mAllUsers;
    }

    public void insertLogin(Login newLogin) {
        new insertAsyncTask(loginDao).execute(newLogin);
    }

    public void findLogin(String name, String pass) {
        LoginAsyncTask task = new LoginAsyncTask(loginDao);
        task.execute(name, pass);
    }

    public void findCountUser(String name, String pass) {
        CountAsyncTask task = new CountAsyncTask(loginDao);
        task.execute(name, pass);
    }

    public void findCodeMob(String username) {
        FindCodeMobAsyncTask task = new FindCodeMobAsyncTask(loginDao);
        task.execute(username);
    }

    public interface OnFinishedListener {
        void OnFinished(int count);
    }

    public interface OnSearchCodeMobListener {
        void OnSearchCodeMob(String codeMob);
    }

    //****************************** ASYNC CLASS*******************************************
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoginDao inLoginDao;

        public PopulateDbAsync(LoginDao loginDao) {
            inLoginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Login login = new Login("test", "test123", "", "", "");
            inLoginDao.insert(login);
            return null;
        }
    }

    // insert user to table
    private static class insertAsyncTask extends AsyncTask<Login, Void, Void> {

        private LoginDao mAsyncTask;

        public insertAsyncTask(LoginDao loginDao) {
            mAsyncTask = loginDao;
        }

        @Override
        protected Void doInBackground(Login... logins) {
            mAsyncTask.insert(logins[0]);
            return null;
        }
    }

    //select user from table
    private static class LoginAsyncTask extends AsyncTask<String, Void, Login> {
        public AsyncResult delegate = null;
        private LoginDao mLoginTask;

        public LoginAsyncTask(LoginDao loginDao) {
            mLoginTask = loginDao;
        }

        @Override
        protected Login doInBackground(String... strings) {
            return mLoginTask.findLoginUserByUsernameAndPassword(strings[0], strings[1]);
        }

        @Override
        protected void onPostExecute(Login login) {
            super.onPostExecute(login);
            delegate.asyncFinished(login);

        }
    }

    // count user from table
    private static class CountAsyncTask extends AsyncTask<String, Void, Integer> {
        public AsyncResult delegate = null;
        private LoginDao mLoginTask;

        public CountAsyncTask(LoginDao loginDao) {
            mLoginTask = loginDao;
        }

        @Override
        protected Integer doInBackground(String... strings) {
            return mLoginTask.findCountUser(strings[0], strings[1]);

        }

        @Override
        protected void onPostExecute(Integer count) {
            super.onPostExecute(count);
            System.out.println("====================OnpostExecute" + count);
            //countUser = count;
            //delegate.asyncUserCount(count);
            if (listener != null)
                listener.OnFinished(count);
        }
    }

    public static class FindCodeMobAsyncTask extends AsyncTask<String, Void, String> {
        private LoginDao mLoginTask;

        public FindCodeMobAsyncTask(LoginDao loginDao) {
            mLoginTask = loginDao;
        }

        @Override
        protected String doInBackground(String... strings) {
            return mLoginTask.findCodMob(strings[0]);
        }

        @Override
        protected void onPostExecute(String codeMob) {
            super.onPostExecute(codeMob);
            if (mobListener != null)
                mobListener.OnSearchCodeMob(codeMob);
        }
    }
}
