package app.cliente.ejb;

import jee.master.ejb.model.Producto;
import jee.master.ejb.service.A0_EjbService;
import jee.master.ejb.service.A1_ServiceEjbRemote;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Properties;

//543 - TE TRAES EL CODIGO DESDE EL PUNTO 14 EN https://docs.wildfly.org/26.1/Developer_Guide.html#JNDI_Remote_Reference
//1 - ESTO ES PARA COMUNICARNOS DE FORMA REMOTA.
//2 - GENERA UNA INSTANCIA DEL OTRO PROYECTO.
//3 - EL STRING LO TOMAS DESDE LA TERMINAL DEL OTRO PROYECTO AL CUAL SE CONECTA ESTE PROYECTO.
//3.1 - SI EL SERVICE EJB DEL OTRO PROYECTO ES STATEFUL, USA ESTA.
//4 - AHORA SE USA.

public class Main {
    public static void main(String[] args) {

        A0_EjbService service; //2

        //1...
        /*
        final Properties env = new Properties();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        env.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");
        env.put("jboss.naming.client.ejb.context", true);
         */

        try {
            /*InitialContext remoteContext = new InitialContext(env);*/   InitialContext remoteContext = new InitialContext();

            //3
            //service = (A0_EjbService)remoteContext.lookup("ejb:/jee-master-ejb-remote/A1_ServiceEjbRemote!jee.master.ejb.service.A0_EjbService");
            //3.1
            service = (A0_EjbService)remoteContext.lookup("ejb:/jee-master-ejb-remote/A1_ServiceEjbRemote!jee.master.ejb.service.A0_EjbService?stateful");


            //4
            String saludo1 = service.saludar("Axel");
            String saludo2 = service.saludar("John");
            System.out.println(saludo1);
            System.out.println(saludo2);

            Producto p = service.crear(new Producto("Sandia"));
            System.out.println("Nuevo producto :" + p);

            service.listar().forEach(System.out::println);

        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
        //...1
    }

}