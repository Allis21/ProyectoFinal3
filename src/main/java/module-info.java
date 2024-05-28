module uniquindio.edu.co.proyectofinal3 {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires org.apache.logging.log4j;


    opens uniquindio.edu.co.proyectofinal3 to javafx.fxml;
    exports uniquindio.edu.co.proyectofinal3.Controllers;
    opens uniquindio.edu.co.proyectofinal3.Controllers to javafx.fxml;
    exports uniquindio.edu.co.proyectofinal3 to javafx.graphics;
}