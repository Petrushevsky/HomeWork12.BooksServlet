package com.example.homeWork12;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
Поправить код таким образом, что бы ответ на запрос, приходил корректным реалиям.

Например.

Кейс 1 - если не корректно задан параметр для передачи. Ожидаем id, а приходит Is или что угодно.

Кейс 2 - вышли за пределы строк.
 */

@WebServlet("/viewByIDServlet")
public class ViewByIDServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String sid;
        int id;
        try {
            sid = request.getParameter("id");
            if (sid == null) {
                out.println("No id - error, try again!!!");
            } else {
                id = Integer.parseInt(sid);
                Books books = BooksRepository.getEmployeeById(id);
                out.println(books);
            }
        }
        finally {
            out.close();
        }
    }
}



