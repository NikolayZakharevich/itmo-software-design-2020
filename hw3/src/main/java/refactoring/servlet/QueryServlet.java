package refactoring.servlet;

import refactoring.repository.ProductRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author akirakozov
 */
public class QueryServlet extends ProductsServlet {

    ProductRepository repository = new ProductRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        String heading = "";
        StringBuilder content = new StringBuilder();

        try {
            if ("max".equals(command)) {
                heading = "Product with max price:";
                appendWrapped(content, repository.getMax());
            } else if ("min".equals(command)) {
                heading = "Product with min price:";
                appendWrapped(content, repository.getMin());
            } else if ("sum".equals(command)) {
                content.append("Summary price: \n").append(repository.getSum());
            } else if ("count".equals(command)) {
                content.append("Number of products: \n").append(repository.getCount());
            } else {
                content.append("Unknown command: ").append(command);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        responseOk(response, getHtmlResponse(heading, content.toString()));
    }

}
