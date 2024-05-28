package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;
import uniquindio.edu.co.proyectofinal3.Exepciones.LocalidadException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;


public class SistemaTaquillera implements Serializable {
    private Administrador administrador;
    private String nombreEven;
    private boolean taquillaAbierta = false;
    private Semaphore semaforo = new Semaphore(3); //Solo 3 clientes pueden comprar boletos al mismo tiempo
    private ArrayList<Cliente> listaClientes = new ArrayList<>();
    private ArrayList<Evento>listaEventos = new ArrayList<>();
    private ArrayList<Localidad>listaLocalidades= new ArrayList<>();
    public ArrayList<Cliente> getListaClientes(){return listaClientes;}
    public ArrayList<Evento> getListaEventos(){return listaEventos;}
    public ArrayList<Localidad>getListaLocalidades(){return listaLocalidades;}
    public SistemaTaquillera() {
        this.administrador = new Administrador("777", "abc");
        Cliente cliente = new Cliente("Juanito Alimaña", "111", "PERROHPTA", "mera");
        getListaClientes().add(cliente);
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public String getNombreEven() {
        return nombreEven;
    }

    public void setNombreEven(String nombreEven) {
        this.nombreEven = nombreEven;
    }

    public boolean isTaquillaAbierta() {
        return taquillaAbierta;
    }

    public void setTaquillaAbierta(boolean taquillaAbierta) {
        this.taquillaAbierta = taquillaAbierta;
    }

    //Visualización de disponibilidad de boletas para cada evento.
    public String mostrarDisponibilidadBoletos() {
        StringBuilder disponibilidad = new StringBuilder();
        for (Evento evento : listaEventos) {
            disponibilidad.append("Evento: ").append(evento.getNombreEvento())
                    .append(", Boletos disponibles: ").append(evento.getBoletosDisponibles()).append("\n");
        }
        return disponibilidad.toString();
    }
    //Utilice hilos para que solo 3 personas puedan acceder simultáneamente a comprar sus
    //boletas mientras los demás siguen en espera.
    public String comprarBoletos(Cliente cliente, Evento evento, int cantidadBoletos) throws InterruptedException {
        semaforo.acquire();
        StringBuilder resultado = new StringBuilder();
        if (taquillaAbierta) {
            synchronized(this) {
                if (evento.getBoletosDisponibles() >= cantidadBoletos) {
                    Thread.sleep(4000);
                    evento.setBoletosDisponibles(evento.getBoletosDisponibles() - cantidadBoletos);
                    resultado.append("Compra exitosa de ").append(cantidadBoletos).append(" boletos para el evento ").append(evento.getNombreEvento()).append(" por parte del cliente ").append(cliente.getNombre());
                } else {
                    resultado.append("No hay suficientes boletos disponibles para el evento ").append(evento.getNombreEvento());
                }
            }
        } else {
            resultado.append("La taquilla está cerrada");
        }
        semaforo.release();
        return resultado.toString();
    }

    // Apertura de la taquilla virtual a una hora específica
    public void abrirTaquilla(LocalTime horaApertura) {

        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        long delayApertura = LocalTime.now().until(horaApertura, ChronoUnit.MINUTES);

        scheduler.schedule(() -> taquillaAbierta = true, delayApertura, TimeUnit.MINUTES);
    }

    // Cierre de la taquilla una hora antes del evento
    public void cerrarTaquilla(LocalTime horaEvento) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        long delayCierre = LocalTime.now().until(horaEvento.minusHours(1), ChronoUnit.MINUTES);

        scheduler.schedule(() -> taquillaAbierta = false, delayCierre, TimeUnit.MINUTES);
    }
    
