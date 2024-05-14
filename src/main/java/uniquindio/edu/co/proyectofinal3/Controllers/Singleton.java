package uniquindio.edu.co.proyectofinal3.Controllers;

import uniquindio.edu.co.proyectofinal3.Exepciones.ClienteException;
import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;
import uniquindio.edu.co.proyectofinal3.Modelo.Cliente;
import uniquindio.edu.co.proyectofinal3.Modelo.Evento;
import uniquindio.edu.co.proyectofinal3.Modelo.SistemaTaquillera;

public class Singleton {
    private Singleton(){
        sistemaTaquillera = new SistemaTaquillera();
    }
    SistemaTaquillera sistemaTaquillera;

    /**
     * Cliente
     */
    public void crearCliente(Cliente cliente)throws ClienteException {
         sistemaTaquillera.registrarCliente(cliente);
    }
    public boolean verificarClienteExistente(String id) throws ClienteException{
        return  sistemaTaquillera.clienteExistente(id);
    }
    public void actualizarCliente(Cliente cliente) throws ClienteException {
        sistemaTaquillera.actualizarCliente(cliente);
    }
    
    /**
     *Evento
     */

    public void crearEvento(Evento evento)throws EventoException{
        sistemaTaquillera.registarEvento(evento);
    }
    public boolean verificarEventoExistente(String codigo) throws EventoException{
        return sistemaTaquillera.eventoExistente(codigo);
    }
    public void actualizarEvento(Evento evento) throws EventoException{
        sistemaTaquillera.actualizarEvento(evento);

    }


}
