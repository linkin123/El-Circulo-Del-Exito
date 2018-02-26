package cinepoilisklic.com.ia.elcirculodelexito.data.models;

/**
 * Created by lrangel on 08/02/2018.
 */

public class Maestro {
    private String horario;
    private int id;
    private String nombre;
    private String telefono;
    private String domicilio;

/*    private final int foto;*/

    public Maestro(int id, String nombre, String domicilio, String telefono , String horario /*, int foto*/) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.domicilio = domicilio;
        this.horario = horario;
/*        this.foto = foto;*/
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getHorario() {
        return horario;
    }

    /*    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }*/
}
