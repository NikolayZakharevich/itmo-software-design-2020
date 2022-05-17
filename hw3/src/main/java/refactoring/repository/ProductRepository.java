package refactoring.repository;

import refactoring.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class ProductRepository {

    private static final String CONNECTION_URL = "jdbc:sqlite:test.db";

    public List<Product> getAll() throws SQLException {
        return getProducts("SELECT * FROM PRODUCT");
    }

    public Product getMax() throws SQLException {
        return getProduct("SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1");
    }

    public Product getMin() throws SQLException {
        return getProduct("SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1");
    }

    public long getSum() throws SQLException {
        return getOperationResult("SELECT SUM(price) FROM PRODUCT");
    }

    public long getCount() throws SQLException {
        return getOperationResult("SELECT COUNT(*) FROM PRODUCT");
    }

    public void save(Product product) throws SQLException {
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "INSERT INTO PRODUCT " +
                    "(NAME, PRICE) VALUES (\"" + product.getName() + "\"," + product.getPrice() + ")";
            Statement stmt = c.createStatement();
            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    public static void init() throws SQLException {
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "CREATE TABLE IF NOT EXISTS PRODUCT" +
                    "(ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    " NAME           TEXT    NOT NULL, " +
                    " PRICE          INT     NOT NULL)";
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        }
    }

    private List<Product> getProducts(String sql) throws SQLException {
        return exec(sql, ProductRepository::productsFromResultSet);
    }

    private long getOperationResult(String sql) throws SQLException {
        return exec(sql, ProductRepository::numberFromResultSet);
    }

    private Product getProduct(String sql) throws SQLException {
        return getProducts(sql).get(0);
    }

    private static int numberFromResultSet(ResultSet rs) {
        try {
            return rs.next() ? rs.getInt(1) : 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private static List<Product> productsFromResultSet(ResultSet rs) {
        List<Product> products = new ArrayList<>();
        try {
            while (rs.next()) {
                products.add(new Product(rs.getString("name"), rs.getInt("price")));
            }
            return products;
        } catch (SQLException e) {
            return products;
        }
    }

    private <R> R exec(String sql, Function<ResultSet, R> resultSetMapper) throws SQLException {
        R res;
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            res = resultSetMapper.apply(rs);
            rs.close();
            stmt.close();
        }
        return res;
    }

}
