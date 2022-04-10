package com.example.homeWork12;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BooksRepository {

    public static void main(String[] args) {
        getConnection();

        Books books = new Books();

        books.setUsername("Eugene");
        books.setEmail(" ");
        books.setItem(" ");
        books.setQuantity(" ");
        books.setTotalprice(" ");
        books.setTelephone(" ");
        save(books);
    }

    public static Connection getConnection() {

        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/books";
        String user = "postgres";
        String password = "postgres";

        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {
                System.out.println("Connected to the PostgreSQL server successfully.");
            } else {
                System.out.println("Failed to make connection!");
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException);
        }
        return connection;
    }

    public static int save(Books books) {
        int status = 0;
        try {
            Connection connection = BooksRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("insert into sales(username,email,item,quantity,totalprice,telephone) values (?,?,?,?,?,?)");
            ps.setString(1, books.getUsername());
            ps.setString(2, books.getEmail());
            ps.setString(3, books.getItem());
            ps.setString(4, books.getQuantity());
            ps.setString(5, books.getTotalprice());
            ps.setString(6, books.getTelephone());

            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return status;
    }

    public static int update(Books books) {

        int status = 0;

        try {
            Connection connection = BooksRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("update sales set username=?,email=?,item=?,quantity=?,totalprice=?,telephone=? where id=?");
            ps.setString(1, books.getUsername());
            ps.setString(2, books.getEmail());
            ps.setString(3, books.getItem());
            ps.setInt(4, books.getId());
            ps.setString(5, books.getQuantity());
            ps.setString(6, books.getTotalprice());
            ps.setString(7, books.getTelephone());


            status = ps.executeUpdate();
            connection.close();

        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return status;
    }

    public static int delete(int id) {

        int status = 0;

        try {
            Connection connection = BooksRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("delete from sales where id=?");
            ps.setInt(1, id);
            status = ps.executeUpdate();

            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return status;
    }

    public static Books getEmployeeById(int id) {

        Books books = new Books();

        try {
            Connection connection = BooksRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from sales where id=?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                books.setId(rs.getInt(1));
                books.setUsername(rs.getString(2));
                books.setEmail(rs.getString(3));
                books.setItem(rs.getString(4));
                books.setQuantity(rs.getString(5));
                books.setTotalprice(rs.getString(6));
                books.setTelephone(rs.getString(7));



            }
            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return books;
    }

    public static List<Books> getAllBooks() {

        List<Books> listBooks = new ArrayList<>();

        try {
            Connection connection = BooksRepository.getConnection();
            PreparedStatement ps = connection.prepareStatement("select * from sales");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                Books books = new Books();

                books.setId(rs.getInt(1));
                books.setUsername(rs.getString(2));
                books.setEmail(rs.getString(3));
                books.setItem(rs.getString(4));
                books.setQuantity(rs.getString(5));
                books.setTotalprice(rs.getString(6));
                books.setTelephone(rs.getString(7));



                listBooks.add(books);
            }
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listBooks;
    }
}
