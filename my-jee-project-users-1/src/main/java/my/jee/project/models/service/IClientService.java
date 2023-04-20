package my.jee.project.models.service;

import my.jee.project.models.entity.Client;

import java.util.List;
import java.util.Optional;

public interface IClientService {

    public List<Client> listaClientes();
    public Optional<Client> getClientById(Long id);
    public void saveClient(Client client);
    public void deleteClientById(Long id);

}
