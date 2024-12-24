package com.booking;

import java.util.ArrayList;
import java.util.List;

public class DiaDeSol extends Alojamiento{

    List<Actividad> actividades;

    public DiaDeSol(String nombre, double calificacion, double precioNoche, String ciudad, List<Actividad> actividades) {
        super(nombre, calificacion, precioNoche, ciudad);
        this.actividades = actividades;
    }

    @Override
    public String getTipo() {
        return "Dia de Sol";
    }

    public List<Actividad> getActividades() {
        return actividades;
    }

    public void setActividades(List<Actividad> actividades) {
        this.actividades = actividades;
    }
}
