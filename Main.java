import java.util.List;
import java.util.Scanner;
import java.time.LocalDateTime;
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
                    "Patient history",
                    "Back");
            switch (opt) {
                case 1 -> docAvailability(office, scanner);
                case 2 -> docAttendNext(office, scanner);
                case 3 -> docRegisterVisit(office, scanner);
                case 4 -> patientHistory(office, scanner);
                case 5 -> running = false;
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
    private static void secSchedule(MedicalOffice office, Scanner scanner) {}
    // ask for patient -> choose specialty -> list doctors -> pick one
    // -> read date -> show that doctors day -> read time -> office.scheduleApp
    // -> print App ID (or fail, pick another time etc)
    private static void secCancel(MedicalOffice office, Scanner scanner) {}
    // read app ID -> office.cancelApp -> print result

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

    private static void secEmergency(MedicalOffice office, Scanner scanner) {}
    // ask for patient -> office.addEmergency(id)
    private static void secToday(MedicalOffice office, Scanner scanner) {}
    // office.getToday() -> print

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

    private static void nurAvailableDoctors(MedicalOffice office, Scanner scanner) {}
    // office.getAvailable() -> print each
    private static void nurFindBySpecialty(MedicalOffice office, Scanner scanner) {}
    // choose specialty -> office.findDoctorsBySpecialty -> print each
    private static void docAvailability(MedicalOffice office, Scanner scanner) {}
    // read doctor ID -> toggle/set
    private static void docAttendNext(MedicalOffice office, Scanner scanner) {}
    // read doctor ID -> office.assignNextPatient(doctorID) -> print who (emergency first)
    private static void docRegisterVisit(MedicalOffice office, Scanner scanner) {}
    // read doctor ID + patient ID -> office.registerVisit()
    private static void patientHistory(MedicalOffice office, Scanner scanner) {}
    // read patient ID -> office.getHistory(id) -> print each
    
    // --------------------------------Misc--------------------------------

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
