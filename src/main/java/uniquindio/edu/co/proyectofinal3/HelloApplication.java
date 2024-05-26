package uniquindio.edu.co.proyectofinal3;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uniquindio.edu.co.proyectofinal3.Utils.Adicional;
import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //showInicioView();
        showAdminView();
        //showClienteView();
    }

    public static void main(String[] args) {
        launch();
    }

    public static void showInicioView() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("inicioView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("LOGIN");
        stage.show();
        stage.setResizable(false);
    }

    public static void showClienteView() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("clienteView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("BIENVENIDO");
        stage.show();
        stage.setResizable(false);
    }

    public static void showAdminView() throws IOException {
        FXMLLoader loader = new FXMLLoader(HelloApplication.class.getResource("adminView.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("CLIENTE");
        stage.show();
        stage.setResizable(false);
    }
}