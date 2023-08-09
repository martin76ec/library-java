/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

/**
 *
 * @author martin
 */
import java.sql.SQLException;

public class Book {
    private String isbn;
    private String name;
    private String author;
    private String category;
    private int price;
    private int yearOfEdition;
    private Persistence persistence;

    public Book(String isbn, String name, String author, String category, int price, int yearOfEdition, Persistence persistence) {
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.category = category;
        this.price = price;
        this.yearOfEdition = yearOfEdition;
        this.persistence = persistence;
    }

    // Getters and setters

    public String getIsbn() {
        return isbn;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public int getPrice() {
        return price;
    }

    public int getYearOfEdition() {
        return yearOfEdition;
    }
    

    // Save the book to the database using run method
    public int save() {
        String sql = "INSERT INTO books (isbn, name, author, category, price, year_of_edition) VALUES ('"
                + isbn + "', '" + name + "', '" + author + "', '" + category + "', " + price + ", " + yearOfEdition + ")";
        return 1;
    }

    // Update the book in the database
    public boolean update() {
        String sql = "UPDATE books SET name = '" + name + "', author = '" + author +
                "', category = '" + category + "', price = " + price + ", year_of_edition = " + yearOfEdition +
                " WHERE isbn = '" + isbn + "'";
        return persistence.update(sql);
    }

    // Delete the book from the database
    public boolean delete() {
        String sql = "DELETE FROM books WHERE isbn = '" + isbn + "'";
        return persistence.delete(sql);
    }

    @Override
    public String toString() {
        return "Book{" +
                "isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", category='" + category + '\'' +
                ", price=" + price +
                ", yearOfEdition=" + yearOfEdition +
                '}';
    }
}
