package com.foxnival.entity;


public class Payload {

    private String name;
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Payload() {

    }

    public Payload(String name, String address) {
        this.name = name;
        this.address = address;
    }
}
