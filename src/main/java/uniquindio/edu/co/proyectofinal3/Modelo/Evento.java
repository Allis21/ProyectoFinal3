package uniquindio.edu.co.proyectofinal3.Modelo;

import uniquindio.edu.co.proyectofinal3.Exepciones.EventoException;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Evento implements Serializable {

    private String nombreEvento;
    private String nombreArtista;
    private String codigoEvento;
    private String ubiacionEvento;
    private LocalDate fehcaEvento;
    private TipoBoleto tipoBoleto;
    private int capacidadEvento;
    private int boletosDisponibles;


    public Evento(){ super(); };

    public Evento(int boletosDisponibles) {
        this.boletosDisponibles = boletosDisponibles;
    }

    public Evento(String nombreEvento, String nombreArtista, String codigoEvento, TipoBoleto tipoBoleto, String ubiacionEvento, LocalDate fehcaEvento, int capacidadEvento){
        this.nombreEvento = nombreEvento;
        this.nombreArtista = nombreArtista;
        this.codigoEvento = codigoEvento;
        this.ubiacionEvento = ubiacionEvento;
        this.fehcaEvento = fehcaEvento;
        this.capacidadEvento = capacidadEvento;
        this.tipoBoleto = tipoBoleto;
    }

    public String getNombreEvento() { return nombreEvento; }
    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }

    public String getNombreArtista() { return nombreArtista; }

    public void setNombreArtista(String nombreArtista) { this.nombreArtista = nombreArtista; }

    public String getCodigoEvento() { return codigoEvento; }

    public int getBoletosDisponibles() {
        return boletosDisponibles;
    }

    public void setBoletosDisponibles(int boletosDisponibles) {
        this.boletosDisponibles = boletosDisponibles;
    }

    public void setCodigoEvento(String codigoEvento) { this.codigoEvento = codigoEvento; }

    public String getUbiacionEvento() { return ubiacionEvento; }

    public void setUbiacionEvento(String ubiacionEvento) { this.ubiacionEvento = ubiacionEvento; }

    public LocalDate getFehcaEvento() { return fehcaEvento; }

    public void setFehcaEvento(LocalDate fehcaEvento) { this.fehcaEvento = fehcaEvento; }

    public int getCapacidadEvento() {return capacidadEvento;}

    public void setCapacidadEvento(int capacidadEvento) { this.capacidadEvento = capacidadEvento;}

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
}
