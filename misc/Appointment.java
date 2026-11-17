package misc;

import java.time.LocalDateTime;

public class Appointment{
    public enum Status { SCHEDULED, CANCELED, COMPLETED };

    private final int id; 
    private final int patientId;
    private final int doctorId;
    private final LocalDateTime time;
    private Status status = Status.SCHEDULED;

    public Appointment (int id, int patientId, int doctorId, LocalDateTime time) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.time = time;
    }

    public int getAppointmentId() {
        return this.id;
    }

    public int getPatientId() {
        return this.patientId;
    }

    public int getDoctorId() {

        return this.doctorId;
    }

    public LocalDateTime getTime() {
        return this.time;
    }

    public Status getStatus() {
        return this.status;
    }

    public void setStatus(Status s) {
        this.status = s;
    }
}
