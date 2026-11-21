package peopleClasses;
import java.util.Scanner;

import ENUMS.Priority;
import Office.MedicalOffice; 
public class Secretary extends Employee{
    MedicalOffice office;
    public Secretary(int id, String name){
        super(id, name);
    }
}
