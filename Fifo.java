package tp.structurededonnees.FIFO;

public class Fifo {
    // Les éléments de la file sont stockés dans un tableau
    private Object[] elements;
    // La tête et la queue de la file sont représentées par deux indices
    private int head = 0;
    private int tail = 0;

    // La variable full permet de savoir si la file est pleine
    private boolean full = false;

    // Le constructeur prend en argument la capacité de la file
    public Fifo(int capacity) {
        // On vérifie que la capacité est positive
        if (capacity <= 0) {
            throw new IllegalArgumentException("La capacité doit être positive !");
        }
        // On crée un tableau pour stocker les éléments de la file
        elements = new Object[capacity];
    }

    // La méthode offer permet d'ajouter un élément à la fin de la file
    public void offer(Object element) {
        // On vérifie que l'élément n'est pas null
        if (element == null) {
            throw new IllegalArgumentException("On ne peut pas ajouter null dans la file !");
        }
        // Si la file est pleine, on lève une exception
        if (full) {
            throw new IllegalStateException("La file est pleine !");
        }
        // On ajoute l'élément à la fin de la file
        elements[tail++] = element;
        // Si la queue atteint la fin du tableau, on revient au début
        if (tail == elements.length) {
            tail = 0;
        }
        // Si la tête et la queue sont à la même position, la file est pleine
        if (tail == head) {
            full = true;
        }
    }

    // La méthode poll permet de récupérer et supprimer l'élément en tête de file
    public Object poll() {
        // Si la file est vide, on lève une exception
        if (isEmpty()) {
            throw new IllegalStateException("La file est vide !");
        }
        // On récupère l'élément en tête de file
        Object element = elements[head];
        // On supprime l'élément en tête de file en mettant à null la case correspondante
        elements[head++] = null;
        // Si la tête atteint la fin du tableau, on revient au début
        if (head == elements.length) {
            head = 0;
        }
        // On indique que la file n'est plus pleine
        full = false;
        // On retourne l'élément en tête de file
        return element;
    }

    // La méthode size retourne le nombre d'éléments dans la file
    public int size() {
        // Si la file est pleine, sa taille est égale à la capacité
        if (full) {
            return elements.length;
        } else {
            // Sinon, la taille est égale à la différence entre la queue et la tête
            // avec un traitement particulier si la queue a bouclé sur le début du tableau
            return (tail - head + elements.length) % elements.length;
        }
    }

    // La méthode isEmpty retourne true si la file est vide
    public boolean isEmpty() {
        return !full && head == tail;
    }

    // La méthode display permet d'afficher la file
    public void display() {
        // On crée un StringBuilder pour construire la chaîne de caractères à afficher
        StringBuilder sb = new StringBuilder();
        sb.append('[');
        if (!isEmpty()) {
            int i = head;
            do {
                sb.append(elements[i]);
                i = (i + 1) % elements.length;
                if (i != tail) {
                    sb.append(", ");
                }
            } while (i != tail);
        }
        sb.append(']');
        System.out.println(sb.toString());
    }
}
