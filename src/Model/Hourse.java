package Model;

public class Hourse extends Pack_animals{
    public Hourse(String type_of_animal, String name, String birthday, String command){
        super.type_of_animal = type_of_animal;
        super.name = name;
        super.birth_day = birthday;
        super.command = command;
    }

    @Override
    public String toString() {
        return type_of_animal + "," + name + "," + birth_day + "," + command;
    }
}
