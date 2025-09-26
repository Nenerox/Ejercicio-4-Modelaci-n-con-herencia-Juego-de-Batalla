
public class Personaje {
    private String nombre;
    private int salud;
    private int ataque;
    private String mensaje_Inicio;
    private String mensaje_Muerte;
    private String mensaje_Ganar;

    public Personaje(String nombre, int salud, int ataque, String mensaje_Inicio, String mensaje_Muerte, String mensaje_Ganar) {
        this.nombre = nombre;
        this.salud = salud;
        this.ataque = ataque;
        this.mensaje_Inicio = mensaje_Inicio;
        this.mensaje_Muerte = mensaje_Muerte;
        this.mensaje_Ganar = mensaje_Ganar;
    }

    public String getMensaje_Inicio() {
        return mensaje_Inicio;
    }
    public String getMensaje_Muerte() {
        return mensaje_Muerte;
    }
    public String getMensaje_Ganar() {
        return mensaje_Ganar;
    }

    public String getNombre() {
        return nombre;
    }

    public int getSalud() {
        return salud;
    }

    public int getAtaque() {
        return ataque;
    }

    public void recibirDaño(int daño) {
        this.salud -= daño;
        if (salud < 0) {
            salud = 0;
        }
    }
    public void curar(int cura){
        this.salud += cura;
    }

    public boolean estaVivo() {
        return salud > 0;
    }
}