import java.util.*;
import ENUMS.*;
import peopleClasses.*;
public class MedicalOffice{
    //Creating a Hashmap for all different types of people
    private final Map<String, Doctor> doctors = new HashMap<>();
    private final Map<String, Patient> patients = new HashMap<>();
    private final Map<String, Nurse> nurses = new HashMap<>();
    private final Map<String, Secretary> secretaries = new HashMap<>();

    //avoid duplicate patients with hashset
    private final Set<Integer> registeredPatientsID = new HashSet<>();
    //queues
    private final Queue<Patient> waitingQueue = new ArrayDeque<>();
    private final PriorityQueue<Patient> emergencyQueue = new PriorityQueue<>(Comparator.comparing(Patient::getPriority).reversed()
            .thenComparingLong(Patient::getArrivalOrder)); // order by priority first; then by arrival order
    //Appoitment
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
    public void addToWaitingList(Patient patient){}
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
