# Hier nous avons travaillé sur les Thread. Cet exercice pour tester vos connaissances sur les notions abordées
## Exercice 1 - Hello Thread

On souhaite créer deux threads exécutant le même code. Pour différencier les deux threads, lors de la construction de celles-ci, un entier unique (id) sera fourni à chacune, 0 pour la première et 1 pour la seconde.
Chaque thread exécutera le même code qui consiste à afficher hello suivi du numéro de la thread ainsi que la valeur d'un compteur indiquant le nombre de fois que la thread a affiché ce message.
**Exemple :**
```Java
hello 0 10714
hello 0 10715
hello 0 10716
hello 0 10717
hello 1 15096
hello 1 15097
hello 1 15098
hello 1 15099
```

1. Expliquer sur l'exemple ci-dessus pourquoi le compteur de hello `0` est beaucoup plus petit que le compteur de `hello 1`.
À quoi sert l'interface `Runnable` ?
2. Écrire le programme demandé en créant un `Thread` et en lui donnant un Runnable dont vous redéfinirez la méthode `run()`(i.e. `new Thread(myRunnable)`).
3. Changer votre code pour que l'on puisse choisir lors de l'exécution du programme (1er paramètre) le nombre de threads que l'on veut lancer en concurrence.
4. Tester la méthode `setPriority(int)` de `Thread`.

## Exercice 2 - Coitus interruptus
On souhaite maintenant permettre à l'utilisateur en tapant un nombre d'interrompre la thread ayant cet identificateur.

1. Expliquer les différences entre les méthodes `interrupted` et `isInterrupted` de la classe `Thread`.
2. Modifier le code en utilisant un scanner `(java.util.Scanner)` pour lire sur l'entrée standard des commandes de contrôle (à partir de la thread principale).
   On implante les commandes suivantes :
   - `kill ID` afin de tuer la thread d'identificateur id. La thread tuée affiche ensuite un message à la fin de son exécution.
   - `exit` qui tue toutes les threads encore en vie.
3. Il est possible de surveiller la machine virtuelle avec la commande `jconsole` (mémoire allouée sur le tas, classes chargées, threads en activité...).
   Lancer cette commande, sélectionner le processus à surveiller et vérifier que les threads sont bien interrompues.

## Exercice 3 - Modification d'une variable en concurrence
On souhaite créer deux threads qui changent le même champ d'un même objet :

```Java
public class Test {
  int value;

  public static void main(String[] args) {
    final Test test = new Test();

    for(int i=0; i<2; i++) {
      final int id = i;
      new Thread(new Runnable() {
        public void run() {
          for(;;) {
            test.value = id;
            if (test.value != id)
              System.out.println("id " + id + " " + test.value);
          }
        }
      }).start();
    }
  }
}
```

1. Qu'affiche le code suivant avec la ligne de commande : `java Test` ?
   Expliquer.
2. Pourquoi l'affichage n'évolue plus au bout d'un laps de temps ?
3. Peut-on en déduire qu'il n'y a plus de problème de concurrence ?
4. Comment doit on faire pour être sûr que chaque thread voit les modifications effectuées sur une variable par l'autre thread ?

## Exercice 4 - strtok
On cherche à implanter en Java une méthode strtok offrant un comportant équivalent à son alter-ego de la bibliothèque standard `C`. La méthode `strtok` :
- lorsqu'elle est appelée avec un chaîne de caractère `( CharSequence )` et un délimiteur `( char )`, renvoie un `CharSequence` correspondant à la chaîne du début de la chaîne jusqu'à la prochaine occurrence du délimiteur.
- lorsqu'elle est appelée avec null et un délimiteur, renvoie un CharSequence de la position lors du dernier appel à la méthode jusqu'à la prochaine occurrence du délimiteur.

Voici le code :

```Java
public static CharSequence strtok(CharSequence input,char delimiter) {
	int offset;
	if (input==null) {
	   input=lastInput;
	   if (input==null)
	      return null;

	   offset=lastOffset;
	}
	else {
	   lastInput=input;
	   offset=0;
	}

	for(int i=offset;i<input.length();i++) {
	   if (input.charAt(i)==delimiter) {
	      lastOffset=i+1;
	      return input.subSequence(offset,i);
	   }
	}
	lastInput=null;
	return input.subSequence(offset, input.length());
}

private static CharSequence lastInput;
private static int lastOffset;
```
et un exemple :

```Java
CharSequence seq1 = strtok("toto est beau",' ');   // toto
CharSequence seq2 = strtok(null,' ');              // est
CharSequence seq3 = strtok(null,' ');              // beau
CharSequence seq4 = strtok(null,' ');              // null
```

1. Rappeler pourquoi la méthode `strtok` ci-dessous n'est pas **thread-safe** .
2. Faire en sorte que la méthode `strtok` soit **thread-safe** en utilisant des variables locales à un thread.