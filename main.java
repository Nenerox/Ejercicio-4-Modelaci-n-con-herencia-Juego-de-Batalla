
import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        String nombre;
        String clase;
        boolean salir = false;
        Controlador controlador = new Controlador();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Cual es tu nombre?");
        nombre = scanner.nextLine();

        System.out.println("Cual es tu clase? (Guerrero/Explorador)");
        clase = scanner.nextLine();

        if (clase.equalsIgnoreCase("guerrero")) {
            controlador.CrearJugador(nombre, 300, 40);
            controlador.SetItems(clase);
        } else {
            controlador.CrearJugador(nombre, 200, 20);
            controlador.SetItems(clase);
        }
        while (controlador.jugador.estaVivo() || salir == false){
            
        }
        System.out.println("Te mataron, recuerda que aveces la recompensa no vale el riesgo.");
    }
}