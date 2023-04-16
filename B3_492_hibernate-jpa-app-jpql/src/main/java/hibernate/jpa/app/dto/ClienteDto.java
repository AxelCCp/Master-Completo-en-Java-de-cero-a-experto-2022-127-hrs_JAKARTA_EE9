package hibernate.jpa.app.dto;

public class ClienteDto {

    public ClienteDto(String nobre, String apellido) {
        this.nobre = nobre;
        this.apellido = apellido;
    }

    public String getNobre() {
        return nobre;
    }

    public void setNobre(String nobre) {
        this.nobre = nobre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    @Override
    public String toString() {
        return "ClienteDto{" +
                "nobre='" + nobre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }

    private String nobre;
    private String apellido;

}
