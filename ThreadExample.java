package tp.thread.Excercice2;

import java.util.ArrayList;
import java.util.Scanner;

class ThreadExample implements Runnable {
    private int id;
    private int counter;
    private boolean alive = true;

    public ThreadExample(int id) {
        this.id = id;
    }

    // La méthode run() contient le code que le thread doit exécuter
    @Override
    public void run() {
        // Le thread continue à exécuter la boucle tant qu'il est en vie
        while (alive) {
            // Le thread affiche le message
            System.out.println("Hello le thread " + id + ", counter = " + counter);

            // Le compteur est incrémenté à chaque itération
            counter++;

            try {
                // Le thread dort pendant 500 millisecondes
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // En cas d'interruption, le thread affiche une trace de la pile d'erreur
                e.printStackTrace();
            }
        }
        System.out.println("Thread " + id + " est terminé.");
    }

    // Méthode pour tuer le thread
    public void kill() {
        alive = false;
    }
}
