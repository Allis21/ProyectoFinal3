package uniquindio.edu.co.proyectofinal3.Controllers;

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
import uniquindio.edu.co.proyectofinal3.Modelo.AppController;
import uniquindio.edu.co.proyectofinal3.Modelo.Localidad;
import uniquindio.edu.co.proyectofinal3.Modelo.SistemaTaquillera;

import java.io.IOException;

public class AdminController {

    SistemaTaquillera sistemaTaquillera = AppController.INSTANCE.getSistemaTaquillera();

    private ObservableList<Localidad> listaLocalidades;

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
    private TableColumn<Localidad, String> columnCiudad;

    @FXML
    private TableColumn<?, ?> columnCodigoEventoRegis;

    @FXML
    private TableColumn<Localidad, String> columnDireccion;

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
    private TableColumn<Localidad, String> columnPais;

    @FXML
    private TableColumn<?, ?> columnUbicacionRegis;

    @FXML
    private AnchorPane panelAperturaTaquilla;

    @FXML
    private AnchorPane panelRegistroEvento;

    @FXML
    private AnchorPane panelRegistroLocalizaciones;

    @FXML
    private TableView<Localidad> tablaEventos;

    @FXML
    private TableView<?> tablaEventos1;

    @FXML
    private TableView<?> tablaFactura;

    @FXML
    private TextField txtCiudadLocalidad;

    @FXML
    private TextField txtDireccionLocalidad;

    @FXML
    private TextField txtPaisLocalidad;

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        HelloApplication.showInicioView();
        ((btnCerrarSesion)).getScene().getWindow().hide();
    }

    @FXML
    void clickedActualizarLocalidad(ActionEvent event) {
        
    }

    @FXML
    void clickedEliminarLocalidad(ActionEvent event) {
    }

    @FXML
    void clickedRegistrarLocalidad(ActionEvent event) {
        String pais = txtPaisLocalidad.getText();
        String ciudad = txtCiudadLocalidad.getText();
        String direccion = txtDireccionLocalidad.getText();

        Localidad localidad = new Localidad(pais, ciudad, direccion);

        sistemaTaquillera.agregarLocalidad(localidad);
        listaLocalidades.addAll(localidad);

        txtPaisLocalidad.clear();
        txtCiudadLocalidad.clear();
        txtDireccionLocalidad.clear();
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

    @FXML
    void initialize() {
        setearTablaLocalidades();
    }

    @FXML
    void setearTablaLocalidades(){
        listaLocalidades = FXCollections.observableArrayList();
        listaLocalidades.addAll(sistemaTaquillera.getListaLocalidades());

        columnPais.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPais()));
        columnCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        columnDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

        tablaEventos.setItems(listaLocalidades);
    }
}
