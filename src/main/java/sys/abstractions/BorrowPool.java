/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

/**
 *
 * @author martin
 */
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class BorrowPool {

    private Borrow[] borrows;
    private Persistence persistence;

    public BorrowPool(Persistence persistence) {
        this.persistence = persistence;
    }

    public void loadBorrows(BookPool bookPool, ClientPool clientPool) throws SQLException {
        ArrayList<Borrow> borrowList = new ArrayList<Borrow>();

        String query = "SELECT * FROM borrows";
        ResultSet resultSet = persistence.run(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String bookIsbn = resultSet.getString("isbn");
            int clientId = resultSet.getInt("client");
            Date borrowDate = new Date(resultSet.getDate("borrow_start_date").getTime());
            Date returnDate = resultSet.getDate("borrow_end_date") == null ? null : new Date(resultSet.getDate("borrow_end_date").getTime());
            boolean returned = resultSet.getBoolean("returned");

            Book book = bookPool.getBookById(bookIsbn);
            Client client = clientPool.getClientById(clientId);

            if (book != null && client != null) {
                Borrow borrow = new Borrow(id, book, client, borrowDate, returnDate, persistence, returned);
                borrowList.add(borrow);
            }
        }

        resultSet.close();
        borrows = borrowList.toArray(new Borrow[0]);
    }

    public Borrow[] getBorrows() {
        return borrows;
    }

    public void loadBorrowsByClientId(int clientId, BookPool bookPool, ClientPool clientPool) throws SQLException {
        List<Borrow> borrowsForClient = new ArrayList<>();

        String query = "SELECT * FROM borrows WHERE client = " + clientId;
        ResultSet resultSet = persistence.run(query);

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String bookIsbn = resultSet.getString("isbn");
            Date borrowDate = new Date(resultSet.getDate("borrow_start_date").getTime());
            Date returnDate = resultSet.getDate("borrow_end_date") == null ? null : new Date(resultSet.getDate("borrow_end_date").getTime());
            boolean returned = resultSet.getBoolean("returned");

            Book book = bookPool.getBookById(bookIsbn);
            Client client = clientPool.getClientById(clientId);

            if (book != null && client != null) {
                Borrow borrow = new Borrow(id, book, client, borrowDate, returnDate, persistence, returned);
                borrowsForClient.add(borrow);
            }
        }

        resultSet.close();
        borrows = borrowsForClient.toArray(new Borrow[0]);
    }

    private Book getBookByIsbn(String isbn, BookPool bookPool) {
        Book[] books = bookPool.getBooks();

        for (Book book : books) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }

        return null;
    }

    private Client getClientById(int clientId, ClientPool clientPool) {
        Client[] clients = clientPool.getClients();

        for (Client client : clients) {
            if (client.getId() == clientId) {
                return client;
            }
        }

        return null;
    }
    
    
    public Borrow getBorrowById(int borrowId) {
        for (Borrow borrow : borrows) {
            if (borrow.getId() == borrowId) {
                return borrow;
            }
        }
        return null;
    }

}
