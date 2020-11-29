package calcularcif;
import java.util.*;
/**
 * @version 1.0, 29/11/2020.
 * @author Fran
 */
public class CalcularCif {
    
    // Objeto de tipo lectura.
    private static final Scanner lectura = new Scanner (System.in);
    
    // Almacena la cadena introducida por el usuario
    private static String cadenaCIF ="";
    
    // Almacena un objeto de tipo CIF.
    private static Cif nuevoCif;
    
    public static void main(String[] args) {
        
        // Pedimos el CIF por teclado
        do {            
            System.out.println("Introduce los nueve ´caracteres del CIF:");
            cadenaCIF=lectura.nextLine();
            // Pasamos la cadena a mayúsculas
            cadenaCIF = cadenaCIF.toUpperCase();
        } while (cadenaCIF.isEmpty() || cadenaCIF.length()!=9);
        
        // Creamos un nuevo objeto CIF
        nuevoCif = new Cif(cadenaCIF);
        
        // Comprobamos que el CIF sea correcto.
        if(nuevoCif.getEstablecerDigitoControl()){
            System.out.println("El CIF es correcto");
        }
        else{
            System.out.println("El CIF NO es incorrecto");
        }
        // Cerramos el objeto lectura.
        lectura.close();
    }
}