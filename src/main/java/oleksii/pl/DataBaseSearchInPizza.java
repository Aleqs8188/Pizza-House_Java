package oleksii.pl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataBaseSearchInPizza {
    OrderList orderList = new OrderList();

    ArrayList<Pizza> fromDBtoList() {
        ArrayList<Pizza> resultList = new ArrayList<>();
        Pizza myObject;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseConnector.getConnection();
            String sql = "SELECT * FROM pizzas WHERE id > ?";
            preparedStatement = connection.prepareStatement(sql);
            int idThreshold = 0;
            preparedStatement.setInt(1, idThreshold);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String size = resultSet.getString("size");
                float price = resultSet.getFloat("price");
                int popularity = resultSet.getInt("popularity");
                String category = resultSet.getString("category");

                myObject = new Pizza(id, name, description, size, price, popularity, category);
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
        return resultList;
    }

    void getPizzaFromDbByParam(int choice) {
        Pizza myObject = null;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DataBaseConnector.getConnection();
            String sql = "SELECT * FROM pizzas WHERE id = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, choice);

            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String size = resultSet.getString("size");
                float price = resultSet.getFloat("price");
                int popularity = resultSet.getInt("popularity");
                String category = resultSet.getString("category");

                myObject = new Pizza(id, name, description, size, price, popularity, category);
                double sum = orderList.getTotalSumToPay();
                orderList.setTotalSumToPay(sum + myObject.getPrice());
                orderList.orderList.add(myObject);
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
    }

    float deleteFromOrder(int number) {
        Iterator<Pizza> iterator = orderList.orderList.iterator();
        Pizza pizza = null;
        float priceToDel = 0;
        while (iterator.hasNext()) {
            pizza = iterator.next();
            if (pizza.getId() == number) {
                priceToDel = pizza.getPrice();
                iterator.remove();
            }
        }
        return priceToDel;
    }

    public void printMenuPizzas() {
        ArrayList<Pizza> pizzas = fromDBtoList();
        for (Pizza pizza : pizzas) {
            System.out.println(pizza);
        }
    }

}
