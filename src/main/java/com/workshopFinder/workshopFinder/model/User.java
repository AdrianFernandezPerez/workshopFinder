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
    private String password;
    private Integer telefono;
    private String email;
    private String direccion;
    private String dni;
    private boolean premium;
    private boolean admin;
    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    List<Vehicle> vehiculos;

    public User() {
    }

    public User(String nombre, String password, Integer telefono, String email, String direccion, String dni) {
        this.nombre = nombre;
        this.password = password;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        this.dni = dni;
        this.premium = false;
        this.admin = false;
        vehiculos = new ArrayList<Vehicle>();

    }

    public User(String email) {
        this.email = email;
    }

    public Long getIdUser() {
        return _id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
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
        return "User{" +
                "_id=" + _id +
                ", nombre='" + nombre + '\'' +
                ", password='" + password + '\'' +
                ", telefono=" + telefono +
                ", email='" + email + '\'' +
                ", direccion='" + direccion + '\'' +
                ", dni='" + dni + '\'' +
                ", premium=" + premium +
                ", admin=" + admin +
                ", vehiculos=" + vehiculos +
                '}';
    }
}
