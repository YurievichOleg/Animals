package Model;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;

public class AnimalStorage {
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

    public void addNewAnimal(String type_of_animal, String name, String birth_day, String command)throws ClassNotFoundException, SQLException, IIOException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("use human_friends");
        statement.executeUpdate("INSERT INTO test (type_of_animal, Name, birth_day, command)\n" +
                "values('" +
                type_of_animal  + "','" + name + "', '" + birth_day + "' ,'" + command +"');");
    }

    public String seeCommand(String name) throws ClassNotFoundException, SQLException, IIOException{
        String res = "";
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("use human_friends");
        ResultSet resultSet = statement.executeQuery("Select * from test where test.Name = '" + name +"';");
        while (resultSet.next()){
            res = resultSet.getString(5);
        }
        if (res == null){
            return "Ничег не умеет";
        } else {
            return res;
        }
    }

    public void addCommand(String name, String command) throws ClassNotFoundException, SQLException, IIOException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("use human_friends");
        if (seeCommand(name).equals("Ничег не умеет")){
            statement.executeUpdate("UPDATE test SET command = '" + command + "' WHERE Name = '" + name + "' LIMIT 1000;");
        } else {
            statement.executeUpdate("UPDATE test SET command = concat(command, ', " + command + "') WHERE Name = '" + name + "' LIMIT 1000;");
        }
    }

    public boolean exist(String name) throws ClassNotFoundException, SQLException, IIOException{
        boolean res = false;
        Class.forName("com.mysql.cj.jdbc.Driver");
        statement.executeUpdate("use human_friends");
        ResultSet resultSet = statement.executeQuery("SELECT EXISTS(SELECT * FROM test WHERE Name = '" + name + "');");
        if (resultSet.next()){
            res = resultSet.getBoolean(1);
        }
        return res;
    }
}
