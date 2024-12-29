package com.booking;

import java.time.LocalDate;
import java.time.LocalTime;

public class Customer {

    private String name;
    private String lastName;
    private String email;
    private String nationality;
    private String phone;
    private LocalTime timeArrival;
    private LocalDate dateBirth;

    public Customer(String name, String lastName, String email, String nationality, String phone, LocalTime timeArrival, LocalDate dateBirth) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.nationality = nationality;
        this.phone = phone;
        this.timeArrival = timeArrival;
        this.dateBirth = dateBirth;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalTime getTimeArrival() {
        return timeArrival;
    }

    public void setTimeArrival(LocalTime timeArrival) {
        this.timeArrival = timeArrival;
    }

    public LocalDate getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(LocalDate dateBirth) {
        this.dateBirth = dateBirth;
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nombre='" + name + '\'' +
                ", apellido='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", nacionalidad='" + nationality + '\'' +
                ", telefono='" + phone + '\'' +
                ", horaLlegada=" + timeArrival +
                ", fechaNacimiento=" + dateBirth +
                '}';
    }
}
