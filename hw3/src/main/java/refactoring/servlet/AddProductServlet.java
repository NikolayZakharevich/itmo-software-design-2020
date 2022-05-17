package refactoring.servlet;

import refactoring.model.Product;
import refactoring.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class AddProductServlet extends ProductsServlet {

    ProductRepository repository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Product product = new Product(request.getParameter("name"), Long.parseLong(request.getParameter("price")));
        try {
            repository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        responseOk(response, OK);
    }
}
