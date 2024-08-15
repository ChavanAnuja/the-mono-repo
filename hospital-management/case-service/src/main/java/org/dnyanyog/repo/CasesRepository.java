package org.dnyanyog.repo;

import java.util.Optional;
import org.dnyanyog.entity.Cases;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface CasesRepository extends JpaRepository<Cases, Long> {

  Optional<Cases> findByCaseId(long caseId);

  Optional<Cases> findByCaseNumber(Long caseNumber);

  Optional<Cases> findByPatientId(long patientId);
}
