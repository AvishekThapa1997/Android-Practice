package com.harry.example.sqlite3.contacts;

import java.io.Serializable;
import java.util.List;

public class Contacts implements Serializable {
    private int id;
    private String name;
    private String phone_number;
    private static List<Contacts> contactList;
    public Contacts(String name,String phone_number){
        this.name=name;
        this.phone_number=phone_number;
        this.id=0;
    }
    public Contacts(int id,String name,String phone_number){
        this.id=id;
        this.name=name;
        this.phone_number=phone_number;
    }
    public Contacts(){ }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setContactList(List<Contacts> contacts){
        contactList=contacts;
    }
    public List<Contacts> getContactList(){
        return contactList;
    }

}
