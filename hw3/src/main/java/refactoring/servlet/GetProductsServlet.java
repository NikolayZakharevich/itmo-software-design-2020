package refactoring.servlet;

import refactoring.model.Product;
import refactoring.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends ProductsServlet {

    ProductRepository repository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        List<Product> products;
        try {
            products = repository.getAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        StringBuilder content = new StringBuilder();
        for (Product product : products) {
            appendWrapped(content, product);
        }

        responseOk(response, getHtmlResponse(content.toString()));
    }
}
