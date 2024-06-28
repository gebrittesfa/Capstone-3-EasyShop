//package org.yearup.data.mysql;
//
//import org.yearup.data.ShoppingCartDao;
//import org.yearup.models.ShoppingCart;
//
//import java.sql.*;
//import java.util.List;
//
//import static jdk.internal.net.http.HttpConnection.getConnection;
//
//public class MySqlShoppingCrtDao implements ShoppingCartDao {
//    @Override
//    public ShoppingCart create(ShoppingCart shoppingCart) {
//
//        try (Connection connection = getConnection();
//             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
//            statement.setString(1, category.getName());
//            statement.setString(2, category.getDescription());
//            statement.executeUpdate();
//
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    category.setCategoryId(generatedKeys.getInt(1));
//                }
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        return category;
//    }
//
//    @Override
//    public List<ShoppingCart> getAllCarts() {
//        return null;
//    }
//
//    @Override
//    public ShoppingCart getById(int cartId) {
//        return null;
//    }
//
//    @Override
//    public void updateCart(int cartId, ShoppingCart shoppingCart) {
//
//    }
//
//    @Override
//    public void deleteCart(int cartId) {
//
//    }
//}
