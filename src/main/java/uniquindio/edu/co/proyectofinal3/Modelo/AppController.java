package uniquindio.edu.co.proyectofinal3.Modelo;

public enum AppController {
    INSTANCE;
    private SistemaTaquillera sistemaTaquillera;

    AppController() {
        sistemaTaquillera = new SistemaTaquillera();
    }
    public SistemaTaquillera getSistemaTaquillera() {
        return sistemaTaquillera;
    }
}
