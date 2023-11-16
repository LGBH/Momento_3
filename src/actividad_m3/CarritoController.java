
package actividad_m3;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class CarritoController implements Initializable {
    

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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
