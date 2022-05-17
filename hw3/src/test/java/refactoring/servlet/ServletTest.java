package refactoring.servlet;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Files;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.AbstractMap.SimpleEntry;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import javax.servlet.http.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ServletTest {

    private final String OK_RESPONSE = "OK";

    private HttpServletRequest request;
    private HttpServletResponse response;

    private Method addProductDoGet;
    private Method getProductsDoGet;
    private Method queryDoGet;

    private StringWriter stringWriter;
    private PrintWriter writer;

    private File dbFile = new File("test.db");
    private File dbCopyFile = new File("test-copy.db");

    @Before
    public void setUp() throws NoSuchMethodException, IOException {
        makeTestEnv();

        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);

        addProductDoGet = AddProductServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        addProductDoGet.setAccessible(true);

        getProductsDoGet = GetProductsServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        getProductsDoGet.setAccessible(true);

        queryDoGet = QueryServlet.class.getDeclaredMethod("doGet", HttpServletRequest.class, HttpServletResponse.class);
        queryDoGet.setAccessible(true);

        stringWriter = new StringWriter();
        writer = new PrintWriter(stringWriter);
        when(response.getWriter()).thenReturn(writer);
    }

    @After
    public void tearDown() throws IOException {
        returnProdEnv();
    }

    @Test
    public void testAddAndGetProduct() throws Exception {
        String name = "Taya";
        String price = String.valueOf(Integer.MAX_VALUE);
        addProduct(name, price);

        getProductsDoGet.invoke(new GetProductsServlet(), request, response);
        writer.flush();
        String res = stringWriter.toString();
        assertTrue(res.contains(OK_RESPONSE));
        assertTrue(res.contains(name + "\t" + price));
    }

    @Test
    public void testAddNProductsAndQuery() throws Exception {
        int n = 5;
        String name = "Taya";
        int price = Integer.MAX_VALUE / n;

        for (int i = 0; i < n; i++) {
            addProduct(name, String.valueOf(price));
        }

        List<Map.Entry<String, String>> commandResults = Arrays.asList(
                new SimpleEntry<>("sum", "Summary price: \n" + price * n),
                new SimpleEntry<>("min", name + "\t" + price),
                new SimpleEntry<>("max", name + "\t" + price)
        );

        for (Map.Entry<String, String> commandResult : commandResults) {
            assertCommandHasResult(commandResult.getKey(), commandResult.getValue());
        }
    }

    @Test
    public void testQuery() throws Exception {
        List<Map.Entry<String, Integer>> products = Arrays.asList(
                new SimpleEntry<>("Taya1", 10),
                new SimpleEntry<>("Taya2", 20),
                new SimpleEntry<>("Taya3", 30),
                new SimpleEntry<>("Taya4", 40)
        );

        for (Map.Entry<String, Integer> product : products) {
            addProduct(product.getKey(), product.getValue().toString());
        }

        List<Map.Entry<String, String>> commandResults = Arrays.asList(
                new SimpleEntry<>("sum", "Summary price: \n100"),
                new SimpleEntry<>("min", "Taya1\t10"),
                new SimpleEntry<>("max", "Taya4\t40")
        );

        for (Map.Entry<String, String> commandResult : commandResults) {
            assertCommandHasResult(commandResult.getKey(), commandResult.getValue());
        }
    }

    private void addProduct(String name, String price) throws InvocationTargetException, IllegalAccessException {
        setAttribute("name", name);
        setAttribute("price", price);

        addProductDoGet.invoke(new AddProductServlet(), request, response);
        writer.flush();
        assertTrue(stringWriter.toString().contains(OK_RESPONSE));
        reset(request);
    }

    private void assertCommandHasResult(String command, String result) throws InvocationTargetException, IllegalAccessException {
        setAttribute("command", command);
        queryDoGet.invoke(new QueryServlet(), request, response);
        writer.flush();
        assertTrue("Command " + command + " failed", stringWriter.toString().contains(result));
    }

    private void makeTestEnv() throws IOException {
        if (dbCopyFile.isFile()) {
            dbCopyFile.delete();
        }
        Files.copy(dbFile.toPath(), dbCopyFile.toPath());
        try (Connection c = DriverManager.getConnection("jdbc:sqlite:test.db")) {
            Statement stmt = c.createStatement();
            stmt.executeQuery("DELETE FROM PRODUCT;");
            stmt.close();
        } catch (Exception e) {
            System.err.println("Sql exception: " + e.toString());
        }
    }

    private void returnProdEnv() throws IOException {
        if (dbFile.isFile()) {
            dbFile.delete();
        }
        Files.copy(dbCopyFile.toPath(), dbFile.toPath());
    }

    private void setAttribute(String name, String value) {
        when(request.getParameter(name)).thenReturn(value);
    }

}
