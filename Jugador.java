public class Jugador extends Personaje {
    public int Efecto;
    public boolean Envenenado;

    public Jugador(String nombre, int salud, int ataque) {
        super(nombre, salud, ataque, "¡Listo para la batalla!", "¡He sido derrotado!", "¡He ganado la batalla!");
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