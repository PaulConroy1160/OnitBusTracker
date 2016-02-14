package com.example.paulconroy.onit.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.paulconroy.onit.model.StopSave;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paulconroy on 23/12/2015.
 */
public class DatabaseHandler extends SQLiteOpenHelper {

    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "stopManager";

    // Stop Saves table name
    private static final String TABLE_SAVES = "stopsaves";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NUMBER = "number";
    private static final String KEY_LOCATION = "location";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_SAVES + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NUMBER + " TEXT,"
                + KEY_LOCATION + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SAVES);

        // Create tables again
        onCreate(db);
    }

    // Adding new contact
    public void addSave(StopSave save) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NUMBER, save.getNumber());
        values.put(KEY_LOCATION, save.getLocation());

        // Inserting Row
        db.insert(TABLE_SAVES, null, values);
        db.close(); // Closing database connection
    }


    // Getting All Contacts
    public List<StopSave> getAllSaves() {
        List<StopSave> saveList = new ArrayList<StopSave>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_SAVES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                StopSave save = new StopSave();
                save.setId(Integer.parseInt(cursor.getString(0)));
                save.setNumber(cursor.getString(1));
                save.setLocation(cursor.getString(2));
                // Adding contact to list
                saveList.add(save);
            } while (cursor.moveToNext());
        }

        // return contact list
        return saveList;
    }


    // Deleting single contact
    public void deleteSave(StopSave save) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_SAVES, KEY_ID + " = ?",
                new String[]{String.valueOf(save.getId())});
        db.close();
    }
}
