package com.martynovevgeny.zadanie.model.dao;

import com.martynovevgeny.zadanie.model.dao.contact.Contact;

import java.util.ArrayList;

/**
 * Created by websu on 30.05.2017.
 */

interface IDaoManager {

    Contact getContact(int id);

    ArrayList<Contact> getContacts ();

    long add(Contact entity);

    void update(Contact entity);

    void deleteContactById(int id);
}
