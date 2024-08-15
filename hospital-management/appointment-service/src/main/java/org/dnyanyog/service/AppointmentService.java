package org.dnyanyog.service;

import org.dnyanyog.dto.request.AppointmentRequest;
import org.dnyanyog.dto.request.UpdateAppointmentRequest;
import org.dnyanyog.dto.response.AppointmentResponse;
import org.dnyanyog.dto.response.DataByPatientId;
import org.dnyanyog.dto.response.UpdateAppointmentResponse;
import org.springframework.http.ResponseEntity;

public interface AppointmentService {

  ResponseEntity<AppointmentResponse> addAppointment(AppointmentRequest request);

  AppointmentResponse deleteAppointment(long appointmentId);

  ResponseEntity<DataByPatientId> getDataByPatientId(Long patientId);

  UpdateAppointmentResponse updateAppointment(Long patientId, UpdateAppointmentRequest updatedData);

  ResponseEntity<DataByPatientId> getDataByAppointmentId(Long appointmentId);
}
