public class nurse extends employee{
    protected Role role;
    nurse(int id, String name, Role role){
        super(id, name);
        this.role = role;
    }
}
