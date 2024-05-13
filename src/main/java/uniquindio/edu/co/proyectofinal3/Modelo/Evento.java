package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Evento implements Serializable {

    private String nombreEvento;
    private String codigoEvento;
    private String ubiacionEvento;
    private LocalDate fehcaEvento;
    private TipoBoleto tipoBoleto;
    private ArrayList<Evento>listaEventos = new ArrayList<>();
    public ArrayList<Evento> getListaEventos(){return listaEventos;}
    public Evento(){ super(); };

    public Evento(String nombreEvento, String codigoEvento, TipoBoleto tipoBoleto, String ubiacionEvento, LocalDate fehcaEvento) {
        this.nombreEvento = nombreEvento;
        this.codigoEvento = codigoEvento;
        this.ubiacionEvento = ubiacionEvento;
        this.fehcaEvento = fehcaEvento;
        this.tipoBoleto = tipoBoleto;
    }

    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }

    public String getCodigoEvento() { return codigoEvento; }
    public void setCodigoEvento(String codigoEvento) { this.codigoEvento = codigoEvento; }

    public String getUbiacionEvento() { return ubiacionEvento; }

    public void setUbiacionEvento(String ubiacionEvento) { this.ubiacionEvento = ubiacionEvento; }

    public LocalDate getFehcaEvento() { return fehcaEvento; }

    public void setFehcaEvento(LocalDate fehcaEvento) { this.fehcaEvento = fehcaEvento; }

    public TipoBoleto getTipoBoleto() { return tipoBoleto; }
    public void setTipoBoleto(TipoBoleto tipoBoleto) { this.tipoBoleto = tipoBoleto; }

    //DEVUELVE UNA CADENA QUE CONTIENE INFORMACIÓN DEL EVENTO
    @Override
    public String toString(){
        return "Evento:" +
                "nombre del evento='" + nombreEvento + '\'' +
                "codigo del evento='" + codigoEvento + '\'' +
                ", tipoBoleto=" + tipoBoleto;
    }

    //--CRUD-Evento----------------------------------------------------------------------------------------------

    public Evento crearEvento(String nombreEvento, String codigoEvento, String ubiacionEvento, LocalDate fehcaEvento, TipoBoleto tipoBoleto)throws EventoException {
        Evento nuevoEvento = null;
        boolean eventoExiste = verificarEventoExiste(codigoEvento);
        if(eventoExiste){
            throw new EventoException("El evento con código "+codigoEvento+" ya exites.");

        }else{
            nuevoEvento = new Evento();
            nuevoEvento.setNombreEvento(nombreEvento);
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

    public boolean actualizarEnveto(String codigoEvento, Evento evento)throws EcentoException{
        Evento eventoActual = obtenerEvento(codigoEvento);
        if (envetoActual == null){
            throw  new EventoException("El evento a actualizar no existe.");
        }else{
            eventoActual.setCodigoEvento(evento.getCodigoEvento());
            eventoActual.setNombreEvento(evento.getNombreEvento());
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
