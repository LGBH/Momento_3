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
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class PrincipalController implements Initializable {

    private ListaP listaProductos;
    private ListaC listacarrito;
    int contador;

    @FXML
    private Button Agregar;

    @FXML
    private Label Cuenta;

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

    @FXML
    void handleButtonAction2(ActionEvent event) {
        abrirVentanaCarrito();
    }

    void actualizarTabla() {
        llenarTablaConProductos();
    }

    void limpiarTabla() {
        tablep.getItems().clear();
    }

    public void moverProductos(ListaC listaC) {
        Producto p = listaC.cab;

        for (int i = 0; i < listacarrito.getLongLista(); i++) {
            listaProductos.setAddFinal(new Producto(p.getIdProducto(), p.getNomProducto(), p.getDescripcion(), p.getPrecioP()));

            // Mover al siguiente producto en ListaC
            p = p.sig;
        }

        // Limpiar ListaC después de mover los productos
        listaC.cab = null;

        // Actualizar TableView en PrincipalController
        llenarTablaConProductos();

        JOptionPane.showMessageDialog(null, "Compra cancelada");
    }

    void abrirVentanaNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
            Parent root = loader.load();

            // Configura el controlador de la nueva escena
            RegistroController nuevoProductoController = loader.getController();
            nuevoProductoController.setListaProductos(listaProductos);
            nuevoProductoController.setControladorp(this);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void abrirVentanaCarrito() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Carrito.fxml"));
            Parent root = loader.load();
            // Configurar el controlador de la nueva escena
            CarritoController carritoController = loader.getController();
            carritoController.setListaCarrito(listacarrito);
            carritoController.setCuenta(Cuenta);
            carritoController.setContador(contador);
            carritoController.setPrincipalController(this);

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
                SimpleStringProperty property = new SimpleStringProperty("$" + String.valueOf(cellData.getValue().getPrecioP()));
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

        }
    }

    private void alCarrito(ActionEvent event) {
        // Obtener el producto seleccionado en la tabla
        Producto productoSeleccionado = tablep.getSelectionModel().getSelectedItem();

        if (productoSeleccionado != null) {
            listaProductos.eliminarProductoPorId(productoSeleccionado.getIdProducto());
            listacarrito.setAddInicio(productoSeleccionado);
            limpiarTabla();
            contador = listacarrito.getLongLista();
            Cuenta.setText(String.valueOf(contador));
            actualizarTabla();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = new ListaP();
        listacarrito = new ListaC();
        llenarTablaConProductos();
        contador = 0;

        // Configurar el ContextMenu para la tabla
        ContextMenu contextMenu = new ContextMenu();

        // Crear el MenuItem para eliminar y agregar a otra lista
        MenuItem eliminarMenuItem = new MenuItem("Agregar al carrito");
        eliminarMenuItem.setOnAction(this::alCarrito);

        // Agregar el MenuItem al ContextMenu
        contextMenu.getItems().add(eliminarMenuItem);

        // Asignar el ContextMenu a la tabla
        tablep.setContextMenu(contextMenu);
    }
}
