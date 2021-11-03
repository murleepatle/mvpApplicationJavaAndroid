package com.example.mvpapp.data.repository;

import android.content.ContentValues;

import com.example.mvpapp.data.model.UserDetail;
import com.example.mvpapp.data.sqlite.UserDbHelper;
import com.example.mvpapp.data.sqlite.UserFieldContract;

/**
 * This class is use for a Sqlite Operations.
 * in this class we can perform all sqlite query like insert delete update etc.
 * this class is access sqlite database class.
 */
public class UserDataSqliteRepository {
    private static UserDataSqliteRepository instance;
    private final UserDbHelper sqliteHelper;

    public UserDataSqliteRepository(UserDbHelper sqliteHelper) {
        this.sqliteHelper = sqliteHelper;
    }

    public static UserDataSqliteRepository getInstance(UserDbHelper sqliteHelper) {
        if (instance == null) {
            instance = new UserDataSqliteRepository(sqliteHelper);
        }
        return instance;
    }

    /**
     * This method is use for register user in app db.
     * if user data inserted successfully it will return ture otherwise it will send false.
     *
     * @param fullName full name of user
     * @param userId   unique user Id of user
     * @param email    email address of user
     * @param password password of user
     * @param mobileNo mobile number of user
     * @param address  postal address of user
     * @return true/ false
     */
    public boolean registerUser(String fullName, String userId, String email, String password, String mobileNo, String address) {
        ContentValues values = new ContentValues();
        values.put(UserFieldContract.UserEntry.COLUMN_FULL_NAME, fullName);
        values.put(UserFieldContract.UserEntry.COLUMN_USER_ID, userId);
        values.put(UserFieldContract.UserEntry.COLUMN_EMAIL, email);
        values.put(UserFieldContract.UserEntry.COLUMN_PASSWORD, password);
        values.put(UserFieldContract.UserEntry.COLUMN_MOBILE_NO, mobileNo);
        values.put(UserFieldContract.UserEntry.COLUMN_ADDRESS, address);
        return sqliteHelper.insertData(UserFieldContract.UserEntry.TABLE_NAME, values);
    }


    /**
     * This method is use for check the user Id and password is match or not.
     * in this method we perform login task from sqlite db
     *
     * @param userId   unique user Id of user
     * @param password password of user
     * @return true/false
     */
    public boolean checkUserCredentials(String userId, String password) {
        return sqliteHelper.verifyCredential(userId, password);
    }

    /**
     * This method is use for the check user is exits or not in our Sqlite Database
     *
     * @param userId user unique identification Id
     * @return true or false
     */
    public boolean checkUserExist(String userId) {
        return sqliteHelper.checkUserExistOrNot(userId);
    }


    public UserDetail getUserDetail(String userID) {
        return sqliteHelper.getUserDetail(userID);
    }
}
