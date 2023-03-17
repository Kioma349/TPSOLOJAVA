## Exercice 1 - Fifo
On souhaite écrire une structure de données **first-in first-out (FIFO)** utilisant un tableau circulaire qui aura, au moins au début, une taille fixée à la création.
Les deux opérations principales sont "offer" qui permet d'insérer un élement à la fin de la file et "poll" qui permet de retirer un élement en début de la file.
L'idée est d'utiliser deux index, un correspondant à la tête de la file pour retirer les élements et un index correspondant à la queue de la file pour ajouter des élements. 
Lorsque l'on insère un élement, on incrémentera la queue de la file, lorsque l'on retirera un élement, on incrémentera la tête de la file.
De plus, il est impossible de stocker null dans la `file`.

1. Cette représentation a un petit problème car si la tête et la queue ont la même valeur, il n'est pas facile de détecter si cela veux dire que la file est pleine ou vide.
Comment doit on faire pour détecter si la file est pleine ou vide ?
Cette question a plusieurs réponses :)
2. Ecrire une classe `Fifo` dans le package tp.poo.queue prenant en paramètre le nombre maximal d'élements que peut stocker la structure de données. Penser à vérifier les préconditions.
3. Ajouter une méthode `offer` qui ajoute un élement de type Object dans la file. Penser à vérifier les préconditions sachant que, de plus, on veut interdire de pouvoir stocker null.
Comment détecter que la file est pleine ?
Que faire si la file est pleine ?
4. Ecrire une méthode `poll` qui retire un élement de type `Object` de la `file`. Penser à vérifier les préconditions.
Que faire si la file est vide ?
5. Rappeler ce qu'est un memory leak en Java et assurez-vous que votre implémentation n'a pas ce comportement indésirable.
6. Ajouter une méthode `size` et une méthode `isEmpty`.
7. Ajouter une méthode d'affichage qui affiche les élements dans l'ordre dans lequel ils seraient sortis en utilisant poll.
Note: attention à bien faire la différence entre la file pleine et la file vide.
L'ensemble des élements devra être affiché entre crochets ('[' et ']') avec les élements séparés par des virgules.
Vérifier avec le test unitaire suivant que votre implantation est bien conforme. `FifoTest.java.md`