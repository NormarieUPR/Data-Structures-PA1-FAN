import ENUMS.*;
import java.util.*;
import peopleClasses.*;
import java.time.LocalDateTime;
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

    //HashSet with IDs of patients currently waiting
    private final Set<Integer> waitingIds = new HashSet<>();

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

    // Secretary checks patients in
    public boolean checkIn(int patientID) {
        Patient p = patients.get(patientID);
        if (p == null) return false;
        if (waitingIds.contains(patientID)) return false;

        p.setPriority(Priority.NORMAL);
        p.setArrivalOrder(arrivalCounter++);
        addToWaitingList(p);
        return true;
    }

    // Adds patient to the line depending on their Priority
    public void addToWaitingList(Patient patient){
        if (patient.getPriority() == Priority.EMERGENCY) {
            emergencyQueue.offer(patient);
        } else {
            waitingQueue.offer(patient);
        }
        waitingIds.add(patient.getId());
    }
    public Patient attendNextPatient() { return null;}
    public Patient assignPatientToDoc(int doctorID){
        return null;
    }

    public void registerVisit(int patientID, int doctorId){}

    // Wait list in order they'll be called
    public List<Patient> showWaitingPatients(){
        List<Patient> list = new ArrayList<>();

        //emergencies first, in priority order
        PriorityQueue<Patient> copy = new PriorityQueue<>(emergencyQueue);
        while(!copy.isEmpty()) {
            list.add(copy.poll());
        }
        // normal line in arrival order
        list.addAll(waitingQueue);
        return list;
    }

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
