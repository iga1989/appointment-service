package com.danieliga.appointment.service.services;

import com.danieliga.appointment.service.config.HealthProviderClient;
import com.danieliga.appointment.service.dtos.HealthProvider;
import com.danieliga.appointment.service.models.Appointment;
import com.danieliga.appointment.service.repositories.AppointmentRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class AppointmentServiceImpl implements AppointmentService{

    private final HealthProviderClient healthProviderClient;
    private final AppointmentRepository appointmentRepository;

    public AppointmentServiceImpl(HealthProviderClient healthProviderClient, AppointmentRepository appointmentRepository) {
        this.healthProviderClient = healthProviderClient;
        this.appointmentRepository = appointmentRepository;
    }
    @Override
    @Cacheable("appointment")
    public String bookAppointment(LocalDate date, String hpDepartment) {
        try {
            // Check if request goes through the proxy
            List<HealthProvider> healthProviders = healthProviderClient.getAvailableHealthProviders(date, hpDepartment);

            Appointment appointment = new Appointment();
            appointment.setAppointmentDate(date);
            appointment.setHpDepartment(hpDepartment);
            appointment.setHpName(healthProviders.get(0).getHpName());

            appointmentRepository.save(appointment);
            return "Your appointment is confirmed.!";

        } catch (HttpClientErrorException ex) {
            // Log the full stack trace and the response details
//            log.error("HTTP Error: Status Code: {}, Response Body: {}", ex.getStatusCode(), ex.getResponseBodyAsString(), ex);
            return "Failed to book appointment: " + ex.getMessage();
        } catch (Exception ex) {
            // Log any other errors that might occur
//            log.error("Error occurred while booking appointment", ex);
            return "An error occurred while booking appointment: " + ex.getMessage();
        }
    }
}