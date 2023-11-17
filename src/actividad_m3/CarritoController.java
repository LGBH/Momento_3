package actividad_m3;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;

public class CarritoController implements Initializable {

    private PrincipalController principalController;
    ListaP listaProductos;
    ListaC listaProductosC;
    private Stage stage;
    private int contador;
    private Label cuentaLabel;

    @FXML
    private Button Comprar;

    @FXML
    private Label Total;

    @FXML
    private Button Cancelar;

    @FXML
    private TableView<Producto> tablec;

    @FXML
    private TableColumn<Producto, String> nomColumnc;

    @FXML
    private TableColumn<Producto, String> descColumnc;

    @FXML
    private TableColumn<Producto, String> precioColumnc;

    public void setPrincipalController(PrincipalController principalController) {
        this.principalController = principalController;
    }

    void actualizarTabla() {
        llenarCarritoconProductos();
    }

    void limpiarTabla() {
        tablec.getItems().clear();
        listaProductosC.cab = null;  // Limpiar ListaC
        actualizarTotal();
    }

    void actualizarTotal() {

        float total = listaProductosC.sumarTotal();
        Total.setText(String.format("$ %.2f", total));
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public void setCuenta(Label cuentaLabel) {
        this.cuentaLabel = cuentaLabel;
    }

    @FXML
    void comprar(ActionEvent event) {

        listaProductosC.limpiarLista();

        limpiarTabla();
        actualizarTotal();

        Stage stage = (Stage) Comprar.getScene().getWindow();
        stage.close();

        cuentaLabel.setText("0");
    }

    @FXML
    void handleCancelar(ActionEvent event) {
        // Llamar al método para mover productos de ListaC a ListaP
        principalController.moverProductos(listaProductosC);
        // Cerrar la ventana de Carrito
        Stage stage = (Stage) Cancelar.getScene().getWindow();
        stage.close();
        cuentaLabel.setText("0");
    }

    void llenarCarritoconProductos() {
        if (!listaProductosC.getIsEmpty()) {

            nomColumnc.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(cellData.getValue().getNomProducto());
                return property;
            });
            descColumnc.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty(cellData.getValue().getDescripcion());
                return property;
            });
            precioColumnc.setCellValueFactory(cellData -> {
                SimpleStringProperty property = new SimpleStringProperty("$" + String.valueOf(cellData.getValue().getPrecioP()));
                return property;
            });
            // Recorre la lista y agrega los productos a la tabla
            Producto p = listaProductosC.cab;
            do {
                tablec.getItems().add(p);
                p = p.sig;
            } while (p != listaProductosC.cab);
        } else {
            // Manejo del caso en que listaProductos o su cabeza (cab) sea null

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listaProductos = new ListaP();
        listaProductosC = new ListaC();
        llenarCarritoconProductos();
        actualizarTotal();

    }

    public void setListaCarrito(ListaC listaCarrito) {
        this.listaProductosC = listaCarrito;
        llenarCarritoconProductos();
        actualizarTotal();
    }

}
