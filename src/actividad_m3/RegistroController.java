package actividad_m3;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class RegistroController {

    private ListaP listaProductos = new ListaP();
    private PrincipalController controladorp;

    // Elimina el método initialize si no necesitas inicialización específica para esta clase
    @FXML
    private TextField idp;

    @FXML
    private TextField nombrep;

    @FXML
    private TextField desc;

    @FXML
    private TextField preciop;

    @FXML
    private Button registrar;

    @FXML
    void registrarProducto() {
        try {
            String id = idp.getText();
            String nombre = nombrep.getText();
            String descripcion = desc.getText();
            float precio = Float.parseFloat(preciop.getText());

            Producto nuevoProducto = new Producto(id, nombre, descripcion, precio);
            listaProductos.setAddInicio(nuevoProducto);

            // Verifica que controladorp no sea nulo antes de llamar a llenarTablaConProductos
            if (controladorp != null) {
                controladorp.llenarTablaConProductos();
            }

            // Cierra la ventana actual (puedes hacer esto según la lógica de tu aplicación)
            registrar.getScene().getWindow().hide();
        } catch (NumberFormatException e) {
            // Manejo específico de la excepción si ocurre un problema al convertir a número flotante
            e.printStackTrace();
            // Puedes mostrar un mensaje de error al usuario si es necesario
        } catch (Exception e) {
            // Manejo de otras excepciones si es necesario
            e.printStackTrace();
        }
    }

    public void setListaProductos(ListaP listaProductos) {
        this.listaProductos = listaProductos;
    }

    public void setControladorp(PrincipalController controladorp) {
        this.controladorp = controladorp;
    }

}
