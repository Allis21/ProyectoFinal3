package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Modelo.AppController;
import uniquindio.edu.co.proyectofinal3.Modelo.Cliente;
import uniquindio.edu.co.proyectofinal3.Modelo.SistemaTaquillera;

public class InicioSesionController {

    SistemaTaquillera sistemaTaquillera = AppController.INSTANCE.getSistemaTaquillera();

    @FXML
    private Button btnIniciarSesion;

    @FXML
    private Button btnRegistrarse;

    @FXML
    private TextField txtFieldEmailReg;

    @FXML
    private TextField txtFieldIDInicio;

    @FXML
    private TextField txtFieldIDReg;

    @FXML
    private TextField txtFieldNombreReg;

    @FXML
    private TextField txtFieldPWInicio;

    @FXML
    private PasswordField txtFieldPWReg;

    @FXML
    void clickedIniciarSesion(ActionEvent event) {

    }

    @FXML
    void clickedRegistrarse(ActionEvent event) throws ClienteException {
        String nombre, id, correo, contrasenia;
        nombre = txtFieldNombreReg.getText();
        id = txtFieldIDReg.getText();
        correo = txtFieldEmailReg.getText();
        contrasenia = txtFieldPWReg.getText();

        Cliente cliente = new Cliente(nombre, id, correo, contrasenia);
        sistemaTaquillera.registrarCliente(cliente);
    }
}