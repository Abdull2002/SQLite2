package com.example.sqlite2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHandler extends SQLiteOpenHelper {

    // Database constants
    private static final String DB_NAME = "contactsdb";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "contacts";
    private static final String ID_COL = "id";
    private static final String FIRST_NAME_COL = "first_name";
    private static final String LAST_NAME_COL = "last_name";
    private static final String ADDRESS_COL = "address";
    private static final String CITY_COL = "city";
    private static final String AGE_COL = "age";

    // Constructor
    public DBHandler(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME + " ("
                + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + FIRST_NAME_COL + " TEXT,"
                + LAST_NAME_COL + " TEXT,"
                + ADDRESS_COL + " TEXT,"
                + CITY_COL + " TEXT,"
                + AGE_COL + " INTEGER)";
        db.execSQL(query);
    }

    // Method to add new contact to SQLite database
    public void addNewContact(String firstName, String lastName, String address, String city, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_NAME_COL, firstName);
        values.put(LAST_NAME_COL, lastName);
        values.put(ADDRESS_COL, address);
        values.put(CITY_COL, city);
        values.put(AGE_COL, age);

        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    // Method to read all contacts from the database
    public ArrayList<ContactModal> readContacts() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        ArrayList<ContactModal> contactList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            do {
                contactList.add(new ContactModal(
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getString(3),
                        cursor.getString(4),
                        cursor.getInt(5)));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contactList;
    }

    // Method to update a contact's details in the database
    public void updateContact(String originalFirstName, String firstName, String lastName, String address, String city, int age) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(FIRST_NAME_COL, firstName);
        values.put(LAST_NAME_COL, lastName);
        values.put(ADDRESS_COL, address);
        values.put(CITY_COL, city);
        values.put(AGE_COL, age);

        db.update(TABLE_NAME, values, "first_name=?", new String[]{originalFirstName});
        db.close();
    }

    // Method to delete a contact from the database
    public void deleteContact(String firstName) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "first_name=?", new String[]{firstName});
        db.close();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}



