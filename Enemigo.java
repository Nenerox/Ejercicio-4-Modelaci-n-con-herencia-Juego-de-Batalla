public class Enemigo extends Personaje {
    private Habilidad habilidad;
    
    public Enemigo(String nombre, int salud, int ataque, Habilidad habilidad) {
        super(nombre, salud, ataque, "!No tan rápido!", "¡Mis compañeros me vengaran!", "¡Esto fue por mis amigos!");
        this.habilidad = habilidad;
    }
}