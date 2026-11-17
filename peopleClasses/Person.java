package peopleClasses;
public abstract class Person {
    protected final int id;
    protected final String name;

    public Person(int id, String name){
        this.id = id;
        this.name = name;
    }

    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    
}


