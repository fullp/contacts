package com.martynovevgeny.zadanie.presenters;

import com.martynovevgeny.zadanie.model.dao.DaoManagerImpl;
import com.martynovevgeny.zadanie.model.dao.contact.Contact;
import com.martynovevgeny.zadanie.utils.MainActivityRecyclerViewAdapter;
import com.martynovevgeny.zadanie.view.IMainActivityView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;

import javax.inject.Inject;

public class MainActivityPresenterImpl implements IMainActivityPresenter {

    private final IMainActivityView view;
    private DaoManagerImpl daoManager;
    private String mWord = "";
    private int mPosition = 0;
    ArrayList<Contact> mContacts;


    @Inject
    public MainActivityPresenterImpl(IMainActivityView view) {
        this.view = view;
    }

    public void init() {
        daoManager = new DaoManagerImpl(this.view.getContext());
        mContacts = daoManager.getContacts();
    }


    @Override
    public void initializeAdapter() {
        MainActivityRecyclerViewAdapter productAdapter = new MainActivityRecyclerViewAdapter
                (sortContacts((containsWord(mContacts, mWord)), mPosition));
        view.initializeAdapter(productAdapter);
    }

    public void setSearchName(String string) {
        mWord = string;
        initializeAdapter();
    }

    public void setSort(int position) {
        mPosition = position;
        initializeAdapter();
    }

    public ArrayList<String> getAutocompleteArray() {
        HashSet<String> autocompleteArray = new HashSet<>();
        for (int i = 0; i < mContacts.size(); i++) {
            autocompleteArray.add(mContacts.get(i).getName());
            autocompleteArray.add(mContacts.get(i).getSurname());
            autocompleteArray.add(mContacts.get(i).getCity());
            autocompleteArray.add(mContacts.get(i).getEmail());
        }
        ArrayList<String>arrayList = new ArrayList<>();
        arrayList.addAll(autocompleteArray);
        Collections.sort(arrayList);
        return arrayList;
    }

    private ArrayList<Contact> containsWord(ArrayList<Contact> contacts, String word) {
        if (word.equals("")) {
            return contacts;
        } else {
            ArrayList<Contact> newContacts = new ArrayList<>();
            for (int i = 0; i < contacts.size(); i++) {
                if (contacts.get(i).getName().toLowerCase().contains(word.toLowerCase())
                        | contacts.get(i).getSurname().toLowerCase().contains(word.toLowerCase())
                        | String.valueOf(contacts.get(i).getAge()).contains(word.toLowerCase())
                        | contacts.get(i).getCity().toLowerCase().contains(word.toLowerCase())
                        | contacts.get(i).getEmail().toLowerCase().contains(word.toLowerCase())) {
                    newContacts.add(contacts.get(i));
                }
            }
            return newContacts;
        }
    }

    private ArrayList<Contact> sortContacts(ArrayList<Contact> contacts, int position) {
        switch (position) {
            case 0: {
                Collections.sort(contacts, (o1, o2) -> o1.getName().compareTo(o2.getName()));
            }
            break;
            case 1: {
                Collections.sort(contacts, (o1, o2) -> o1.getSurname().compareTo(o2.getSurname()));
            }
            break;
            case 2: {
                Collections.sort(contacts, (o1, o2) -> String.valueOf(o1.getAge()).compareTo(String.valueOf(o2.getAge())));
            }
            break;
            case 3: {
                Collections.sort(contacts, (o1, o2) -> o1.getCity().compareTo(o2.getCity()));
            }
            break;
            case 4: {
                Collections.sort(contacts, (o1, o2) -> o1.getEmail().compareTo(o2.getEmail()));
            }
            break;
        }
        return contacts;
    }


    public void fillDataBase() {
        if (daoManager.getContacts().size() == 0) {
            Contact contact1 = new Contact();
            contact1.setName("Петр");
            contact1.setSurname("Ермольник");
            contact1.setAge(35);
            contact1.setCity("Могилев");
            contact1.setEmail("permolnik@gmail.com");

            Contact contact2 = new Contact();
            contact2.setName("Иван");
            contact2.setSurname("Плошкин");
            contact2.setAge(28);
            contact2.setCity("Минск");
            contact2.setEmail("ivan2345@yandex.ru");

            Contact contact3 = new Contact();
            contact3.setName("Степан");
            contact3.setSurname("Котов");
            contact3.setAge(31);
            contact3.setCity("Витебск");
            contact3.setEmail("kot1990@gmail.com");

            Contact contact4 = new Contact();
            contact4.setName("Виктор");
            contact4.setSurname("Кипячев");
            contact4.setAge(25);
            contact4.setCity("Екатеринбург");
            contact4.setEmail("viktorkip76@mail.ru");

            Contact contact5 = new Contact();
            contact5.setName("Александр");
            contact5.setSurname("Карась");
            contact5.setAge(21);
            contact5.setCity("Минск");
            contact5.setEmail("alex7212@gmail.com");

            daoManager.add(contact1);
            daoManager.add(contact2);
            daoManager.add(contact3);
            daoManager.add(contact4);
            daoManager.add(contact5);
        }
    }
}