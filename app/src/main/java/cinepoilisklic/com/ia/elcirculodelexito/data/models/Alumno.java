package cinepoilisklic.com.ia.elcirculodelexito.data.models;

/**
 * Created by lrangel on 30/10/2017.
 */

public class Alumno {
    int id;
    String name;
    String namePadre;
    String telefonoPadre;

    public Alumno(int id, String name, String namePadre, String telefonoPadre) {
        this.id = id;
        this.name = name;
        this.namePadre = namePadre;
        this.telefonoPadre = telefonoPadre;
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

    public String getNamePadre() {
        return namePadre;
    }

    public void setNamePadre(String namePadre) {
        this.namePadre = namePadre;
    }

    public String getTelefonoPadre() {
        return telefonoPadre;
    }

    public void setTelefonoPadre(String telefonoPadre) {
        this.telefonoPadre = telefonoPadre;
    }
}
