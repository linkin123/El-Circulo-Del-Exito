package cinepoilisklic.com.ia.elcirculodelexito;

/**
 * Created by Dell on 03/11/2017.
 */

public class Asesoria {

    int materiaImag;
    int maestroImag;
    String maestroNombre;
    String fecha;
    String nivel;
    String tiempoDuracion;
    String tiempoRestante;

    public Asesoria(int materiaImag, int maestroImag, String maestroNombre, String fecha, String nivel, String tiempoDuracion, String tiempoRestante) {
        this.materiaImag = materiaImag;
        this.maestroImag = maestroImag;
        this.maestroNombre = maestroNombre;
        this.fecha = fecha;
        this.nivel = nivel;
        this.tiempoDuracion = tiempoDuracion;
        this.tiempoRestante = tiempoRestante;
    }

    public int getMateriaImag() {
        return materiaImag;
    }

    public void setMateriaImag(int materiaImag) {
        this.materiaImag = materiaImag;
    }

    public int getMaestroImag() {
        return maestroImag;
    }

    public void setMaestroImag(int maestroImag) {
        this.maestroImag = maestroImag;
    }

    public String getMaestroNombre() {
        return maestroNombre;
    }

    public void setMaestroNombre(String maestroNombre) {
        this.maestroNombre = maestroNombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public String getTiempoDuracion() {
        return tiempoDuracion;
    }

    public void setTiempoDuracion(String tiempoDuracion) {
        this.tiempoDuracion = tiempoDuracion;
    }

    public String getTiempoRestante() {
        return tiempoRestante;
    }

    public void setTiempoRestante(String tiempoRestante) {
        this.tiempoRestante = tiempoRestante;
    }
}
