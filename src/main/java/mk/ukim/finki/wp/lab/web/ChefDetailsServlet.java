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

    @WebServlet(name = "ChefDetailsServlet", urlPatterns = "/addDish")
    @Component
    public class ChefDetailsServlet extends HttpServlet {

        private final ChefService chefService;
        private final DishService dishService;

        public ChefDetailsServlet(ChefService chefService, DishService dishService) {
            this.chefService = chefService;
            this.dishService = dishService;
        }

        @Override
        protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {

            Long chefId = Long.parseLong(req.getParameter("chefId"));
            String dishId = req.getParameter("dishId");

            Chef chef = chefService.findById(chefId);
            Dish dish = dishService.findByDishId(dishId);

            if (chef != null && dish != null) {
                chefService.addDishToChef(chefId, dishId);
            }

            resp.setContentType("text/html;charset=UTF-8");
            PrintWriter out = resp.getWriter();

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<meta charset='utf-8'>");
            out.println("<title>Chef Details</title>");
            out.println("<style type='text/css'>");
            out.println("body {width:800px;margin:auto;font-family:Arial,sans-serif;}");
            out.println("h1 {color:#333;}");
            out.println("ul {list-style-type:none;padding:0;}");
            out.println("li {padding:5px;margin:5px 0;background-color:#f5f5f5;border-radius:3px;}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");

            out.printf("<header><h1>Chef: %s %s</h1></header>",
                    chef.getFirstName(), chef.getLastName());
            out.printf("<section><h2>Bio: %s</h2>", chef.getBio());
            out.println("<h2>Dishes prepared by this chef:</h2>");
            out.println("<ul>");

            List<Dish> chefDishes = chef.getDishes();
            for (Dish d : chefDishes) {
                out.printf("<li>%s (%s, %d min)</li>",
                        d.getName(), d.getCuisine(), d.getPreparationTime());
            }

            out.println("</ul>");
            out.println("</section>");
            out.println("</body>");
            out.println("</html>");
        }


    }
