package uniquindio.edu.co.proyectofinal3.Modelo;

import java.time.LocalTime;


public class Administrador {

   private String idAdmin;
   private String conAdmin;

    public Administrador(String idAdmin, String conAdmin) {
        this.idAdmin = idAdmin;
        this.conAdmin = conAdmin;
    }

    public String getIdAdmin() { return idAdmin; }

    public void setIdAdmin(String idAdmin) { this.idAdmin = idAdmin; }

    public String getConAdmin() { return conAdmin; }

    public void setConAdmin(String conAdmin) { this.conAdmin = conAdmin; }
}




