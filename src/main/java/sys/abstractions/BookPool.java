/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author martin
 */
public class BookPool {

    private Book[] books;
    private Persistence persistence;

    public BookPool(Persistence persistence) {
        this.persistence = persistence;
    }

    public void loadBooks() throws SQLException {
        List<Book> booksList = new ArrayList<>();

        String query = "SELECT * FROM books";
        ResultSet resultSet = persistence.run(query);

        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            String category = resultSet.getString("category");
            int price = resultSet.getInt("price");
            int yearOfEdition = resultSet.getInt("year_of_edition");

            Book book = new Book(isbn, name, author, category, price, yearOfEdition, persistence);
            booksList.add(book);
        }

        resultSet.close();
        books = booksList.toArray(new Book[0]);

    }

    public Book[] getBooks() {
        return books;
    }

    public void loadBooksByQuery(String searchQuery) throws SQLException {
        List<Book> booksList = new ArrayList<>();
        String query = "SELECT * FROM books WHERE name LIKE '%" + searchQuery + "%' OR author LIKE '%" + searchQuery + "%' OR category LIKE '%" + searchQuery + "%' OR isbn LIKE '%" + searchQuery + "%'";
        ResultSet resultSet = persistence.run(query);

        while (resultSet.next()) {
            String isbn = resultSet.getString("isbn");
            String name = resultSet.getString("name");
            String author = resultSet.getString("author");
            String category = resultSet.getString("category");
            int price = resultSet.getInt("price");
            int yearOfEdition = resultSet.getInt("year_of_edition");

            Book book = new Book(isbn, name, author, category, price, yearOfEdition, persistence);
            booksList.add(book);
        }

        resultSet.close();
        books = booksList.toArray(new Book[0]);
    }

    public Book getBookById(String bookIsbn) {
        for (Book book : books) {
            if (book.getIsbn().equals(bookIsbn)) {
                return book;
            }
        }
        return null;
    }

}
