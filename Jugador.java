import java.util.ArrayList;
public class Jugador extends Personaje {
    ArrayList<String> inventario;
    public int Efecto;
    public boolean Envenenado;

    public Jugador(String nombre, int salud, int ataque) {
        super(nombre, salud, ataque, "¡Listo para la batalla!", "¡He sido derrotado!", "¡He ganado la batalla!");
        this.inventario = new ArrayList<>();
    }

    public void quitarItems(String item) {
        inventario.remove(item);
    }

    public void setEfecto(int efecto) {
        this.Efecto = efecto;
    }
    public void setEnvenenado(boolean envenenado) {
        this.Envenenado = envenenado;
    }
    public int getEfecto() {
        return Efecto;
    }
    public boolean getEnvenenado() {
        return Envenenado;
    }
}