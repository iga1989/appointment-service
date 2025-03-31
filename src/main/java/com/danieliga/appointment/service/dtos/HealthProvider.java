package com.danieliga.appointment.service.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HealthProvider {

    private String id;
    private String hpName;
    private String hpDepartment;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHpName() {
        return hpName;
    }

    public void setHpName(String hpName) {
        this.hpName = hpName;
    }

    public String getHpDepartment() {
        return hpDepartment;
    }

    public void setHpDepartment(String hpDepartment) {
        this.hpDepartment = hpDepartment;
    }

    public Boolean getDailyAvailable() {
        return isDailyAvailable;
    }

    public void setDailyAvailable(Boolean dailyAvailable) {
        isDailyAvailable = dailyAvailable;
    }

    public List<Long> getDaysAvailable() {
        return daysAvailable;
    }

    public void setDaysAvailable(List<Long> daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public LocalTime getDutyStartTime() {
        return dutyStartTime;
    }

    public void setDutyStartTime(LocalTime dutyStartTime) {
        this.dutyStartTime = dutyStartTime;
    }

    public LocalTime getDutyEndTime() {
        return dutyEndTime;
    }

    public void setDutyEndTime(LocalTime dutyEndTime) {
        this.dutyEndTime = dutyEndTime;
    }

    private Boolean isDailyAvailable;
    private List<Long> daysAvailable;
    private LocalTime dutyStartTime;
    private LocalTime dutyEndTime;

    @Override
    public String toString() {
        return "HealthProvider{" +
                "id='" + id + '\'' +
                ", hpName='" + hpName + '\'' +
                ", hpDepartment='" + hpDepartment + '\'' +
                ", isDailyAvailable=" + isDailyAvailable +
                ", daysAvailable=" + daysAvailable +
                ", dutyStartTime=" + dutyStartTime +
                ", dutyEndTime=" + dutyEndTime +
                '}';
    }
}