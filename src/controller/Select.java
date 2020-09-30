package controller;


import model.BL.TblUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Teacher on 3/11/2016.
 */
public class Select extends HttpServlet {
    RegularExpression regular = new RegularExpression();

    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

         fetch(email,password,request,response);
        //fetchMap(email, password, request, response);

    }

    public void fetch(String email, String password,
                      HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {

        ResultSet r;

       try {
            if (regular.validateEmail(email)) {
                TblUsers t = new TblUsers();
                r = t.select(email, password);
                if (r.next()) {
                    request.setAttribute("ResultSet", r);
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                } else {
                    request.setAttribute("Result", "NO Data");
                    request.getRequestDispatcher("/main.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("Error", "Email is not Valid ");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
           System.out.println(e.getMessage());
        }

    }

    public void fetchMap(String email, String password,
                         HttpServletRequest request,
                         HttpServletResponse response)
            throws IOException, ServletException {
        Map<String, String> map = new HashMap<>();

        if (regular.validateEmail(email)) {
            TblUsers t = new TblUsers();
            map = t.fetchMap(email, password);
            if (!map.isEmpty()) {
                request.setAttribute("ResultSetMap", map);
                request.getRequestDispatcher("/mainMap.jsp").forward(request, response);
            } else {
                request.setAttribute("Result", "NO Data");
                request.getRequestDispatcher("/mainMap.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("Error", "Email is not Valid ");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    }

}
