package com.example.cbpierre.epromonitor;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.models.Login;


@Database(entities = {Login.class}, version = 1)
public abstract class EproMonitorRoomDatabase extends RoomDatabase {

    public abstract LoginDao loginDao();

    private static volatile EproMonitorRoomDatabase INSTANCE;

 public    static EproMonitorRoomDatabase getDatabase(final Context context) {
        Log.d("test","room1");
        if (INSTANCE == null) {
            synchronized (EproMonitorRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EproMonitorRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                    Log.d("test","room2");
                }

            }
        }
        return INSTANCE;

    }


    // To repopulate the database whenever the app is started

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                    Log.d("test","room3");
                }
            };



    public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoginDao loginDao;

        public PopulateDbAsync(EproMonitorRoomDatabase db) {
            loginDao = db.loginDao();
        }

        @Override
        protected Void doInBackground(Void...  voids) {
            Login login = new Login("epro", "epro123");
            loginDao.insert(login);
            Log.d("test","room4");
            return null;
        }
    }

}
