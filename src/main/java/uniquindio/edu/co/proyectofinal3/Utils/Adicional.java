package uniquindio.edu.co.proyectofinal3.Utils;

import javafx.scene.control.Alert;

public class Adicional {
    public static void sendAlerta(String titulo, String encabezado, String contenido) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(titulo);
        alert.setHeaderText(encabezado);
        alert.setContentText(contenido);
        alert.showAndWait();
    }
}
