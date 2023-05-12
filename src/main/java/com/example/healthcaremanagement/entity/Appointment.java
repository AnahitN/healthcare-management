package com.example.healthcaremanagement.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@Entity
@Table(name = "appointment")
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date dateTime;
    @ManyToOne
    private Doctor doctor;
    @ManyToOne
    private Patient patient;

}
