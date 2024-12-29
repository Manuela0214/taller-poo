package com.booking;

public class Activity {

    private String name;
    private Boolean includesLunch;
    private Double price;

    public Activity(String name, Boolean includesLunch, Double price) {
        this.name = name;
        this.includesLunch = includesLunch;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIncludesLunch() {
        return includesLunch;
    }

    public void setIncludesLunch(boolean includesLunch) {
        this.includesLunch = includesLunch;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Actividad{" +
                "nombre='" + name + '\'' +
                ", incluyeComida=" + includesLunch +
                ", precio=" + price +
                '}';
    }
}
