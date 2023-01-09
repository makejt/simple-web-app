package org.example.servlets;

import org.example.dao.RegionDAO;
import org.example.model.Region;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet("/table")
public class TableServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        resp.setContentType("text/html;charset=utf-8");

            RegionDAO dao = new RegionDAO();
            Set<Region> regions = dao.getAll();

//            for (Region region : regions) {
//
//                String name = region.getName();
//                req.setAttribute("name", name);
//
//                int population = region.getPopulation();
//                req.setAttribute("population", population);
//
//                int square = region.getSquare();
//                req.setAttribute("square", square);
//            }

           req.setAttribute("regions", regions);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/table.jsp");
            dispatcher.forward(req, resp);
    }
}