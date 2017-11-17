package cinepoilisklic.com.ia.elcirculodelexito;

import android.media.Image;

/**
 * Created by Dell on 11/11/2017.
 */

public class Materia {

    private int imagen;
    private String nombre;
    private  int horas;
    private  String fecha;

    public Materia(int  imagen, String nombre, int horas, String fecha) {
        this.imagen = imagen;
        this.nombre = nombre;
        this.horas = horas;
        this.fecha = fecha;
    }

    public int getImagen() {
        return imagen;
    }

    public void setImagen(int imagen) {
        this.imagen = imagen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

}
