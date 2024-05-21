package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.HelloApplication;
import uniquindio.edu.co.proyectofinal3.Modelo.AppController;
import uniquindio.edu.co.proyectofinal3.Modelo.Cliente;
import uniquindio.edu.co.proyectofinal3.Modelo.SistemaTaquillera;
import uniquindio.edu.co.proyectofinal3.Utils.Adicional;

import java.io.IOException;

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
        // PRUEBA DE INICIO DE SESION ADMIN
        if(txtFieldIDInicio.getText().equals(sistemaTaquillera.getAdministrador().getIdAdmin()) && txtFieldPWInicio.getText().equals(sistemaTaquillera.getAdministrador().getConAdmin())) {
            try {
                HelloApplication.showAdminView();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            ((btnIniciarSesion)).getScene().getWindow().hide();
        }
        else {
            Adicional.sendAlerta("ERROR", "No eres Admin Manito", null);
        }
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
        if(sistemaTaquillera.verificarClienteExiste(id)) {
            Adicional.sendAlerta("ERROR", "Datos Incorrectos", "Un Usuario ya está registrado con el ID " + id);
        }

        txtFieldNombreReg.clear();
        txtFieldIDReg.clear();
        txtFieldEmailReg.clear();
        txtFieldPWReg.clear();
    }
}