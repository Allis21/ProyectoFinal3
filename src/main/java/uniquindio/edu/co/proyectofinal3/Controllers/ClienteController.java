package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uniquindio.edu.co.proyectofinal3.HelloApplication;

import java.io.IOException;

public class ClienteController {

    @FXML
    private Button btnCarrito;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEventos;

    @FXML
    private Button btnFinalizarCompra;

    @FXML
    private TableColumn<?, ?> columnBoletasBronceFac;

    @FXML
    private TableColumn<?, ?> columnBoletasOroFac;

    @FXML
    private TableColumn<?, ?> columnBoletasPlataFac;

    @FXML
    private TableColumn<?, ?> columnCapacidadMateria;

    @FXML
    private TableColumn<?, ?> columnCodigoEventoFac;

    @FXML
    private TableColumn<?, ?> columnCodigoMateria;

    @FXML
    private TableColumn<?, ?> columnEstadoMateria;

    @FXML
    private TableColumn<?, ?> columnNombreEventoFac;

    @FXML
    private TableColumn<?, ?> columnNombreMateria;

    @FXML
    private TableColumn<?, ?> columnValorFac;

    @FXML
    private AnchorPane panelEventos;

    @FXML
    private AnchorPane panelFactura;

    @FXML
    private TableView<?> tablaEventos;

    @FXML
    private TableView<?> tablaFactura;

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        HelloApplication.showInicioView();
        ((btnCerrarSesion)).getScene().getWindow().hide();
    }
    
    @FXML
    void cliclkedFinalizarCompra(ActionEvent event) {
    }

    @FXML
    void mostrarPanelEventos(ActionEvent event) {
        panelEventos.setVisible(true);
        panelFactura.setVisible(false);
    }

    @FXML
    void mostrarPanelFactura(ActionEvent event) {
        panelEventos.setVisible(false);
        panelFactura.setVisible(true);
    }
}
