package uniquindio.edu.co.proyectofinal3.Modelo;

import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Administrador {

   private String idAdmin;
   private String conAdmin;

   private SistemaTaquillera sistemaTaquillera;

    public Administrador(String idAdmin, String conAdmin) {
        this.idAdmin = idAdmin;
        this.conAdmin = conAdmin;
    }
    public void abrirTaquilla(LocalTime horaApertura){
        sistemaTaquillera.abrirTaquilla(horaApertura);
    }

    public String getIdAdmin() { return idAdmin; }

    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }

    public String getConAdmin() { return conAdmin; }

    public void setConAdmin(String conAdmin) { this.conAdmin = conAdmin; }
}




