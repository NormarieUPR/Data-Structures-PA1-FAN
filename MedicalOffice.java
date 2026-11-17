import java.util.*;
import java.time.LocalDateTime;
import ENUMS.*;
import peopleClasses.*;
import misc.*;

public class MedicalOffice{
    // Creating a Hashmap for all different types of people
    private final Map<Integer, Doctor> doctors = new HashMap<>();
    private final Map<Integer, Patient> patients = new HashMap<>();
    private final Map<Integer, Nurse> nurses = new HashMap<>();
    private final Map<Integer, Secretary> secretaries = new HashMap<>();

    // Avoid duplicate patients with hashset
    private final Set<Integer> registeredPatientsID = new HashSet<>();
    // Queues
    private final Queue<Patient> waitingQueue = new ArrayDeque<>();
    private final PriorityQueue<Patient> emergencyQueue = new PriorityQueue<>(Comparator.comparing(Patient::getPriority).reversed()
            .thenComparingLong(Patient::getArrivalOrder)); // order by priority first; then by arrival order
    // Appointments
    private final Map<Integer, Appointment> appointments = new HashMap<>();

    // Orden de llegada
    private long arrivalCounter = 0;

    // Employees Operations
    public void addDoctor(Doctor doctor){ }
    public void removeDoctor(int doctorId) {}
    public void registerEmployee(Employee employee) { }

    // Patients
    public boolean registerPatient(Patient patient){
        return false;
    }
    public void addToWaitingList(Patient patient){}
    public Patient attendNextPatient() { return null;}
    public Patient assignPatientToDoc(int doctorID){
        return null;
    }

    public void registerVisit(int patientID, int doctorId){}
    public List<Patient> showWaitingPatients(){return null;}

    // Search
    public List<Doctor> findDoctorsBySpecialty(Specialty specialty){
        return null;
    }

    public List<Doctor> showAvailableDoctors(){return null;}

    // Citas
    public Appointment scheduleAppointment(int patientId, int doctorId, LocalDateTime time) { 
        if (time == null || time.isBefore(LocalDateTime.now().plusMinutes(1))) {
            throw new IllegalArgumentException("Invalid time");
        }

        if (!patients.containsKey(patientId)) {
            throw new IllegalArgumentException("Patient not found"); 
        }
            
        if (!doctors.containsKey(doctorId)) {
            throw new IllegalArgumentException("Doctor not found");
        }

        int appointmentId = 1;

        return new Appointment(appointmentId, patientId, doctorId, time); 
    } 
    public boolean cancelAppointment(int appointmentId) { return false; }      
    public List<Appointment> showPatientHistory(int patientId) { return null; } 
    public List<Appointment> showTodayAppointments() { return null; }       

}
