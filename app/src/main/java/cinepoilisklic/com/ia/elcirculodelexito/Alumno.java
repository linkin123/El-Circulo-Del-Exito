package cinepoilisklic.com.ia.elcirculodelexito;

/**
 * Created by lrangel on 30/10/2017.
 */

public class Alumno {
    String name;
    String status;
    int id;
    int horas;
    int color;
    String fechaFinPaquete;

    public Alumno(String name, String status, int id, int horas, int color, String fechaFinPaquete) {
        this.name = name;
        this.status = status;
        this.id = id;
        this.horas = horas;
        this.color = color;
        this.fechaFinPaquete = fechaFinPaquete;

    }

    public String getFechaFinPaquete() {
        return fechaFinPaquete;
    }

    public void setFechaFinPaquete(String fechaFinPaquete) {
        this.fechaFinPaquete = fechaFinPaquete;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHoras() {
        return horas;
    }

    public void setHoras(int horas) {
        this.horas = horas;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }
}
