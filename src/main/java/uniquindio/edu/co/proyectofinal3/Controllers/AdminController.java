package uniquindio.edu.co.proyectofinal3.Controllers;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
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
import uniquindio.edu.co.proyectofinal3.Modelo.Evento;
import uniquindio.edu.co.proyectofinal3.Modelo.Localidad;
import uniquindio.edu.co.proyectofinal3.Modelo.SistemaTaquillera;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;

public class AdminController {

    SistemaTaquillera sistemaTaquillera = AppController.INSTANCE.getSistemaTaquillera();

    private ObservableList<Localidad> listaLocalidades;

    private ObservableList<Evento> listaEventos;

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
    private TableColumn<Evento, String> columnArtistaRegis;

    @FXML
    private TableColumn<Evento, Integer> columnBronceRegis;

    @FXML
    private TableColumn<Evento, Integer> columnCapacidadRegis;

    @FXML
    private TableColumn<Localidad, String> columnCiudad;

    @FXML
    private TableColumn<Evento, String> columnCodigoEventoRegis;

    @FXML
    private TableColumn<Localidad, String> columnDireccion;

    @FXML
    private TableColumn<?, ?> columnEstadoAper;

    @FXML
    private TableColumn<?, ?> columnFechaAper;

    @FXML
    private TableColumn<Evento, LocalDate> columnFechaRegis;

    @FXML
    private TableColumn<?, ?> columnHoraAper;

    @FXML
    private TableColumn<Evento, String> columnLocalidadRegis;

    @FXML
    private TableColumn<?, ?> columnNombreEventoApert;

    @FXML
    private TableColumn<Evento, String> columnNombreEventoRegis;

    @FXML
    private TableColumn<Evento, Integer> columnOroRegis;

    @FXML
    private TableColumn<Localidad, String> columnPais;

    @FXML
    private TableColumn<Evento, Integer> columnPlataRegis;

    @FXML
    private TableColumn<Evento, Float> columnValorCobreRegis;

    @FXML
    private TableColumn<Evento, Float> columnValorOroRegis;

    @FXML
    private TableColumn<Evento, Float> columnValorPlataRegis;

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
    private TableView<?> tablaEventos1;

    @FXML
    private TableView<Localidad> tablaLocalidades;

    @FXML
    private TableView<Evento> tablaRegistroEvento;

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
    private TextField txtValorBronce;

    @FXML
    private TextField txtValorOro;

    @FXML
    private TextField txtValorPlata;

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
        Evento newEvento = new Evento();
        newEvento.setNombreEvento(txtNombreEvento.getText());
        newEvento.setNombreArtista(txtArtistaEvento.getText());
        newEvento.setCodigoEvento(txtCodigoEvento.getText());
        newEvento.setLocalidad(comboBoxLocalidades.getValue());
        newEvento.setFechaEvento(seleccionarFechaEvento.getValue());
        newEvento.setCapacidadEvento(Integer.parseInt(txtCapacidadMaxEvento.getText()));

        ArrayList<Integer> distribuirBoletos = newEvento.distribuirBoletos();
        newEvento.setBoletosBronce(distribuirBoletos.get(0));
        newEvento.setBoletosPlata(distribuirBoletos.get(1));
        newEvento.setBoletosOro(distribuirBoletos.get(2));

        newEvento.setValorOro(Integer.parseInt(txtValorOro.getText()));
        newEvento.setValorPlata(Integer.parseInt(txtValorPlata.getText()));
        newEvento.setValorBronce(Integer.parseInt(txtValorBronce.getText()));

        try {
            sistemaTaquillera.registarEvento(newEvento);
        } catch (EventoException e) {
            throw new RuntimeException(e);
        }
        listaEventos.addAll(newEvento);

