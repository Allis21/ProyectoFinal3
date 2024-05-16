package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SistemaTaquillera implements Serializable {

    private String nombreEven;
    private boolean taquillaAbierta = false;
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Evento>listaEventos = new ArrayList<>();

    public ArrayList<Cliente> getListaClientes(){return listaClientes;}
    public ArrayList<Evento> getListaEventos(){return listaEventos;}

    public SistemaTaquillera(){ super(); }
    public SistemaTaquillera(String nombreEven, boolean taquillaAbierta) {
        this.nombreEven = nombreEven;
        this.taquillaAbierta = taquillaAbierta;
    }

    public boolean isTaquillaAbierta() {
        return taquillaAbierta;
    }

    public void setTaquillaAbierta(boolean taquillaAbierta) {
        this.taquillaAbierta = taquillaAbierta;
    }

    public String getNombreEven() { return nombreEven; }

    public void setNombreEven(String nombreEven) { this.nombreEven = nombreEven; }

    // Apertura de la taquilla virtual a una hora específica
    public void abrirTaquilla(LocalTime horaApertura) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        long delayApertura = LocalTime.now().until(horaApertura, ChronoUnit.MINUTES);

        scheduler.schedule(() -> taquillaAbierta = true, delayApertura, TimeUnit.MINUTES);
    }
    // El cierre de taquilla debe ser una hora antes del evento
    public void cerrarTaquilla(LocalTime horaEvento) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        long delayCierre = LocalTime.now().until(horaEvento.minusHours(1), ChronoUnit.MINUTES);

        scheduler.schedule(() -> taquillaAbierta = false, delayCierre, TimeUnit.MINUTES);
    }

    //--CRUD-cliente------------------------------------------------------------------------------------------
    public Cliente registrarCliente(Cliente cliente)throws ClienteException {
        Cliente nuevoCliente = null;
        boolean clienteExiste = verificarClienteExiste(cliente.getId());
        if (clienteExiste){
            throw  new ClienteException("El cliente con id "+cliente.getId()+" ya está registrado");
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
        if (clienteExistente(id)){
            throw new ClienteException("El cliente con cédula "+id+" ya exite.");
        }else{
            return false;
        }
    }

    public boolean clienteExistente(String id){
        boolean clienteEncontrado = false;
        for(Cliente cliente: listaClientes){
            if(cliente.getId().equalsIgnoreCase(id)){
                clienteEncontrado = true;
                break;
            }
        }
        return clienteEncontrado;
    }

    public boolean actualizarCliente(Cliente cliente)throws ClienteException{
        Cliente clienteActual = obtenerCLiente(cliente.getId());
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

    public Evento registarEvento(Evento evento )throws EventoException {
        Evento nuevoEvento = null;
        boolean eventoExiste = verificarEventoExiste(evento.getCodigoEvento());
        if(eventoExiste){
            throw new EventoException("El evento con código "+evento.getCodigoEvento()+" ya exites.");

        }else{
            nuevoEvento = new Evento();
            nuevoEvento.setNombreEvento(evento.getNombreEvento());
            nuevoEvento.setNombreArtista(evento.getNombreArtista());
            nuevoEvento.setCodigoEvento(evento.getCodigoEvento());
            nuevoEvento.setUbiacionEvento(evento.getUbiacionEvento());
            nuevoEvento.setFehcaEvento(evento.getFehcaEvento());
            nuevoEvento.setTipoBoleto(evento.getTipoBoleto());

            getListaEventos().add(nuevoEvento);

            LocalTime horaEvento = nuevoEvento.getFehcaEvento().atStartOfDay().plusHours(1).toLocalTime();
            cerrarTaquilla(horaEvento);

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

    public boolean eventoExistente(String codigoEvento){
        boolean enventoEncontrado = false;
        for (Evento evento: listaEventos){
            if (evento.getCodigoEvento().equalsIgnoreCase(codigoEvento)){
                enventoEncontrado = true;
                break;
            }
        }
        return enventoEncontrado;
    }

    public boolean actualizarEvento( Evento evento)throws EventoException{
        Evento eventoActual = obtenerEvento(evento.getCodigoEvento());
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
