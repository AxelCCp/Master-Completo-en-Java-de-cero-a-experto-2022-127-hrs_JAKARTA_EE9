package hibernate.jpa.app.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente {

    public Cliente() {
        direcciones = new ArrayList<>();
        facturas = new ArrayList<>();
    }

    public Cliente(String nombre, String apellido) {
        this();                                                                                     //CON ESTO SE LLAMA AL CONSTRUCTOR VACIO PARA Q LO INICIALICE.
        this.nombre = nombre;
        this.apellido = apellido;
    }

    public Cliente(Long id, String nombre, String apellido, String formaPago) {
        this();
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.formaPago = formaPago;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getFormaPago() {
        return formaPago;
    }

    public void setFormaPago(String formaPago) {
        this.formaPago = formaPago;
    }

    public List<Direccion> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(List<Direccion> direcciones) {
        this.direcciones = direcciones;
    }

    public Auditoria getAudit() {
        return audit;
    }

    public void setAudit(Auditoria audit) {
        this.audit = audit;
    }

    public List<Factura> getFacturas() {
        return facturas;
    }

    public void setFacturas(List<Factura> facturas) {
        this.facturas = facturas;
    }

    public ClienteDetalle getDetalle() {
        return detalle;
    }

    public void setDetalle(ClienteDetalle detalle) {
        this.detalle = detalle;
    }

    @Override
    public String toString() {

        //PARA Q NO DÉ NULL POINTER EXCEPTION:
        LocalDateTime creado = this.audit != null ? audit.getCreadoEn() : null;
        LocalDateTime editado = this.audit != null ? audit.getEditadoEn() : null;

        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", formaPago='" + formaPago + '\'' +
                ", creadoEn=" + creado + '\'' +
                ", editadoEn=" + editado + '\'' +
                ", direcciones= " + direcciones + '\'' +
                ", facturas= " + facturas + '\'' +
                ", detalle= " + detalle + '\'' +
                '}';
    }

    public void addFactura(Factura factura){
        this.facturas.add(factura);
        factura.setCliente(this);
    }


    public  void removeFactura(Factura factura){
        this.facturas.remove(factura);
        factura.setCliente(null);
    }



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String apellido;
    @Column(name="forma_pago")
    private String formaPago;
    @Embedded
    private Auditoria audit = new Auditoria();

    //515 - SE PERSONALIZA EL NOMBRE DE LA TABLA INTERMEDIA y LOS NOMBRES DE LAS FOREIGN KEY Q CONFORMAN LA TABLA INTERMEDIA.
    @JoinTable(name = "tbl_clientes_direcciones", joinColumns = @JoinColumn(name = "id_cliente"),
                                                  inverseJoinColumns = @JoinColumn(name = "id_direccion"),
                                                  uniqueConstraints = @UniqueConstraint(columnNames = "id_direccion"))       //515 - COMO ES ONE TO MANY, A DIRECCION SE LE PONE EL UNIQUE CONSTRAINT
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Direccion>direcciones;

    //517 - INICIALMENTE ERA UNA RELACION UNIDIRECCIONAL QUE ESTABA SOLO EN FACTURA. AHORA SE AGREGA LA BIDIRECCIONAL. EL MAPPEDBY ESPARA HACER REFERENCIA AL ATRIBUTO CLIENTE QUE ESTÁ EN LA CLASE FACTURA.
    //EL @JOINCOLUMN VA EN LA CLASE DUEÑA DE LA RELACION.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "cliente")
    private List<Factura>facturas;

    // 519 al final del video -
    @OneToOne  //@JoinColumn(name="detalle_id") //PARA DARLE OTRO NOMBRE.
    private ClienteDetalle detalle;

}
