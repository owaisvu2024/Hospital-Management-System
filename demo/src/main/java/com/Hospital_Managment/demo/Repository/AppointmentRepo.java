package com.Hospital_Managment.demo.Repository;

import com.Hospital_Managment.demo.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment, Long> {

}