    //--CRUD-cliente------------------------------------------------------------------------------------------
    public Cliente registrarCliente(Cliente cliente) throws ClienteException {
        Cliente nuevoCliente = null;
        boolean clienteExiste = clienteExistente(cliente.getId());
        if(clienteExiste) {
            throw new ClienteException("El cliente con id "+cliente.getId()+" ya está registrado");
        }else{
            if(cliente.getNombre()== null || cliente.getNombre().isBlank()){
                throw new ClienteException("No ingreso la el nombre");
            }
            if (cliente.getId()== null || cliente.getId().isBlank()){
                throw new ClienteException("No ingresó la cedula");
            }
            if(cliente.getCorreo()==null || cliente.getCorreo().isBlank()){
                throw new ClienteException("No ingreso el correo");
            }
            if (cliente.getContrasenia()== null || cliente.getContrasenia().isBlank()){
                throw new ClienteException("No ingreso la contraseña");
            }
            nuevoCliente = new Cliente();
            nuevoCliente.setNombre(cliente.getNombre());
            nuevoCliente.setId(cliente.getId());
            nuevoCliente.setCorreo(cliente.getCorreo());

            getListaClientes().add(nuevoCliente);
            try {
                FileWriter fw = new FileWriter(new File("src/main/resources/persistencia/registro.txt"), true);
                Formatter ft = new Formatter(fw);
                ft.format(cliente.getNombre() + ";" + cliente.getId()
                        + ";" + cliente.getCorreo());
                ft.close();
            } catch (IOException e) {

                throw new ClienteException("El cliente no se pudo guardar");
            }
        }
        return nuevoCliente;
    }

