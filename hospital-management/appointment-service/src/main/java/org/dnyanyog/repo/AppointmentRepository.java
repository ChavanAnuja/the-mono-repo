package org.dnyanyog.repo;

import java.time.LocalDate;
import java.util.Optional;
import org.dnyanyog.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

  boolean existsByexaminationDateAndAppointmentTime(
      LocalDate examinationDate, String appointmentTime);

  Optional<Appointment> findByAppointmentId(long appointmentId);

  Optional<Appointment> findByPatientId(Long patientId);
}
