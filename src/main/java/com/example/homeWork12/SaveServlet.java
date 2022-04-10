package com.example.homeWork12;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/saveServlet")
public class SaveServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");

        PrintWriter out = response.getWriter();

        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String item = request.getParameter("item");
        String quantity = request.getParameter("quantity");
        String totalprice = request.getParameter("totalprice");
        String telephone = request.getParameter("telephone");

        Books books = new Books();

        books.setUsername(username);
        books.setEmail(email);
        books.setItem(item);
        books.setQuantity(quantity);
        books.setTotalprice(totalprice);
        books.setTelephone(telephone);




        //out.println(employee.toString());
        //out.println(EmployeeRepository.getConnection());

        int status = BooksRepository.save(books);
        //out.println(status);

        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }
}
