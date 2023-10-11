package oleksii.pl;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataBaseSearch {
    public Client searchByParameter(String parameterValue1, String parameterValue2) {
        List<Client> resultList = new ArrayList<>();
        Client myObject = new Client();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseConnector.getConnection();
            String sql = "SELECT * FROM clients WHERE email = ? and password = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, parameterValue1);
            preparedStatement.setString(2, parameterValue2);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                String email = resultSet.getString("email");
                int numberOfCard = resultSet.getInt("numbercard");
                int pinOfCard = resultSet.getInt("cardpin");
                BigDecimal money = resultSet.getBigDecimal("money");
                String username = resultSet.getString("username");
                String password = resultSet.getString("password");

                myObject = new Client(id, name, surname, email, numberOfCard, pinOfCard, money, username, password);
                resultList.add(myObject);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return myObject;
    }
}
