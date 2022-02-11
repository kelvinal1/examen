package m5b.examen.Models;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "producto")
public class producto {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codigo;

    @Column(nullable = false, length = 100)
    private String descripcion;
    
    @Column(nullable = false, scale = 2)
    private double precio;

    @Column(nullable = false)
    private int cantidad;

    
   
    
    private double calculado;

    public producto() {
    }

    
    public producto(String descripcion, double precio, int cantidad, double calculado) {
        this.descripcion = descripcion;
        this.precio = precio;
        this.cantidad = cantidad;
        this.calculado = calculado;
    }


    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    

    public double getCalculado() {
        return calculado;
    }


    public void setCalculado(double calculado) {
        this.calculado = calculado;
    }


    @Override
    public String toString() {
        return "producto [cantidad=" + cantidad + ", codigo=" + codigo + ", descripcion=" + descripcion + ", precio="
                + precio + "]";
    }


    

    
}
