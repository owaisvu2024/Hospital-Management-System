package com.Hospital_System.demo.Repository;

import com.Hospital_System.demo.Entity.AppointmentEntity;
import com.Hospital_System.demo.Entity.DoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface AppointmentRepo extends JpaRepository<AppointmentEntity,Long> {
    @Query("SELECT COUNT(a) FROM AppointmentEntity a WHERE a.doctor = :doctor AND a.appointmentDate = :date")
int countByDoctorAndPatientAppointmentDate(@Param("doctor") DoctorEntity doctor, @Param("date") LocalDate  appointmentDate);




}
