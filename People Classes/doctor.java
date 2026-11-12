public class doctor extends person {
    protected final Specialty specialty;
    protected Boolean available; 
    
    public doctor(int id, String name, Specialty specialty, Boolean available){
        super(id,name);
        this.specialty = specialty; 
        this.available = available; 
    }

    public Specialty getSpecialty(){
        return this.specialty;
    }
}
