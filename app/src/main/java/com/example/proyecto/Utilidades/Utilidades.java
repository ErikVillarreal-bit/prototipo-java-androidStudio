package com.example.proyecto.Utilidades;

public class Utilidades {

    public static final String TABLA_USUARIOS="usuarios";
    public static final String CAMPO_FOLIO="folio";
    public static final String CAMPO_NOMBRE="nombre";
    public static final String CAMPO_EDAD="edad";
    public static final String CAMPO_APPFAVORITA="appFavorita";

    public  static final String CREAR_TABLA_USUARIO="CREATE TABLE "+TABLA_USUARIOS+"("+CAMPO_FOLIO+" INTEGER,"+CAMPO_NOMBRE+" TEXT,"+CAMPO_EDAD+" TEXT, "+CAMPO_APPFAVORITA+" TEXT)";
}
