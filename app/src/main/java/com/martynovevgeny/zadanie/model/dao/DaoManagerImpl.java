package com.martynovevgeny.zadanie.model.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.martynovevgeny.zadanie.model.dao.contact.Contact;
import com.martynovevgeny.zadanie.model.dao.contact.ContactDaoImpl;
import com.martynovevgeny.zadanie.model.database.DBHelper;

import java.util.ArrayList;


/**
 * Created by websu on 30.05.2017.
 */

public class DaoManagerImpl implements IDaoManager {

    private final ContactDaoImpl contactDao;

    public DaoManagerImpl(Context context) {
        SQLiteDatabase database = new DBHelper(context).getWritableDatabase();
        contactDao = new ContactDaoImpl(database);
    }

    @Override
    public Contact getContact(int id) {
        return contactDao.get(id);
    }

    @Override
    public ArrayList<Contact> getContacts() {
        return contactDao.get();
    }

    @Override
    public long add(Contact entity) {
        return contactDao.add(entity);
    }

    @Override
    public void update(Contact entity) {
        contactDao.update(entity);
    }

    @Override
    public void deleteContactById(int id) {
        contactDao.deleteById(id);
    }
}
