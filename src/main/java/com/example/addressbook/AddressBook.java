package com.example.addressbook;

import java.util.HashMap;
import java.util.Map;

class AddressBook {
    private Map<String, Contact> contacts;

    public AddressBook() {
        contacts = new HashMap<>();
    }

    public void addContact(String key, Contact contact) {
        contacts.put(key, contact);
    }

    public Contact searchByName(String name) {
        for (Contact contact : contacts.values()) {
            if (contact.getFirstName().equals(name) || contact.getLastName().equals(name)) {
                return contact;
            }
        }
        return null;
    }

    public Contact searchByPhoneNumber(String phoneNumber) {
        for (Contact contact : contacts.values()) {
            if (contact.getPhoneNumber().equals(phoneNumber)) {
                return contact;
            }
        }
        return null;
    }
}
