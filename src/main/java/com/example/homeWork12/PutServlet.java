package com.example.homeWork12;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/putServlet")
public class PutServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String sid = request.getParameter("id");
        int id = Integer.parseInt(sid);

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String quantity = request.getParameter("quantity");
        String totalprice = request.getParameter("totalprice");
        String telephone = request.getParameter("telephone");

        Books books = new Books();
        books.setId(id);
        books.setUsername(username);
        books.setEmail(email);
        books.setItem(request.getParameter("Item"));
        books.setQuantity(quantity);
        books.setTotalprice(totalprice);
        books.setTelephone(telephone);

        int status = BooksRepository.update(books);

        if (status > 0) {
            response.sendRedirect("viewServlet");
        } else {
            out.println("Sorry! unable to update record");
        }
        out.close();
    }
}
