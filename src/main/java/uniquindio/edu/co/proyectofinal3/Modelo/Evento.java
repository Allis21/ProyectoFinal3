package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evento implements Serializable {

    private String nombreEvento;
    private String nombreArtista;
    private String codigoEvento;
    private Localidad localidad;
    private LocalDate fechaEvento;
    private int capacidadEvento;
    private int boletosOro, boletosPlata, boletosBronce;
    private float valorOro, valorPlata, valorBronce;
    private int boletosDisponibles;

    public Evento(){ super(); };

    public Evento(int boletosDisponibles) {
        this.boletosDisponibles = boletosDisponibles;
    }

    public Evento(String nombreEvento, String nombreArtista, String codigoEvento, Localidad localidad, LocalDate fechaEvento, int boletosOro, int boletosPlata, int boletosBronce, float valorOro, float valorPlata, float valorBronce, int capacidadEvento, int boletosDisponibles) {
        this.nombreEvento = nombreEvento;
        this.nombreArtista = nombreArtista;
        this.codigoEvento = codigoEvento;
        this.localidad = localidad;
        this.fechaEvento = fechaEvento;
        this.boletosOro = boletosOro;
        this.boletosPlata = boletosPlata;
        this.boletosBronce = boletosBronce;
        this.valorOro = valorOro;
        this.valorPlata = valorPlata;
        this.valorBronce = valorBronce;
        this.capacidadEvento = capacidadEvento;
        this.boletosDisponibles = boletosDisponibles;
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

    public Localidad getLocalidad() {
        return localidad;
    }

    public void setLocalidad(Localidad localidad) {
        this.localidad = localidad;
    }

    public LocalDate getFechaEvento() { return fechaEvento; }

    public void setFechaEvento(LocalDate fechaEvento) { this.fechaEvento = fechaEvento; }

    public int getCapacidadEvento() {return capacidadEvento;}

    public void setCapacidadEvento(int capacidadEvento) { this.capacidadEvento = capacidadEvento;}

    public int getBoletosOro() {
        return boletosOro;
    }

    public void setBoletosOro(int boletosOro) {
        this.boletosOro = boletosOro;
    }

    public int getBoletosPlata() {
        return boletosPlata;
    }

    public void setBoletosPlata(int boletosPlata) {
        this.boletosPlata = boletosPlata;
    }

    public int getBoletosBronce() {
        return boletosBronce;
    }

    public void setBoletosBronce(int boletosBronce) {
        this.boletosBronce = boletosBronce;
    }

    public float getValorOro() {
        return valorOro;
    }

    public void setValorOro(float valorOro) {
        this.valorOro = valorOro;
    }

    public float getValorPlata() {
        return valorPlata;
    }

    public void setValorPlata(float valorPlata) {
        this.valorPlata = valorPlata;
    }

    public float getValorBronce() {
        return valorBronce;
    }

    public void setValorBronce(float valorBronce) {
        this.valorBronce = valorBronce;
    }

    //DEVUELVE UNA CADENA QUE CONTIENE INFORMACIÓN DEL EVENTO
    @Override
    public String toString(){
        return "Evento:" +
                "nombre del evento='" + nombreEvento + '\'' +
                "codigo del evento='" + codigoEvento + '\'' ;
    }

    public ArrayList<Integer> distribuirBoletos() {
        int aforoTotal = capacidadEvento;
        ArrayList<Integer> boletas= new ArrayList<>();
        int boletosCobre = (int) (aforoTotal * 0.60);
        int boletosPlata = (int) (aforoTotal * 0.30);
        int boletosOro = (int) (aforoTotal * 0.10);


        int totalBoletosDistribuidos = boletosCobre + boletosPlata + boletosOro;


        int diferencia = aforoTotal - totalBoletosDistribuidos;


        if (diferencia > 0) {

            if (boletosOro <= boletosPlata && boletosOro <= boletosCobre) {
                boletosOro += diferencia;
            } else if (boletosPlata <= boletosCobre) {
                boletosPlata += diferencia;
            } else {
                boletosCobre += diferencia;
            }
        } else if (diferencia < 0) {

            diferencia = Math.abs(diferencia);
            if (boletosCobre >= boletosPlata && boletosCobre >= boletosOro) {
                boletosCobre -= diferencia;
            } else if (boletosPlata >= boletosOro) {
                boletosPlata -= diferencia;
            } else {
                boletosOro -= diferencia;
            }
        }
        boletas.add(boletosCobre);
        boletas.add(boletosPlata);
        boletas.add(boletosOro);
        return boletas;
    }
}
