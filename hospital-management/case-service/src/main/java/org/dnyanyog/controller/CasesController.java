package org.dnyanyog.controller;

import org.dnyanyog.dto.request.CaseRequest;
import org.dnyanyog.dto.response.CasesResponse;
import org.dnyanyog.service.CasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CasesController {

  @Autowired private CasesService caseService;

  @PostMapping("/api/v1/case/add")
  public ResponseEntity<CasesResponse> addCase(@RequestBody CaseRequest request) {
    return caseService.addCase(request);
  }

  @GetMapping("/api/v1/case/{caseId}")
  public CasesResponse searchCase(@PathVariable Long caseId) {
    return caseService.searchCase(caseId);
  }

  @PostMapping("/api/v1/case/update/{caseId}")
  public CasesResponse updateCase(@PathVariable Long caseId, @RequestBody CaseRequest request) {
    return caseService.updateCase(caseId, request);
  }

  @GetMapping("/api/v1/case/get/{patientId}")
  public CasesResponse searchCaseByPatientId(@PathVariable Long patientId) {
    return caseService.searchCaseByPatientId(patientId);
  }

  @DeleteMapping(path = "/api/v1/case/{caseId}")
  public CasesResponse deleteCases(@PathVariable Long caseId) {
    CasesResponse casesResponse = caseService.deleteCase(caseId);
    caseService.deleteCase(caseId);
    // return ResponseEntity.status(casesResponse.getStatus()).body(casesResponse)
    return casesResponse;
  }
}
