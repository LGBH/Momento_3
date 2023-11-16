package actividad_m3;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

public class LoginController implements Initializable {

    String user1 = "User";
    String pass1 = "1234";

    @FXML
    private TextField user;

    @FXML
    private PasswordField pass;

    @FXML
    private Button Iniciar;

    @FXML
    void Verify(ActionEvent event) {
        if (user.getText().equals(user1) && pass.getText().equals(pass1)) {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Principal.fxml"));
                Parent root = loader.load();

                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();

                Iniciar.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos! intente nuevamente.");
            user.setText("");
            pass.setText("");

        }
    }

    void abrirVentanaNuevoProducto() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/Registro.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
