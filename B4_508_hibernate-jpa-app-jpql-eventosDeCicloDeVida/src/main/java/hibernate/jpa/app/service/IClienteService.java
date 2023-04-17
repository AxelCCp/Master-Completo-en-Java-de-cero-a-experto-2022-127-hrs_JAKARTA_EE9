package hibernate.jpa.app.service;

import hibernate.jpa.app.entity.Cliente;

import java.util.List;
import java.util.Optional;

public interface IClienteService {
    List<Cliente> listar();
    Optional<Cliente>porId(Long id);
    void guardar(Cliente cliente);
    void eliminar(Long id);
}
