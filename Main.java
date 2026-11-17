import java.util.Scanner;

public class Main{
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        MedicalOffice office = new MedicalOffice();
        System.out.println("Welcome To Heisenbergs Ophthalmology!");
        boolean running = true;
        while (running){
            System.out.println("What can we do for you?");
            System.out.println("Choose an action below by typing its number:");
            System.out.println("-".repeat(45));
            System.out.println("1. Patient");
            System.out.println("2. Employee");
            System.out.println("3. Exit");

            int opt;
            opt = scanner.nextInt();
            switch(opt){
                case 1:
                    patientMenu(office, scanner);
                    break;
                case 2:
                    staffMenu(office,scanner);
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid. Try again.");

            }
        }

        System.out.println("Thank you for visiting us!");
        scanner.close();

    }
    private static void patientMenu(MedicalOffice office, Scanner scanner){
        boolean running = true;
        System.out.println("What can we do for you?");
        while(running){
            System.out.println("Choose an action below by typing its number:");
            System.out.println("-".repeat(45));
            System.out.println("1. Schedule Appointment");
            System.out.println("2. I have an Emergency");
            System.out.println("3. Cancel an Appointment");
            System.out.println("4. Check in");
            System.out.println("5. View my history");
            System.out.println("6. Back");

            int opt;
            opt = scanner.nextInt();
            switch(opt){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;

                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid. Try again.");
            }
        }
    }
    //un maybe for now to access staff things
    private static void staffMenu(MedicalOffice office, Scanner scanner){
        boolean running = true;
        System.out.println("***AUTHORIZED PERSONNEL ONLY***");
        while(running){
            System.out.println("Choose an action below by typing its number:");
            System.out.println("-".repeat(45));
            System.out.println("1. Secretary");
            System.out.println("2. Nurse");
            System.out.println("3. Doctor");
            System.out.println("4. Back");

            int opt;
            opt = scanner.nextInt();
            switch(opt){
                case 1:
                    secretaryMenu(office,scanner);
                    break;
                case 2:
                    nurseMenu(office,scanner);
                    break;
                case 3:
                    docMenu(office,scanner);
                    break;
                case 4:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid. Try again.");
            }
        }
    }
    private static void docMenu(MedicalOffice office, Scanner scanner){}
    private static void nurseMenu(MedicalOffice office, Scanner scanner){}
    private static void secretaryMenu(MedicalOffice office, Scanner scanner){}

    

}
