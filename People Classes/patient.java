public class patient extends person {
    protected Priority priority;
    protected final long arrivalOrder;
    
    public patient(int id, String name, Priority priority, long arrivalOrder){
        super(id, name);
        this.priority = priority;
        this.arrivalOrder = arrivalOrder;
    }
    
}
