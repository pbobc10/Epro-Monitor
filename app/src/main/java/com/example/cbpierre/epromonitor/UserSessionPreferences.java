package com.example.cbpierre.epromonitor;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.cbpierre.epromonitor.LoginActivity;

public class UserSessionPreferences {

    //SharedPreferences variable
    SharedPreferences mSettings;

    //SharedPreferences Editor
    SharedPreferences.Editor editor;

    //context
    Context mContext;

    // Shared preferences file name
    public static final String PREFERENCE_FILE_NAME = "Settings";

    // All Shared Preferences Keys
    public static final String IS_USER_LOGIN = "isUserLoggedIn";

    //Username
    public static final String USERNAME = "username";

    //constructor
    public UserSessionPreferences(Context context) {
        this.mContext = context;
        mSettings = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
        //create an Editor instance of SharedPreferences
        editor = mSettings.edit();
    }

    //Create login session
    public void createUserLoginSession(String username) {
        // Storing login value as TRUE
        editor.putBoolean(IS_USER_LOGIN, true);

        //Storing username
        editor.putString(USERNAME, username);

        // commit changes
        editor.commit();

    }

    public boolean isLoggedInUser() {
        return mSettings.getBoolean(IS_USER_LOGIN, false);
    }

    public boolean checkLogin() {
        if (isLoggedInUser()) {

            // user is not logged in redirect him to Login Activity
            Intent intent = new Intent(mContext, MainActivity.class);
            // Closing all the Activities from stack
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            // Add new Flag to start new Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);

            return true;
        }
        return false;
    }

    public void logoutUser() {
        editor.clear();
        editor.commit();

        // After logout redirect user to  Login Activity
        Intent intent = new Intent(mContext, LoginActivity.class);
        // Closing all the Activities from stack
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        // Add new Flag to start new Activity
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    public String getUserDetails() {
        return mSettings.getString(USERNAME,null);
    }
}
