
Exercice 1 - Hello Thread---------------------------TREAD------------------------------------

Question 1
le compteur pour "hello 0" est beaucoup plus petit que celui pour hello 1.
Cela est dû selon moi au fait que les deux threads s'exécutent en même temps. 
Surement je pense que le thread 1 à eu un temps d'exécution plus court que le thread 0, 
ce qui aurait permis au thread 1 d'incrémenter son compteur un peu plus rapidement que le thread 0. 

L'interface Runnable est une interface fonctionnelle de base du package java.lang. 
Elle est utilisée pour définir un objet qui représente une tâche exécutable, 
qui peut être exécutée en parallèle avec d'autres tâches dans un thread séparé. 
Elle permet de créer des threads en fournissant une implémentation de la méthode run() 
qui contient le code exécuté par le thread.
-----------------------------------------------------------------------------------------------------

Exercice 2 - Coitus interruptus--------------TREAD---------------------------------


1- Les méthodes interrupted et isInterrupted sont toutes les deux utilisées pour savoir si un thread a été interrompue.
La méthode "interrupted" est une méthode statique qui vérifie si le thread courant a été interrompue, 
puis réinitialise son statut d'interruption. Cela signifie que si vous appelez cette méthode deux fois de suite, 
la deuxième fois, elle renverra false même si le thread a été interrompue. 
Par contre, la méthode "isInterrupted" est une méthode d'instance qui renvoie le statut d'interruption du thread sur laquelle elle est appelée, 
mais ne réinitialise pas son statut d'interruption.


3- Fait

Exercice 3 ----------------------------------TREAD----------------------------------

1-En exécutant le code java Test, on obtient un affichage qui peut varier en fonction de l'exécution. 
En effet, on obtient des messages du type "id 0 1" ou "id 1 0". 
Ces messages indiquent que la valeur de test.value a été modifiée par un thread alors que l'autre thread n'était pas encore passé par là.

2-L'affichage n'évolue plus au bout d'un laps de temps car les deux threads tournent en boucle indéfiniment et essaient constamment de modifier la valeur de test.value. 
Cela peut saturer le processeur et entraîner des ralentissements.

3-On ne peut pas en déduire qu'il n'y a plus de problème de concurrence, 
car le fait que l'affichage n'évolue plus ne garantit pas qu'il n'y ait plus de modifications concurrentes sur test.value. 
Il est possible que les threads continuent de modifier la valeur de manière concurrente mais que les messages ne s'affichent plus car ils sont bloqués quelque part.

4-Pour être sûr que chaque thread voit les modifications effectuées sur une variable par l'autre thread, 
il faut utiliser un mécanisme de synchronisation tel que les verrous ou les sémaphores. 
Cela permet de garantir que chaque thread attendra que l'autre ait fini de modifier la variable avant de prendre le relais.


Exercice 4-----------------TREAD----------------------------

1-La méthode strtok n'est pas thread-safe car elle utilise des variables statiques partagées entre les appels, 
notamment lastInput et lastOffset, pour stocker l'état de l'appel précédent. Cela peut causer des problèmes si deux threads exécutent simultanément la méthode, 
car les modifications des variables par l'un des threads peuvent interférer avec les opérations de l'autre thread, entraînant des résultats imprévisibles ou des erreurs.

2-----------------------------------------------------------


public static synchronized CharSequence strtok(CharSequence input, char delimiter) {
int offset;
if (input == null) {
// Utilisation des variables locales au thread pour stocker la dernière chaîne et position traitée
input = lastInput.get();
if (input == null)
return null;

        offset = lastOffset.get();
    } else {
        lastInput.set(input);
        offset = 0;
    }

    for (int i = offset; i < input.length(); i++) {
        if (input.charAt(i) == delimiter) {
            // on stock la dernière position traitée dans les variables locales du thread
            lastOffset.set(i + 1);
            return input.subSequence(offset, i);
        }
    }
    // on stock la dernière position traitée dans les variables locales du thread
    lastInput.set(null);
    return input.subSequence(offset, input.length());
}

// Utilisation de variables ThreadLocal pour stocker les dernières chaînes et positions traitées par chaque thread
private static final ThreadLocal<CharSequence> lastInput = new ThreadLocal<>();
private static final ThreadLocal<Integer> lastOffset = ThreadLocal.withInitial(() -> 0);


Exercice 1 ------------------STRUCTURE DE DONNEE-------------------------------------

1-Une façon courante de détecter si une file est vide est de garder un compteur du nombre d'éléments dans la file. 
Si ce compteur est à zéro, la file est vide. Si la file a une taille maximale fixée à la création, 
on peut initialiser ce compteur à zéro et l'incrémenter à chaque ajout et le décrémenter à chaque suppression. 
Si le compteur est égal à la taille maximale, la file est pleine.

2eme facons possible
Pour détecter si la file est pleine, on peut également utiliser une approche similaire, en gardant un compteur du nombre d'éléments dans la file. 
Si la file a une taille maximale fixée à la création, on peut initialiser ce compteur à zéro et l'incrémenter à chaque ajout.

5- Un memory leak en Java est une situation où la mémoire allouée pour un objet n'est jamais libérée alors qu'il n'est plus utilisé



