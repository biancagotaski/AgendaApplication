package com.developer.bianca.agendaapplication;

public class Person {
    private int id;
    private String name;
    private String telephone;
    private String email;
    private String city;

    public Person(int id, String name, String telephone, String email, String city) {
        this.id = id;
        this.name = name;
        this.telephone = telephone;
        this.email = email;
        this.city = city;
    }

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

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
