package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.Serializable;
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

    //--CRUD-cliente------------------------------------------------------------------------------------------
    public Cliente nuevoCliente(Cliente cliente, String nombre, String id, String correo, String contrasenia)throws ClienteExcpetion{
        Cliente nuevoCliente = null;
        boolean clienteExiste = verificarClienteExiste(id);
        if (clienteExiste){
            throw  new clienteException("El cliente con id "+id+" ya está registrado");
        }else{
            nuevoCliente = new Cliente();
            nuevoCliente.setNombre(cliente.getNombre());
            nuevoCliente.setId(cliente.getId());
            nuevoCliente.setCorreo(cliente.getCorreo());

            getListaCompradores().add(nuevoCliente);
        }
        return nuevoCliente;
    }

    public void agregarCliente(Cliente nuevoCliente)throws ClienteException{
        getListaClientes().add(nuevoCliente);
    }

    public boolean verificarClienteExiste(String id)throws ClienteException{
        if (clienteExiste(id)){
            throw new ClienteExeption("El cliente con cédula "+id+" ya exite.");
        }else{
            return false;
        }
    }

    private boolean clienteExiste(String id){
        boolean clienteEncontrado = false;
        for(Cliente cliente: listaClientes){
            if(cliente.getId().equalsIgnoreCase(id)){
                clienteEncontrado = true;
                break;
            }
        }
        return clienteEncontrado;
    }

    public boolean actualizarCliente(Cliente cliente, String id, String nombre, String correo, String contrasenia)throws ClienteException{
        Cliente clienteActual = obtenerCliente(id);
        if (clienteActual == null){
            throw new ClienteException("El cliente que desea actualizar no existe");
        }else{
            clienteActual.setNombre(cliente.getNombre());
            clienteActual.setId(cliente.getId());
            clienteActual.setCorreo(cliente.getCorreo());
            clienteActual.setContrasenia(cliente.getContrasenia());
            return true;
        }
    }

    public Cliente obtenerCLiente(String id){
        Cliente clienteEncontrado = null;
        for (Cliente cliente : listaClientes){
            if(cliente.getId().equalsIgnoreCase(id)) {
                clienteEncontrado = cliente;
                break;

            }
        }
        return clienteEncontrado;
    }

    public boolean eliminarCliente(String id)throws ClienteExeption{
        Cliente cliente = null;
        boolean flagExiste = false;
        cliente = obtenerCLiente(id);
        if (cliente == null){
            throw new ClienteException("El cliente que desea eliminar no existe.");
        }else{
            getListaClientes().remove(cliente);
            flagExiste = true;
        }
        return flagExiste;
    }

}
