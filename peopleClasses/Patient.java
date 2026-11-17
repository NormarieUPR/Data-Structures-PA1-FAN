package peopleClasses;
import ENUMS.Priority;

public class Patient extends Person {
    protected Priority priority;
    protected long arrivalOrder;
    
    public Patient(int id, String name, Priority priority, long arrivalOrder){
        super(id, name);
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
    }

    // getters
    public Priority getPriority() {
        return priority;
    }
    public long getArrivalOrder() {
        return arrivalOrder;    
    }

    // setters
    public void setArrivalOrder(long arrivalOrder) {
        this.arrivalOrder = arrivalOrder;
    }
    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    @Override 
    public String toString() {
        return "#" + id + " " + name + " (" + priority + ")";
    }
}
