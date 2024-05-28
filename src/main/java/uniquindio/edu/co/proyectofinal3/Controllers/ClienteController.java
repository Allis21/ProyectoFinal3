package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import uniquindio.edu.co.proyectofinal3.HelloApplication;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import uniquindio.edu.co.proyectofinal3.Modelo.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Date;
import java.util.Locale;

public class ClienteController {

    SistemaTaquillera sistemaTaquillera = AppController.INSTANCE.getSistemaTaquillera();

    private ObservableList<Object> listaEventos;

    private ObservableList<Object> listaCarrito;

    @FXML
    private Button btnAnadirCarrito;

    @FXML
    private Button btnCarrito;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEventos;

    @FXML
    private TableColumn<Factura, Integer> columnBoletasBronceFac;

    @FXML
    private TableColumn<Factura, Integer> columnBoletasOroFac;

    @FXML
    private TableColumn<Factura, Integer> columnBoletasPlataFac;

    @FXML
    private TableColumn<Evento, String> columnCodigoEventoFac;

    @FXML
    private TableColumn<Evento, LocalDate> columnFechaEvento;

    @FXML
    private TableColumn<Evento, String> columnInvitadosEvento;

    @FXML
    private TableColumn<Evento, Localidad> columnLugarEvento;

    @FXML
    private TableColumn<Evento, String> columnNombreEvento;

    @FXML
    private TableColumn<Evento, String> columnNombreEventoFac;

    @FXML
    private TableColumn<Evento, Float> columnValorBronce;

    @FXML
    private TableColumn<Factura, Float> columnValorFac;

    @FXML
    private TableColumn<Evento, Float> columnValorOro;

    @FXML
    private TableColumn<Evento, Float> columnValorPlata;

    @FXML
    private AnchorPane panelEventos;

    @FXML
    private AnchorPane panelFactura;

    @FXML
    private TableView<Object> tablaEventosCompra;

    @FXML
    private TableView<Object> tablaFactura;

    @FXML
    private TextField txtFieldCantBoletasCobre;

    @FXML
    private TextField txtFieldCantBoletasOro;

    @FXML
    private TextField txtFieldCantBoletasPlata;
    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        HelloApplication.showInicioView();
        ((btnCerrarSesion)).getScene().getWindow().hide();
    }
    
    /*@FXML
    void cliclkedFinalizarCompra(ActionEvent event) {
        logger.info("Compra finalizada");
    }*/

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

    @FXML
    void initialize() {
        setearTablaEventos();

    }

    @FXML
    void setearTablaEventos() {
        listaEventos = FXCollections.observableArrayList();

    }

    @FXML
    void mostrarEventoEnCampos(){
    }
}
