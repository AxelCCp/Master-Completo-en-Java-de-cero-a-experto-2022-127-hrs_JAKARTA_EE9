package jee.master.ejb.service;


import jakarta.ejb.Stateful;
import jakarta.enterprise.context.RequestScoped;

//538
    //1 - ESTO LO CONVIERTE EN UN EJB Y AL PUBLICARLO EN WILDFLY, CA A TENER CARACTERISTICAS TRANSACCIONALES, MANEJO DE CONCURRENCIA, LLAMAR A MÉTODOS REMOTOS.

//539 - 2,
    //3 - CON ESTO SE LOGRA Q EL STATEFUL Q SE INJECTA EN EL CLIENTE (EL SERVLET), SEA INDEPENDIENTE DE LOS DEMÁS Y SE MANTENGA EL HILO. CON ESTO SE AGREGA EL SERVICEEJB AL CONTEXTO.
            //PARA QUE EL REQUESTSCOPE FUNCIONE CON EL STATEFUL, HAY Q INYECTAR EN EL SERVLET CON @INJECT Y NO CON @EJB.

@RequestScoped      //3
//@Stateless        //1
@Stateful           //2
public class A0_ServiceEjb {

        public String saludar(String nombre){

            System.out.println("imprimiendo dentro del ejb con instancia: " + this);

            contador++;

            System.out.println("valor del contador en método saludar " + contador);

            return "Hola que tal " + nombre;
        }



        private int contador;
}
