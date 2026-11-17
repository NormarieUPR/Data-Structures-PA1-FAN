package peopleClasses;
import ENUMS.Specialty;
import java.util.TreeMap;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.LocalDate;
import misc.*;

public class Doctor extends Employee {
    protected final Specialty specialty;
    protected boolean available; 
    private final TreeMap<LocalDateTime, Appointment> schedule = new TreeMap<>();
    
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
    public boolean hasAppointmentAt(LocalDateTime time) {
        return schedule.containsKey(time);
    }
    public void setAvailable(boolean available) {
        this.available = available;
    } 
    public void addToSchedule(Appointment a) {
        schedule.put(a.getTime(), a);
    }
    public void removeFromSchedule(Appointment a) {
        schedule.remove(a.getTime());
    }
    public List<Appointment> getAppointmentsOn(LocalDate day) {
        return new ArrayList<>(schedule.subMap(day.atStartOfDay(),
                    day.plusDays(1).atStartOfDay()).values());
    }

}
