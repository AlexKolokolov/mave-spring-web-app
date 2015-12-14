package ua.djhans.servlets;

import ua.djhans.dao.DAO;
import ua.djhans.dao.Writer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by Administrator on 09.12.2015.
 */
public class Writers extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Collection<Writer> allWriters = DAO.getDAO().getWriters(0);
//        Collection<Writer> allWriters = new ArrayList<>();
//        allWriters.add(new Writer(1, "Jack London", "Джек Лондон"));
//        allWriters.add(new Writer(2, "Jack London2", "Джек Лондон2"));
//        allWriters.add(new Writer(3, "Jack London3", "Джек Лондон3"));
        req.setAttribute("writers", allWriters);
        req.getRequestDispatcher("writers.jsp").forward(req,resp);
    }
}
