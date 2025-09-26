import java.util.*;

public class Controlador {
    private ArrayList<String> itemsGuerrero = new ArrayList<>(Arrays.asList("Pocion","Pocion","Antidoto","Antidoto"));
    private ArrayList<String> itemsExplorador = new ArrayList<>(Arrays.asList("Pocion","Pocion","Pocion","Pocion","Antidoto","Antidoto","Antidoto","Antidoto"));
    private ArrayList<String> inventario;
    Random random = new Random();
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

    public void usarItems(String item){
        if (item.equalsIgnoreCase("pocion")) {
            jugador.curar(20);
            inventario.remove("Pocion");
        } else if (item.equalsIgnoreCase("antidoto")) {
            jugador.setEfecto(0);
            jugador.setEnvenenado(false);
            inventario.remove("Antidoto");
        }
    }

    public void atacarEnemigo(Personaje enemigo){
        enemigo.recibirDaño(jugador.getAtaque());
    }

    public void atacarJugador(int daño){
        jugador.recibirDaño(daño);
    }

    public List<Personaje> generarEnemigos(int cantidad) {
        List<Personaje> enemigos = new ArrayList<>();
        for (int i = 0; i < cantidad; i++) {
            int tipo = random.nextInt(2); // 0 = rata, 1 = golem
            if (tipo == 0) {
                enemigos.add(new Ratas());
            } else {
                enemigos.add(new Golem());
            }
        }
        return enemigos;
    }
    public List<Personaje> generarJefe() {
        List<Personaje> jefe = new ArrayList<>();
        int tipo = random.nextInt(2); // 0 = jefe rata, 1 = jefe golem
        if (tipo == 0) {
                jefe.add(new Jefe_Rata());
            } else {
                jefe.add(new Jefe_Golem());
            }
            return jefe;
        }

    public void enemigoUsaHabilidad(Enemigo enemigo) {
    jugador.recibirDaño(enemigo.usarHabilidad());
    }
}