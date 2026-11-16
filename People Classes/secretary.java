public class secretary extends employee{
    protected Role role;
    
    secretary(int id, String name, Role role){
        super(id, name);
        this.role = role;
    }
}
