package com.alexcompany.contacts.contacts.model;

/**
 * Created by Android1 on 8/4/2015.
 */
public class Contact {

    private String firstName;
    private String surname;
    private String address;
    private String phoneNumber;
    private String email;

    public Contact(){

    }

    public Contact(String firstName, String surname, String address, String phoneNumber, String email){
        this.firstName = firstName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString(){
        return "{firstName:" + firstName + "," +
                "surname:" + surname + "," +
                "address:" + address + "," +
                "phoneNumber:" + phoneNumber + "," +
                "email:" + email + "}";
    }

}
