package org.dnyanyog.service;

import org.dnyanyog.dto.request.PatientRequest;
import org.dnyanyog.dto.response.PatientResponse;
import org.springframework.http.ResponseEntity;

public interface PatientService {

  ResponseEntity<PatientResponse> addPatientInfo(PatientRequest request);

  PatientResponse deletePatient(Long patientId);

  PatientResponse searchPatient(Long patientId);

  PatientResponse updatePatient(Long patientId, PatientRequest request);
}
