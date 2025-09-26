public class Habilidad {
    private String nombre;
    private String descripcion;
    private int ataque;

    public Habilidad(String nombre, int ataque, String descripcion) {
        this.nombre = nombre;
        this.ataque = ataque;
        this.descripcion = descripcion;
    }
    public String getNombre() {
        return nombre;
    }
    public int getAtaque() {
        return ataque;
    }
    public String getDescripcion() {
        return descripcion;
    }
}