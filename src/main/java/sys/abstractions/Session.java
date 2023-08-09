/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author martin
 */
public class Session {

    private Persistence persistence;
    private int type;
    private String email;
    private String password;
    private Librarian librarian;
    private Client client;

    public Session(Persistence persistence, int type, String email, String password) {
        this.persistence = persistence;
        this.type = type;
        this.email = email;
        this.password = password;
    }

    public boolean librarianLogin() throws SQLException {
        String table = "librarians t";
        ResultSet result = this.persistence.run("SELECT t.id, t.first_name, t.last_name FROM " + table + " INNER JOIN login_credentials lc ON lc.id = t.login WHERE lc.email = '" + this.email + "' AND lc.password = '" + this.password + "'");
        
        if (result.next()) {
            int id = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            this.librarian = new Librarian(id, firstName, lastName, this.persistence);
            result.close();
            return true;
        }
        
        result.close();
        return false;
    }

    public boolean clientLogin() throws SQLException {
        String table = "clients t";
        ResultSet result = this.persistence.run("SELECT t.id, t.first_name, t.last_name, t.address, t.phone FROM " + table + " INNER JOIN login_credentials lc ON lc.id = t.login WHERE lc.email = '" + this.email + "' AND lc.password = '" + this.password + "'");
        
        if (result.next()) {
            int id = result.getInt("id");
            String firstName = result.getString("first_name");
            String lastName = result.getString("last_name");
            String address = result.getString("address");
            String phone = result.getString("phone");
            this.client = new Client(id, firstName, lastName, address, phone, persistence);
            result.close();
            return true;
        }
        
        result.close();
        return false;
    }

    public boolean login() throws SQLException {
        if (type == 0) {
            return clientLogin();
        } else if (type == 1) {
            return librarianLogin();
        } else {
            return false;
        }
    }

    public Librarian getLibrarian() {
        return this.librarian;
    }

    public Client getClient() {
        return this.client;
    }
    
    public int save() {
        String table = (type == 0) ? "clients" : "librarians";
        String sql = "INSERT INTO login_credentials (email, password) VALUES ('" + email + "', '" + password + "')";
        return persistence.save(sql);
    }
}
