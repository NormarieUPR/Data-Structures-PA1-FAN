import ENUMS.*;
import java.util.*;
import peopleClasses.*;
public class MedicalOffice{
    //Creating a Hashmap for all different types of people
    private final Map<Integer, Doctor> doctors = new HashMap<>();
    private final Map<Integer, Patient> patients = new HashMap<>();
    private final Map<Integer, Nurse> nurses = new HashMap<>();
    private final Map<Integer, Secretary> secretaries = new HashMap<>();

    //avoid duplicate patients with hashset
    private final Set<Integer> registeredPatientsID = new HashSet<>();
    //queues
    private final Queue<Patient> waitingQueue = new ArrayDeque<>();
    private final PriorityQueue<Patient> emergencyQueue = new PriorityQueue<>(Comparator.comparing(Patient::getPriority).reversed()
            .thenComparingLong(Patient::getArrivalOrder)); // order by priority first; then by arrival order

    //HashSet with IDs of patients currently waiting
    private final Set<Integer> waitingIds = new HashSet<>();

    //Appointment
    private final Map<String, appointment> appointments = new HashMap<>();

    //orden de llegada
    private long arrivalCounter = 0;

    // Employees Operations
    public void addDoctor(Doctor doctor){ }
    public void removeDoctor(Integer doctorId) {}
    public void registerEmployee(Employee employee) { }

    //Pacients
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

    public void registerVisit(String patientID, String doctorId){}
    public List<Patient> showWaitingPatients(){return null;}

    // Search
    public List<Doctor> findDoctorsBySpecialty(Specialty specialty){
        return null;
    }

    public List<Doctor> showAvailableDoctors(){return null;}

    // Citas
    public boolean scheduleAppointment(appointment appointment) { return false; } 
    public boolean cancelAppointment(String appointmentId) { return false; }      
    public List<appointment> showPatientHistory(String patientId) { return null; } 
    public List<appointment> showTodayAppointments() { return null; }       

}
