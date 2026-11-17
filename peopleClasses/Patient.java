package peopleClasses;
import java.util.List;
import java.util.ArrayList;
import ENUMS.*;
import misc.*;

public class Patient extends Person {
    protected Priority priority;
    protected final long arrivalOrder;
    protected final List<Appointment> history;
    
    public Patient(int id, String name, Priority priority, long arrivalOrder){
        super(id, name);
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
        this.history = new ArrayList<>();
    }

    public Priority getPriority() {
        return priority;
    }

    public long getArrivalOrder() {
        return arrivalOrder;    
    }

    public List<Appointment> getHistory() {
        return history;
    }

    public void addToHistory(Appointment a) {
        history.add(a);
    }

    public void removeFromHistory(Appointment a) {
        history.remove(a);
    }
}
