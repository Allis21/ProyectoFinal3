package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTaquillera {

    public  static void ejecutarServidorSocket() {
        // TODO Auto-generated method stub
        try {
            ServerSocket server = new ServerSocket(8090);
            System.out.println("Esperando conexiones con el sistema BE");
            Socket connection = server.accept();
            System.out.printf("Socket %s conectado\n", connection.getInetAddress().getHostAddress());

            DataInputStream data = new DataInputStream((connection.getInputStream()));
            String message = data.readUTF();

            System.out.printf("El cliente %s envía: %s", connection.getInetAddress().getHostAddress(), message);

            server.close();
            connection.close();
            data.close();

        } catch (IOException e) {
            //TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
