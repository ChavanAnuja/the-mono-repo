package org.dnyanyog.repository;

import java.util.Optional;
import org.dnyanyog.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PatientRepo extends JpaRepository<Patient, Long> {

  boolean existsByMobileNumber(String mobileNumber);

  Optional<Patient> findByPatientId(Long patientId);
}
