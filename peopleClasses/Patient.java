package peopleClasses;
import ENUMS.Priority;

public class Patient extends Person {
    protected Priority priority;
    protected final long arrivalOrder;
    
    public Patient(int id, String name, Priority priority, long arrivalOrder){
        super(id, name);
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
    }
    public Priority getPriority() {
        return priority;
    }
    public long getArrivalOrder() {
        return arrivalOrder;    
    }
}
