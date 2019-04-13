package com.example.cbpierre.epromonitor;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.cbpierre.epromonitor.dao.ContactDao;
import com.example.cbpierre.epromonitor.dao.LoginDao;
import com.example.cbpierre.epromonitor.dao.PostLoginDao;
import com.example.cbpierre.epromonitor.models.Contact;
import com.example.cbpierre.epromonitor.models.Login;
import com.example.cbpierre.epromonitor.models.PostLogin;


@Database(entities = {Login.class, Contact.class, PostLogin.class}, version = 1)
@TypeConverters({DateTypeConverter.class})
public abstract class EproMonitorRoomDatabase extends RoomDatabase {

    private static volatile EproMonitorRoomDatabase INSTANCE;
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {

                @Override
                public void onOpen(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    // new PopulateDbAsync(INSTANCE).execute();
                    Log.d("test", "room3");

                    //test to insert contact
                    // new InsertContactTask(INSTANCE).execute();
                }
            };

    public static EproMonitorRoomDatabase getDatabase(final Context context) {
        Log.d("test", "room1");
        if (INSTANCE == null) {
            synchronized (EproMonitorRoomDatabase.class) {
                if (INSTANCE == null) {
                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), EproMonitorRoomDatabase.class, "word_database").addCallback(sRoomDatabaseCallback).build();
                    Log.d("test", "room2");
                }

            }
        }
        return INSTANCE;

    }

    public abstract LoginDao loginDao();

    public abstract ContactDao contactDao();

    // To repopulate the database whenever the app is started

    public abstract PostLoginDao postLoginDao();


    /*public static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final LoginDao loginDao;

        public PopulateDbAsync(EproMonitorRoomDatabase db) {
            loginDao = db.loginDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            Login login = new Login("Test ePM", "testepm","SAM-004","4837489328734890","");
            loginDao.insert(login);
            Log.d("test", "room4");
            return null;
        }
    }*/

    //test to insert contacts
   /* private static class InsertContactTask extends AsyncTask<Void, Void, Void> {

        private ContactDao mContact;

        public InsertContactTask(EproMonitorRoomDatabase db) {
            this.mContact = db.contactDao();
        }

        @Override
        protected Void doInBackground(Void... contacts) {
            Contact contact = new Contact(102, "test", "O'Black", "David", "NON APLLICABLE", "PRESCRIPTEUR", "OBSTETRIQUE ET GYNECOLOGIE",
                    "F1", "33515777", "33515777", "33515777", "oblack2012@gmail.com", 0, "BOB", null, "BOB", null, "BOB", null, 0, "BOB", null);

            mContact.insert(contact);
            return null;
        }
    }*/

}
