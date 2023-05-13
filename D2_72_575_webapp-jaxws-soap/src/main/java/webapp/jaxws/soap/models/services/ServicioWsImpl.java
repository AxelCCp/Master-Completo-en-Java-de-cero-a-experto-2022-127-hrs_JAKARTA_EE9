package webapp.jaxws.soap.models.services;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import webapp.jaxws.soap.models.Curso;

import java.util.Arrays;
import java.util.List;


//575
//1 - QUIERE DECIR Q TODOS LOS MÉTODOS DE ESTA INTERFAZ, SE VAN A PUBLICAR EN EL SERVICIO WEB.
//2 - ES PARA PUBLICAR EXCLUSIVAMENTE LOS MÉTODOS DE LA INTERFAZ Y NO LOS DE LA CLASE.
//3 - @WebMethod : PARA Q LOS MÉTODOS SE PUEDAN PUBLICAR EN EL SERVICIO WEB.


      //1           //2
@WebService(endpointInterface = "webapp.jaxws.soap.models.services.ServicioWs")
public class ServicioWsImpl implements ServicioWs{

    private int contador;
    
    @Override
    @WebMethod //3
    public String saludar(String persona) {
        System.out.println("imprimiendo dentro del servicio web con instancia : " + this);
        contador++;
        System.out.println("valor del contador en el método saludar es: " + contador);
        return "Hola que tal " + persona;
    }

    @Override
    @WebMethod
    public List<Curso> listar() {
        return Arrays.asList(new Curso("Java"), new Curso("Spring"), new Curso("Jakarta ee"));
    }

    @Override
    @WebMethod
    public Curso crear(Curso curso) {
        System.out.println("Curso guardado con exito... " + curso.getNombre());
        Curso nuevoCurso = new Curso();
        nuevoCurso.setNombre(curso.getNombre());
        return nuevoCurso;
    }
}
