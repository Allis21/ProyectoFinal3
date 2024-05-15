package uniquindio.edu.co.proyectofinal3.Modelo;

public enum AppController {
    INSTANCE;
    private Evento evento;

    AppController() {
        evento = new Evento();
    }
    public Evento getEvento(){
        return evento;
    }
}
