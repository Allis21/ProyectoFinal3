package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import uniquindio.edu.co.proyectofinal3.HelloApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uniquindio.edu.co.proyectofinal3.Modelo.Evento;

import java.io.IOException;
import java.util.Date;

public class ClienteController {
    private static final Logger logger = LogManager.getLogger(ClienteController.class);


    @FXML
    private Button btnCarrito;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEventos;

    @FXML
    private Button btnFinalizarCompra;

    @FXML
    private TableColumn<Evento, Float> columnBoletasBronceFac;

    @FXML
    private TableColumn<Evento, Float> columnBoletasOroFac;

    @FXML
    private TableColumn<Evento, Float> columnBoletasPlataFac;

    @FXML
    private TableColumn<Evento, Date> columnFechaEventoFac;

    @FXML
    private TableColumn<Evento, String> columnCodigoEventoFac;

    @FXML
    private TableColumn<Evento, String> columnInvitadosEvento;

    @FXML
    private TableColumn<Evento, String> columnLugarEvento;

    @FXML
    private TableColumn<Evento, String> columnNombreEvento;

    @FXML
    private TableColumn<Evento, String> columnNombreEventoFac;

    @FXML
    private TableColumn<Evento, Float> columnValorFac;

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

        logger.info("Compra finalizada");
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
