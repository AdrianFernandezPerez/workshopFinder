package com.workshopFinder.workshopFinder.model;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "vehiculos")
public class Vehicle implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long _id;
    private String marca;
    private String modelo;
    private String matricula;
    private VehicleType tipoVehiculo;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "vehicle", fetch = FetchType.EAGER)
    List<Appointment> appointments;

    //private ArrayList<Integer> reparaciones;

    public Vehicle() {
    }

    public Vehicle(String marca, String modelo, String matricula, VehicleType vehicleType) {
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.tipoVehiculo = vehicleType;
        appointments = new ArrayList<Appointment>();
    }

    public Long getIdVehiculo() {
        return _id;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public VehicleType getTipoVehiculo() {
        return tipoVehiculo;
    }

    public void setTipoVehiculo(VehicleType tipoVehiculo) {
        this.tipoVehiculo = tipoVehiculo;
    }

    public User getCliente() {
        return user;
    }

    public void setCliente(User cliente) {
        this.user = cliente;
    }

    /*
    public ArrayList<Integer> getReparaciones() {
        return reparaciones;
    }

    public void setReparaciones(ArrayList<Integer> reparaciones) {
        this.reparaciones = reparaciones;
    }
    */

    public void addAppointments(Appointment appointment) {
        if (!getAppointments().contains(appointment)) {
            getAppointments().add(appointment);
            appointment.setVehicle(this);
        }
    }

    public Collection<Appointment> getAppointments() {
        return appointments;
    }

    @Override
    public String toString() {
        return " "+getMarca() + " " + getModelo() + " " + getMatricula() + "\n";
    }
}
