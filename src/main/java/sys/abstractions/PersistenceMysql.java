/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sys.abstractions;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author martin
 */
public class PersistenceMysql implements Persistence {

    private Connection connection;
    
    public PersistenceMysql() throws SQLException {
        initDB();
    }

    private void initDB() throws SQLException {
        this.connection = getConnection();
    }

    private Connection getConnection() throws SQLException {
      String url = "jdbc:mysql://localhost:3306/library";
      String user = "librarian";
      String password = "password";
      return  DriverManager.getConnection(url, user, password);
    }

    @Override
    public ResultSet run(String sql) {
        ResultSet result = null;
        try {
            result = this.connection.prepareCall(sql).executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }


    @Override
    public int save(String sql) {
        int generatedId = -1;
        try {
            Statement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.executeUpdate(sql, statement.RETURN_GENERATED_KEYS);
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                generatedId = generatedKeys.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceMysql.class.getName()).log(Level.SEVERE, null, ex);
        }
        return generatedId;
    }

    @Override
    public boolean update(String sql) {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceMysql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

    @Override
    public boolean delete(String sql) {
        try {
            Statement statement = connection.createStatement();
            int rowsAffected = statement.executeUpdate(sql);
            statement.close();
            return rowsAffected > 0;
        } catch (SQLException ex) {
            Logger.getLogger(PersistenceMysql.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
