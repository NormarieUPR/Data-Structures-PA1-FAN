import java.util.*;
public class medical_office{
    //Creating a Hashmap for all different types of people
    private final Map<String, doctor> doctors = new HashMap<>();
    private final Map<String, patient> patients = new HashMap<>();
    private final Map<String, nurse> nurse = new HashMap<>();
    private final Map<String, secretary> secretary = new HashMap<>();

    //avoid duplicate patients with hashset
    private final Set<Integer> registerdPatientsID = new HashSet<>();
    //queues
    private final Queue<patient> waitingQueue = new ArrayDeque<>();
    private final PriorityQueue<patient> emergencyQueue = new PriorityQueue<>();  // **bono**
    //Appoitment
    private final Map<String, appointment> appointments = new HashMap<>();

    //orden de llegada
    private long arrivalCounter = 0;

    // Employees Operations
    public void addDoctor(doctor doctor){ }
    public void removeDoctor(Integer doctorId) {}
    public void registerEmployee(employee employee) { }

    //Pacients
    public boolean registerPatient(patient patient){
        return false;
    }
    public void addToWaitingList(patient patient){}
    public patient attendNextPatient() { return null;}
    public patient assignPatienTtoDoc(int doctorID){
        return null;
    }

    public void registerVisit(String patientID, String doctorId){}
    public List<patient> showWaitingPatients(){return null;}

    // Search
    public List<doctor> findDoctorsBySpecialty(Specialty specialty){
        return null;
    }

    public List<doctor> showAvailableDoctors(){return null;}

    // Citas
    public boolean scheduleAppointment(appointment appointment) { return false; } 
    public boolean cancelAppointment(String appointmentId) { return false; }      
    public List<appointment> showPatientHistory(String patientId) { return null; } 
    public List<appointment> showTodayAppointments() { return null; }       

}