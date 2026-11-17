import java.util.Scanner;
import java.time.LocalDateTime;
import ENUMS.*;
import misc.*;

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
    
    private static void secRegisterPatient(MedicalOffice office, Scanner scanner) {}
    // read name -> office.registerPatient(name) -> print ID
    private static void secRegisterEmployee(MedicalOffice office, Scanner scanner) {}
    // choose employee type, read name (and specialty) -> call office.hire/registeremployee() -> print ID
    private static void secSchedule(MedicalOffice office, Scanner scanner) {}
    // ask for patient -> choose specialty -> list doctors -> pick one
    // -> read date -> show that doctors day -> read time -> office.scheduleApp
    // -> print App ID (or fail, pick another time etc)
    private static void secCancel(MedicalOffice office, Scanner scanner) {}
    // read app ID -> office.cancelApp -> print result
    private static void secCheckIn(MedicalOffice office, Scanner scanner) {}
    // ask for patient -> office.checkin(id) -> print place in line
    private static void secEmergency(MedicalOffice office, Scanner scanner) {}
    // ask for patient -> office.addEmergency(id)
    private static void secToday(MedicalOffice office, Scanner scanner) {}
    // office.getToday() -> print
    private static void nurWaiting(MedicalOffice office, Scanner scanner) {}
    // office.getWaiting() -> print
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
