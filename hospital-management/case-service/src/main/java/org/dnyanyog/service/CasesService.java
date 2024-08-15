package org.dnyanyog.service;

import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CasesResponse;
import org.springframework.http.ResponseEntity;

public interface CasesService {

  ResponseEntity<CasesResponse> addCase(CaseRequest request);

  CasesResponse searchCase(long caseId);

  CasesResponse updateCase(long caseId, CaseRequest request);

  CasesResponse searchCaseByPatientId(long patientId);

  CasesResponse deleteCase(Long caseId);
}
