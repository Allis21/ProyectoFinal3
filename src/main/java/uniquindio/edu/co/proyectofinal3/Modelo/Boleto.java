package uniquindio.edu.co.proyectofinal3.Modelo;

import java.io.Serializable;

public class Boleto implements Serializable {
    String nombreEvento;
    String codigoBoleto;
    String codigoEvento;
    TipoBoleto tipoBoleto;

    public Boleto(String nombreEvento,String codigoBoleto, String codigoEvento, TipoBoleto tipoBoleto) {
        this.nombreEvento = nombreEvento;
        this.codigoBoleto = codigoBoleto;
        this.codigoEvento = codigoEvento;
        this.tipoBoleto = tipoBoleto;
    }

    public String getNombreEvento() { return nombreEvento; }

    public void setNombreEvento(String nombreEvento) { this.nombreEvento = nombreEvento; }

    public String getCodigoBoleto() { return codigoBoleto; }

    public void setCodigoBoleto(String codigoBoleto) { this.codigoBoleto = codigoBoleto; }

    public String getCodigoEvento() { return codigoEvento; }

    public void setCodigoEvento(String codigoEvento) { this.codigoEvento = codigoEvento; }

    public TipoBoleto getTipoBoleto() { return tipoBoleto; }

    public void setTipoBoleto(TipoBoleto tipoBoleto) { this.tipoBoleto = tipoBoleto; }

    //DEVUELVE UNA CADENA QUE CONTIENE INFORMACIÓN DEL BOLETO
    @Override
    public String toString() {
        return "Boleto --- " +
                "Nombre del evento: '" + nombreEvento + '\'' +
                ", código del evento: '" + codigoEvento + '\'' +
                ", código del boleto: '" + codigoBoleto + '\''+
                "clase del boleto: '" + tipoBoleto ;
    }

}
