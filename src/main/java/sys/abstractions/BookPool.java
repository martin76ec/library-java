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
    private List<Book> books;
    private Persistence persistence;

    public BookPool(Persistence persistence) {
        this.persistence = persistence;
        books = new ArrayList<>();
    }

    public void loadBooks() throws SQLException {
        books.clear();
        
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
            books.add(book);
        }
        
        resultSet.close();
    }

    public List<Book> getBooks() {
        return books;
    }

}