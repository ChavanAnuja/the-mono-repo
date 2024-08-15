package org.dnyanyog.controller;

import org.dnyanyog.dto.request.PatientRequest;
import org.dnyanyog.dto.response.PatientResponse;
import org.dnyanyog.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientController {

  private final PatientService patientService;

  @Autowired
  public PatientController(PatientService patientService) {
    this.patientService = patientService;
  }

  @PostMapping("/api/v1/patient/add")
  public ResponseEntity<PatientResponse> addPatient(@RequestBody PatientRequest patientRequest)
      throws Exception {

    return patientService.addPatientInfo(patientRequest);
  }

  @DeleteMapping(path = "/api/v1/patient/{patientId}")
  public ResponseEntity<PatientResponse> deletePatient(@PathVariable Long patientId) {
    PatientResponse patientResponse = patientService.deletePatient(patientId);
    patientService.deletePatient(patientId);
    return ResponseEntity.status(patientResponse.getStatus()).body(patientResponse);
  }

  @GetMapping(path = "/api/v1/patient/{patientId}")
  public PatientResponse searchPatient(@PathVariable Long patientId) {

    return patientService.searchPatient(patientId);
  }

  @PostMapping("/api/v1/patient/{patientId}")
  public ResponseEntity<PatientResponse> updatePatient(
      @PathVariable Long patientId, @RequestBody PatientRequest request) {
    PatientResponse response = patientService.updatePatient(patientId, request);
    return ResponseEntity.status(response.getStatus()).body(response);
  }
}
