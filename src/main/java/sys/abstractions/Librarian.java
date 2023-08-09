/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

/**
 *
 * @author martin
 */
public class Librarian {
    private int id;
    private String firstName;
    private String lastName;
    private Persistence persistence;

    public Librarian(int id, String firstName, String lastName, Persistence persistence) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.persistence = persistence;
    }

    // Getters and setters

    // Save the librarian to the database
    public int save() {
        String sql = "INSERT INTO librarians (first_name, last_name) VALUES ('"
                + firstName + "', '" + lastName + "')";   
        int id = persistence.save(sql);
        this.id = id;
        return id;
    }

    // Update the librarian in the database
    public boolean update() {
        String sql = "UPDATE librarians SET first_name = '" + firstName + "', last_name = '" + lastName +
                "' WHERE id = " + id;
        return persistence.update(sql);
    }

    // Delete the librarian from the database
    public boolean delete() {
        String sql = "DELETE FROM librarians WHERE id = " + id;
        return persistence.delete(sql);
    }

    @Override
    public String toString() {
        return "Librarian{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}