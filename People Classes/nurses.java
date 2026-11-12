public class nurses extends person{
    protected Role role;
    nurses(int id, String name, Role role){
        super(id, name);
        this.role = role;
    }
}
