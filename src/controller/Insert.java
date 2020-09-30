package controller;

import model.BL.TblUsers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by asus on 4/30/2020.
 */
public class Insert extends HttpServlet{

    RegularExpression regular= new RegularExpression();

    @Override
    public void service(HttpServletRequest request, HttpServletResponse response) throws IOException,ServletException {

        String email= request.getParameter("email");
        String username= request.getParameter("username");
        String password=request.getParameter("password");



          if (regular.validateEmail(email)) {
              TblUsers t = new TblUsers();
              t.insert(username, email, password);

          } else {
              request.setAttribute("Error", "Email is not Valid ");
              request.setAttribute("name", request.getParameter("name"));
              request.getRequestDispatcher("/index.jsp").forward(request, response);
         }


    }


}
