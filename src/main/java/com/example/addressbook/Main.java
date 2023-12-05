package com.example.addressbook;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ContactManager contactManager = new ContactManager();
        Scanner myObj = new Scanner(System.in);
        while (true){
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
                contactManager.addContact(contact);
            }
            else if (command.equals("2")){
                System.out.println("Please Enter name or number to search contact details\n");
                String input = myObj.nextLine();
                if(input.matches("[0-9]+")){
                    List<Integer> resultByPhone = contactManager.searchByPhone(input);
                    if (!resultByPhone.isEmpty()) {
                        List<Contact> contactsByPhone = contactManager.getContactsByHashCodes(resultByPhone);
                        System.out.println("Contacts found by phone:");
                        for (Contact contact : contactsByPhone) {
                            System.out.println("Name: "+contact.getFirstName()+" "+contact.getLastName()+"\n"+"Phone No. : "+contact.getPhoneNumber()+"\n"+"Address :"+contact.getAddress());
                        }
                    } else {
                        System.out.println("Contact not found by phone number.");
                    }
                }
                else{
                    List<Integer> resultByName = contactManager.searchByName(input);
                    if (!resultByName.isEmpty()) {
                        List<Contact> contactsByName = contactManager.getContactsByHashCodes(resultByName);
                        System.out.println("Contacts found by name:");
                        for (Contact contact : contactsByName) {
                            System.out.println("Name: "+contact.getFirstName()+" "+contact.getLastName()+"\n"+"Phone No. : "+contact.getPhoneNumber()+"\n"+"Address :"+contact.getAddress());
                        }
                    } else {
                        System.out.println("Contact not found by name.");
                    }
                }
            }
            else if (command.equals("3"))break;
            else System.out.println("Please enter valid option");
        }
        System. exit(0);
//        Contact contact2 = new Contact("Alice", "Smith", "456 Oak Ave", "9876543210");
//        contactManager.addContact("AliceSmith", contact2);
    }
}