        txtNombreEvento.clear();
        txtArtistaEvento.clear();
        txtCodigoEvento.clear();
        comboBoxLocalidades.setValue(null);
        seleccionarFechaEvento.setValue(null);
        txtCapacidadMaxEvento.clear();
        txtValorOro.clear();
        txtValorPlata.clear();
        txtValorBronce.clear();
    }

    @FXML
    void clickedRegistrarLocalidad(ActionEvent event) {
        String pais = txtPaisLocalidad.getText();
        String ciudad = txtCiudadLocalidad.getText();
        String direccion = txtDireccionLocalidad.getText();

        Localidad localidad = new Localidad(pais, ciudad, direccion);

        try {
            sistemaTaquillera.registrarLocalidad(localidad);
        } catch (LocalidadException e) {
            throw new RuntimeException(e);
        }
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
        setearTablaEventos();
        comboBoxLocalidades.setItems(listaLocalidades);
    }

    @FXML
    void setearTablaEventos() {
        listaEventos = FXCollections.observableArrayList();
        listaEventos.addAll(sistemaTaquillera.getListaEventos());

        columnNombreEventoRegis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreEvento()));
        columnCodigoEventoRegis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCodigoEvento()));
        columnArtistaRegis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getNombreArtista()));
        columnLocalidadRegis.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getLocalidad().toString()));
        columnFechaRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<LocalDate>(cellData.getValue().getFechaEvento()));
        columnCapacidadRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().getCapacidadEvento()));
        columnBronceRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().distribuirBoletos().get(0)));
        columnPlataRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().distribuirBoletos().get(1)));
        columnOroRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Integer>(cellData.getValue().distribuirBoletos().get(2)));
        columnValorCobreRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getValorBronce()));
        columnValorPlataRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getValorPlata()));
        columnValorOroRegis.setCellValueFactory(cellData -> new SimpleObjectProperty<Float>(cellData.getValue().getValorOro()));

        tablaRegistroEvento.setOnMouseClicked(event -> mostrarEventoEnCampos());
        tablaRegistroEvento.setItems(listaEventos);
    }

    @FXML
    void mostrarEventoEnCampos(){
        Evento eventoSeleccionado = tablaRegistroEvento.getSelectionModel().getSelectedItem();
        if (eventoSeleccionado != null) {
            txtNombreEvento.setText(eventoSeleccionado.getNombreEvento());
            txtCodigoEvento.setText(eventoSeleccionado.getCodigoEvento());
            txtArtistaEvento.setText(eventoSeleccionado.getNombreArtista());
            comboBoxLocalidades.setValue(eventoSeleccionado.getLocalidad());
            seleccionarFechaEvento.setValue(eventoSeleccionado.getFechaEvento());
            txtCapacidadMaxEvento.setText(Integer.toString(eventoSeleccionado.getCapacidadEvento()));
            txtValorBronce.setText(Integer.toString(eventoSeleccionado.getBoletosBronce()));
            txtValorPlata.setText(Integer.toString(eventoSeleccionado.getBoletosPlata()));
            txtValorOro.setText(Integer.toString(eventoSeleccionado.getBoletosOro()));
            txtValorBronce.setText(Float.toString(eventoSeleccionado.getValorBronce()));
            txtValorPlata.setText(Float.toString(eventoSeleccionado.getValorPlata()));
            txtValorOro.setText(Float.toString(eventoSeleccionado.getValorOro()));
        }
    }

    @FXML
    void setearTablaLocalidades(){
        listaLocalidades = FXCollections.observableArrayList();
        listaLocalidades.addAll(sistemaTaquillera.getListaLocalidades());

        columnPais.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getPais()));
        columnCiudad.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCiudad()));
        columnDireccion.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDireccion()));

        tablaLocalidades.setOnMouseClicked(event -> mostrarLocalidadEnCampos());
        tablaLocalidades.setItems(listaLocalidades);
    }

    @FXML
    void mostrarLocalidadEnCampos(){
        Localidad localidadSeleccionada = tablaLocalidades.getSelectionModel().getSelectedItem();
        if (localidadSeleccionada != null) {
            txtPaisLocalidad.setText(localidadSeleccionada.getPais());
            txtCiudadLocalidad.setText(localidadSeleccionada.getCiudad());
            txtDireccionLocalidad.setText(localidadSeleccionada.getDireccion());
        }
    }
}
