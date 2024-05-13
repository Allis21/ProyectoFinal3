package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class SistemaTaquillera implements Serializable {

   private String nombreEven;

    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Evento>listaEventos = new ArrayList<>();

    public ArrayList<Cliente> getListaClientes(){return listaClientes;}
    public ArrayList<Evento> getListaEventos(){return listaEventos;}

    public SistemaTaquillera(){ super(); }

    public SistemaTaquillera(String nombreEven) { this.nombreEven = nombreEven; }

    public String getNombreEven() { return nombreEven; }

    public void setNombreEven(String nombreEven) { this.nombreEven = nombreEven; }


    //--CRUD-cliente------------------------------------------------------------------------------------------
    public Cliente nuevoCliente(Cliente cliente, String nombre, String id, String correo, String contrasenia)throws ClienteException {
        Cliente nuevoCliente = null;
        boolean clienteExiste = verificarClienteExiste(id);
        if (clienteExiste){
            throw  new ClienteException("El cliente con id "+id+" ya está registrado");
        }else{
            nuevoCliente = new Cliente();
            nuevoCliente.setNombre(cliente.getNombre());
            nuevoCliente.setId(cliente.getId());
            nuevoCliente.setCorreo(cliente.getCorreo());

            getListaClientes().add(nuevoCliente);
        }
        return nuevoCliente;
    }

    public void agregarCliente(Cliente nuevoCliente)throws ClienteException{
        getListaClientes().add(nuevoCliente);
    }

    public boolean verificarClienteExiste(String id)throws ClienteException{
        if (clienteExiste(id)){
            throw new ClienteException("El cliente con cédula "+id+" ya exite.");
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
        Cliente clienteActual = obtenerCLiente(id);
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

    public boolean eliminarCliente(String id)throws ClienteException{
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

    //--CRUD-Evento----------------------------------------------------------------------------------------------

    public Evento crearEvento(String nombreEvento, String nombreArtista, String codigoEvento, String ubiacionEvento, LocalDate fehcaEvento, TipoBoleto tipoBoleto)throws EventoException {
        Evento nuevoEvento = null;
        boolean eventoExiste = verificarEventoExiste(codigoEvento);
        if(eventoExiste){
            throw new EventoException("El evento con código "+codigoEvento+" ya exites.");

        }else{
            nuevoEvento = new Evento();
            nuevoEvento.setNombreEvento(nombreEvento);
            nuevoEvento.setNombreArtista(nombreArtista);
            nuevoEvento.setCodigoEvento(codigoEvento);
            nuevoEvento.setUbiacionEvento(ubiacionEvento);
            nuevoEvento.setFehcaEvento(fehcaEvento);
            nuevoEvento.setTipoBoleto(tipoBoleto);

            getListaEventos().add(nuevoEvento);

        }
        return nuevoEvento;
    }

    public void agregarEvento(Evento nuevoEvento)throws EventoException{
        getListaEventos().add(nuevoEvento);
    }

    public boolean verificarEventoExiste(String codigoEvento) throws EventoException{
        if(verificarEventoExiste(codigoEvento)){
            throw new EventoException("El evento con código: "+codigoEvento+" ya existe.");
        }else{
            return false;
        }
    }

    private boolean eventoExiste(String codigoEvento){
        boolean enventoEncontrado = false;
        for (Evento evento: listaEventos){
            if (evento.getCodigoEvento().equalsIgnoreCase(codigoEvento)){
                enventoEncontrado = true;
                break;
            }
        }
        return enventoEncontrado;
    }

    public boolean actualizarEnveto(String codigoEvento, Evento evento)throws EventoException{
        Evento eventoActual = obtenerEvento(codigoEvento);
        if (eventoActual == null){
            throw  new EventoException("El evento a actualizar no existe.");
        }else{
            eventoActual.setCodigoEvento(evento.getCodigoEvento());
            eventoActual.setNombreEvento(evento.getNombreEvento());
            eventoActual.setNombreArtista(evento.getNombreArtista());
            eventoActual.setUbiacionEvento(evento.getUbiacionEvento());
            eventoActual.setFehcaEvento(evento.getFehcaEvento());
            eventoActual.setTipoBoleto(evento.getTipoBoleto());
            return true;
        }
    }

    public Evento obtenerEvento(String codigoEvento){
        Evento eventoEncontrado = null;
        for (Evento evento: listaEventos){
            if (evento.getCodigoEvento().equalsIgnoreCase(codigoEvento)){
                eventoEncontrado = evento;
                break;
            }
        }
        return eventoEncontrado;
    }

    public boolean eliminarEvento(String codigoEvento)throws EventoException{
        Evento evento = null;
        boolean flagExiste = false;
        evento = obtenerEvento(codigoEvento);
        if (evento == null){
            throw new EventoException("El evento a eliminar no existe.");
        }else{
            getListaEventos().remove(evento);
            flagExiste = true;
        }
        return flagExiste;
    }

}
