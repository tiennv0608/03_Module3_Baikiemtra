package DAO;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements GeneralDAO<Product>{
    private SQLConnection sqlConnection = new SQLConnection();
    private final String FIND_ALL = "SELECT * FROM product;";
    private final String CREATE = "INSERT INTO product(name, price, quantity, color, description, categoryId) values (?,?,?,?,?,?);";
    private final String FIND_BY_ID = "SELECT * FROM product where id = ?;";
    private final String UPDATE = "Update product set name = ?, price = ?, quantity = ?, color = ?, description =?, categoryId =? where id =?;";
    private final String DELETE = "DELETE from product where id =?;";
    private final String FIND_BY_NAME = "SELECT * FROM product where name like ?;";


    @Override
    public List<Product> findAll() throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String color = resultSet.getString("color");
            String description = resultSet.getString("description");
            int categoryId = resultSet.getInt("categoryId");
            products.add(new Product(id, name, price, quantity, color, description, categoryId));
        }
        return products;
    }

    @Override
    public Product findById(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
        preparedStatement.setInt(1,id);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            String name = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String color = resultSet.getString("color");
            String description = resultSet.getString("description");
            int categoryId = resultSet.getInt("categoryId");
            return new Product(id, name, price, quantity, color, description, categoryId);
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) throws SQLException, ClassNotFoundException {
        List<Product> products = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_NAME);
        preparedStatement.setString(1,"%"+name+"%");
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("id");
            String productName = resultSet.getString("name");
            double price = resultSet.getDouble("price");
            int quantity = resultSet.getInt("quantity");
            String color = resultSet.getString("color");
            String description = resultSet.getString("description");
            int categoryId = resultSet.getInt("categoryId");
            products.add(new Product(id, productName, price, quantity, color, description, categoryId));
        }
        return products;
    }

    @Override
    public void add(Product product) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3,product.getQuantity());
        preparedStatement.setString(4,product.getColor());
        preparedStatement.setString(5, product.getDescription());
        preparedStatement.setInt(6, product.getCategoryId());
        preparedStatement.executeUpdate();
    }

    @Override
    public void edit(int id, Product product) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getQuantity());
        preparedStatement.setString(4, product.getColor());
        preparedStatement.setString(5, product.getDescription());
        preparedStatement.setInt(6, product.getCategoryId());
        preparedStatement.setInt(7,id);
        preparedStatement.executeUpdate();
    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
        preparedStatement.setInt(1, id);
        preparedStatement.executeUpdate();
    }
}
