package com.example.sqlite2;

public class ContactModal {

    // Variables for contact details
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private int age;
    private int id;

    // Constructor
    public ContactModal(String firstName, String lastName, String address, String city, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.city = city;
        this.age = age;
    }

    // Getter and Setter methods
    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
}
