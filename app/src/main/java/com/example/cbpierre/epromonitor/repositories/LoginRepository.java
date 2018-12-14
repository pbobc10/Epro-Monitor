package com.example.cbpierre.epromonitor.repositories;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import com.example.cbpierre.epromonitor.AsyncResult;
import com.example.cbpierre.epromonitor.EproMonitorRoomDatabase;
import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.models.Login;

public class LoginRepository {
    private LoginDao loginDao;
    private static OnFinishedListener listener;

    public interface OnFinishedListener {
        void OnFinished(int count);
    }

    //constructor
    public LoginRepository(Application application) {
        EproMonitorRoomDatabase db = EproMonitorRoomDatabase.getDatabase(application);
        loginDao = db.loginDao();
    }

    public void setOnFinishedListener(OnFinishedListener listener) {
        LoginRepository.listener = listener;
    }


    public void insertLogin(Login newLogin) {
        new insertAsyncTask(loginDao).execute(newLogin);
    }

    public void findLogin(String name, String pass) {
        LoginAsyncTask task = new LoginAsyncTask(loginDao);
        //task.delegate = this;
        task.execute(name, pass);
    }

    public void findCountUser(String name, String pass) {
        CountAsyncTask task = new CountAsyncTask(loginDao);
        //task.delegate = this;
        task.execute(name, pass);
      /*  while(task.getStatus().equals(AsyncTask.Status.FINISHED)){
            System.out.println(task.getStatus().toString());

        }*/

    }


    //****************************** ASYNC CLASS*******************************************
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoginDao inLoginDao;

        public PopulateDbAsync(LoginDao loginDao) {
            inLoginDao = loginDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Login login = new Login("test", "test123");
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
        private LoginDao mLoginTask;
        public AsyncResult delegate = null;

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
        private LoginDao mLoginTask;
        public AsyncResult delegate = null;

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
            if(listener != null)
                listener.OnFinished(count);
        }
    }
}
