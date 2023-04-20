package my.jee.project.models.service;

import my.jee.project.models.dao.ClientRepository;
import my.jee.project.models.dao.IGenericRepository;
import my.jee.project.models.entity.Client;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class ClientService implements IClientService{

    public ClientService(Connection connection){
        genericRepository = new ClientRepository(connection);
    }

    @Override
    public List<Client> listaClientes() {
        try {
            return genericRepository.list();
        } catch (SQLException e) {
            throw new MyExceptionJdbc(e.getMessage(), e.getCause());
        }
    }

    @Override
    public Optional<Client> getClientById(Long id) {
        try {
            return Optional.ofNullable((Client) genericRepository.getById(id));
        } catch (SQLException e) {
            throw new MyExceptionJdbc(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void saveClient(Client client) {
        try {
            genericRepository.save(client);
        } catch (SQLException e) {
            throw new MyExceptionJdbc(e.getMessage(), e.getCause());
        }
    }

    @Override
    public void deleteClientById(Long id) {
        try {
            genericRepository.deleteById(id);
        } catch (SQLException e) {
            throw new MyExceptionJdbc(e.getMessage(), e.getCause());
        }
    }


    private IGenericRepository genericRepository;
}
