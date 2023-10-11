package oleksii.pl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ObjectWriter {
    public void writePersonToDatabase(Client person) {
        String insertQuery = "INSERT INTO clients (name, surname, email, numbercard, cardPin, money, username, password) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = DataBaseConnector.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getEmail());
            preparedStatement.setInt(4, person.getNumbercard());
            preparedStatement.setInt(5, person.getCardPin());
            preparedStatement.setObject(6, person.getMoney());
            preparedStatement.setString(7, person.getUsername());
            preparedStatement.setString(8, person.getPassword());
            preparedStatement.executeUpdate();
            System.out.println("*****************Successful*****************");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
