package tp.thread.Excercice2;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Scanner;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Création de deux instances de la classe ThreadExample avec les identifiants 0 et 1
        ThreadExample t1 = new ThreadExample(0);
        ThreadExample t2 = new ThreadExample(1);

        // Lancement des deux threads avec les méthodes start()
        new Thread(t1).start();
        new Thread(t2).start();

        // Création d'un scanner pour lire les commandes de l'utilisateur
        Scanner scanner = new Scanner(System.in);

        // Affichage d'un message pour indiquer que le programme est prêt à recevoir des commandes
        System.out.println("Ready to receive commands");

        // Boucle pour lire les commandes de l'utilisateur
        while (true) {
            if (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                String[] tokens = input.split(" ");
                String command = tokens[0];
                if (command.equals("kill")) {
                    int id = Integer.parseInt(tokens[1]);
                    if (id == 0) {
                        t1.kill();
                    } else if (id == 1) {
                        t2.kill();
                    }
                } else if (command.equals("exit")) {
                    t1.kill();
                    t2.kill();
                    break;
                }
            }
        }
        System.out.println("Program terminated.");
    }
}