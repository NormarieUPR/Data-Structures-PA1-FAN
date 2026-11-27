import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import ENUMS.*;
import Office.MedicalOffice;
import misc.*;
import peopleClasses.*;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MedicalOffice office = new MedicalOffice();

        System.out.println("Welcome To Heisenbergs Ophthalmology!");
        System.out.println("***AUTHORIZED PERSONNEL ONLY***");

        boolean running = true;
        while (running) {
            int opt = readMenu(scanner, "Who are you", 
                    "Secretary", "Nurse", "Doctor", "Exit");
            switch (opt) {
                case 1 -> secretaryMenu(office, scanner);
                case 2 -> nurseMenu(office, scanner);
                case 3 -> doctorMenu(office, scanner);
                case 4 -> running = false;
                default -> System.out.println("Invalid. Try again.");
            }
        }
        System.out.println("Thank you!");
        scanner.close();
    }

    // --------------------------------Menus--------------------------------
    private static void secretaryMenu(MedicalOffice office, Scanner scanner) {
        boolean running = true;
        while (running) {
            int opt = readMenu(scanner, "====Secretary====", 
                    "Register new patient",
                    "Register new employee",
                    "Schedule appointment",
                    "Cancel appointment",
                    "Check in patient",
                    "Emergency patient",
                    "Patient history",
                    "Today's appointments",
                    "Back");
            switch (opt) {
                case 1 -> secRegisterPatient(office, scanner);
                case 2 -> secRegisterEmployee(office, scanner);
                case 3 -> secSchedule(office, scanner);
                case 4 -> secCancel(office, scanner);
                case 5 -> secCheckIn(office, scanner);
                case 6 -> secEmergency(office, scanner);
                case 7 -> patientHistory(office, scanner);
                case 8 -> secToday(office, scanner);
                case 9 -> running = false;
                default -> System.out.println("Invalid. Try again.");
            }
        }
    }

    private static void nurseMenu(MedicalOffice office, Scanner scanner) {
    
        boolean running = true;
        while (running) {
            int opt = readMenu(scanner, "====Nurse====", 
                    "Show waiting patients",
                    "Show available doctors",
                    "Find doctors by specialty",
                    "Back");
            switch (opt) {
                case 1 -> nurWaiting(office, scanner);
                case 2 -> nurAvailableDoctors(office, scanner);
                case 3 -> nurFindBySpecialty(office, scanner);
                case 4 -> running = false;
                default -> System.out.println("Invalid. Try again.");
            }
        } 
    }

    private static void doctorMenu(MedicalOffice office, Scanner scanner) {
        boolean running = true;
        while (running) {
            int opt = readMenu(scanner, "====Doctor====",
                    "Set my availability",
                    "Attend next patient",
                    "Register visit",
                    "Recent visits",
                    "Patient history",
                    "Back");
            switch (opt) {
                case 1 -> docAvailability(office, scanner);
                case 2 -> docAttendNext(office, scanner);
                case 3 -> docRegisterVisit(office, scanner);
                case 4 -> docRecentVisits(office, scanner);
                case 5 -> patientHistory(office, scanner);
                case 6 -> running = false;
                default -> System.out.println("Invalid. Try again.");
            }
        }
    }

    // --------------------------------Tools--------------------------------
    // read input, call office method, print
    // read name -> office.registerPatient(name) -> print ID
    private static void secRegisterPatient(MedicalOffice office, Scanner scanner) {
        System.out.print("Patient's full name: ");
        String name = scanner.nextLine().trim();

        int type = readMenu(scanner, "Type of visit", "Regular", "Emergency");
        Priority priority = (type == 1) ? Priority.NORMAL : Priority.EMERGENCY; 
        Patient p = office.registerPatient(name, priority);
        System.out.println("Registered. Patient ID: " + p.getId());
    }
    // choose employee type, read name (and specialty) -> call office.registeremployee() -> print ID
    private static void secRegisterEmployee(MedicalOffice office, Scanner scanner) {
        System.out.println("Employee's full name: ");
        String name = scanner.nextLine().trim();
        int roleChoice = readMenu(scanner, "Role", "Secretary", "Nurse", "Doctor");
        Roles role = switch (roleChoice) {
        case 1 -> Roles.SECRETARY;
        case 2 -> Roles.NURSE;
        default -> Roles.DOCTOR;
        };
        Specialty specialty = null;
        if(role == Roles.DOCTOR){
            int specChoice = readMenu(scanner, "Specialty", "General", "Pediatric", "Neuro-opthalmologist", "Retinologist");
            specialty = switch(specChoice) {
                case 1 -> Specialty.OFTALMOLOGO_GENERAL;
                case 2 -> Specialty.OFTALMOLOGO_PEDIATRICO;
                case 3 -> Specialty.NEURO_OFTALMOLOGO;
                default -> Specialty.RETINOLOGO;
            };
        }
            Employee e = office.registerEmployee(name, role, specialty);
            System.out.println("Registered " + role + ". Employee ID:" + e.getId());
    }
    // ask for patient -> choose specialty -> pick doctor -> date -> show taken times -> time -> schedule
    private static void secSchedule(MedicalOffice office, Scanner scanner) {
        Patient patient = askForPatient(office, scanner);

        Specialty specialty = chooseSpecialty(scanner);
        List<Doctor> doctors = office.findDoctorsBySpecialty(specialty);
        if (doctors.isEmpty()) {
            System.out.println("No doctors with that specialty.");
            return;
        }
        for (Doctor d : doctors) {
            System.out.println("ID: " + d.getId() + " Name: " + d.getName());
        }

        // keep asking until the ID is one of the doctors listed
        Doctor doctor = null;
        while (doctor == null) {
            int id = readInt(scanner, "Doctor ID: ");
            for (Doctor d : doctors) {
                if (d.getId() == id) doctor = d;
            }
            if (doctor == null) System.out.println("Choose one of the doctors listed.");
        }

        LocalDate day = readDate(scanner);
        System.out.println("Taken that day:");
        for (Appointment a : office.getDoctorAppointments(doctor.getId(), day)) {
            System.out.println("  " + a.getTime().toLocalTime());
        }

        // if the time is rejected, show why and let them try another
        while (true) {
            LocalTime time = readTime(scanner);
            try {
                Appointment a = office.scheduleAppointment(patient.getId(), doctor.getId(), day.atTime(time));
                System.out.println("Scheduled. Appointment ID: " + a.getAppointmentId());
                return;
            } catch (IllegalArgumentException e) {
                System.out.println("Could not schedule: " + e.getMessage());
                if (readMenu(scanner, "Try another time?", "Yes", "No") == 2) return;
            }
        }
    }

    // read appointment ID -> office.cancelAppointment -> print result
    private static void secCancel(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Appointment ID: ");
        try {
            office.cancelAppointment(id);
            System.out.println("Appointment " + id + " canceled.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not cancel: " + e.getMessage());
        }
    }

    // ask for patient -> office.checkIn(id) -> print place in line
    private static void secCheckIn(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Patient ID: ");
        if (office.checkIn(id)) {
            int place = office.showWaitingPatients().size();
            System.out.println("Patient " + id + " checked in. Place in line: " + place);
        } else {
            System.out.println("Could not check in: patient not registered or already waiting.");
        }
    }
    // ask for patient -> office.addEmergency(id)
    private static void secEmergency(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Patient ID: ");
        if (office.addEmergency(id)) {
            System.out.println("Patient " + id + " moved to the emergency line.");
        } else {
            System.out.println("Could not add: patient not registered or already an emergency.");
        }
    }

    // office.showTodayAppointments() -> print each
    private static void secToday(MedicalOffice office, Scanner scanner) {
        List<Appointment> today = office.showTodayAppointments();
        if (today.isEmpty()) {
            System.out.println("No appointments today.");
            return;
        }
        System.out.println("Today's appointments:");
        for (Appointment a : today) {
            System.out.println(a);
        }
    }

    // office.getWaiting() -> print
    private static void nurWaiting(MedicalOffice office, Scanner scanner) {
        List<Patient> waiting = office.showWaitingPatients();
        if (waiting.isEmpty()) {
            System.out.println("No patients waiting.");
            return;
        }
        System.out.println("Waiting patients (in call order):");
        for (int i = 0; i < waiting.size(); i++) {
            System.out.println((i + 1) + ". " + waiting.get(i));
        }
    }
   // office.getAvailable() -> print each
    private static void nurAvailableDoctors(MedicalOffice office, Scanner scanner) {
        List<Doctor> available = office.showAvailableDoctors();
        if(available.isEmpty()){
            System.out.println("There are no doctors available at the moment");
            return;
        }
        System.out.println("Doctor Available at the moment:");
        for(Doctor d: available){
            System.out.println("ID: " + d.getId() + " Name: "+ d.getName()+ " Specialty: "+ d.getSpecialty());
        }

    }
    // choose specialty -> office.findDoctorsBySpecialty -> print each

    private static void nurFindBySpecialty(MedicalOffice office, Scanner scanner) {
        int choice = readMenu(scanner, "Specialty", "General", "Pediatric", "Neuro-opthalmologist", "Retinologist");
        Specialty specialty = switch (choice){
            case 1 -> Specialty.OFTALMOLOGO_GENERAL;
            case 2 -> Specialty.OFTALMOLOGO_PEDIATRICO;
            case 3 -> Specialty.NEURO_OFTALMOLOGO;
            case 4 -> Specialty.RETINOLOGO;
            default ->Specialty.OFTALMOLOGO_GENERAL;
        };

        List<Doctor> doctors = office.findDoctorsBySpecialty(specialty);
        if(doctors.isEmpty()){
            System.out.println("No doctors with that specialty");
            return;
        }
        System.out.print("Doctors with that specialty");
        for(Doctor d: doctors){
            String status = d.isAvailable() ? "available" : "not available";
            System.out.println("ID: " + d.getId() + " Name: " + d.getName() + " (" + status + ")");

        }
    }

    // read doctor ID -> toggle/set
    private static void docAvailability(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Doctor ID: ");
        Boolean nowAvailable = office.toggleDoctorAvailability(id);
        if (nowAvailable == null) {
            System.out.println("No doctor with that ID.");
        } else if (nowAvailable) {
            System.out.println("Doctor " + id + " is now AVAILABLE.");
        } else {
            System.out.println("Doctor " + id + " is now BUSY.");
        }
    }

    // read doctor ID -> office.assignPatientToDoc (doctorID) -> print who (emergency first)
    private static void docAttendNext(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Doctor ID: ");
        Patient p = office.assignPatientToDoc(id);
        if (p != null) {
            System.out.println("Now attending: " + p);
        } else {
            System.out.println("Could not assign: doctor not found or busy, or no patients waiting.");
        }
    }

    // read doctor ID + patient ID -> office.registerVisit -> print the visit
    private static void docRegisterVisit(MedicalOffice office, Scanner scanner) {
        int doctorId = readInt(scanner, "Doctor ID: ");
        int patientId = readInt(scanner, "Patient ID: ");
        try {
            Appointment visit = office.registerVisit(patientId, doctorId);
            System.out.println("Visit registered: " + visit);
            System.out.println("Doctor " + doctorId + " is available again.");
        } catch (IllegalArgumentException e) {
            System.out.println("Could not register visit: " + e.getMessage());
        }
    }

    // office.showRecentVisits() -> print, most recent first
    private static void docRecentVisits(MedicalOffice office, Scanner scanner) {
        List<Appointment> recent = office.showRecentVisits();
        if (recent.isEmpty()) {
            System.out.println("No visits yet.");
            return;
        }
        for (Appointment a : recent) {
            System.out.println(a);
        }
    }
    // read patient ID -> office.showPatientHistory(id) -> print each
    private static void patientHistory(MedicalOffice office, Scanner scanner) {
        int id = readInt(scanner, "Patient ID: ");
        try {
            List<Appointment> history = office.showPatientHistory(id);
            if (history.isEmpty()) {
                System.out.println("No appointments yet.");
                return;
            }
            for (Appointment a : history) {
                System.out.println(a);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    
    // --------------------------------Misc--------------------------------

    // type an existing patient's ID, or 0 to register a new one (real IDs start at 1001)
    private static Patient askForPatient(MedicalOffice office, Scanner scanner) {
        while (true) {
            int id = readInt(scanner, "Patient ID (0 = new patient): ");
            if (id == 0) {
                String name = readLine(scanner, "Patient's full name: ");
                Patient p = office.registerPatient(name, Priority.NORMAL);
                System.out.println("Registered. Patient ID: " + p.getId());
                return p;
            }
            Patient p = office.findPatient(id);
            if (p != null) return p;
            System.out.println("No patient with ID " + id + ".");
        }
    }

    // print every specialty with a number, return the one chosen
    private static Specialty chooseSpecialty(Scanner scanner) {
        Specialty[] all = Specialty.values();
        for (int i = 0; i < all.length; i++) {
            System.out.println("(" + (i + 1) + ") " + all[i]);
        }
        while (true) {
            int choice = readInt(scanner, "Specialty: ");
            if (choice >= 1 && choice <= all.length) return all[choice - 1];
            System.out.println("Choose a number from 1 to " + all.length + ".");
        }
    }

    // read a line that isn't empty
    private static String readLine(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            if (!line.isEmpty()) return line;
            System.out.println("This can't be empty.");
        }
    }

    // read a date like 2026-12-01 (yyyy-MM-dd)
    private static LocalDate readDate(Scanner scanner) {
        while (true) {
            try {
                return LocalDate.parse(readLine(scanner, "Date (yyyy-MM-dd): "));
            } catch (DateTimeParseException e) {
                System.out.println("Use the format yyyy-MM-dd, for example 2026-12-01.");
            }
        }
    }

    // read a time like 09:30 (HH:mm)
    private static LocalTime readTime(Scanner scanner) {
        while (true) {
            try {
                return LocalTime.parse(readLine(scanner, "Time (HH:mm, on the hour or half hour): "));
            } catch (DateTimeParseException e) {
                System.out.println("Use the format HH:mm, for example 09:30.");
            }
        }
    }

    // print numbered menu and keep asking until int is valid
    private static int readMenu(Scanner scanner, String title, String... options) {
        System.out.println();
        System.out.println(title);
        System.out.println("-".repeat(45));
        for (int i = 0; i < options.length; i++) {
            System.out.println("(" + (i + 1) + ") " + options[i]);
        }
        while (true) {
            int choice = readInt(scanner, "Choose an option: ");
            if (choice >= 1 && choice <= options.length) {
                return choice;
            }
            System.out.println("Choose a number from 1 to " + options.length + ".");
        }
    }

    // read line and ask until receive int
    private static int readInt(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String line = scanner.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("\"" + line + "\" is not a number. Try again.");
            }
    }
}
}
