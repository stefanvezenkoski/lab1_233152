package mk.ukim.finki.wp.lab.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import mk.ukim.finki.wp.lab.model.Chef;
import mk.ukim.finki.wp.lab.model.Dish;
import mk.ukim.finki.wp.lab.service.ChefService;
import mk.ukim.finki.wp.lab.service.DishService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "DishServlet", urlPatterns = "/dish")
@Component
public class DishServlet extends HttpServlet {

    private final ChefService chefService;
    private final DishService  dishService;

    public DishServlet(ChefService chefService, DishService dishService) {
        this.chefService = chefService;
        this.dishService = dishService;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Long chefId = Long.parseLong(req.getParameter("chefId"));
        Chef selectedChef = chefService.findById(chefId);

        List<Dish> dishes = dishService.listDishes();

        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<meta charset='utf-8'>");
        out.println("<title>Add Dish to Chef</title>");
        out.println("<style type='text/css'>");
        out.println("body {width:800px;margin:auto;font-family:Arial,sans-serif;}");
        out.println("table {width:100%;margin-top:20px;border-collapse:collapse;}");
        out.println("table, td, th {border:1px solid black;padding:10px;}");
        out.println("th {background-color:#4CAF50;color:white;}");
        out.println("section {float:left;margin:0 1.5%;width:63%;}");
        out.println("aside {float:right;margin:0 1.5%;width:30%;}");
        out.println("</style>");
        out.println("</head>");
        out.println("<body>");

        out.println("<header><h1>Select the Dish to add to the Chef</h1></header>");

        out.println("<section>");
        out.println("<h2>Select dish:</h2>");
        out.println("<form action='/addDish' method='POST'>");

        for (Dish dish : dishes) {
            out.printf(
                    "<input type='radio' name='dishId' value='%s'> %s (%s cuisine, %d min)<br/>",
                    dish.getDishId(),
                    dish.getName(),
                    dish.getCuisine(),
                    dish.getPreparationTime()
            );
        }

        out.printf("<input type='hidden' name='chefId' value='%d'/>", chefId);
        out.println("<br/><input type='submit' value='Add dish'>");
        out.println("</form>");
        out.println("</section>");

        out.println("<aside>");
        out.println("<table>");
        out.println("<tr><th colspan='2'>Chef Details</th></tr>");
        out.printf("<tr><td><b>Chef ID</b></td><td>%d</td></tr>", selectedChef.getId());
        out.printf("<tr><td><b>Chef Name</b></td><td>%s %s</td></tr>",
                selectedChef.getFirstName(), selectedChef.getLastName());
        out.printf("<tr><td><b>Bio</b></td><td>%s</td></tr>", selectedChef.getBio());
        out.println("</table>");
        out.println("</aside>");

        out.println("</body>");
        out.println("</html>");
    }

    }
