package oleksii.pl;


import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RewriterClientDB {
    Connection connection = null;
    PreparedStatement preparedStatement = null;

    public void updateMoneyForClient(Client client, BigDecimal newMoney) {
        try {
            connection = DataBaseConnector.getConnection();
            String sql = "UPDATE clients SET money = ? WHERE id = ?";

            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setBigDecimal(1, newMoney);
            preparedStatement.setDouble(2, client.getId());
            client.setMoney(newMoney);
            preparedStatement.executeUpdate();
        } catch (
                SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
