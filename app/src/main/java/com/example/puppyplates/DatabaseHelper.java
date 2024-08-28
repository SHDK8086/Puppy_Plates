package com.example.puppyplates;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "createaccount.db";
    private static final int DATABASE_VERSION = 2; // Incremented version to force upgrade

    // Constructor for DatabaseHelper
    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Called when the database is created for the first time
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            Log.d("DatabaseHelper", "Creating tables...");

            // Create tables
            db.execSQL("CREATE TABLE IF NOT EXISTS allusers(" +
                    "email TEXT PRIMARY KEY, " +
                    "password TEXT)");

            db.execSQL("CREATE TABLE IF NOT EXISTS userprofile(" +
                    "email TEXT PRIMARY KEY, " +
                    "fullname TEXT, " +
                    "phone TEXT, " +
                    "address TEXT, " +
                    "FOREIGN KEY(email) REFERENCES allusers(email))");

            Log.d("DatabaseHelper", "Tables created successfully.");

        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while creating the tables", e);
        }
    }

    // Called when the database needs to be upgraded (e.g., schema changes)
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        try {
            Log.d("DatabaseHelper", "Upgrading database...");

            // Drop existing tables
            db.execSQL("DROP TABLE IF EXISTS allusers");
            db.execSQL("DROP TABLE IF EXISTS userprofile");

            // Recreate tables
            onCreate(db);

            Log.d("DatabaseHelper", "Database upgraded successfully.");
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while upgrading the database", e);
        }
    }

    // Insert new data (email and password) into the 'allusers' table
    public boolean insertdata(String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);

        long result = -1;
        try {
            result = db.insert("allusers", null, contentValues);
            Log.d("DatabaseHelper", "Insert result for allusers: " + result);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting data into allusers", e);
        }

        return result != -1;
    }

    // Insert or update profile data in the 'userprofile' table
    public boolean insertProfileData(String email, String fullName, String phoneNo, String address) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("fullname", fullName);
        contentValues.put("phone", phoneNo);
        contentValues.put("address", address);

        int rowsAffected = 0;
        try {
            rowsAffected = db.update("userprofile", contentValues, "email = ?", new String[]{email});
            if (rowsAffected == 0) {
                long result = db.insert("userprofile", null, contentValues);
                Log.d("DatabaseHelper", "Insert result for userprofile: " + result);
                return result != -1;
            } else {
                Log.d("DatabaseHelper", "Update result for userprofile: " + rowsAffected);
                return rowsAffected > 0;
            }
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error inserting/updating profile data", e);
            return false;
        }
    }

    // Retrieve profile data for a given email
    public Cursor getProfileData(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM userprofile WHERE email = ?", new String[]{email});
            Log.d("DatabaseHelper", "Profile data retrieved for email: " + email);
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error retrieving profile data", e);
        }
        return cursor;
    }

    // Check if an email already exists in the 'allusers' table
    public boolean checkemail(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ?", new String[]{email});
            boolean exists = cursor.getCount() > 0;
            Log.d("DatabaseHelper", "Email check result for " + email + ": " + exists);
            return exists;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while checking email", e);
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    // Check if the email and password match a record in the 'allusers' table
    public boolean checkemailpassword(String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM allusers WHERE email = ? AND password = ?", new String[]{email, password});
            boolean match = cursor.getCount() > 0;
            Log.d("DatabaseHelper", "Email and password check result for " + email + ": " + match);
            return match;
        } catch (Exception e) {
            Log.e("DatabaseHelper", "Error while checking email and password", e);
            return false;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }
}
