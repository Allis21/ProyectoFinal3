package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements Serializable {

    private String nombre, id, correo, contrasenia;


    public Cliente(){ super(); }

   public Cliente(String nombre, String id, String correo, String contrasenia) {
     this.nombre = nombre;
     this.id = id;
     this.correo = correo;
     this.contrasenia = contrasenia;
   }

    public String getNombre() { return nombre; }

    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getId() { return id; }

    public void setId(String id) { this.id = id; }

    public String getCorreo() { return correo; }

    public void setCorreo(String correo) { this.correo = correo; }

    public String getContrasenia() { return contrasenia; }

    public void setContrasenia(String contrasenia) { this.contrasenia = contrasenia; }
    public void conectarConServidor() {
        try {
            Socket socket = new Socket("localhost", 8090);

            // Crea un PrintWriter que enviará mensajes al servidor a través del OutputStream del Socket
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

            // Crea un BufferedReader que leerá las respuestas del servidor a través del InputStream del Socket
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            // Envía un mensaje al servidor
            out.println("Hola, servidor!");

            // Lee la respuesta del servidor y la imprime
            System.out.println("Respuesta del servidor: " + in.readLine());

            // Cierra el Socket
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   /* public void ejecutarSocketCliente(){
        // TODO Auto-generated method stub
        try{
            Socket socket = new Socket("local host", 8090);
            DataOutputStream data = new DataOutputStream(socket.getOutputStream());
            data.writeUTF("Cliente conectado :)");
            
            data.close();
            socket.close();
        } catch (UnknownHostException e){
            // TODO Auto-generated method stub
            e.printStackTrace();
        } catch (IOException e){
            // TODO Auto-generated method stub
            e.printStackTrace();
        }

    }*/

    //DEVUELVE UNA CADENA QUE CONTIENE INFORMACIÓN DEL CLIENTE
    @Override
    public String toString() {
      return "Cliente:" +
             "nombre='" + nombre + '\'' +
             ", correo='" + correo + '\'' +
             ", id='" + id;
      }

    //COMPARA SI EXISTEN CLIENTES YA REGISTRADOS CON POSIBLES CLIENTES NUEVOS
    @Override
    public boolean equals(Object o) {
     if (this == o) return true;
     if (o == null || getClass() != o.getClass()) return false;
     Cliente cliente = (Cliente) o;
     return Objects.equals(id, cliente.id);
    }
}
