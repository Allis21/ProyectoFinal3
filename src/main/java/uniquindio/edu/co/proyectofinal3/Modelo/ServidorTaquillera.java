package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;

public class ServidorTaquillera {

    public class Server {
        // Define el puerto en el que el servidor escuchará
        private static final int PORT = 8090;
        // Crea un ExecutorService con un pool de hilos de tamaño fijo de 3
        private static final ExecutorService executor = Executors.newFixedThreadPool(3);

        public static void main(String[] args) throws IOException {
            // Crea un nuevo ServerSocket que escucha en el puerto definido
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (true) {
                // Acepta una conexión de un cliente y guarda la información de la conexión en la variable "clientSocket"
                Socket clientSocket = serverSocket.accept();
                // Envía una nueva tarea al ExecutorService para manejar la conexión del cliente
                executor.submit(new ClientHandler(clientSocket));
            }
        }

        // Define una clase interna que implementa Runnable para manejar las conexiones de los clientes
        static class ClientHandler implements Runnable {
            private final Socket clientSocket;

            // El constructor toma un Socket que representa la conexión del cliente
            public ClientHandler(Socket socket) {
                this.clientSocket = socket;
            }

            // Este método se ejecutará en un nuevo hilo cuando se envíe la tarea al ExecutorService
            @Override
            public void run() {
                try (PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
                    String inputLine;
                    // Lee un mensaje del cliente y envía una respuesta
                    while ((inputLine = in.readLine()) != null) {
                        out.println("Processed: " + inputLine);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
