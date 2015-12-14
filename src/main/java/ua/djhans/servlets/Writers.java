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
        Collection<Writer> writers = DAO.getDAO().getWriters();

        req.setAttribute("writers", writers);
        req.getRequestDispatcher("writers.jsp").forward(req,resp);
    }
}
