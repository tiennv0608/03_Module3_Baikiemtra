package controller;

import DAO.CategoryDAO;
import DAO.ProductDAO;
import model.Category;
import model.Product;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private ProductDAO productDAO = new ProductDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    showFormCreate(request, response);
                    break;
                case "edit":
                    showFormEdit(request, response);
                    break;
                case "delete":
                    showFormDelete(request, response);
                    break;
                default:
                    findAll(request, response);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    create(request, response);
                    break;
                case "edit":
                    update(request, response);
                    break;
                case "delete":
                    delete(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void findAll(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product/list.jsp");
        List<Product> products = productDAO.findAll();
        request.setAttribute("products", products);
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request, response);
    }

    private void showFormCreate(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product/create.jsp");
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request, response);
    }

    private void create(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        double price = Double.parseDouble(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        String color = request.getParameter("color");
        String description = request.getParameter("description");
        String categoryName = request.getParameter("category");
        List<Category> categories = categoryDAO.findAll();
        int categoryId = 0;
        for (Category category : categories) {
            if (categoryName.equals(category.getName())) {
                categoryId = category.getId();
            }
        }
        Product product = new Product(name, price, quantity, color, description, categoryId);
        productDAO.add(product);
        findAll(request, response);
    }

    private void showFormEdit(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product/update.jsp");
        Product product = productDAO.findById(id);
        request.setAttribute("product", product);
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request, response);
    }

    private void update(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        product.setName(request.getParameter("name"));
        product.setPrice(Double.parseDouble(request.getParameter("price")));
        product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
        product.setColor(request.getParameter("color"));
        product.setDescription(request.getParameter("description"));
        String categoryName = request.getParameter("category");
        int categoryId = 0;
        List<Category> list = categoryDAO.findAll();
        for (Category category : list) {
            if (categoryName.equals(category.getName())) {
                categoryId = category.getId();
            }
        }
        product.setCategoryId(categoryId);
        productDAO.edit(id, product);
        findAll(request, response);
    }

    private void showFormDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, ClassNotFoundException {
        int id = Integer.parseInt(request.getParameter("id"));
        Product product = productDAO.findById(id);
        request.setAttribute("product", product);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product/delete.jsp");
        requestDispatcher.forward(request, response);
    }

    private void delete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        productDAO.delete(id);
        findAll(request, response);
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException, ClassNotFoundException, ServletException, IOException {
        String name = request.getParameter("name");
        List<Product> list = productDAO.findByName(name);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Product/list.jsp");
        request.setAttribute("products",list);
        List<Category> categories = categoryDAO.findAll();
        request.setAttribute("categories", categories);
        requestDispatcher.forward(request,response);
    }
}
