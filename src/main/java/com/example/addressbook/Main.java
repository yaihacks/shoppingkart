package com.example.addressbook;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        AddressBook addressBook = new AddressBook();
        boolean flag=true;
        Scanner myObj = new Scanner(System.in);
        while (flag){
            System.out.println("Press 1 to add contact or press 2 to search contact or press 3 to exit\n");
            String command = myObj.nextLine();
            if(command.equals("1")){
                System.out.println("Please Enter firstname");
                String firstname = myObj.nextLine();
                System.out.println("Please Enter lastname");
                String lastname = myObj.nextLine();
                System.out.println("Please Enter address");
                String address = myObj.nextLine();
                System.out.println("Please Enter phone no.");
                String phoneNo = myObj.nextLine();
                Contact contact = new Contact(firstname,lastname,address,phoneNo);
                addressBook.addContact(firstname+lastname, contact);
            }
            else if (command.equals("2")){
                System.out.println("Please Enter name or number to search contact details\n");
                String input = myObj.nextLine();
                if(input.matches("[0-9]+")){
                    Contact resultByPhone = addressBook.searchByPhoneNumber(input);
                    if (resultByPhone != null) {
                        System.out.println("Search by phone: " + resultByPhone.getPhoneNumber());
                    } else {
                        System.out.println("Contact not found by phone number.");
                    }
                }
                else{
                    Contact resultByName = addressBook.searchByName(input);
                    if (resultByName != null) {
                        System.out.println("Search by name: " + resultByName.getFirstName() + " " + resultByName.getLastName());
                    } else {
                        System.out.println("Contact not found by name.");
                    }
                }
            }
            else break;
        }
        System. exit(0);
//        Contact contact2 = new Contact("Alice", "Smith", "456 Oak Ave", "9876543210");
//        addressBook.addContact("AliceSmith", contact2);
    }
}
