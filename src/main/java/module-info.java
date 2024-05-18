module uniquindio.edu.co.proyectofinal3 {
    requires javafx.controls;
    requires javafx.fxml;


    opens uniquindio.edu.co.proyectofinal3 to javafx.fxml;
    exports uniquindio.edu.co.proyectofinal3.Controllers;
    opens uniquindio.edu.co.proyectofinal3.Controllers to javafx.fxml;
    exports uniquindio.edu.co.proyectofinal3 to javafx.graphics;
}