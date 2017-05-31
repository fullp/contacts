package com.martynovevgeny.zadanie.model.dao.contact;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.martynovevgeny.zadanie.model.database.DBHelper;

import static com.martynovevgeny.zadanie.model.database.DBHelper.TAG;

import java.util.ArrayList;

/**
 * Created by websu on 30.05.2017.
 */

public class ContactDaoImpl implements IContactDao {

    private final SQLiteDatabase mDatabase;
    private final ContentValues mContentValues;

    public ContactDaoImpl(SQLiteDatabase database) {
        mDatabase = database;
        mContentValues = new ContentValues();
    }

    @Override
    public Contact get(int id) {
        Log.d(TAG, this.getClass() + "--- get contact by id ---");
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CONTACT_NAME, null, null, null, null, null, null);
        Contact contact = new Contact();
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.CONTACT_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.CONTACT_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.CONTACT_SURNAME);
            int ageIndex = cursor.getColumnIndex(DBHelper.CONTACT_AGE);
            int cityIndex = cursor.getColumnIndex(DBHelper.CONTACT_CITY);
            int emailIndex = cursor.getColumnIndex(DBHelper.CONTACT_EMAIL);
            do {
                if (cursor.getInt(idIndex) == id) {
                    contact.setId(cursor.getInt(idIndex));
                    contact.setName(cursor.getString(nameIndex));
                    contact.setSurname(cursor.getString(surnameIndex));
                    contact.setAge(cursor.getInt(ageIndex));
                    contact.setCity(cursor.getString(cityIndex));
                    contact.setEmail(cursor.getString(emailIndex));
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        return contact;
    }

    @Override
    public ArrayList<Contact> get() {
        Log.d(TAG, this.getClass() + "--- get contact all ---");
        ArrayList<Contact> contacts = new ArrayList<>();
        Cursor cursor = mDatabase.query(DBHelper.TABLE_CONTACT_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(DBHelper.CONTACT_ID);
            int nameIndex = cursor.getColumnIndex(DBHelper.CONTACT_NAME);
            int surnameIndex = cursor.getColumnIndex(DBHelper.CONTACT_SURNAME);
            int ageIndex = cursor.getColumnIndex(DBHelper.CONTACT_AGE);
            int cityIndex = cursor.getColumnIndex(DBHelper.CONTACT_CITY);
            int emailIndex = cursor.getColumnIndex(DBHelper.CONTACT_EMAIL);
            do {
                Contact contact = new Contact();
                contact.setId(cursor.getInt(idIndex));
                contact.setName(cursor.getString(nameIndex));
                contact.setSurname(cursor.getString(surnameIndex));
                contact.setAge(cursor.getInt(ageIndex));
                contact.setCity(cursor.getString(cityIndex));
                contact.setEmail(cursor.getString(emailIndex));
                contacts.add(contact);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return contacts;
    }

    @Override
    public long add(Contact entity) {
        Log.d(TAG, this.getClass() + "--- add contact ---");
        mContentValues.put(DBHelper.CONTACT_NAME, entity.getName());
        mContentValues.put(DBHelper.CONTACT_SURNAME, entity.getSurname());
        mContentValues.put(DBHelper.CONTACT_AGE, entity.getAge());
        mContentValues.put(DBHelper.CONTACT_CITY, entity.getCity());
        mContentValues.put(DBHelper.CONTACT_EMAIL, entity.getEmail());
        long id = mDatabase.insert(DBHelper.TABLE_CONTACT_NAME, null, mContentValues);
        Log.d(TAG, this.getClass() + "Success add to base: " + id + ", " + entity.getName() + ", " + entity.getSurname()
                + ", " + entity.getAge() + ", " + entity.getCity() + ", " + entity.getEmail());
        return id;
    }

    @Override
    public void update(Contact entity) {
        Log.d(TAG, this.getClass() + "--- update contact ---");
        mContentValues.put(DBHelper.CONTACT_ID, entity.getId());
        mContentValues.put(DBHelper.CONTACT_NAME, entity.getName());
        mContentValues.put(DBHelper.CONTACT_SURNAME, entity.getSurname());
        mContentValues.put(DBHelper.CONTACT_AGE, entity.getAge());
        mContentValues.put(DBHelper.CONTACT_CITY, entity.getCity());
        mContentValues.put(DBHelper.CONTACT_EMAIL, entity.getEmail());
        int updCount = mDatabase.update(DBHelper.TABLE_CONTACT_NAME, mContentValues,
                DBHelper.CONTACT_ID + " = " + String.valueOf(entity.getId()), null);
        Log.d(TAG, this.getClass() + "--- updated rows count = " + updCount + " ---");
    }

    @Override
    public void deleteById(int id) {
        Log.d(TAG, this.getClass() + "--- delete contact by id ---");
        int delCount = mDatabase.delete(DBHelper.TABLE_CONTACT_NAME, DBHelper.CONTACT_ID + " = " + id, null);
        Log.d(TAG, this.getClass() + "--- deleted rows count = " + delCount + " ---");
    }
}
