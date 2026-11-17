package peopleClasses;
import ENUMS.Role;

public class Secretary extends Employee{
    protected Role role;
    
    public Secretary(int id, String name, Role role){
        super(id, name);
        this.role = role;
    }
}
