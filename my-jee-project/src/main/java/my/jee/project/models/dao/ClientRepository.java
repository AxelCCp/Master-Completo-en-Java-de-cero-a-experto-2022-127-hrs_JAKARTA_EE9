package my.jee.project.models.dao;

import my.jee.project.models.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository implements IGenericRepository <Client> {

    public ClientRepository(Connection connection){
        this.connection = connection;
    }

    @Override
    public List<Client> list() throws SQLException {
        List<Client>clients = new ArrayList<>();
        try(Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery("Select * from clients")){
            while(rs.next()){
                Client client = this.getClient(rs);
                clients.add(client);
            }
        }
        return clients;
    }

    @Override
    public Client getById(Long id) throws SQLException {
        Client client = new Client();
        try(PreparedStatement pst = connection.prepareStatement("select * from clients where id=?")){
            pst.setLong(1, id);
            try(ResultSet rs = pst.executeQuery()){
                if(rs.next()){
                    client = this.getClient(rs);
                }
            }
        }
        return client;
    }

    @Override
    public void save(Client client) throws SQLException {
        String sql;
        if(client.getId() != null && client.getId() > 0){
            sql = "update clients set name=?, lastname1=?, lastname2=?, email=?, registry=? where id=?";
        } else {
            sql = "insert into clients (name, lastname1, lastname2, email, registry) values(?,?,?,?,?)";
        }
        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setString(1, client.getName());
            pst.setString(2, client.getLastName1());
            pst.setString(3, client.getLastName2());
            pst.setString(4, client.getEmail());
            pst.setDate(5, Date.valueOf(client.getRegistry()));
            if(client.getId() != null && client.getId() > 0){
                pst.setLong(6,client.getId());
            }
            pst.executeUpdate();
        }
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "delete from clients where id=?";
        try(PreparedStatement pst = connection.prepareStatement(sql)){
            pst.setLong(1, id);
            pst.executeUpdate();
        }
    }

    private static Client getClient(ResultSet rs) throws SQLException {
        Client client = new Client();
        client.setId(rs.getLong("id"));
        client.setName(rs.getString("name"));
        client.setLastName1(rs.getString("lastname1"));
        client.setLastName2(rs.getString("lastname2"));
        client.setEmail(rs.getString("email"));
        client.setRegistry(rs.getDate("registry").toLocalDate());
        return client;
    }

    private Connection connection;
}
