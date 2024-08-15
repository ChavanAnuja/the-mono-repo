package org.dnyanyog.service;

import jakarta.validation.Valid;
import java.util.Optional;
import org.dnyanyog.common.ResponseCodes;
import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CasesData;
import org.dnyanyog.dto.response.CasesResponse;
import org.dnyanyog.entity.Cases;
import org.dnyanyog.repo.CasesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CasesServiceImpl implements CasesService {
  @Autowired private CasesRepository caseRepo;

  @Autowired private CasesResponse caseResponse;

  @Override
  public ResponseEntity<CasesResponse> addCase(@Valid CaseRequest request) {
    Optional<Cases> existingCase = caseRepo.findByCaseNumber(request.getCaseNumber());

    if (existingCase.isPresent()) {
      caseResponse.setStatus(ResponseCodes.ADD_CASE_FAIL.getStatus());
      caseResponse.setMessage(ResponseCodes.ADD_CASE_FAIL.getMessage());
      return ResponseEntity.status(HttpStatus.CONFLICT).body(caseResponse);
    }

    Cases newCase = new Cases();
    newCase.setPatientNameEnglish(request.getPatientNameEnglish());
    newCase.setPatientId(request.getPatientId());
    newCase.setCaseNumber(request.getCaseNumber());
    newCase.setExaminationDate(request.getExaminationDate());
    newCase.setSymptoms(request.getSymptoms());
    newCase.setPrescription(request.getPrescription());
    newCase.setCaseStatus(request.getCaseStatus());

    caseRepo.save(newCase);

    caseResponse.setStatus(ResponseCodes.ADD_CASE_SUCCESS.getStatus());
    caseResponse.setMessage(ResponseCodes.ADD_CASE_SUCCESS.getMessage());
    return ResponseEntity.status(HttpStatus.CREATED).body(caseResponse);
  }

  public CasesResponse deleteCase(Long caseId) {
    Optional<Cases> existingCase = caseRepo.findByCaseId(caseId);

    CasesResponse caseResponse = new CasesResponse();

    if (existingCase.isPresent()) {
      Cases deleteCase = (existingCase).get();
      deleteCase.setCaseStatus(ResponseCodes.DELETE_CASE_INACTIVE.getStatus());
      caseRepo.save(deleteCase);

      caseResponse.setStatus(ResponseCodes.DELETE_CASE_INACTIVE.getStatus());
      caseResponse.setMessage(ResponseCodes.DELETE_CASE_INACTIVE.getMessage());
    } else {
      caseResponse.setStatus(ResponseCodes.CASE_NOT_FOUND.getStatus());
      caseResponse.setMessage(ResponseCodes.CASE_NOT_FOUND.getMessage());
    }

    return caseResponse;
  }

  @Override
  public CasesResponse searchCase(long caseId) {
    Optional<Cases> optionalCase = caseRepo.findByCaseId(caseId);

    CasesResponse caseResponse = CasesResponse.getInstance();
    if (optionalCase.isEmpty()) {
      caseResponse.setMessage(ResponseCodes.CASE_NOT_FOUND.getMessage());
      caseResponse.setStatus(ResponseCodes.CASE_NOT_FOUND.getStatus());
    } else {
      Cases caseEntity = optionalCase.get();
      if (caseResponse.getData() == null) {
        caseResponse.setData(new CasesData());
      }
      populateCaseResponse(caseResponse, caseEntity);
      caseResponse.setMessage(ResponseCodes.CASE_FOUND.getMessage());
      caseResponse.setStatus(ResponseCodes.CASE_FOUND.getStatus());
    }
    return caseResponse;
  }

  private void populateCaseResponse(CasesResponse response, Cases caseEntity) {
    CasesData data = response.getData();
    data.setCaseId(caseEntity.getCaseId());
    data.setPatientId(caseEntity.getPatientId());
    data.setPatientNameEnglish(caseEntity.getPatientNameEnglish());
    data.setCaseNumber(caseEntity.getCaseNumber());
    data.setExaminationDate(caseEntity.getExaminationDate());
    data.setSymptoms(caseEntity.getSymptoms());
    data.setPrescription(caseEntity.getPrescription());
  }

  @Override
  public CasesResponse updateCase(long caseId, CaseRequest request) {
    Optional<Cases> optionalCase = caseRepo.findByCaseId(caseId);

    CasesResponse caseResponse = CasesResponse.getInstance();
    if (optionalCase.isEmpty()) {
      caseResponse.setStatus(ResponseCodes.UPDATE_CASE_FAIL.getStatus());
      caseResponse.setMessage(ResponseCodes.UPDATE_CASE_FAIL.getMessage());
    } else {
      Cases caseEntity = optionalCase.get();
      caseEntity.setPatientNameEnglish(request.getPatientNameEnglish());
      caseEntity.setPatientId(request.getPatientId());
      caseEntity.setCaseNumber(request.getCaseNumber());
      caseEntity.setExaminationDate(request.getExaminationDate());
      caseEntity.setSymptoms(request.getSymptoms());
      caseEntity.setPrescription(request.getPrescription());

      caseRepo.save(caseEntity);

      if (caseResponse.getData() == null) {
        caseResponse.setData(new CasesData());
      }
      populateCaseResponse(caseResponse, caseEntity);
      caseResponse.setMessage(ResponseCodes.UPDATE_CASE_SUCCESS.getMessage());
      caseResponse.setStatus(ResponseCodes.UPDATE_CASE_SUCCESS.getStatus());
    }

    return caseResponse;
  }

  @Override
  public CasesResponse searchCaseByPatientId(long patientId) {
    Optional<Cases> optionalCase = caseRepo.findByPatientId(patientId);

    CasesResponse caseResponse = CasesResponse.getInstance();
    if (optionalCase.isEmpty()) {
      caseResponse.setMessage(ResponseCodes.CASE_NOT_FOUND.getMessage());
      caseResponse.setStatus(ResponseCodes.CASE_NOT_FOUND.getStatus());
    } else {
      Cases caseEntity = optionalCase.get();
      if (caseResponse.getData() == null) {
        caseResponse.setData(new CasesData());
      }
      populateCaseResponse(caseResponse, caseEntity);
      caseResponse.setMessage(ResponseCodes.CASE_FOUND.getMessage());
      caseResponse.setStatus(ResponseCodes.CASE_FOUND.getStatus());
    }
    return caseResponse;
  }
}
