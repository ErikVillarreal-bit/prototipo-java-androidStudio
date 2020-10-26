package com.example.proyecto.entidades;

public class Usuario {
    private Integer folio;
    private String nombre;
    private String edad;
    private String appFavorita;

    public Usuario(Integer folio, String nombre, String edad, String appFavorita) {
        this.folio = folio;
        this.nombre = nombre;
        this.edad = edad;
        this.appFavorita = appFavorita;
    }

    public Usuario() {

    }

    public Integer getFolio() {
        return folio;
    }

    public void setFolio(Integer folio) {
        this.folio = folio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getAppFavorita() {
        return appFavorita;
    }

    public void setAppFavorita(String appFavorita) {
        this.appFavorita = appFavorita;
    }
}
