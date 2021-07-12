package DAO;

import model.Category;
import model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO implements GeneralDAO<Category>{
    private SQLConnection sqlConnection = new SQLConnection();
    private final String FIND_ALL = "SELECT * FROM category;";
    @Override
    public List<Category> findAll() throws SQLException, ClassNotFoundException {
        List<Category> categories = new ArrayList<>();
        Connection connection = sqlConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            int id = resultSet.getInt("categoryId");
            String name = resultSet.getString("categoryName");
            categories.add(new Category(id, name));
        }
        return categories;
    }

    @Override
    public Category findById(int id) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public List<Category> findByName(String name) throws SQLException, ClassNotFoundException {
        return null;
    }

    @Override
    public void add(Category category) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void edit(int id, Category category) throws SQLException, ClassNotFoundException {

    }

    @Override
    public void delete(int id) throws SQLException, ClassNotFoundException {

    }
}
