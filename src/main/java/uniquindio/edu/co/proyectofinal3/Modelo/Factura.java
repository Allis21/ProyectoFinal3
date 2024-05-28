package uniquindio.edu.co.proyectofinal3.Modelo;

import java.util.ArrayList;

public class Factura{
    private int cantOro, cantPlanta, cantBronce;
    private int valorFactura;

    public Factura(int cantOro, int cantPlanta, int cantBronce, int valorFactura) {
        this.cantOro = cantOro;
        this.cantPlanta = cantPlanta;
        this.cantBronce = cantBronce;
        this.valorFactura = valorFactura;
    }

    public Factura() {
    }

    public int getCantOro() {
        return cantOro;
    }

    public void setCantOro(int cantOro) {
        this.cantOro = cantOro;
    }

    public int getCantPlanta() {
        return cantPlanta;
    }

    public void setCantPlanta(int cantPlanta) {
        this.cantPlanta = cantPlanta;
    }

    public int getCantBronce() {
        return cantBronce;
    }

    public void setCantBronce(int cantBronce) {
        this.cantBronce = cantBronce;
    }

    public int getValorFactura() {
        return valorFactura;
    }

    public void setValorFactura(int valorFactura) {
        this.valorFactura = valorFactura;
    }
}
