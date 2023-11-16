package actividad_m3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Stage;

public class PrincipalController implements Initializable {

    private ListaP listaProductos;  // No inicialices aquí, hazlo en initialize

    @FXML
    private Button Agregar;

    @FXML
    private TableView<Producto> tablep;

    @FXML
    private TableColumn<Producto, String> idColumn;

    @FXML
    private TableColumn<Producto, String> nomColumn;

    @FXML
    private TableColumn<Producto, String> descColumn;

    @FXML
    private TableColumn<Producto, String> precioColumn;

    @FXML
    void handleButtonAction(ActionEvent event) {
        abrirVentanaNuevoProducto();
    }

    void actualizarTabla() {
        llenarTablaConProductos();
    }

    void abrirVentanaNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
            Parent root = loader.load();

            // Configura el controlador de la nueva escena
            RegistroController nuevoProductoController = loader.getController();
            nuevoProductoController.setListaProductos(listaProductos);
            nuevoProductoController.setControladorp(this); // Pasa la instancia actual de FXMLDocumentController

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
        void abrirVentanaCarrito() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
            Parent root = loader.load();

            // Configura el controlador de la nueva escena
            RegistroController nuevoProductoController = loader.getController();
            nuevoProductoController.setListaProductos(listaProductos);
            nuevoProductoController.setControladorp(this); // Pasa la instancia actual de FXMLDocumentController

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void llenarTablaConProductos() {
        if (!listaProductos.getIsEmpty()) {

            idColumn.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(cellData.getValue().getIdProducto());
                return property;
            });
            nomColumn.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(cellData.getValue().getNomProducto());
                return property;
            });
            descColumn.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(cellData.getValue().getDescripcion());
                return property;
            });
            precioColumn.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(String.valueOf(cellData.getValue().getPrecioP()));
                return property;
            });
            // Limpia la tabla antes de agregar nuevos elementos
            tablep.getItems().clear();

            // Recorre la lista y agrega los productos a la tabla
            Producto p = listaProductos.cab;
            do {
                tablep.getItems().add(p);
                p = p.sig;
            } while (p != listaProductos.cab);
        } else {
            // Manejo del caso en que listaProductos o su cabeza (cab) sea null
            System.out.println("Error: listaProductos o su cabeza es null");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = new ListaP();
        llenarTablaConProductos();
    }
}
