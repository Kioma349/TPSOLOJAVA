package tp.structurededonnees.ExerciceTABLE;

import java.util.*;
import java.util.stream.Stream;

public class Table<T, K extends Comparable<K>> {

    private final List<T> elements;
    private final Function<T, K> keyExtractor;

    // Liste des Groupes créés pour cette table
    private final List<Group<K>> groups = new ArrayList<>();

    // Constructeur privé pour Table statique
    private Table(List<T> elements, Function<T, K> keyExtractor) {
        this.elements = elements;
        this.keyExtractor = keyExtractor;
    }

    // Constructeur public pour Table dynamique
    public Table(Function<T, K> keyExtractor) {
        this(new ArrayList<>(), keyExtractor);
    }

    // Crée un groupe avec la méthode de comparaison spécifiée et l'ajoute à la liste des Groupes de la table
    public Group<K> groupBy(Comparator<K> comparator) {
        Group<K> group = new Group<>(comparator);
        groups.add(group);
        // Pour chaque élément de la table, on ajoute son index au groupe correspondant à sa clé
        for (int i = 0; i < elements.size(); i++) {
            T element = elements.get(i);
            K key = keyExtractor.apply(element);
            group.add(key, i);
        }
        return group;
    }

    // Crée une Table dynamique
    public static <T, K extends Comparable<K>> Table<T, K> dynamic(Function<T, K> keyExtractor) {
        return new Table<>(keyExtractor);
    }

    // Ajoute un élément à la Table dynamique et met à jour les Groupes si nécessaire
    public void add(T element) {
        elements.add(element);
        K key = keyExtractor.apply(element);
        // Pour chaque groupe de la table, on ajoute l'index de l'élément correspondant à sa clé
        for (Group<K> group : groups) {
            group.add(key, elements.size() - 1);
        }
    }

    // Classe interne Group
    public static class Group<K extends Comparable<K>> {

        private final Map<K, List<Integer>> map = new TreeMap<>();
        private final Comparator<K> comparator;

        public Group(Comparator<K> comparator) {
            this.comparator = comparator;
        }

        // Ajoute l'index à la liste correspondante à la clé
        public void add(K key, int index) {
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(index);
        }

        // Renvoie le nombre de clés dans le groupe
        public int keySize() {
            return map.size();
        }

        // Parcours les éléments du groupe en ordre de la fonction de comparaison et applique la fonction passée en paramètre à chacun d'entre eux
        public void forEach(Table<T, K> table, Consumer<T> consumer) {
            map.entrySet().stream()
                    .sorted(Map.Entry.comparingByKey(comparator))
                    .flatMap(entry -> entry.getValue().stream())
                    .map(table.elements::get)
                    .forEach(consumer);
        }

        // Renvoie une liste snapshot des éléments correspondants à la clé
        public List<T> lookup(Table<T, K> table, K key) {
            return Collections.unmodifiableList(map.getOrDefault(key, Collections.emptyList()).stream()
                    .map(table.elements::get)
                    .toList());
        }
    }
}