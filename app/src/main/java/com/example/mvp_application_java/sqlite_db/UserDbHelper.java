package com.example.mvp_application_java.sqlite_db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * This class is Sqlite Helper class this class create ,upgrade and downgrade our database.
 */
public class UserDbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "UserDetail.db";
    private static final String TAG = UserDbHelper.class.getSimpleName();
    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + UserFieldContract.UserEntry.TABLE_NAME + " (" +
                    UserFieldContract.UserEntry.COLUMN_FULL_NAME + " TEXT," +
                    UserFieldContract.UserEntry.COLUMN_USER_ID + " TEXT PRIMARY KEY," +
                    UserFieldContract.UserEntry.COLUMN_EMAIL + " TEXT," +
                    UserFieldContract.UserEntry.COLUMN_PASSWORD + " TEXT," +
                    UserFieldContract.UserEntry.COLUMN_MOBILE_NO + " TEXT," +
                    UserFieldContract.UserEntry.COLUMN_ADDRESS + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + UserFieldContract.UserEntry.TABLE_NAME;

    public UserDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }


    /**
     * This method insert the user data, and it will return boolean value.
     *
     * @param table  carrier the table name.
     * @param values carrier the values of the user.
     * @return true/false.
     **/
    public boolean insertData(String table, ContentValues values) {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.insert(table, null, values);
        if (result == -1) {
            Log.d(TAG, "failed to save data!");
            return false;
        } else {
            Log.d(TAG, "save data successful");
            return true;
        }
    }

    /**
     * This method verify the user to userId and password is match, and it will return boolean value.
     *
     * @param userId carrier the email of user.
     * @param pass   carrier the pass of the user.
     * @return true/false.
     **/
    public boolean verifyCredential(String userId, String pass) {

        //columns to fetch
        String[] columns = {
                UserFieldContract.UserEntry.COLUMN_USER_ID
        };

        SQLiteDatabase db = this.getReadableDatabase();
        // validation
        String selection = UserFieldContract.UserEntry.COLUMN_USER_ID + " =? " + " AND " +
                UserFieldContract.UserEntry.COLUMN_PASSWORD + " =? ";

        //arguments
        String[] args = {userId, pass};

        //query to user table
        Cursor cursor = db.query(UserFieldContract.UserEntry.TABLE_NAME,
                columns, //return
                selection, //where clause
                args, // value of the clause
                null,
                null,
                null);

        int count = cursor.getCount();
        cursor.close();
        db.close();
        if (count > 0) {
            Log.d(TAG, "return true");
            return true;
        }

        return false;
    }

    /**
     * This method check if the user existing or not, and it will return boolean value.
     *
     * @param userId carrier the email of user.
     * @return true/false.
     **/
    public boolean checkUserExistOrNot(String userId) {
        SQLiteDatabase db = this.getReadableDatabase();
        boolean userExist;
        String[] columns = {
                UserFieldContract.UserEntry.COLUMN_USER_ID,
        };
        String selection = UserFieldContract.UserEntry.COLUMN_USER_ID + " =? ";
        String[] args = {userId};

        Cursor cursor = db.query(UserFieldContract.UserEntry.TABLE_NAME, columns,
                selection, args, null, null, null);
        userExist= cursor != null && cursor.moveToFirst();
        if (cursor != null) {
            cursor.close();
        }
        return userExist;
    }

    /**
     * This method delete user details permanently, and it returns boolean value.
     *
     * @param userId param email provide email of the user that are logged in the app.
     * @return true/false.
     **/
    public boolean deleteUser(String userId) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(UserFieldContract.UserEntry.TABLE_NAME, UserFieldContract.UserEntry.COLUMN_USER_ID + " =? ",
                new String[]{userId}) > 0;
    }

}