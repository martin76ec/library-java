package sys.abstractions;


import sys.abstractions.Client;
import sys.abstractions.Persistence;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author martin
 */
public class ClientPool {
    private Client[] clients;
    private Persistence persistence;

    public ClientPool(Persistence persistence) {
        this.persistence = persistence;
    }

    public void loadClients() throws SQLException {
        List<Client> clientList = new ArrayList<>();
        
        String query = "SELECT * FROM clients";
        ResultSet resultSet = persistence.run(query);
        
        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String firstName = resultSet.getString("first_name");
            String lastName = resultSet.getString("last_name");
            String address = resultSet.getString("address");
            String phone = resultSet.getString("phone");
            
            Client client = new Client(id, firstName, lastName, address, phone, persistence);
            clientList.add(client);
        }
        
        resultSet.close();
        
        clients = clientList.toArray(new Client[0]);
    }

    public Client[] getClients() {
        return clients;
    }
}