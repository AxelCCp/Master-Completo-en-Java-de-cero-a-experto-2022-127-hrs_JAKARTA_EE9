package hibernate.jpa.app.entity;

import jakarta.persistence.*;

//519

@Entity
@Table(name="clientes_detalles")
public class ClienteDetalle {

    public ClienteDetalle() {
    }

    public ClienteDetalle(Boolean prime, Long puntosAcumulados) {
        this.prime = prime;
        this.puntosAcumulados = puntosAcumulados;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getPrime() {
        return prime;
    }

    public void setPrime(Boolean prime) {
        this.prime = prime;
    }

    public Long getPuntosAcumulados() {
        return puntosAcumulados;
    }

    public void setPuntosAcumulados(Long puntosAcumulados) {
        this.puntosAcumulados = puntosAcumulados;
    }

    /*
    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
    */

    @Override
    public String toString() {
        return "ClienteDetalle{" +
                "id=" + id +
                ", prime=" + prime +
                ", puntosAcumulados=" + puntosAcumulados +
                '}';
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean prime;

    @Column(name="puntos_acumulados")
    private Long puntosAcumulados;

    /*
    //519 - al inicio del video - AL TENER SOLO EL ONE TO ONE AQUÍ Y NADA EN LA CLASE CLIENTES, LA RELACIÓN ES UNIDIRECCIONAL Y LA Y LA DUEÑA DE LA RELACION EN CLIENTEDETALLE, YA Q LA FOREING KEY ESTÁ AQUÍ.
    @OneToOne
    private Cliente cliente;
    */

}
