package View;

import Model.AnimalStorage;
import Model.Hourse;
import Presenter.Presenter;


import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class App {
    public static final String USER_NAME =  "root";
    public  static  final String PASSWORD;

    static {
        try {
            PASSWORD = Files.readString(Path.of("/Users/goodday/TextFiles/Pass.txt"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final String URL = "jdbc:mysql://localhost:3306/mysql";
    public static Statement statement;
    public static Connection connection;
    static {
        try {
            connection = DriverManager.getConnection(URL, USER_NAME, PASSWORD);
        }catch (SQLException throwables){
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    static {
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            throw new RuntimeException();
        }
    }
    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }
    public static void createTableTables() throws ClassNotFoundException, SQLException, IIOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("use human_friends");
        statement.executeUpdate("INSERT INTO test (type_of_animal, Name, birth_day, command)\n" +
                "values ('hourse', 'Gosha', '2021-12-2', 'stop')");
    }
    public static void main(String[] args) {

        Calendar d = new GregorianCalendar(2021, 12, 1) ;
        System.out.println(d.get(Calendar.YEAR));
        String s = "2022-2-3";
        System.out.println(isValidDate(s));
        Hourse hor = new Hourse("hourse", "Gosha", "2021-12-2", "stop");
        System.out.println(hor.toString());
//        try {
//            createTableTables();
//        } catch (ClassNotFoundException e) {
//            throw new RuntimeException(e);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        } catch (IIOException e) {
//            throw new RuntimeException(e);
//        }
        AnimalStorage animStor = new AnimalStorage();
        Presenter presenter = new Presenter();
        presenter.getNewAnimal("dog", "Tort", "20212-12-21", "фас");
        System.out.println(presenter.seeListOfCommand("Smoke"));
        presenter.trainNewTeams("Tor", "play");

//        String asd = "2023-2-29";
//        System.out.println(presenter.isValidDate(asd));
//        System.out.println(Integer.parseInt(asd.split("-")[0]));
//        Calendar calendar = new GregorianCalendar(Integer.parseInt(asd.split("-")[0]),Integer.parseInt(asd.split("-")[1]),Integer.parseInt(asd.split("-")[2]));
//        System.out.println(calendar.before(new GregorianCalendar()));
        View v = new View();
        v.run();
    }
}
