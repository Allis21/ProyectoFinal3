package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class adminController {

    @FXML
    private Button btnAbrirTaquilla;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnPanelEventos;

    @FXML
    private Button btnPanelLocaciones;

    @FXML
    private TableColumn<?, ?> columnArtistaRegis;

    @FXML
    private TableColumn<?, ?> columnBoletaBronceRegis;

    @FXML
    private TableColumn<?, ?> columnBoletaOroRegis;

    @FXML
    private TableColumn<?, ?> columnBoletaPlataRegis;

    @FXML
    private TableColumn<?, ?> columnCiudad;

    @FXML
    private TableColumn<?, ?> columnCodigoEventoRegis;

    @FXML
    private TableColumn<?, ?> columnDireccion;

    @FXML
    private TableColumn<?, ?> columnEstadoAper;

    @FXML
    private TableColumn<?, ?> columnFechaAper;

    @FXML
    private TableColumn<?, ?> columnFechaRegis;

    @FXML
    private TableColumn<?, ?> columnHoraAper;

    @FXML
    private TableColumn<?, ?> columnNombreEventoApert;

    @FXML
    private TableColumn<?, ?> columnNombreEventoRegis;

    @FXML
    private TableColumn<?, ?> columnPais;

    @FXML
    private TableColumn<?, ?> columnUbicacionRegis;

    @FXML
    private AnchorPane panelAperturaTaquilla;

    @FXML
    private AnchorPane panelRegistroEvento;

    @FXML
    private AnchorPane panelRegistroLocalizaciones;

    @FXML
    private TableView<?> tablaEventos;

    @FXML
    private TableView<?> tablaEventos1;

    @FXML
    private TableView<?> tablaFactura;

    @FXML
    void cerrarSesion(ActionEvent event) {

    }

    @FXML
    void clickedActualizarLocalidad(ActionEvent event) {
        
    }

    @FXML
    void clickedEliminarLocalidad(ActionEvent event) {
    }

    @FXML
    void clickedRegistrarLocalidad(ActionEvent event) {
    }

    @FXML
    void clickedPanelAbrirTaquilla(ActionEvent event) {
        panelRegistroLocalizaciones.setVisible(false);
        panelRegistroEvento.setVisible(false);
        panelAperturaTaquilla.setVisible(true);
    }

    @FXML
    void clickedPanelEventos(ActionEvent event) {
        panelRegistroLocalizaciones.setVisible(false);
        panelRegistroEvento.setVisible(true);
        panelAperturaTaquilla.setVisible(false);
    }

    @FXML
    void clickedPanelLocaciones(ActionEvent event) {
        panelRegistroLocalizaciones.setVisible(true);
        panelRegistroEvento.setVisible(false);
        panelAperturaTaquilla.setVisible(false);
    }
}
