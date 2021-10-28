package com.example.mvpapp.sqlite;

import android.provider.BaseColumns;

/**
 * This class is hold constant value for the Sqlite database.
 */
public final class UserFieldContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private UserFieldContract() {
    }

    /* Inner class that defines the table contents */
    public static class UserEntry implements BaseColumns {
        public static final String TABLE_NAME = "user_table";
        public static final String COLUMN_FULL_NAME = "full_name";
        public static final String COLUMN_USER_ID = "user_id";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_PASSWORD = "password";
        public static final String COLUMN_MOBILE_NO = "mobile_no";
        public static final String COLUMN_ADDRESS = "address";
    }

}