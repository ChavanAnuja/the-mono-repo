package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.request.PatientRequest;
import org.dnyanyog.dto.response.PatientData;
import org.dnyanyog.dto.response.PatientResponse;
import org.dnyanyog.entity.Patient;
import org.dnyanyog.repository.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {

  @Autowired private PatientRepo patientRepository;
  @Autowired private PatientResponse patientResponse;

  @Autowired
  public PatientServiceImpl(PatientRepo patientRepository, PatientResponse patientResponse) {
    this.patientRepository = patientRepository;
    this.patientResponse = patientResponse;
  }

  @Override
  public ResponseEntity<PatientResponse> addPatientInfo(PatientRequest request) {

    patientResponse.setData(new PatientData());

    if (patientRepository.existsByMobileNumber(request.getMobileNumber())) {

      patientResponse = new PatientResponse();
      patientResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
      patientResponse.setMessage(ResponseCodes.ADD_PATIENT_FAILED.getMessage());
      return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(patientResponse);
    }

    Patient patient =
        Patient.getInstance()
            .setAddress(request.getAddress())
            .setBirthDate(request.getBirthDate())
            .setFirstExaminationDate(request.getFirstExaminationDate())
            .setGender(request.getGender())
            .setMobileNumber(request.getMobileNumber())
            .setPatientId(request.getPatientId())
            .setPatientNameEnglish(request.getPatientNameEnglish())
            .setPatientNameMarathi(request.getPatientNameMarathi());

    patient = patientRepository.save(patient);

    patientResponse.setStatus(HttpStatus.CREATED.value());
    patientResponse.setMessage(ResponseCodes.ADD_PATIENT.getMessage());

    patientResponse.getData().setPatientId(patient.getPatientId());
    patientResponse.getData().setPatientNameEnglish(patient.getPatientNameEnglish());
    patientResponse.getData().setPatientNameMarathi(patient.getPatientNameMarathi());
    patientResponse.getData().setGender(patient.getGender());
    patientResponse.getData().setMobileNumber(patient.getMobileNumber());
    patientResponse.getData().setAddress(patient.getAddress());
    patientResponse.getData().setBirthDate(request.getBirthDate());
    patientResponse.getData().setFirstExaminationDate(patient.getFirstExaminationDate());
    patientResponse.getData().setPatientStatus(patient.getPatientStatus());

    return ResponseEntity.status(HttpStatus.CREATED).body(patientResponse);
  }

  public PatientResponse deletePatient(Long patientId) {
    Optional<Patient> existingPatient = patientRepository.findByPatientId(patientId);

    PatientResponse patientResponse = new PatientResponse();

    if (existingPatient.isPresent()) {
      Patient patientToDelete = (existingPatient).get();
      patientToDelete.setPatientStatus(ResponseCodes.INACTIVE_PATIENT.getMessage());
      patientRepository.save(patientToDelete);

      patientResponse.setStatus(HttpStatus.OK.value());
      patientResponse.setMessage(ResponseCodes.INACTIVE_PATIENT_STATUS.getMessage());
    } else {
      patientResponse.setStatus(HttpStatus.NOT_FOUND.value());
      patientResponse.setMessage(ResponseCodes.PATIENT_NOT_FOUND.getMessage());
    }

    return patientResponse;
  }

  @Override
  public PatientResponse searchPatient(Long patientId) {
    Optional<Patient> optionalPatient = patientRepository.findByPatientId(patientId);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setMessage(ResponseCodes.SEARCH_PATIENT_FAILED.getMessage());
      patientResponse.setStatus(HttpStatus.NOT_FOUND.value());
    } else {
      Patient patients = optionalPatient.get();
      if (patientResponse.getData() == null) {
        patientResponse.setData(new PatientData());
      }
      populatePatientResponse(patientResponse, patients);
      patientResponse.setMessage(ResponseCodes.SEARCH_PATIENT.getMessage());
      patientResponse.setStatus(HttpStatus.FOUND.value());
    }
    return patientResponse;
  }

  private void populatePatientResponse(PatientResponse response, Patient patient) {
    response.getData().setAddress(patient.getAddress());
    response.getData().setBirthDate(patient.getBirthDate());
    response.getData().setFirstExaminationDate(patient.getFirstExaminationDate());
    response.getData().setGender(patient.getGender());
    response.getData().setMobileNumber(patient.getMobileNumber());
    response.getData().setPatientId(patient.getPatientId());
    response.getData().setPatientNameEnglish(patient.getPatientNameEnglish());
    response.getData().setPatientNameMarathi(patient.getPatientNameMarathi());
    response.getData().setPatientStatus(patient.getPatientStatus());
  }

  public PatientResponse updatePatient(Long patientId, PatientRequest request) {
    Optional<Patient> optionalPatient = patientRepository.findByPatientId(patientId);

    PatientResponse patientResponse = PatientResponse.getInstance();
    if (optionalPatient.isEmpty()) {
      patientResponse.setStatus(HttpStatus.NOT_FOUND.value());
      patientResponse.setMessage(ResponseCodes.UPDATE_PATIENT_FAILED.getMessage());
    } else {
      Patient patient = optionalPatient.get();
      patient.setAddress(request.getAddress());
      patient.setMobileNumber(request.getMobileNumber());
      patient.setBirthDate(request.getBirthDate());
      patient.setFirstExaminationDate(request.getFirstExaminationDate());
      patient.setGender(request.getGender());
      patient.setPatientNameEnglish(request.getPatientNameEnglish());
      patient.setPatientNameMarathi(request.getPatientNameMarathi());
      patient.setPatientStatus(request.getPatientStatus());

      patientRepository.save(patient);

      populatePatientResponse(patientResponse, patient);
      patientResponse.setMessage(ResponseCodes.UPDATE_PATIENT.getMessage());
      patientResponse.setStatus(HttpStatus.OK.value());
    }

    return patientResponse;
  }
}
