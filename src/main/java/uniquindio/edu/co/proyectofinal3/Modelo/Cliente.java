package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;

public class Cliente implements Serializable {

    private String nombre;
    private String id;
    private String correo;
    private String contrasenia;



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