    public void agregarCliente(Cliente nuevoCliente)throws ClienteException{
        getListaClientes().add(nuevoCliente);
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
            throw new EventoException("El evento con código "+evento.getCodigoEvento()+" ya existe.");

        }else{

            if(evento.getNombreEvento()== null || evento.getNombreEvento().isBlank()){
                throw new EventoException("No ingreso la cedula");
            }
            if (evento.getCodigoEvento()== null || evento.getCodigoEvento().isBlank()){
                throw new EventoException("No ingreso el codigo");
            }
            if(evento.getFechaEvento()== null ){
                throw new EventoException("No ingreso la fecha");
            }
            if(evento.getNombreArtista()== null || evento.getNombreArtista().isBlank()){
                throw  new EventoException("No ingreso el nombre del Artista");
            }
            if(evento.getLocalidad()== null ){
                throw new EventoException("No ingreso localidad");
            }
            nuevoEvento = new Evento();
            nuevoEvento.setNombreEvento(evento.getNombreEvento());
            nuevoEvento.setNombreArtista(evento.getNombreArtista());
            nuevoEvento.setCodigoEvento(evento.getCodigoEvento());
            nuevoEvento.setLocalidad(evento.getLocalidad());
            nuevoEvento.setFechaEvento(evento.getFechaEvento());
            nuevoEvento.setValorOro(evento.getValorOro());
            nuevoEvento.setValorPlata(evento.getValorPlata());
            nuevoEvento.setValorBronce(evento.getValorBronce());
            getListaEventos().add(nuevoEvento);

            LocalTime horaEvento = nuevoEvento.getFechaEvento().atStartOfDay().plusHours(1).toLocalTime();
            cerrarTaquilla(horaEvento);

            try {
                FileWriter fw= new FileWriter(new File("src/main/resources/Persistencia/eventos.txt"),true);
                Formatter ft= new Formatter(fw);
                ft.format(evento.getNombreEvento()+";"+evento.getNombreEvento()+";"+evento.getNombreArtista()
                        +";"+evento.getCodigoEvento()+";"+evento.getLocalidad()+";"+evento.getFechaEvento()
                        +";"+evento.getBoletosOro()+";"+evento.getBoletosPlata()+";"+evento.getBoletosBronce());
                ft.close();
            }catch (IOException e){
                throw new EventoException("El evento no se pudo guardar");
            }

        }
        return nuevoEvento;
    }

    public void agregarEvento(Evento nuevoEvento)throws EventoException{
        getListaEventos().add(nuevoEvento);
    }

    public boolean verificarEventoExiste(String codigoEvento) throws EventoException{
        if(eventoExistente(codigoEvento)){
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

    public void actualizarEvento(Evento evento)throws EventoException{
        Evento eventoActual = obtenerEvento(evento.getCodigoEvento());
        if (eventoActual == null){
            throw  new EventoException("El evento a actualizar no existe.");
        }else{
            eventoActual.setCodigoEvento(evento.getCodigoEvento());
            eventoActual.setNombreEvento(evento.getNombreEvento());
            eventoActual.setNombreArtista(evento.getNombreArtista());
            eventoActual.setLocalidad(evento.getLocalidad());
            eventoActual.setFechaEvento(evento.getFechaEvento());
            eventoActual.setValorOro(evento.getValorOro());
            eventoActual.setValorPlata(evento.getValorPlata());
            eventoActual.setValorBronce(evento.getValorBronce());
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

    //--CRUD-Localidades----------------------------------------------------------------------------------------------

    public Localidad registrarLocalidad(Localidad localidad) throws LocalidadException {
        Localidad nuevalocalidad= null;
        boolean localidadExistente = LocalidadExistente(localidad.getDireccion());
        if(localidadExistente){
            throw new LocalidadException("La locacion con la direccion "+localidad.getDireccion()+" ya existe");

        }else {

            if(localidad.getPais()==null || localidad.getPais().isBlank()){
                throw new LocalidadException("No se ingresó el país");
            }
            if(localidad.getCiudad()== null || localidad.getCiudad().isBlank()){
                throw new LocalidadException("No se ingresó la ciudad");
            }
            if(localidad.getDireccion()== null || localidad.getDireccion().isBlank()){
                throw new LocalidadException("No se ingresó dirección");
            }

            nuevalocalidad = new Localidad();
            nuevalocalidad.setPais(localidad.getPais());
            nuevalocalidad.setPais(localidad.getPais());
            nuevalocalidad.setDireccion(localidad.getDireccion());

            getListaLocalidades().add(nuevalocalidad);

            try {
                FileWriter fw = new FileWriter(new File("src/main/resources/Persistencia/localidades.txt"), true);
                Formatter ft = new Formatter(fw);
                ft.format(localidad.getPais()
                        +";"+localidad.getCiudad()+
                        ";"+localidad.getDireccion());
                ft.close();

            }catch (IOException e){
                throw  new LocalidadException("La locación No se pudo registrar ");

            }
        }
        return nuevalocalidad;

    }
    public void agregarLocalidad(Localidad nuevaLocalidad){
        getListaLocalidades().add(nuevaLocalidad);
    }

    private boolean LocalidadExistente(String direccion) {
        boolean localidadEncontrada = false;
        for(Localidad localidad: listaLocalidades){
            if(localidad.getDireccion().equalsIgnoreCase(direccion)){
                localidadEncontrada=true;
                break;
            }
        }
        return localidadEncontrada;
    }

    public boolean actualizarLocalidad(Localidad localidad) throws EventoException, LocalidadException {
        Localidad nuevaLocalidad = obtenerLocalidad(localidad.getDireccion());
        if(nuevaLocalidad==null){
            throw  new LocalidadException("El evento a actulizar no existe");
        }else {
            nuevaLocalidad.setPais(localidad.getPais());
            nuevaLocalidad.setCiudad(localidad.getCiudad());
            nuevaLocalidad.setDireccion(localidad.getDireccion());
            return true;
        }
    }

    private Localidad obtenerLocalidad(String direccion) {
        Localidad localidadEncontrada = null;
        for (Localidad localidad: listaLocalidades){
            if(localidad.getDireccion().equalsIgnoreCase(direccion)){
                localidadEncontrada = localidad;
            }
        }
        return localidadEncontrada;
    }

    public  boolean eliminarLocalidad (String direccion) throws LocalidadException {
        Localidad localidad= null;
        boolean flagExiste= false;
        localidad = obtenerLocalidad(direccion);
        if (localidad == null){
            throw  new LocalidadException("No existe la localidad");

        }else {
            getListaLocalidades().remove(localidad);
            flagExiste= true;
        }
        return flagExiste;
    }
}