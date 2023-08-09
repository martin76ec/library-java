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

public class Client {

    private int id;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private Persistence persistence;

    public Client(int id, String firstName, String lastName, String address, String phone, Persistence persistence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.persistence = persistence;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    // Save the client to the database
    public int save(int login) {
        String sql = "INSERT INTO clients (first_name, last_name, address, phone, login) VALUES ('"
                + firstName + "', '" + lastName + "', '" + address + "', '" + phone + "', " + login + ")";
        int id = persistence.save(sql);
        this.id = id;
        return id;
    }

    // Update the client in the database
    public boolean update() {
        String sql = "UPDATE clients SET first_name = '" + firstName + "', last_name = '" + lastName
                + "', address = '" + address + "', phone = '" + phone + "' WHERE id = " + id;
        return persistence.update(sql);
    }

    // Delete the client from the database
    public boolean delete() {
        String sql = "DELETE FROM clients WHERE id = " + id;
        return persistence.delete(sql);
    }

    @Override
    public String toString() {
        return "Client{"
                + "id=" + id
                + ", firstName='" + firstName + '\''
                + ", lastName='" + lastName + '\''
                + ", address='" + address + '\''
                + ", phone='" + phone + '\''
                + '}';
    }
}
