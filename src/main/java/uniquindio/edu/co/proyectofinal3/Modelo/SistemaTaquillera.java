package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;
import uniquindio.edu.co.proyectofinal3.Exepciones.LocalidadException;

import java.io.Serializable;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
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

    public void iniciarCompraBoletos() {
        // Crear instancias de Cliente y Evento
        Cliente cliente1 = new Cliente();
        cliente1.setNombre("Cliente 1");

        Cliente cliente2 = new Cliente();
        cliente2.setNombre("Cliente 2");

        Cliente cliente3 = new Cliente();
        cliente3.setNombre("Cliente 3");

        Cliente cliente4 = new Cliente();
        cliente4.setNombre("Cliente 4");

        Evento evento1 = new Evento();
        evento1.setNombreEvento("Evento 1");
        evento1.setBoletosDisponibles(2); // 10 boletos disponibles

        // Crear hilos para cada cliente intentando comprar boletos
        Thread hilo1 = new Thread(() -> {
            try {
                comprarBoletos(cliente1, evento1, 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread hilo2 = new Thread(() -> {
            try {
                comprarBoletos(cliente2, evento1, 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread hilo3 = new Thread(() -> {
            try {
                comprarBoletos(cliente3, evento1, 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        Thread hilo4 = new Thread(() -> {
            try {
                comprarBoletos(cliente4, evento1, 2);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();
        hilo3.start();
        hilo4.start();
    }
    //Utilice hilos para que solo 3 personas puedan acceder simultáneamente a comprar sus
    //boletas mientras los demás siguen en espera.
    public void comprarBoletos(Cliente cliente, Evento evento, int cantidadBoletos) throws InterruptedException {
            semaforo.acquire();
            if (taquillaAbierta) {
                synchronized(this) {
                    if (evento.getBoletosDisponibles() >= cantidadBoletos) {
                        Thread.sleep(4000);
                        evento.setBoletosDisponibles(evento.getBoletosDisponibles() - cantidadBoletos);
                        System.out.println("Compra exitosa de " + cantidadBoletos + " boletos para el evento " + evento.getNombreEvento() + " por parte del cliente " + cliente.getNombre());
                    } else {
                        System.out.println("No hay suficientes boletos disponibles para el evento " + evento.getNombreEvento());
                    }
                }
            } else {
                System.out.println("La taquilla está cerrada");
            }
            semaforo.release();
    }

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
        boolean clienteExiste = clienteExistente(cliente.getId());
        if(clienteExiste) {
            throw new ClienteException("El cliente con id "+cliente.getId()+" ya está registrado");
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

    //--CRUD-Localidades----------------------------------------------------------------------------------------------

    public Localidad registrarLocalidad(Localidad localidad) throws LocalidadException {
        Localidad nuevalocalidad= null;
        boolean localidadExistente = LocalidadExistente(localidad.getDireccion());
        if(localidadExistente){
            throw new LocalidadException("La locacion con la direccion "+localidad.getDireccion()+" ya existe");

        }else {
            nuevalocalidad = new Localidad();
            nuevalocalidad.setPais(localidad.getPais());
            nuevalocalidad.setPais(localidad.getPais());
            nuevalocalidad.setDireccion(localidad.getDireccion());

            getListaLocalidades().add(nuevalocalidad);
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