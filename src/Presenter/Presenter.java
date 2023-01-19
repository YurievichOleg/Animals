package Presenter;

import Model.AnimalStorage;

import javax.imageio.IIOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Presenter implements GetNewAnimal, SeeListOfCommand, TrainNewTeams{
    AnimalStorage animalStorage = new AnimalStorage();

    @Override
    public void getNewAnimal(String type_of_animal, String name, String birth_day, String command) {


        try {
            if(!animalStorage.exist(name)){
                if (isValidDate(birth_day) ){
                    Calendar calendar = new GregorianCalendar(Integer.parseInt(birth_day.split("-")[0]),
                            Integer.parseInt(birth_day.split("-")[1]) - 1,Integer.parseInt(birth_day.split("-")[2]));
                    if (calendar.before(new GregorianCalendar())){
                        animalStorage.addNewAnimal(type_of_animal, name, birth_day, command);
                    } else {
                        System.out.println("Не верный формат даты рождени животного.");
                    }
                } else {
                    System.out.println("Не верный формат даты рождени животного.");
                }

            } else {
                System.out.println("Выберете другую кличку для животного, эта уже принадлежит другому животному.");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IIOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String seeListOfCommand(String name) {
        String listOfCommand;
        try {
            if (animalStorage.exist(name)){
                listOfCommand = animalStorage.seeCommand(name);
            } else {
                listOfCommand = "Нет животного с такой кличкой.";
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IIOException e) {
            throw new RuntimeException(e);
        }

        return listOfCommand;
    }

    @Override
    public void trainNewTeams(String name, String command) {
        try {
            if (animalStorage.exist(name)){
                animalStorage.addCommand(name, command);
            } else {
                System.out.println("Нет животного с такой кличкой.");
            }
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (IIOException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
}
