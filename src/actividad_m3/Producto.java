package actividad_m3;

//Clase producto que tendra los atributos, el metodo constructor, los metodos get y los metodos set
import java.util.Objects;

public class Producto {

    String idProducto;
    String nomProducto;
    String descripcion;
    float precioP;
    Producto sig;

    Producto() {

        idProducto = "";
        nomProducto = "";
        descripcion = "";
        precioP = -1;
        sig = null;

    }

    public Producto(String idProducto, String nomProducto, String descripcion, float precioP) {
        this.idProducto = idProducto;
        this.nomProducto = nomProducto;
        this.descripcion = descripcion;
        this.precioP = precioP;
        this.sig = null;
    }

    public String getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(String idProducto) {
        this.idProducto = idProducto;
    }

    public String getNomProducto() {
        return nomProducto;
    }

    public void setNomProducto(String nomProducto) {
        this.nomProducto = nomProducto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public float getPrecioP() {
        return precioP;
    }

    public void setPrecioP(float precioP) {
        this.precioP = precioP;
    }

    public Producto getSig() {
        return sig;
    }

    public void setSig(Producto sig) {
        this.sig = sig;
    }

   

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Producto producto = (Producto) obj;
        return idProducto.equals(producto.idProducto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProducto);
    }

}
