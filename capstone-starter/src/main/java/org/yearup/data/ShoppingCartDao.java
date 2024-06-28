package org.yearup.data;

import org.yearup.models.Product;
import org.yearup.models.ShoppingCart;

import java.math.BigDecimal;
import java.util.List;

public interface ShoppingCartDao
{



    ShoppingCart create(ShoppingCart shoppingCart);

    List<ShoppingCart> getAllCarts();
    ShoppingCart getById(int cartId);

    void updateCart(int cartId, ShoppingCart shoppingCart);
    void deleteCart(int cartId);
}
