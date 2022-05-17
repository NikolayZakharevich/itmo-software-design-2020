package refactoring.servlet;

import refactoring.Utils;
import refactoring.model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

abstract public class ProductsServlet extends HttpServlet {

    protected final String OK = "OK";

    protected void responseOk(HttpServletResponse response, String text) throws IOException {
        response.setContentType("text/html");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println(text);
    }

    protected String getHtmlResponse(String content) {
        return getHtmlResponse(content, "");
    }

    protected String getHtmlResponse(String content, String heading) {
        StringBuilder builder = new StringBuilder();
        builder.append("<html><body>\n");
        if (!Utils.isNullOrEmpty(heading)) {
            builder.append("<h1>\n")
                    .append(heading + "\n")
                    .append("</h1>\n");
        }

        if (!Utils.isNullOrEmpty(content)) {
            builder.append(content + "</br>\n");
        }
        builder.append("</body></html>\n");
        return builder.toString();
    }

    protected void appendWrapped(StringBuilder builder, Product product) {
        builder.append(product.getName())
                .append("\t")
                .append(product.getPrice())
                .append("</br>");
    }

}
