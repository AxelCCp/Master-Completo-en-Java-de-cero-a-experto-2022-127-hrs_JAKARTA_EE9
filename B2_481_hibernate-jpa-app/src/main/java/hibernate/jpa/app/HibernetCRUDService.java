package hibernate.jpa.app;

import hibernate.jpa.app.entity.Cliente;
import hibernate.jpa.app.service.ClienteServiceImpl;
import hibernate.jpa.app.service.IClienteService;
import hibernate.jpa.app.util.JpaUtil;
import jakarta.persistence.EntityManager;

import java.util.List;
import java.util.Optional;

public class HibernetCRUDService {

    public static void main(String[]args){
        EntityManager em = JpaUtil.getEntityManager();
        IClienteService service = new ClienteServiceImpl(em);

        System.out.println("============== LISTAR ==============");
        List<Cliente> clientes = service.listar();
        clientes.forEach(c -> System.out.println(c));

        System.out.println("============== POR ID ==============");
        Optional<Cliente> clienteOptional = service.porId(1L);
        /*if(clienteOptional.isPresent()){
            System.out.println(clienteOptional.get());
        }*/
        clienteOptional.ifPresent(System.out::println);

        System.out.println("============== INSERTAR CLIENTE ==============");
        Cliente cliente = new Cliente();
        cliente.setNombre("Luci");
        cliente.setApellido("Mena");
        cliente.setFormaPago("paypal");
        service.guardar(cliente);
        System.out.println("cliente guardado con éxito!");
        service.listar().forEach(c -> System.out.println(c));

        System.out.println("============== EDITAR CLIENTE ==============");
        Long id = cliente.getId();
        clienteOptional = service.porId(id);
        clienteOptional.ifPresent(c -> {
            c.setFormaPago("mercado pago");
            service.guardar(c);
            System.out.println("Cliente editado con éxito");
            service.listar().forEach(System.out::println);
        });

        System.out.println("============== ELIMINAR CLIENTE ==============");
        id = cliente.getId();
        clienteOptional = service.porId(id);
        clienteOptional.ifPresent(c -> {
            service.eliminar(c.getId());
            System.out.println("Cliente eliminado con éxito");
        });
        em.close();
    }

}
