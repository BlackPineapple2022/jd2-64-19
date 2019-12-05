package by.it.academy.travelcompany.servlet;

import by.it.academy.travelcompany.model.Product;
import by.it.academy.travelcompany.service.ProductService;
import by.it.academy.travelcompany.service.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/pro")

public class ProductsServlet extends HttpServlet {

    private ProductService productService = new ProductServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> allProducts = productService.getAllProducts();

        req.setAttribute("productList", allProducts);

        req.getRequestDispatcher("/WEB-INF/products.jsp").forward(req,resp);

    }


}
