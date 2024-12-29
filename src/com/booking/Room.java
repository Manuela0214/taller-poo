package com.booking;

public class Room {

    private String name;
    private String characteristics;
    private Double nightPrice;
    private Integer quantityAvailable;
    private Boolean statusAvailability;
    private Integer capacityMaximum;

    public Room(String name, String characteristics, Double nightPrice, Integer quantityAvailable, Integer capacityMaximum) {
        this.name = name;
        this.characteristics = characteristics;
        this.nightPrice = nightPrice;
        this.quantityAvailable = quantityAvailable;
        this.statusAvailability = true;
        this.capacityMaximum = capacityMaximum;
    }

    public String getName() {
        return name;
    }

    public String getCharacteristics() {
        return characteristics;
    }

    public double getNightPrice() {
        return nightPrice;
    }

    public int getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(int quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public boolean isStatusAvailability() {
        return statusAvailability;
    }

    public void setStatusAvailability(boolean statusAvailability) {
        this.statusAvailability = statusAvailability;
    }

    public int getCapacityMaximum() {
        return capacityMaximum;
    }

    public void setCapacityMaximum(int capacityMaximum) {
        this.capacityMaximum = capacityMaximum;
    }

//    public void disminuirDisponibilidad() {
//        if (this.quantityAvailable > 0) {
//            this.quantityAvailable--;
//            if(this.quantityAvailable == 0){
//                this.statusAvailability = false;
//            }
//        } else {
//            System.out.println("No hay habitaciones disponibles");
//        }
//    }
//
//    public void aumentarDisponibilidad() {
//        this.quantityAvailable++;
//        if(this.quantityAvailable > 0 ){
//            this.statusAvailability = true;
//        }
//    }

    @Override
    public String toString() {
        return "Habitacion{" +
                "nombre='" + name + '\'' +
                ", caracteristicas='" + characteristics + '\'' +
                ", precioNoche=" + nightPrice +
                ", cantidadDisponible=" + quantityAvailable +
                ", estadoDisponibilidad=" + statusAvailability +
                ", capacidadMaxima=" + capacityMaximum +
                '}';
    }
}
