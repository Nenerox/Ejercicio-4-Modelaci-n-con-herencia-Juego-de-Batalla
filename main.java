
import java.util.*;

public class main {
    public static void main(String[] args) {
        String nombre;
        String clase;
        boolean salir = false;
        Controlador controlador = new Controlador();
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

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

        List<Personaje> bestias = controlador.generarEnemigos(1 + random.nextInt(2)); 
        System.out.println(controlador.jugador.getMensaje_Inicio());
        for (Personaje e : bestias) System.out.println(e.getMensaje_Inicio());
        
        while (controlador.jugador.estaVivo() || salir == false){

            for (int i = 0; i < bestias.size(); i++) {
                if (bestias.get(i).estaVivo()) {
                    System.out.println((i+1) + ") " + bestias.get(i).getNombre() + " (Vida: " + bestias.get(i).getSalud() + ")");
                }
            }
            
            System.out.println("\nTurno de " + controlador.jugador.getNombre() + ". Vida: " + controlador.jugador.getSalud());
            System.out.println("Elige acción: 1) Atacar  2) Pasar  3) items");
            int opcion = scanner.nextInt();

            switch(opcion) {
                case 1: {
                    System.out.println("Elije un enemigo para atacar");
                    int enemigo = scanner.nextInt();
                    controlador.atacarEnemigo(bestias.get(enemigo));
                }
                case 2: System.out.println("Se paso el turno");
                case 3: controlador.usarItems(clase);
            }

            //Turno enemigos
            for (int i = 0; i < bestias.size(); i++) {
                if (bestias.get(i).estaVivo()) {
                    if (random.nextBoolean()) {
                        controlador.atacarJugador(bestias.get(i).getAtaque());
                    } else {
                        if (bestias.get(i).getNombre().equals("Rata")) {
                            controlador.jugador.setEfecto(3);
                            controlador.jugador.setEnvenenado(true);
                        }
                        controlador.enemigoUsaHabilidad((Enemigo) bestias.get(i));
                    }
                }
            }

            if (controlador.jugador.getEnvenenado()) {
                controlador.atacarJugador(controlador.jugador.getEfecto());
            }
        }
        System.out.println("Te mataron, recuerda que aveces la recompensa no vale el riesgo.");
    }
}