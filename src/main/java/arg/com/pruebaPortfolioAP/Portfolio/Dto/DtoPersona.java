package arg.com.pruebaPortfolioAP.Portfolio.Dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class DtoPersona {
    @NotBlank
    private String nombre;
    @NotBlank
    private String apellido;

    private String cargo;

    @NotBlank
    private String descripcion;

    private String img;

    public DtoPersona(){
    }

    public DtoPersona(String nombre, String apellido, String cargo, String descripcion, String img) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cargo = cargo;
        this.descripcion = descripcion;
        this.img = img;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImg() {
        return img;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
