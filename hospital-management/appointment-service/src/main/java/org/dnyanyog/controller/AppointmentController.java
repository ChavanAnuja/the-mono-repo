package org.dnyanyog.controller;

import jakarta.validation.Valid;
import org.dnyanyog.dto.request.AppointmentRequest;
import org.dnyanyog.dto.request.UpdateAppointmentRequest;
import org.dnyanyog.dto.response.AppointmentResponse;
import org.dnyanyog.dto.response.DataByPatientId;
import org.dnyanyog.dto.response.UpdateAppointmentResponse;
import org.dnyanyog.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppointmentController {

  private final AppointmentService appointmentService;

  @Autowired
  public AppointmentController(AppointmentService appointmentService) {
    this.appointmentService = appointmentService;
  }

  @PostMapping("/api/v1/appointment/add")
  public ResponseEntity<AppointmentResponse> addAppointment(
      @Valid @RequestBody AppointmentRequest request) throws Exception {
    return appointmentService.addAppointment(request);
  }

  @DeleteMapping(path = "/api/v1/appointment/{appointmentId}")
  public ResponseEntity<AppointmentResponse> deleteAppointment(@PathVariable Long appointmentId) {
    AppointmentResponse appointmentResponse = appointmentService.deleteAppointment(appointmentId);
    appointmentService.deleteAppointment(appointmentId);
    return ResponseEntity.status(appointmentResponse.getStatus()).body(appointmentResponse);
  }

  @GetMapping("/api/v1/appointment/{patientId}")
  public ResponseEntity<DataByPatientId> getAppointmentByPatientId(@PathVariable long patientId) {
    return appointmentService.getDataByPatientId(patientId);
  }

  @GetMapping("/api/v1/appointment/get/{appointmentId}")
  public ResponseEntity<DataByPatientId> getAppointmentByAppointmentId(
      @PathVariable Long appointmentId) {
    return appointmentService.getDataByAppointmentId(appointmentId);
  }

  @PostMapping("/api/v1/updateAppointment/{patientId}")
  public UpdateAppointmentResponse updateAppointmentByPatientId(
      @Valid @PathVariable long patientId, @RequestBody UpdateAppointmentRequest updatedData) {
    return appointmentService.updateAppointment(patientId, updatedData);
  }
}
