package com.primerp.exam.Class;
import android.content.Intent;

import java.io.Serializable;

public class Persona implements Serializable {
    private String Nombre;
    protected String Telefono;
    private String Description;
    private int Color; // Color de la persona en hexadecimal
    private int imageResId;  // ID del recurso de la imagen
    private Intent accion;

    public Persona(String nombre, String telefono, String description, int imageResId, int color) {
        this.Nombre = nombre;
        this.Telefono = telefono;
        this.Description = description;
        this.imageResId = imageResId;
        this.Color = color;
    }
    public String getNombre() {
        return Nombre;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getDescription() {
        return Description;
    }
    public void setDescription(String description) {
        Description = description;
    }
    public String getTelefono() {
        return Telefono;
    }
    public void setTelefono(String telefono) {
        Telefono = telefono;
    }
    public int getColor() {
        return Color;
    }
    public void setColor(int color) {Color=color;}
    public Intent getAccion() {
        return accion;
    }
    public void setAccion(Intent accion) {
        this.accion = accion;
    }
    public int getImageResId() {
        return imageResId;
    }
    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

}
