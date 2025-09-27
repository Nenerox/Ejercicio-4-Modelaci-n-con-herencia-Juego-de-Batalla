
import java.util.*;

public class main {
    public static void main(String[] args) {
        String nombre;
        String clase;
        boolean salir = false;
        int contadorRondas = 0;
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

        while (controlador.jugador.estaVivo() && salir == false) {
        contadorRondas++; // Cada exploración suma una ronda
        //se generan una cantidad y tipos de enemigos al azar
        List<Personaje> bestias = controlador.generarEnemigos(1 + random.nextInt(3)); 
        System.out.println(controlador.jugador.getMensaje_Inicio());
        for (Personaje e : bestias) System.out.println(e.getMensaje_Inicio());
        
        while (controlador.jugador.estaVivo() && controlador.hayEnemigosVivos(bestias)){

            for (int i = 0; i < bestias.size(); i++) {
                if (bestias.get(i).estaVivo()) {
                    System.out.println((i+1) + ") " + bestias.get(i).getNombre() + " (Vida: " + bestias.get(i).getSalud() + ")");
                }
            }
            
            //Turno de jugador
            System.out.println("\nTurno de " + controlador.jugador.getNombre() + ". Vida: " + controlador.jugador.getSalud());
            System.out.println("Elige acción: 1) Atacar  2) Pasar  3) items");
            int opcion = scanner.nextInt();
            scanner.nextLine();

            switch(opcion) {
                case 1: {
                    System.out.println("Elije un enemigo para atacar");
                    int enemigo = (scanner.nextInt() - 1);
                    scanner.nextLine();
                    controlador.atacarEnemigo(bestias.get(enemigo));
                    break;
                }
                case 2: {
                    System.out.println("Se paso el turno");
                    break;
                }
                case 3: {
                    System.out.println(controlador.getInventario());
                    System.out.println("Escribe el item que quieras usar");
                    String item = scanner.nextLine();
                    controlador.usarItems(item);
                    break;
                }
                default:
                    System.out.println("Selecciona una opcion valida, se paso el turno");
                    break;
            }

            //Turno enemigos
            for (int i = 0; i < bestias.size(); i++) {
                if (bestias.get(i).estaVivo()) {
                    if (random.nextBoolean()) {
                        controlador.atacarJugador(bestias.get(i).getAtaque());
                        System.out.println(bestias.get(i).getNombre()+" ataco e hizo: "+ bestias.get(i).getAtaque());
                    } else {
                        if (bestias.get(i).getNombre().equals("Rata")) {
                            controlador.jugador.setEfecto(3);
                            controlador.jugador.setEnvenenado(true);
                            System.out.println("Haz sido envenenado, recibiras 3 de daño cada turno");
                        }
                        controlador.enemigoUsaHabilidad((Enemigo) bestias.get(i));
                        System.out.println(bestias.get(i).getNombre()+" uso "+controlador.enemigoNombreHabilidad((Enemigo) bestias.get(i))+"hizo "+controlador.enemigoAtaqueHabilidad((Enemigo) bestias.get(i)));
                    }
                }
            }

            if (controlador.jugador.getEnvenenado()) {
                controlador.atacarJugador(controlador.jugador.getEfecto());
            }
        }

        // Preguntar por jefe después de 2 rondas
        if (contadorRondas == 2 && controlador.jugador.estaVivo()) {
                System.out.println("¿Quieres entrar a la sala del jefe? si/no");
                String decision = scanner.nextLine();
                if (decision.equalsIgnoreCase("si")) {
                    List<Personaje> jefe = controlador.generarJefe();
                    System.out.println("¡Ha aparecido el jefe!");
                    for (Personaje j : jefe) System.out.println(j.getMensaje_Inicio());

                    while (controlador.jugador.estaVivo() && controlador.hayEnemigosVivos(jefe)) {
                        
                        if (jefe.get(0).estaVivo()) {
                            System.out.println("1) " + jefe.get(0).getNombre() + " (Vida: " + jefe.get(0).getSalud() + ")");
                        }

                        System.out.println("\nTurno de " + controlador.jugador.getNombre() + ". Vida: " + controlador.jugador.getSalud());
                        System.out.println("Elige acción: 1) Atacar  2) Pasar  3) items");
                        int opcion = scanner.nextInt();
                        scanner.nextLine();

                        switch(opcion) {
                            case 1: {
                                controlador.atacarEnemigo(jefe.get(0));
                                break;
                            }
                            case 2: {
                                System.out.println("Se paso el turno");
                                break;
                            }
                            case 3: {
                                System.out.println(controlador.getInventario());
                                System.out.println("Escribe el item que quieras usar");
                                String item = scanner.nextLine();
                                controlador.usarItems(item);
                                break;
                            }
                            default:
                                System.out.println("Selecciona una opcion valida, se paso el turno");
                                break;
                        }

                        // Turno jefe
                        if (jefe.get(0).estaVivo()) {
                            if (random.nextBoolean()) {
                                controlador.atacarJugador(jefe.get(0).getAtaque());
                                System.out.println(jefe.get(0).getNombre()+" ataco e hizo: "+ jefe.get(0).getAtaque());
                            } else {
                                controlador.enemigoUsaHabilidad((Enemigo) jefe.get(0));
                                System.out.println(jefe.get(0).getNombre()+" uso "+controlador.enemigoNombreHabilidad((Enemigo) jefe.get(0))+" hizo "+controlador.enemigoAtaqueHabilidad((Enemigo) jefe.get(0)));
                            }
                        }
                    }
                    System.out.println("Haz ganado contra el jefe es hora de salir");
                    salir = true; // Salir del juego tras jefe
                }
            }

            if (!salir && controlador.jugador.estaVivo()) {
                System.out.println("Quieres seguir explorando? si/no");
                String continuar = scanner.nextLine();
                if (continuar.equalsIgnoreCase("no")){
                    salir = true;
                }
            }
        }

        if (controlador.jugador.estaVivo() == false) {
            System.out.println("Te mataron, recuerda que aveces la recompensa no vale el riesgo.");   
        }
    }
}