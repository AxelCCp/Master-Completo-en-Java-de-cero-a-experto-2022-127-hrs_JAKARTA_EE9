package hibernate.jpa.app.entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name="facturas")
public class Factura {

    public Factura() {
    }

    public Factura(String descripcion, Long total) {

        this.descripcion = descripcion;
        this.total = total;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", total=" + total +
                //", cliente=" + cliente +    SE QUITA AL CLIENTE DEL TO STRING AL GENERAR LA RELACION BIDIRECCIONAL EN 517, YA QUE SI SE LLAMA AL TO STRING, SE VAN A ESTAR LLAMANDO LOS TO STRING EN UN LOOP INFINITO.
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Factura factura = (Factura) o;
        return Objects.equals(id, factura.id) && Objects.equals(descripcion, factura.descripcion) && Objects.equals(total, factura.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, descripcion, total);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private Long total;

    //INICIALMENTE LA RELACION ERA UNIDIRECCIONAL, YA QUE SOLO ESTABA LA RELACIÓN EN FACTURA. Y SOLO APARECIA EL ID CLIENTE EN LA TABLA FACTURA.
    //EN LA CLASE 517 SE AGREGA LA RELACION BIDIRECCIONAL, AGREGANDO LA RELACIÓN TMBN EN CLASE CLIENTE.
    //EL JOINCOLUMN VA EN LA CLASE DUEÑA DE LA RELACION.
    @ManyToOne
    @JoinColumn(name="cliente_id")
    private Cliente cliente;

}
