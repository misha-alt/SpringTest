package misha.dao;

import misha.models.Person;
import org.springframework.stereotype.Component;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Component
public class PersonDAO {
    private static int PIOPLE_COUNT;
    private static final String URL = "jdbc:h2:~/test2";
    private static final String USERNAME = "a2";
    private static final String PASSWORD = "1";

   private static Connection connection;
   static {
       try {
           Class.forName("org.h2.Driver");
       } catch (ClassNotFoundException e) {
           e.printStackTrace();
       }
       {
           try {
               connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
           } catch (SQLException e) {
               e.printStackTrace();
           }
       }
   }
    public List<Person> index(){

        List<Person> piple  = new ArrayList<>();
            try {
                Statement statement = connection.createStatement();
                String SQL = "SELECT * FROM PERSONS";
                ResultSet resultSet = statement.executeQuery(SQL);

                while (resultSet.next()){
                    Person person = new Person();
                    person.setId(resultSet.getInt("id"));
                    person.setName(resultSet.getString("name"));
                    person.setAge(resultSet.getInt("age"));
                    person.setEmail(resultSet.getString("email"));
                    piple.add(person);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        return piple;
    }
    public Person shpw(int id){
       Person person =null;
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("select * from PERSONS where id =?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();

            person = new Person();
            person.setId(resultSet.getInt("id"));
            person.setName(resultSet.getString("name"));
            person.setAge(resultSet.getInt("age"));
            person.setEmail(resultSet.getString("email"));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return person;
    }
    public void save (Person person){
      //  person.setId(++PIOPLE_COUNT);
       // piple.add(person);
        try {
            PreparedStatement preparedStatement =
                    connection.prepareStatement("INSERT INTO PERSONS VALUES(1,?,?,?)");
            preparedStatement.setString(1, person.getName());
            preparedStatement.setInt(2,person.getAge());
            preparedStatement.setString(3,person.getEmail());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void update(int id,Person updatedPerson){
        Person personToBeUpdated= shpw(id);
        personToBeUpdated.setName(updatedPerson.getName());
    }
}
