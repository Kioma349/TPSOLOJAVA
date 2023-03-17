Le but de ce TP noté est d'implanter une structure de données appelée Table qui permet grouper les éléments de la structure de données suivant différentes propriétés.
Les données groupées peuvent être parcourues et doivent se mettent à jour automatiquement si les données de la Table sont mises à jour (ajout/remplacement).

Vous pouvez consulter la javadoc.
Vous avez le droit de lire le sujet jusqu'au bout, cela vous donnera une bonne idée de là où on veut aller !

## Exercice 1 - Table
La classe `Table` est un conteneur d'éléments non null qui stocke ses éléments dans une liste.
Il y a deux façons de créer une `Table`
- en utilisant la méthode `of`,
```Java
- var table = Table.of(new Person("Gil", 34), new Person("Bob", 54), new Person("Ana", 34));
```
la Table est alors non mutable.
- en utilisant la méthode `dynamic` puis la méthode `add` pour ajouter des éléments,
```Java
var table = Table.<Person>dynamic();
table.add(new Person("Gil", 34));
table.add(new Person("Bob", 54));
table.add(new Person("Ana", 34));
```
la `Table` est alors mutable.

En plus des méthodes `of`, `dynamic` et `add`, une Table possède aussi les méthodes
- `size` qui renvoie le nombre d'élément dans la table
- `replace()` permet de remplacer toutes les occurrences d'un élément de la table par un autre élément.

À partir d'une `Table`, il est possible de créer des groupes
```Java
var byName = table.groupBy(Person::name, String::compareTo);
var byAge = table.groupBy(Person::age, Integer::compareTo);
```

Le code ci-dessus crée un groupe `byName` qui permet d'obtenir les personnes de la table groupées en fonction de leur nom `(Person::name)` et en les triant dans l'ordre lexicographique (de leur nom) en utilisant `String::compareTo` et un groupe `byAge` qui permet d'obtenir les personnes de la table groupées en fonction de leur âge `(Person::age)` et en les triant dans l'ordre croissant (de leur âge) en utilisant `Integer::compareTo`.
Un groupe stocke, pour chaque clé, les indexes des éléments correspondant. Les clés sont obtenues à partir des éléments de la table grâce à la première fonction (fonction de projection) et triées suivant la deuxième fonction (fonction de comparaison).
Par exemple, pour `byName`, le Groupe affiche
```Java
System.out.println(byName);
// Ana: [2]
// Bob: [1]
// Gil: [0]
```

Dans l'ordre lexicographique, Ana est avant Bob qui est avant Gil. Ana est le nom de l'élément 2, Bob est le nom de l'élément 1 et Gil est le nom de l'élément 0
Et pour byAge, le Groupe affiche
```Java
System.out.println(byAge);
// 34: [0, 2]
// 54: [1]
```

Dans l'ordre croissant 34 est inférieur à 54. 34 est l'âge de l'élément 0 et de l'élément 2, 54 est l'âge de l'élément 1.
On peut remarquer que lorsqu'il y a plusieurs index, ceux-ci sont stockés en ordre croissant.
En plus de la capacité à s'afficher, un groupe possède les opérations suivantes
- `keySize` qui renvoie le nombre de clés différentes. Dans notre exemple, byName a une keySize de 3 et byAge a une keySize de 2.
- `forEach` qui prend en paramètre une fonction et appelle celle-ci avec chaque élément correspondant aux index parcourus de haut en bas et de gauche à droite. 
Pour byAge, le parcours correspond aux index 0, 2, et 1 donc ça correspond aux personnes dont le nom est Gil, Ana puis Bob.
- `lookup` qui prend en paramètre une clé key et renvoie une liste des éléments correspondants à cette clé. 
Par exemple, pour byAge, lookup(34) renvoie les éléments d'index 0 et 2, donc les personnes dont le nom est Gil et Ana.
- `stream` renvoie un Stream qui permet de parcourir les éléments dans le même ordre que forEach.

La classe `Table` est paramétrée par le type des éléments qu'elle contient et ces éléments ne peuvent pas être null.

Des tests unitaires correspondant à l'implantation sont ici : `TableTest.java.md`.

1. On souhaite écrire la classe `Table` ainsi que les méthodes
   - `of` qui prend les éléments séparés par des virgules et crée la table contenant ces éléments dans le même ordre.
   - `size` qui indique combien d'éléments sont contenus dans la table.

Écrire la classe `Table` sachant que la seule façon de créer une Table doit être de passer par la méthode `of`.
Vérifier que les tests unitaires marqués "Q1" passent.

