package com.danieliga.appointment.service.controllers;

import com.danieliga.appointment.service.services.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService){
        this.appointmentService = appointmentService;
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_Admin')")
    @PostMapping
    public String bookAppointment(@RequestParam LocalDate selectedDate, @RequestParam String department) {
        return appointmentService.bookAppointment(selectedDate, department);
    }
}