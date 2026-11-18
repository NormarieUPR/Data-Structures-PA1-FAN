package Office;
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

    // per doctor availability

    // Avoid duplicate patients with hashset
    //private final Set<Integer> registeredPatientsID = new HashSet<>(); sacar since ya resolvimos el problema 

    // Queues
    private final Queue<Patient> waitingQueue = new ArrayDeque<>();
    private final PriorityQueue<Patient> emergencyQueue = new PriorityQueue<>(Comparator.comparing(Patient::getPriority).reversed()
            .thenComparingLong(Patient::getArrivalOrder)); // order by priority first; then by arrival order
                                                           
    //HashSet with IDs of patients currently waiting
    private final Set<Integer> waitingIds = new HashSet<>();

    // Appointments by their ID
    private final Map<Integer, Appointment> appointments = new HashMap<>();

    // Counters
    private long arrivalCounter = 0;
    private int appointmentCounter = 0;

    // Employees Operations
    private int nextEmployeeId = 1; //reserved 1-200 for employees
    private int nextEmployeeId(){
        if (nextEmployeeId > 200){
            throw new IllegalStateException("NO staff IDs left");
        }
        return nextEmployeeId++;
    }
    public void addDoctor(Doctor doctor){ }
    public void removeDoctor(int doctorId) {}
    public Employee registerEmployee(String name, Roles role, Specialty specialty) { 
        int id = nextEmployeeId();
        if(role == Roles.SECRETARY){
            Secretary s = new Secretary(id, name);
            secretaries.put(id, s);
            return s;
        }
        else if(role == Roles.DOCTOR){
            Doctor d = new Doctor(id, name, specialty, false);
            doctors.put(id, d);
            return d;
        }
        else if(role == Roles.NURSE){
            Nurse n = new Nurse(id, name);
            nurses.put(id, n);
            return n;
        }
        throw new IllegalArgumentException("Unknown role " + role);
     }

    // Patients
    private int nextPatientId = 201; //ID 101 and up are for patients
    public Patient registerPatient(String name, Priority priority){
        int id = nextPatientId++;
        Patient p = new Patient(id, name, priority, 0);
        patients.put(id,p);
        return p;

    }

    // Secretary checks patients in
    public boolean checkIn(int patientID) {
        Patient p = patients.get(patientID);
        if (p == null) return false;
        if (waitingIds.contains(patientID)) return false;
 
        p.setPriority(Priority.NORMAL); //idk if to delete cuz ya en register estoy pidiendo el type of visit lmk q piensas
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
        // validate appointment
        if (time == null || time.isBefore(LocalDateTime.now().plusMinutes(1))) {
            throw new IllegalArgumentException("Invalid time");
        }

        if (time.getMinute() % 30 != 0) {
            throw new IllegalArgumentException("Appointments start on the hour or half hour");
        }

        if (!patients.containsKey(patientId)) {
            throw new IllegalArgumentException("Patient not found"); 
        }
            
        Doctor doc = doctors.get(doctorId);
        if (doc == null) throw new IllegalArgumentException("Doctor not found");

        if (doc.hasAppointmentAt(time)) {
            throw new IllegalArgumentException("Conflicts with doctor schedule");
        }

        // create appointment
        int id = ++appointmentCounter;
        Appointment app = new Appointment(id, patientId, doctorId, time);

        // update appointment & history lists
        appointments.put(id, app);
        doc.addToSchedule(app);
        patients.get(patientId).addToHistory(app);

        return app; 
    } 
    public void cancelAppointment(int appointmentId) {
        Appointment app = appointments.get(appointmentId);
        if (app == null) throw new IllegalArgumentException("Appointment not found");
        if (app.getStatus() != Appointment.Status.SCHEDULED) throw new IllegalArgumentException("Only scheduled appointments can be canceled");
        doctors.get(app.getDoctorId()).removeFromSchedule(app);
        app.setStatus(Appointment.Status.CANCELED);
    }      
    public List<Appointment> showPatientHistory(int patientId) { return null; } 
    public List<Appointment> showTodayAppointments() { return null; }       

}