2. On souhaite écrire la méthode `groupBy` qui prend une fonction de projection, qui étant un élément renvoie une clé et une fonction de comparaison qui indique comment comparer deux clés renvoyées par la fonction de projection. 
Cette méthode Les La méthode `groupBy` renvoie un nouveau groupe dont les clés sont celles renvoyées par la fonction de projection sur les éléments de la table. Pour chaque clé, ce groupe stocke en interne une liste des index des éléments pour lesquels l'appel de la fonction de projection sur élément renvoie cette clé. Enfin, les clés doivent êtres triées suivant la fonction de comparaison.

3. On souhaite de plus, pour tester, qu'un groupe ait une méthode `keySize` qui renvoie le nombre de clés dans le groupe.
Créer la classe `Group` à l'intérieur de la classe `Table` et implanter la méthode `groupBy` dans la classe `Table` ainsi que la méthode `keySize` dans la classe `Group`.
Vérifier que les tests unitaires marqués "Q2" passent.
Note : Group stocke des index sur les éléments plutôt que des éléments eux-mêmes car on en a besoin pour simplifier l'implantation de la méthode `replace` que nous introduirons plus tard.
Indication : il existe déjà une structure de donnée en Java qui sait associer des valeurs à des clés en maintenant les clés triées en fonction d'une fonction de comparaison.
Dans la classe `Group`, écrire la méthode d'affichage habituelle qui permet d'afficher une clé et sa liste d'index par ligne, dans l'ordre de la fonction de comparaison.
Vérifier que les tests unitaires marqués "Q3" passent.

4. On souhaite maintenant pouvoir créer des tables dynamiques dans lesquelles on peut ajouter des éléments. 
5. Pour cela, dans la classe `Table`, on va ajouter les méthodes `dynamic` qui renvoie une Table vide qui peut s’agrandir et add qui permet d'ajouter des éléments à une table dynamique.
Dans le cas où la table a été crée avec of, donc dans le cas où la table est non mutable, il ne doit pas être possible d'ajouter des éléments à celle-ci.
Dans le cas où des groupes ont déjà été définis sur une table, ajouter un élément avec add doit provoquer la mise à jour de ces groupes avec le nouvel index. 
Remarque : cela signifie qu'une Table doit connaître les Group qu'elle a permis de créer.
Écrire les méthodes `dynamic` et `add`.
Vérifier que les tests unitaires marqués "Q4" passent.
5. Dans la classe `Group`, on souhaite écrire une méthode `forEach` qui prend en paramètre une fonction qui prend un élément en paramètre et fait un effet de bord. La méthode `forEach` appelle cette fonction avec chaque élément correspondant à chaque index dans le Group dans l'ordre des clés.
Écrire la méthode `forEach` en utilisant un Stream comme implantation.
Vérifier que les tests unitaires marqués "Q5" passent.
Note : si vous ne trouvez pas comment l'écrire avec un Stream, faite une version sans Stream comme cela vous ne perdrez qu'un seul point à la question.

6. On souhaite, toujours dans la classe `Group`, écrire une méthode `lookup` qui pour une clé renvoie une liste non mutable des éléments correspondants à la clé. 
L'implantation de cette liste ne doit pas stocker les éléments mais aller les chercher dans la Table et la complexité pire cas de l'implantation de la méthode `get` de la liste doit être en O(1).
Enfin, les changements de la Table postérieurs à l'appel à lookup ne doivent pas être visibles dans la liste, autrement dit, la liste à une sémantique snapshot at the beginning.
Écrire la méthode `lookup`.
Vérifier que les tests unitaires marqués "Q6" passent.

7. On souhaite, dans la classe `Table`, écrire autre une méthode `groupBy`, une surcharge donc, qui ne prend qu'un seul paramètre et qui dans ce cas utilise l'ordre naturel des clés (la méthode `compareTo` des clés) pour trier celles-ci.
Écrire la méthode `groupBy` avec un seul paramètre.
Vérifier que les tests unitaires marqués "Q7" passent.

8. On souhaite ajouter à la classe `Group` une méthode `stream()` qui renvoie un Stream des éléments de la table triés par les clés du groupe, donc voir les éléments dans le même ordre que la méthode `forEach`.
Votre implantation doit contrôler les caractéristiques du Stream, mais dans un premier temps votre Stream n'a pas besoin savoir se couper en deux.
Conseil : vous pouvez utliser un Spliterator pour acceder aux listes d'inces du Group et des Ieterator pour les parcourir.
Vérifier que les tests unitaires marqués "Q8" passent.
9. Modifier votre implantation de la méthode `stream()` pour que le Stream renvoyé sache se couper en deux.

Vérifier que les tests unitaires marqués "Q9" passent.
10. Enfin, on souhaite écrire une méthode `replace` qui prend deux éléments en paramètre et remplace toutes les occurrences du premier élément dans la table par le second élément.
Pour que le remplacement soit possible, il faut s'assurer que pour tous les groupes créés sur la table, la fonction de projection du groupe ait la même valeur pour les deux éléments.
Écrire la méthode `replace`.
Vérifier que les tests unitaires marqués "Q10" passent.