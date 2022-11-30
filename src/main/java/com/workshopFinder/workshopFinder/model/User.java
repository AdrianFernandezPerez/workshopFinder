package com.workshopFinder.workshopFinder.model;


import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    private String nombre;
    private Integer telefono;
    private String email;
    private String direccion;
    private String dni;
    private boolean premium;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Vehicle> vehiculos;

    public User() {
    }

    public User(String nombre, Integer telefono, String email, String direccion, String dni, boolean premium) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.dni = dni;
        this.premium = premium;
        vehiculos = new ArrayList<Vehicle>();

    }

    public Long getIdCliente() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public void addVehiculos(Vehicle vehiculo) {
        if (!getVehiculos().contains(vehiculo)) {
            getVehiculos().add(vehiculo);
            vehiculo.setCliente(this);
        }
    }

    public Collection<Vehicle> getVehiculos() {
        return vehiculos;
    }



    @Override
    public String toString() {

        return "Nombre: "+ getNombre() + "---" +  "Telefono: " + getTelefono() + "\n";
    }
}
