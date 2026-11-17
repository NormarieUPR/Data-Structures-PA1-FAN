package peopleClasses;
import ENUMS.Specialty;
public class Doctor extends Employee {
    protected final Specialty specialty;
    protected boolean available; 
    
    public Doctor(int id, String name, Specialty specialty, boolean available){
        super(id,name);
        this.specialty = specialty; 
        this.available = available; 
    }

    public Specialty getSpecialty(){
        return this.specialty;
    }
    public boolean isAvailable() {
        return available; 
    } 
     public void setAvailable(boolean available) {
        this.available = available;
    } 
}
