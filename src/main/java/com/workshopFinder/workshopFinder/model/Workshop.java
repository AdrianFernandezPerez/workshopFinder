package com.workshopFinder.workshopFinder.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "workshops")
public class Workshop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    private String nombre;
    private Integer telefono;
    private String email;
    private String direccion;

    @OneToMany(mappedBy = "workshop", fetch = FetchType.EAGER)
    List<Appointment> appointments;

    public Workshop() {
    }

    public Workshop(String nombre, Integer telefono, String email, String direccion) {
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
        appointments = new ArrayList<Appointment>();
    }

    public Long get_id() {
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

    public void addAppointments(Appointment appointment) {
        if (!getAppointments().contains(appointment)) {
            getAppointments().add(appointment);
            appointment.setWorkshop(this);
        }
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }
}
