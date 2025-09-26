import java.util.ArrayList;
import java.util.Arrays;

public class Controlador {
    private ArrayList<String> itemsGuerrero = new ArrayList<>(Arrays.asList("Pocion","Pocion","Antidoto","Antidoto"));
    private ArrayList<String> itemsExplorador = new ArrayList<>(Arrays.asList("Pocion","Pocion","Pocion","Pocion","Antidoto","Antidoto","Antidoto","Antidoto"));
    private ArrayList<String> inventario;
    Jugador jugador;

    public void CrearJugador(String nombre, int salud,  int ataque){
        this.jugador = new Jugador(nombre, salud, ataque);
    }

    public void SetItems(String clase){
        if (clase.equalsIgnoreCase("guerrero")) {
            this.inventario = itemsGuerrero;
        } else {
            this.inventario = itemsExplorador;
        }
    }

    public void quitarItems(String item) {
        inventario.remove(item);
    }


}