package com.example.addressbook;

import java.util.*;

public class ContactManager {
    private static final int NUMBER_OF_PARTITIONS = 100;
    private List<Map<Integer, List<Contact>>> partitions;
    private Map<Integer, List<Integer>> nameHashCodes;
    private Map<Integer, List<Integer>> phoneHashCodes;

    public ContactManager() {
        partitions = new ArrayList<>(NUMBER_OF_PARTITIONS);
        for (int i = 0; i < NUMBER_OF_PARTITIONS; i++) {
            partitions.add(new HashMap<>());
        }
        nameHashCodes = new HashMap<>();
        phoneHashCodes = new HashMap<>();
    }

    private int hash(int hashCode) {
        return Math.abs(hashCode % NUMBER_OF_PARTITIONS);
    }

    public void addContact(Contact contact) {
        int combinedHashCode = Objects.hash(contact.getFirstName(), contact.getLastName(), contact.getPhoneNumber());

        int partitionIndex = hash(combinedHashCode);
        partitions.get(partitionIndex).computeIfAbsent(combinedHashCode, k -> new ArrayList<>()).add(contact);

        int nameHashCode = Objects.hash(contact.getFirstName()+" "+contact.getLastName());
        List<Integer> nameHashCodesList = nameHashCodes.getOrDefault(nameHashCode,new ArrayList<>());
        nameHashCodesList.add(combinedHashCode);
        nameHashCodes.put(nameHashCode, nameHashCodesList);

        int phoneHashCode = Objects.hash(contact.getPhoneNumber());
        List<Integer> phoneHashCodeList = phoneHashCodes.getOrDefault(phoneHashCode,new ArrayList<>());
        phoneHashCodeList.add(combinedHashCode);
        phoneHashCodes.put(phoneHashCode, phoneHashCodeList);
    }

    public List<Integer> searchByName(String name) {
        int nameHashCode = Objects.hash(name);
        List<Integer> result = nameHashCodes.getOrDefault(nameHashCode, new ArrayList<>());
        return result;
    }

    public List<Integer> searchByPhone(String phoneNumber) {
        int phoneHashCode = Objects.hash(phoneNumber);
        return phoneHashCodes.getOrDefault(phoneHashCode, new ArrayList<>());
    }

    public List<Contact> getContactsByHashCodes(List<Integer> hashCodes) {
        List<Contact> contacts = new ArrayList<>();
        for (int hashCode : hashCodes) {
            int partitionIndex = hash(hashCode);
            contacts.addAll(partitions.get(partitionIndex).getOrDefault(hashCode, new ArrayList<>()));
        }
        return contacts;
    }
}