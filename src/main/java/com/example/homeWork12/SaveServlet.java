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
        //задаём лимит и обрезаем лишнее индивидуально для каждого поля
        books.setUsername(lengthStringCut(username, 50));// Здесь лимит длины строки 50, после 50 символа идет обрезание строки
        books.setEmail(lengthStringCut(email, 15));
        books.setItem(lengthStringCut(item, 50));
        books.setQuantity(quantity);
        books.setTotalprice(totalprice);
        books.setTelephone(lengthStringCut(telephone, 10));

        int status = BooksRepository.save(books);

        if (status > 0) {
            out.print("Record saved successfully!");
        } else {
            out.println("Sorry! unable to save record");
        }
        out.close();
    }

    public String lengthStringCut(String string, int limit) {
        // лимит строки передается в метод после строки
        String result;
        int length = string.length();
        if (length > limit) {
            result = string.substring(0, limit);
            return result;
        } else return string;


    }

    public String lengthStringValidate(String string) {
        int limit = 3;// we made limit for length hardcode
        int length = string.length();
        if (length <= limit) {
            return string;
        } else return "Max limit reached";
    }

}
