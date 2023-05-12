package com.example.healthcaremanagement.controller;

import com.example.healthcaremanagement.entity.Appointment;
import com.example.healthcaremanagement.entity.Doctor;
import com.example.healthcaremanagement.entity.Patient;
import com.example.healthcaremanagement.repository.AppointmentRepository;
import com.example.healthcaremanagement.repository.DoctorRepository;
import com.example.healthcaremanagement.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public String appointmentsPage(ModelMap modelMap) {
        List<Appointment> all = appointmentRepository.findAll();
        modelMap.addAttribute("appointments", all);
        return "appointments";
    }

    @GetMapping("/add")
    public String addAppointmentPage(ModelMap modelMap){
        List<Patient> all = patientRepository.findAll();
        modelMap.addAttribute("patients",all);
        List<Doctor> doctors = doctorRepository.findAll();
        modelMap.addAttribute("doctors",doctors);
        return "addAppointment";
    }

    @PostMapping("/add")
    public String addAppointment(@ModelAttribute Appointment appointment){
        appointmentRepository.save(appointment);
        return "redirect:/appointments";
    }

    @GetMapping("/remove")
    public String removeAppointment(@RequestParam("id") int id){
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }
}
