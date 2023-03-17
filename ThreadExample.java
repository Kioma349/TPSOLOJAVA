package tp.thread.Exercice1;

public class ThreadExample implements Runnable {
    private int id;
    private int counter;

    public ThreadExample(int id) {
        this.id = id;
    }

    // La méthode run() contient le code que le thread doit exécuter
    @Override
    public void run() {
        // Le thread continue à exécuter la boucle tant que le compteur est inférieur à 5
        while (counter < 5) {
            // Le thread affiche le message "Hello from Thread {id}, counter = {counter}"
            System.out.println("Hello from Thread " + id + ", counter = " + counter);

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
    }
}
