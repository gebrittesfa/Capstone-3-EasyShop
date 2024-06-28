package org.yearup.data.mysql;

//import com.sun.jdi.connect.spi.Connection;
import java.sql.Connection;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.yearup.data.CategoryDao;
import org.yearup.models.Category;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Component
//@Repository
public class MySqlCategoryDao extends MySqlDaoBase implements CategoryDao {
   // private DataSource dataSource;

//    @Autowired
//    public MySqlCategoryDao(BasicDataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    @Autowired
    public MySqlCategoryDao(DataSource dataSource) {
        super(dataSource);
        this.dataSource =dataSource;
    }

//    @Autowired
//    MySqlCategoryDao mySqlCategoryDao;

    @Override
    public List<Category> getAllCategories()  {
        // get all categories
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";

//        try (Connection connection = dataSource.getConnection()) {
       // Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection =getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Category category = new Category();
                category.setCategoryId(resultSet.getInt("category_id"));
                category.setName(resultSet.getString("name"));
                category.setDescription(resultSet.getString("description"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
        //mySqlCategoryDao.getAllCategories();
    }
    @Override
    public Category getById(int categoryId) {
        Category category = null;
        String query = "SELECT * FROM categories WHERE category_id = ?";
        try (Connection connection =getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, categoryId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) { category = mapRow(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

//    public Category getById(int categoryId)
//    {
//        // get category by id
//        Category category = null;
//        String query = "SELECT id, name, description FROM categories WHERE" +
//                " category_id=?;";
//        try (Connection connection = dataSource.getConnection();
//             PreparedStatement statement = connection.prepareStatement(query);){
//            statement.setString(1, category.getCategoryId());
//            statement.setString(2, category.getName());
//            statement.setString(3, category.getDescription());
//            statement.executeUpdate();
//
//            try (ResultSet resultSet = statement.executeQuery()){
//                if(resultSet.next()) {
//                    category.setCategoryId(resultSet.getInt("categoryId"));
//                    category.setName(resultSet.getString("name"));
//                    category.setDescription(resultSet.getString("description"));
//                    category = new Category(categoryId, name, description);
//                }
//            }
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return category;
//    }
        //return mySqlCategoryDao.getById(categoryId);


    @Override
    public Category create(Category category)
    {
        // create a new category
        String sql = "INSERT INTO categories (name, description) VALUES (?, ?)";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.executeUpdate();

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setCategoryId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return category;
    }

    @Override
    public void update(int categoryId, Category category)
    {
        // update category
        String sql = "UPDATE categories SET name = ?, description = ? WHERE category_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, category.getName());
            statement.setString(2, category.getDescription());
            statement.setInt(3, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void delete(int categoryId)
    {
        // delete category
        String sql = "DELETE FROM categories WHERE category_id = ?";
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private Category mapRow(ResultSet row) throws SQLException
    {
        int categoryId = row.getInt("category_id");
        String name = row.getString("name");
        String description = row.getString("description");

        Category category = new Category()
        {{
            setCategoryId(categoryId);
            setName(name);
            setDescription(description);
        }};

        return category;
    }
}
