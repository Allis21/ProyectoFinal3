package uniquindio.edu.co.proyectofinal3.Controllers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

public class Propiedades {

    private static Propiedades instance;


    private String idioma;


    private ResourceBundle bundle;

    public static Propiedades getInstance(){
        if(instance==null){
            instance=new Propiedades();
        }
        return instance;
    }

    private Propiedades(){
        idioma = leerIdioma();
        bundle= ResourceBundle.getBundle("Propiedades.propiedades", new Locale(idioma) );
    }

    public String leerIdioma(){
        try {
            Properties ppr = new Properties();
            FileInputStream fos = new FileInputStream("src/main/resources/Propiedades/idioma.properties");
            ppr.load(fos);
            idioma = ppr.getProperty("idioma");
            fos.close();
            return idioma;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public void escribirIdioma(String idioma){
        try {
            this.idioma = idioma;
            this.bundle = ResourceBundle.getBundle("propiedades", new Locale(idioma));
            Properties ppr = new Properties();
            FileOutputStream fos = new FileOutputStream("Propiedades/idioma.properties");
            ppr.setProperty("idioma", idioma);
            ppr.store(fos,"");
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public String getIdioma() {
        return idioma;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }
}
