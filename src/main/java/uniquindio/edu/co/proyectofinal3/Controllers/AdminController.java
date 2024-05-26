package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;
import uniquindio.edu.co.proyectofinal3.Exepciones.LocalidadException;
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
    private Button btnActualizarEvento;

    @FXML
    private Button btnActualizarLocalidad;

    @FXML
    private Button btnCerrarSesion;

    @FXML
    private Button btnEliminarEvento;

    @FXML
    private Button btnEliminarLocalidad;

    @FXML
    private Button btnPanelEventos;

    @FXML
    private Button btnPanelLocaciones;

    @FXML
    private Button btnRegistrarEvento;

    @FXML
    private Button btnRegistrarLocalidad;

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
    private TableColumn<?, ?> columnLocalidadRegis;

    @FXML
    private TableColumn<?, ?> columnNombreEventoApert;

    @FXML
    private TableColumn<?, ?> columnNombreEventoRegis;

    @FXML
    private TableColumn<Localidad, String> columnPais;

    @FXML
    private ComboBox<Localidad> comboBoxLocalidades;

    @FXML
    private AnchorPane panelAperturaTaquilla;

    @FXML
    private AnchorPane panelRegistroEvento;

    @FXML
    private AnchorPane panelRegistroLocalizaciones;

    @FXML
    private DatePicker seleccionarFechaEvento;
    @FXML
    private TableView<Localidad> tablaLocalidades;

    @FXML
    private TableView<?> tablaEventos1;

    @FXML
    private TableView<?> tablaRegistroEvento;

    @FXML
    private TextField txtArtistaEvento;

    @FXML
    private TextField txtCapacidadMaxEvento;

    @FXML
    private TextField txtCiudadLocalidad;

    @FXML
    private TextField txtCodigoEvento;

    @FXML
    private TextField txtDireccionLocalidad;

    @FXML
    private TextField txtNombreEvento;

    @FXML
    private TextField txtPaisLocalidad;

    @FXML
    void cerrarSesion(ActionEvent event) throws IOException {
        HelloApplication.showInicioView();
        ((btnCerrarSesion)).getScene().getWindow().hide();
    }

    @FXML
    void clickedActualizarEvento(ActionEvent event) {

    }

    @FXML
    void clickedActualizarLocalidad(ActionEvent event) {
        String pais = txtPaisLocalidad.getText();
        String ciudad = txtCiudadLocalidad.getText();
        String direccion = txtDireccionLocalidad.getText();

        Localidad localidad = new Localidad(pais, ciudad, direccion);

        try{
            boolean updateLocalidad = sistemaTaquillera.actualizarLocalidad(localidad);

            if (updateLocalidad){
                for (int i = 0; i < listaLocalidades.size(); i++){
                    Localidad l = listaLocalidades.get(i);
                    if(l.getDireccion().equals(direccion)){
                        //Actualiza los valores de la localidad en la lista observable
                        l.setPais(pais);
                        l.setCiudad(ciudad);
                        l.setDireccion(direccion);
                        //Notifica el cambio en la lista
                        listaLocalidades.set(i, l);
                        break;
                    }
                }
            }
        }catch (EventoException | LocalidadException e){
            //Maneja excepciones mostradno un mensaje de error o logueandolas
            System.err.println("Error al actuliazar la localidad: " + e.getMessage());
        }
        txtPaisLocalidad.clear();
        txtCiudadLocalidad.clear();
        txtDireccionLocalidad.clear();
    }

    @FXML
    void clickedEliminarEvento(ActionEvent event) {

    }

    @FXML
    void clickedEliminarLocalidad(ActionEvent event) throws LocalidadException {
        String direccion = txtDireccionLocalidad.getText();

        boolean removedLocalidad = sistemaTaquillera.eliminarLocalidad(direccion);

        if (removedLocalidad){
            Localidad localidadRemove = null;
            for (Localidad localidad : listaLocalidades){
                if(localidad.getDireccion().equals(direccion)){
                    localidadRemove = localidad;
                    break;
                }
            }
            if(localidadRemove != null){
                listaLocalidades.remove(localidadRemove);
            }
        }

        txtPaisLocalidad.clear();
        txtCiudadLocalidad.clear();
        txtDireccionLocalidad.clear();

    }

    @FXML
    void clickedRegistrarEvento(ActionEvent event) {

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
        comboBoxLocalidades.setItems(listaLocalidades);
    }

    @FXML
    void setearTablaLocalidades(){
        listaLocalidades = FXCollections.observableArrayList();
        listaLocalidades.addAll(sistemaTaquillera.getListaLocalidades());

        columnPais.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPais()));
        columnCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        columnDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

        tablaLocalidades.setItems(listaLocalidades);
    }
}
