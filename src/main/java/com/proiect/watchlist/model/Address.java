package com.proiect.watchlist.model;

public class Address {

    private Integer id;
    private String city;
    private String country;
    private Actor actorId;

    public Address() {
    }

    public Address(Integer id, String city, String country, Actor actorId) {
        this.id = id;
        this.city = city;
        this.country = country;
        this.actorId = actorId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Actor getActorId() {
        return actorId;
    }

    public void setActorId(Actor actorId) {
        this.actorId = actorId;
    }

    @Override
    public String toString() {
        return "Address{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", actorId=" + actorId +
                '}';
    }
}
