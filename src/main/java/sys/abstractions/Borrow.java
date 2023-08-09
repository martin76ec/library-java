/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

/**
 *
 * @author martin
 */
import java.util.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import utils.PersistenceUtils;

public class Borrow {

    private int id;
    private Book book;
    private Client client;
    private Date borrowDate;
    private Date returnDate;
    private Persistence persistence;
    private boolean returned;

    public Borrow(int id, Book book, Client client, Date borrowDate, Date returnDate, Persistence persistence, boolean returned) {
        this.id = id;
        this.book = book;
        this.client = client;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
        this.persistence = persistence;
        this.returned = returned;
    }

    // Getters and setters
    public int save() {
        String insertQuery = "INSERT INTO borrows (client, isbn, borrow_start_date, borrow_end_date) VALUES ("
                + client.getId() + ", '"
                + book.getIsbn() + "', '"
                + PersistenceUtils.formatDate(borrowDate) + "', "
                + (returnDate != null ? "'" + PersistenceUtils.formatDate(returnDate) + "'" : "NULL")
                + ")";
        int id = persistence.save(insertQuery);
        this.id = id;
        return id;
    }

    public int getId() {
        return id;
    }

    public Book getBook() {
        return book;
    }

    public Client getClient() {
        return client;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public boolean update() {
        this.returnDate = new Date();
        this.returned = true;
        
        String updateQuery = "UPDATE borrows SET client = " + client.getId()
                + ", isbn = '" + book.getIsbn()
                + "', borrow_start_date = '" + PersistenceUtils.formatDate(borrowDate)
                + "', borrow_end_date = " + (returnDate != null ? "'" + PersistenceUtils.formatDate(returnDate) + "'" : "NULL")
                + ", returned = TRUE"
                + " WHERE id = " + id;

        return persistence.update(updateQuery);
    }

    // Delete the borrow record from the database
    public boolean delete() {
        String sql = "DELETE FROM borrows WHERE id = " + id;
        return persistence.delete(sql);
    }

    @Override
    public String toString() {
        return "Borrow{"
                + "id=" + id
                + ", book=" + book
                + ", client=" + client
                + ", borrowDate=" + borrowDate
                + ", returnDate=" + returnDate
                + '}';
    }
}
