package com.example.mvpapp.data.preference;

import android.content.Context;
import android.content.SharedPreferences;

public final class SharedPreferencesManager {
    private  static final String MY_APP_PREFERENCES = "ca7eed88-2409-4de7-b529-52598af76734";
    private static final String IS_USER_LOGIN = "is_user_login";
    private static final String USER_LOGIN_ID = "user_login_id";

    private final SharedPreferences sharedPrefs;
    private static SharedPreferencesManager instance;

    private SharedPreferencesManager(Context context){
        //using application context just to make sure we don't leak any activities
        sharedPrefs = context.getApplicationContext().getSharedPreferences(MY_APP_PREFERENCES, Context.MODE_PRIVATE);
    }

    public static synchronized SharedPreferencesManager getInstance(Context context){
        if(instance == null) {
            instance = new SharedPreferencesManager(context);
        }
        return instance;
    }

    public boolean isUserLogin(){
        return sharedPrefs.getBoolean(IS_USER_LOGIN, false);
    }

    public void setIsUserLogin(boolean value){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putBoolean(IS_USER_LOGIN, value);
        editor.apply();
    }
    public void setSaveUserID(String userID){
        SharedPreferences.Editor editor = sharedPrefs.edit();
        editor.putString(USER_LOGIN_ID, userID);
        editor.apply();
    }

    public String getUserID(){
        return sharedPrefs.getString(USER_LOGIN_ID, "false");
    }

}