package refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import refactoring.repository.ProductRepository;
import refactoring.servlet.AddProductServlet;
import refactoring.servlet.GetProductsServlet;
import refactoring.servlet.QueryServlet;

/**
 * @author akirakozov
 */
public class Main {
    public static void main(String[] args) throws Exception {
        ProductRepository.init();

        Server server = new Server(8081);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new AddProductServlet()), "/add-product");
        context.addServlet(new ServletHolder(new GetProductsServlet()), "/get-products");
        context.addServlet(new ServletHolder(new QueryServlet()), "/query");

        server.start();
        server.join();
    }
}
