package uniquindio.edu.co.proyectofinal3.Archivos;

import java.io.*;

public class ArchivoUtils {
    public static void serializarObjeto(String ruta, Object objeto) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ruta));
        oos.writeObject(objeto);
        oos.close();
    }
    public static Object deserializarObjeto(String ruta) throws Exception{
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ruta));
        Object objeto = ois.readObject();
        ois.close();
        return objeto;
    }



}
