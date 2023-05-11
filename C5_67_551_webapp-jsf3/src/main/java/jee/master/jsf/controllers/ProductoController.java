package jee.master.jsf.controllers;


//1 - LOS CONTROLADORES CUANDO ESTÁN CON JAVA SERVER FACES, TIENEN Q ESTAR ANOTADOS CON UN CONTEXTO. CONTEXTO DE REQUEST, TAMBN PUEDE LLEVAR ALGUN NOMBRE, ETC. ..
//  ..DEBE SER UN COMPONENTE MANEJADO POR EL CONTENEDOR CDI.

//2 - LA ANOTACIÓN  MODEL ES DE JAKARTA Y JUNTA NAMED Y REQUESTSCOPED.

//3 - LOS DATOS SE PASAN A LA VISTA CON PRODUCES. SE CREA UN COMPONENTE Y SE REGISTRA EN EL CONTENEDOR CDI. LUEGO LA VISTA PUEDE ACCEDER A ESTOS COMPONENTES Q ESTÁN REGISTRADOS.
    //..LA VISTA ACCEDE A LO QUE DEVUELVE EL MÉTODO USANDO EL NOMBRE DEL MÉTODO.
//4 - SI QUIERES PONERLE UN NOMBRE Y USAR ESTE NOMBRE PARA OBTENER EL RETURN DEL MÉTODO, USA NAMED Y REQUEST SCOPED EN VEZ DE MODEL.

//555
//5, 6

//556
//7,
//8 - SE GUARDA EN EL CONTEXTO CDI DEL REQUEST, PARA QUE ESTÉ DISPONIBLE AUTOMÁTICAMENTE EN LA VISTA , EN EL FORMULARIO. ASÍ SE PUEDEN MAPEAR LOS CAMPOS CON LOS ATRIBUTOS DE ESTE OBJ.
//9 - LO PONEMOS EN EL CONTEXTO DEL REQUEST.

//558
//10 - A ESTA NUEVA VARIABLE SE LE ASIGNAN EL GETTER Y SETTER, PARA PODER ASIGNAR EL ID AUTOMÁTICAMENTE.
//11 - SI EL PRODUCTO YA TIENE ID.

//560
//12 - SE CREA EL COMPONENETE CDI CON LAS CATEGORIAS, DEL TIPO DEL REQUEST. PARA POBLAR LA LISTA DESPLEGABLE.

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Model;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jee.master.jsf.entities.Categoria;
import jee.master.jsf.entities.Producto;
import jee.master.jsf.services.ProductoService;

import java.util.Arrays;
import java.util.List;

//@Named          //1
//@RequestScoped  //1
@Model            //2
public class ProductoController {

    private Long id; //10

    private Producto producto;//7

    @Inject //5
    private ProductoService service;

    //3
    @Produces
    @Model
    public String titulo(){
        return "Primer java server faces 3.0";
    }

    @Produces
    @RequestScoped
    @Named("listado")     //4
    public List<Producto>findAll(){
        return service.listar(); //6
    }

    @Produces//8
    @Model //9
    public Producto producto(){                                     //CON EL PRODUCES, DESDE LA VISTA, ESTE MÉTODO ES INVOCADO COMO "producto" <h:inputText id="nombre" value="#{producto.nombre}"/>  EN FORM.XHTML, PRODUCTO ES EL MÉTODO DONDE SE PUEDEN IR CARGANDO LOS ATRIBUTOS PARA EL OBJ PRODUCTO Q DEVUELVE.
        this.producto = new Producto();
        //11
        if(id != null && id > 0) {
             service.porId(id).ifPresent(p -> {
                this.producto = p;
            });
        }
        return this.producto;
    }

    public String guardar(){
        System.out.println(producto);
        service.guardar(producto);
        return "index.xhtml?faces-redirect=true";                    // MANEJO DE NAVEGACIÓN EN JSF : ESTO ES UN REDIRECT, PARA Q REDIRIJA A LA LISTA CON EL PRODUCTO NUEVO O EDITADO.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String editar(Long id){
        this.id = id;
        return "form.xhtml";
    }

    public String eliminar(Long id){
        service.eliminar(id);
        return "index.xhtml?faces-redirect=true";
    }


    @Produces
    @Model
    public List<Categoria>categorias(){
        return  service.listarCategorias();
    }


}
