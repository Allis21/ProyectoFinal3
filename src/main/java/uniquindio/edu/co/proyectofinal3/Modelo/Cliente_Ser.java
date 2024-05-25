package uniquindio.edu.co.proyectofinal3.Modelo;

public class Cliente_Ser {
    public static void main(String[] args) {
        Cliente cliente1 = new Cliente("Allis", "123", "allis@gmail.com", "Flor");
        cliente1.conectarConServidor();
        Cliente cliente2 = new Cliente("Allis", "123", "allis@gmail.com", "Flor");
        cliente2.conectarConServidor();
        Cliente cliente3 = new Cliente("Allis", "123", "allis@gmail.com", "Flor");
        cliente3.conectarConServidor();
        Cliente cliente4 = new Cliente("Allis", "123", "allis@gmail.com", "Flor");
        cliente4.conectarConServidor();

    }
}
