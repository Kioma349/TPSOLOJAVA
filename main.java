package tp.thread.Exercice1;

public class main {
    public static void main(String[] args) {
        // Question 1: créer deux threads
        // Création de deux instances de la classe ThreadExample avec les identifiants 0 et 1
        Thread t1 = new Thread(new ThreadExample(0));
        Thread t2 = new Thread(new ThreadExample(1));

        // Lancement des deux threads avec les méthodes start()
        t1.start();
        t2.start();

        // Question 2: choisir le nombre de threads
        // Vérification qu'au moins un argument a été fourni
        if (args.length > 0) {
            // Lecture du nombre de threads à lancer depuis le premier argument de la ligne de commande
            int numThreads = Integer.parseInt(args[0]);

            // Boucle pour créer et lancer un nouveau thread pour chaque identifiant de 0 à numThreads - 1
            for (int i = 0; i < numThreads; i++) {
                Thread t = new Thread(new ThreadExample(i));
                t.start();
            }
        }

        // Question 3: tester la méthode setPriority()
        // Définition de la priorité maximale pour t1 et minimale pour t2
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
    }
}

