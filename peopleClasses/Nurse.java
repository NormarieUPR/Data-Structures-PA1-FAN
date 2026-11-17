package peopleClasses;
import ENUMS.Role;

public class Nurse extends Employee{
    protected Role role;

    public Nurse(int id, String name, Role role){
        super(id, name);
        this.role = role;
    }
}
