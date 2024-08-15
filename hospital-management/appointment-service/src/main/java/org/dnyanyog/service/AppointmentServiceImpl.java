package org.dnyanyog.service;

import java.util.Optional;
import org.dnyanyog.dto.request.AppointmentRequest;
import org.dnyanyog.dto.request.UpdateAppointmentRequest;
import org.dnyanyog.dto.response.AppointmentData;
import org.dnyanyog.dto.response.AppointmentResponse;
import org.dnyanyog.dto.response.DataByPatientId;
import org.dnyanyog.dto.response.UpdateAppointmentResponse;
import org.dnyanyog.entity.Appointment;
import org.dnyanyog.repo.AppointmentRepository;
import org.dnynayog.common.ResponseCodes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AppointmentServiceImpl implements AppointmentService {

  @Autowired private AppointmentRepository appointmentRepo;

  @Autowired private AppointmentResponse appointmentResponse;

  @Autowired private UpdateAppointmentResponse updateAppointementResponse;

  @Autowired
  public AppointmentServiceImpl(
      AppointmentRepository appointmentRepo, AppointmentResponse appointmentResponse) {
    this.appointmentRepo = appointmentRepo;
    this.appointmentResponse = appointmentResponse;
  }

  public ResponseEntity<AppointmentResponse> addAppointment(AppointmentRequest request) {
    appointmentResponse = new AppointmentResponse();
    appointmentResponse.setData(new AppointmentData());

    if (appointmentRepo.existsByexaminationDateAndAppointmentTime(
        request.getExaminationDate(), request.getAppointmentTime())) {
      appointmentResponse.setStatus(HttpStatus.CONFLICT.value());
      appointmentResponse.setMessage(ResponseCodes.DUPLICATE_APPOINTMENT.getMessage());
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(appointmentResponse);
    }
    Appointment appointment =
        Appointment.getInstance()
            .setExaminationDate(request.getExaminationDate())
            .setAppointmentTime(request.getAppointmentTime())
            .setPatientId(request.getPatientId())
            .setPatientNameEnglish(request.getPatientNameEnglish())
            .setAppointmentStatus(request.getAppointmentStatus())
            .setExaminationDate(request.getExaminationDate());

    appointment = appointmentRepo.save(appointment);

    appointmentResponse.setStatus(HttpStatus.CREATED.value());
    appointmentResponse.setMessage(ResponseCodes.ADD_APPOINTMENT_SUCCESS.getMessage());
    appointmentResponse.getData().setExaminationDate(appointment.getExaminationDate());
    appointmentResponse.getData().setAppointmentId(appointment.getAppointmentId());
    appointmentResponse.getData().setAppointmentTime(appointment.getAppointmentTime());
    appointmentResponse.getData().setPatientId(appointment.getPatientId());
    appointmentResponse.getData().setPatientNameEnglish(appointment.getPatientNameEnglish());

    return ResponseEntity.status(HttpStatus.CREATED).body(appointmentResponse);
  }

  public AppointmentResponse deleteAppointment(long appointmentId) {
    Optional<Appointment> existingAppointment = appointmentRepo.findByAppointmentId(appointmentId);

    AppointmentResponse appointmentResponse = new AppointmentResponse();

    if (existingAppointment.isPresent()) {
      Appointment deleteAppointment = (existingAppointment.get());
      deleteAppointment.setAppointmentStatus(ResponseCodes.DELETE_APPOINTMENT.getMessage());
      appointmentRepo.save(deleteAppointment);

      appointmentResponse.setStatus(HttpStatus.OK.value());
      appointmentResponse.setMessage(ResponseCodes.INACTIVE_APPOINTMENT.getMessage());
    } else {
      appointmentResponse.setStatus(HttpStatus.NOT_FOUND.value());
      appointmentResponse.setMessage(ResponseCodes.APPOINTMENT_NOT_FOUND.getMessage());
    }

    return appointmentResponse;
  }

  @Override
  public ResponseEntity<DataByPatientId> getDataByPatientId(Long patientId) {
    Optional<Appointment> appointmentOpt = appointmentRepo.findByPatientId(patientId);

    DataByPatientId response = new DataByPatientId();
    if (appointmentOpt.isEmpty()) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
      response.setMessage(ResponseCodes.APPOINTMENT_NOT_FOUND.getMessage());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    Appointment appointment = appointmentOpt.get();
    response.setStatus(HttpStatus.OK.value());
    response.setMessage(ResponseCodes.APPOINTMENT_FOUND.getMessage());

    response.setExaminationDate(appointment.getExaminationDate());
    response.setAppointmentId(appointment.getAppointmentId());
    response.setAppointmentStatus(appointment.getAppointmentStatus());
    response.setAppointmentTime(appointment.getAppointmentTime());
    response.setPatientId(appointment.getPatientId());
    response.setPatientNameEnglish(appointment.getPatientNameEnglish());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  @Override
  public ResponseEntity<DataByPatientId> getDataByAppointmentId(Long appointmentId) {
    Optional<Appointment> appointmentOpt = appointmentRepo.findByAppointmentId(appointmentId);

    DataByPatientId response = new DataByPatientId();
    if (appointmentOpt.isEmpty()) {
      response.setStatus(HttpStatus.NOT_FOUND.value());
      response.setMessage(ResponseCodes.APPOINTMENT_NOT_FOUND.getMessage());
      return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    Appointment appointment = appointmentOpt.get();
    response.setStatus(HttpStatus.OK.value());
    response.setMessage(ResponseCodes.APPOINTMENT_FOUND.getMessage());

    response.setExaminationDate(appointment.getExaminationDate());
    response.setAppointmentId(appointment.getAppointmentId());
    response.setAppointmentStatus(appointment.getAppointmentStatus());
    response.setAppointmentTime(appointment.getAppointmentTime());
    response.setPatientId(appointment.getPatientId());
    response.setPatientNameEnglish(appointment.getPatientNameEnglish());

    return ResponseEntity.status(HttpStatus.OK).body(response);
  }

  public UpdateAppointmentResponse updateAppointment(
      Long patientId, UpdateAppointmentRequest appointmentRequest) {

    Optional<Appointment> appointment = appointmentRepo.findByPatientId(patientId);
    if (appointment.isEmpty()) {
      updateAppointementResponse.setStatus(HttpStatus.NOT_FOUND.value());
      updateAppointementResponse.setMessage(ResponseCodes.APPOINTMENT_NOT_FOUND.getMessage());
    } else {
      Appointment appointmentData =
          Appointment.getInstance()
              .setAppointmentId(appointmentRequest.getAppointmentId())
              .setAppointmentStatus(appointmentRequest.getAppointmentStatus())
              .setAppointmentTime(appointmentRequest.getAppointmentTime())
              .setExaminationDate(appointmentRequest.getExaminationDate())
              .setPatientId(appointmentRequest.getPatientId())
              .setPatientNameEnglish(appointmentRequest.getPatientNameEnglish());

      appointmentRepo.save(appointmentData);

      updateAppointementResponse.setStatus(HttpStatus.OK.value());
      updateAppointementResponse.setMessage(ResponseCodes.APPOINTMENT_UPDATE.getMessage());
    }

    return updateAppointementResponse;
  }
}
